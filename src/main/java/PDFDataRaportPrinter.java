
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.time.LocalDate;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
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
			document.open();
			for (String[] record : raport.getRaport()) {
				for (String value : record) {
					document.add(new Paragraph(value));

					document.add(Chunk.NEWLINE);
				}
				document.add(Chunk.NEWLINE);

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