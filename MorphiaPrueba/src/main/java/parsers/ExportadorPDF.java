package parsers;

import java.io.File;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.TabStop;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.AreaBreakType;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TabAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import dao.TorneoDao;
import dao.TorneoDaoMorphia;
import modelo.Jugador;
import modelo.Partido;
import modelo.Torneo;

public class ExportadorPDF {
	private static final String carpeta = "pdf";
	private static final String LOGO = "/images/logo.png";
	private static final String LIBRE = "LIBRE";
	private static final String TBD = "23:59";
	
	
	public static void exportarPlanillasPDF(List<Partido> listaPartidos, String nombreArchivo) throws Exception{
		String DEST = carpeta+"/"+ nombreArchivo+"-planillas.pdf";
		File file = new File(DEST);
        file.getParentFile().mkdirs();
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(file));
   	 	pdfDoc.setDefaultPageSize(PageSize.A4);//.rotate()
   	 	Document doc = new Document(pdfDoc);
   	 	
       for(Partido p: listaPartidos) {
    	   if(!p.getHora().equals(TBD) && p.getLocal() != null && p.getVisitante() != null) {
    		   armarPlanilla(doc,p);
    		   if(listaPartidos.get(listaPartidos.size()-1)!= p)doc.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
    	   }
       }
             //CERRAR EL DOCUMENTO
             doc.close();
	}
	
	private static void armarPlanilla(Document doc, Partido p) throws MalformedURLException {
		//LOGO
        Image image = new Image(ImageDataFactory.create(ExportadorPDF.class.getResource(LOGO).toString()));
        image.setWidthPercent(30);
        image.setHorizontalAlignment(HorizontalAlignment.CENTER);
        image.setMarginBottom(10);
        doc.add(image);
        
        //TABLA INFO
        Table tablaInfo = new Table(4);
        tablaInfo.setFontSize(10);
        LocalDate aux = LocalDate.parse(p.getFecha());
        tablaInfo.addCell("Dia: "+aux.getDayOfMonth()+"/"+aux.getMonthValue()+"/"+aux.getYear());
        tablaInfo.addCell("Hora: "+p.getHora());
        tablaInfo.addCell("Sede "+ encontrarTorneo(p).getSede());
        tablaInfo.addCell("Fecha: "+p.getMatchday());
        doc.add(tablaInfo);
        
        //TABLA EQUIPOS-RESULTADO
        Table tablaEquipos = new Table(8);
        tablaEquipos.setMarginTop(10);
        tablaEquipos.setFontSize(16);
        tablaEquipos.addCell(new Cell(1,3).add(p.getLocal().getNombre()).setBold().setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE));
        tablaEquipos.addCell("");tablaEquipos.addCell("");
        tablaEquipos.addCell(new Cell(1,3).add(p.getVisitante().getNombre()).setBold().setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE));
        doc.add(tablaEquipos);
       
        //TABLA JUGADORES
        Table tablaJugadores = new Table(24);
        tablaJugadores.setWidthPercent(100);
        tablaJugadores.setMarginTop(10);
        tablaJugadores.setFontSize(10);
        //crear encabezados
        tablaJugadores.addCell(new Cell().add("N°").setTextAlignment(TextAlignment.CENTER).setBold());
        tablaJugadores.addCell(new Cell(1,7).add("JUGADOR").setBold());
        tablaJugadores.addCell(new Cell().add("G").setTextAlignment(TextAlignment.CENTER).setBold());
        tablaJugadores.addCell(new Cell().add("A").setTextAlignment(TextAlignment.CENTER).setBold());
        tablaJugadores.addCell(new Cell().add("R").setTextAlignment(TextAlignment.CENTER).setBold());
        Cell d = new Cell(1,2);
        d.setBorderBottom(new SolidBorder(Color.WHITE,0));
        d.setBorderTop(new SolidBorder(Color.WHITE,0));     
        tablaJugadores.addCell(d);
        tablaJugadores.addCell(new Cell().add("N°").setTextAlignment(TextAlignment.CENTER).setBold());
        tablaJugadores.addCell(new Cell(1,7).add("JUGADOR").setBold());
        tablaJugadores.addCell(new Cell().add("G").setTextAlignment(TextAlignment.CENTER).setBold());
        tablaJugadores.addCell(new Cell().add("A").setTextAlignment(TextAlignment.CENTER).setBold());
        tablaJugadores.addCell(new Cell().add("R").setTextAlignment(TextAlignment.CENTER).setBold());
        //agregar jugadores
        int cantLocal = p.getLocal().getJugadores().size();
        int cantVisitante = p.getVisitante().getJugadores().size();
        int i = 0;
        while(i<=cantLocal || i<=cantVisitante) {
	    	tablaJugadores.addCell("");
	       	 if(i<cantLocal) tablaJugadores.addCell(new Cell(1,7).add(p.getLocal().getJugadores().get(i).toString()));
	       	 else tablaJugadores.addCell(new Cell(1,7).add("").setMinHeight(15));
	       	 tablaJugadores.addCell("");tablaJugadores.addCell("");tablaJugadores.addCell("");
	       	 //acomodando las celdas del medio
	       	 tablaJugadores.addCell(d);
	       	tablaJugadores.addCell("");
	       	 if(i<cantVisitante) tablaJugadores.addCell(new Cell(1,7).add(p.getVisitante().getJugadores().get(i).toString()));
	       	 else tablaJugadores.addCell(new Cell(1,7).add("").setMinHeight(15));
	       	 tablaJugadores.addCell("");tablaJugadores.addCell("");tablaJugadores.addCell("");
	       	 i++;
        }
        
        doc.add(tablaJugadores);
        
        //tabla arbitro
        Table tablaArbitro = new Table(3); 
        tablaArbitro.setWidthPercent(50);
        tablaArbitro.setMarginTop(15);
        tablaArbitro.addCell(createCell("Arbitro:",1,1));
        tablaArbitro.addCell(new Cell(1,2));
        doc.add(tablaArbitro);
        
        //tabla desempeño del arbitro        
        Table tablaDesempeño = new Table(new float[] {1,1,1,1,1,1,1,1,1,1,1,1});
        tablaDesempeño.setMarginTop(10);
        tablaDesempeño.setWidthPercent(100);
        tablaDesempeño.setFixedLayout();
                
        tablaDesempeño.addCell(createCell("Arbitraje:",2,2).setVerticalAlignment(VerticalAlignment.MIDDLE));
        tablaDesempeño.addCell(createCell("Excelente",1,2));
        tablaDesempeño.addCell(createCell("Muy Bueno",1,2));
        tablaDesempeño.addCell(createCell("Bueno",1,2));
        tablaDesempeño.addCell(createCell("Regular",1,2));
        tablaDesempeño.addCell(createCell("Malo",1,2));
        
        tablaDesempeño.addCell(createCell("",1,1));
        tablaDesempeño.addCell(createCell("",1,1));
        tablaDesempeño.addCell(createCell("",1,1));
        tablaDesempeño.addCell(createCell("",1,1));
        tablaDesempeño.addCell(createCell("",1,1));
        tablaDesempeño.addCell(createCell("",1,1));
        tablaDesempeño.addCell(createCell("",1,1));
        tablaDesempeño.addCell(createCell("",1,1));
        tablaDesempeño.addCell(createCell("",1,1));
        tablaDesempeño.addCell(createCell("",1,1));
        
        doc.add(tablaDesempeño);
        
        //tabla notas
        Table tablaNotas = new Table(1);
        tablaNotas.setMarginTop(10);
        //tablaNotas.setMinHeight(80);
        Cell c = new Cell();
        c.setMinHeight(20);
        c.setBorderLeft(new SolidBorder(Color.WHITE,0));
        c.setBorderRight(new SolidBorder(Color.WHITE,0));
        c.setBorderBottom(new SolidBorder(Color.BLACK,1));
        Cell notas = new Cell();
        notas.setMinHeight(20);
        notas.add("Notas: ").setVerticalAlignment(VerticalAlignment.BOTTOM);
        //notas.setBorderLeft(new SolidBorder(Color.WHITE,0));
        //notas.setBorderRight(new SolidBorder(Color.WHITE,0));
        //notas.setBorderTop(new SolidBorder(Color.WHITE,0));
        tablaNotas.addCell(notas);
        tablaNotas.addCell(new Cell().setMinHeight(20));
        tablaNotas.addCell(new Cell().setMinHeight(20));
        tablaNotas.addCell(new Cell().setMinHeight(20));

        doc.add(tablaNotas);
        
        //firmas
        Paragraph par = new Paragraph();
        par.setMarginTop(8);
        par.add("FIRMA");
        par.addTabStops(new TabStop(1000, TabAlignment.RIGHT));
        par.add(new Tab());
        par.add("FIRMA");
        
        doc.add(par);
	}
	
	public static void exportarHorariosPDF(List<Partido> listaPartidos, String fecha) throws Exception{
		String DEST = carpeta+"/"+ fecha+"-horarios.pdf";
		File file = new File(DEST);
        file.getParentFile().mkdirs();
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(file));
   	 	pdfDoc.setDefaultPageSize(PageSize.A4);//.rotate()
   	 	Document doc = new Document(pdfDoc);
   	 	//algoritmo
   	 	armarHorarios2(doc,listaPartidos);
        //CERRAR EL DOCUMENTO
        doc.close();
	}
	
	private static void armarHorarios(Document doc, List<Partido> partidos) throws MalformedURLException {
		//LOGO
        Image image = new Image(ImageDataFactory.create(ExportadorPDF.class.getResource(LOGO).toString()));
        image.setWidthPercent(30);
        image.setHorizontalAlignment(HorizontalAlignment.CENTER);
        image.setMarginBottom(10);
        doc.add(image);
        
        //TABLA HORARIOS
        List<Torneo> torneos = new ArrayList<Torneo>();
        for(Partido p:partidos) {
        	Torneo t = encontrarTorneo(p);
        	if(t!=null && !torneos.contains(t)) torneos.add(t);
        }
        
        for(Torneo t:torneos) {
        	Table tabla = new Table(5); tabla.setMarginTop(10);
        	tabla.addHeaderCell(new Cell(1,3).add(t.getNombre())); tabla.addHeaderCell("HORARIO");tabla.addHeaderCell("CANCHA");
        	tabla.getHeader().setBackgroundColor(Color.BLACK);
        	tabla.getHeader().setFontColor(Color.WHITE);
        	for(Partido p:partidos) {
        		if(p.getVisitante() != null && t.getPartidos().contains(p)) {
        			tabla.addCell(new Cell(1,3).add(p.getLocal() + " vs " + p.getVisitante()));
        			tabla.addCell(p.getHora()+" hs.");
        			tabla.addCell("");
        		}
        	}
        	doc.add(tabla);
        }   
	}
	
	private static void armarHorarios2(Document doc, List<Partido> partidos) throws MalformedURLException {
		//LOGO
        Image image = new Image(ImageDataFactory.create(ExportadorPDF.class.getResource(LOGO).toString()));
        image.setWidthPercent(30);
        image.setHorizontalAlignment(HorizontalAlignment.CENTER);
        image.setMarginBottom(10);
        doc.add(image);
        
        //TABLA HORARIOS
        Table tabla = new Table(5); tabla.setMarginTop(10);
    	tabla.addHeaderCell(new Cell(1,3).add("PARTIDO")); tabla.addHeaderCell("HORARIO");tabla.addHeaderCell("CANCHA");
    	tabla.getHeader().setBackgroundColor(Color.BLACK);
    	tabla.getHeader().setFontColor(Color.WHITE);
        for(Partido p:partidos) {
        	if(p.getVisitante()!=null && p.getLocal()!=null && !p.getHora().equals("23:59")) {
    			tabla.addCell(new Cell(1,3).add(p.getLocal() + " vs " + p.getVisitante()));
    			tabla.addCell(p.getHora()+" hs.");
    			tabla.addCell("");
    		}
        }
        doc.add(tabla);
	}
	
	private static Torneo encontrarTorneo(Partido p) {
		TorneoDao daoT = new TorneoDaoMorphia();
		List<Torneo> torneos = daoT.getTorneos();
		for(Torneo t:torneos) {
			if(t.getPartidos().contains(p)) return t;
		}
		return null;
	}
	
	private static Cell createCell(String cont, int rowspan, int colspan) {
		Cell c = new Cell(rowspan, colspan);
		c.add(cont);
		c.setTextAlignment(TextAlignment.CENTER);
		c.setMinHeight(12);	
		return c;
	}
	
	
	
}
