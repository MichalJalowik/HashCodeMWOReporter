
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.time.LocalDate;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFDataRaportPrinter implements DataRaportPrinter {

	@Override
	public void printRaport(Raport raport) throws IOException {
		String raportDate = LocalDate.now().toString();
		String raportType = raport.getClass().getName();
		String fileName = raportType + "_" + raportDate + ".pdf";

		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(fileName));
			int i = raport.getRaport()[0].length;
			BaseFont helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);
			Font helvetica16=new Font(helvetica,16);
			PdfPTable table = new PdfPTable(i);
			table.setWidthPercentage(90f);
			document.open();

			for (String[] record : raport.getRaport()) {
				for (String value : record) {
					table.addCell(new Paragraph(value,helvetica16));
				}
			}
			document.add(table);
			document.close();

			System.out.println("Raport zapisany do pliku " + fileName);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

}