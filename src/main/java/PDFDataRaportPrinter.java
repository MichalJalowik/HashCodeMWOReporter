
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFDataRaportPrinter extends DataRaportPrinter {

	public PDFDataRaportPrinter(Raport raport) {
		super(raport);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void printRaport() throws IOException {
		String raportDate = LocalDate.now().toString();
		String raportType = this.getRaportObject().getClass().getName();
		String fileName = raportType + "_" + raportDate + ".pdf";

		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(fileName));
			String raportName = this.getRaportName();
			String periodOfData = "Dane za okres: " + this.getTimeRange();
			int i = raport.getRaport()[0].length;
			BaseFont helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);
			Font helvetica11 = new Font(helvetica, 11);
			Font helvetica13 = new Font(helvetica, 13);
			PdfPTable table = new PdfPTable(i);
			table.setWidthPercentage(100f);
			document.open();
			document.add(new Paragraph(raportName, helvetica13));
			document.add(new Paragraph(periodOfData, helvetica13));
			document.add(new Paragraph("\n", helvetica13));
			for (int n = 0; n < 1; n++) {
				String[] tablica = raport.getRaport()[0];
				for (String value : tablica) {
					helvetica13.setColor(BaseColor.WHITE);
					PdfPCell name = new PdfPCell(new Phrase(value.toUpperCase(), helvetica13));
					name.setBackgroundColor(BaseColor.DARK_GRAY);
					table.addCell(name);
				}
			}
			for (int m = 1; m < raport.getRaport().length; m++) {
				String[] tablica1 = raport.getRaport()[m];
				for (String value : tablica1) {
					table.addCell(new Paragraph(value, helvetica11));
				}

			}
			document.add(table);
			String imgName = raportType + "_" + raportDate + "_chart.jpg";
			File curDir = new File(".");
			File[] filesList = curDir.listFiles();
			for (File f : filesList) {
				if (f.getName().equals(imgName)) {
					int indentation = 0;
					Image image = Image.getInstance(imgName);
					float scaler = ((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin()
							- indentation) / image.getWidth()) * 100;
					image.scalePercent(scaler);
					document.add(new Paragraph("\n", helvetica13));
					document.add(image);
					Path p = Paths.get(imgName);
					Files.deleteIfExists(p);
				}

			}
			document.close();

			System.out.println("Raport zapisany do pliku " + fileName);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

}