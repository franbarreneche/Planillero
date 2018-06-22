package parsers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.property.AreaBreakType;

import modelo.Partido;
import modelo.Torneo;

public class ExportadorCSV {	
	
	private static final String carpeta = "csv";
	private static final String LIBRE = "LIBRE";
	private static final String TBD = "TBD";
	
	public static void exportarPartidosCSV(List<Partido> listaPartidos, Torneo torneo) throws Exception{
		String DEST = carpeta+"/"+ torneo.getNombre()+"/"+LocalDate.now().toString()+".csv";
		File file = new File(DEST);
        file.getParentFile().mkdirs();
		FileWriter out = new FileWriter(file);
		
		CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT);
		printer.printRecord("Date","Hour","Court","Home","Away","Matchday");
		for(Partido p: listaPartidos) {
			System.out.println("Partido:" + p.getFecha() +" | "+ p.getHora() +" | "+ p.getMatchday());
			LocalDate dia = LocalDate.parse(p.getFecha());
			printer.printRecord(dia.getYear()+"/"+dia.getMonthValue()+"/"+dia.getDayOfMonth(),p.getHora(),"Sede "+torneo.getSede(),p.getLocal()==null?"":p.getLocal(),p.getVisitante()==null?"":p.getVisitante(),p.getMatchday());      
		}
        //CERRAR EL DOCUMENTO
        out.close();
	}
}
