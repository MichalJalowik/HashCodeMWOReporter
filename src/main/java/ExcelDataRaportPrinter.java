import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataRaportPrinter implements DataRaportPrinter {

    @Override
    public void printRaport(Raport raport) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Raport");

        String[][] raportToPrint = raport.getRaport();
        String raportName = "Raport: " + raport.getName();
        String periodOfData = "Dane za okres: " + raport.getMinDate() + " - " + raport.getMaxDate();

        int rowCount = -1;
        
        Row raportNameRow = sheet.createRow(++rowCount);
        Cell raportNameCell = raportNameRow.createCell(0);
        raportNameCell.setCellValue(raportName);
        
        Row periodOfDataRow = sheet.createRow(++rowCount);
        Cell periodOfDataCell = periodOfDataRow.createCell(0);
        periodOfDataCell.setCellValue(periodOfData);
        

        for (String[] record : raportToPrint) {
            Row row = sheet.createRow(++rowCount);

            int columnCount = -1;

            for (String value : record) {
                Cell cell = row.createCell(++columnCount);
                cell.setCellValue(value);
                if (isNumeric(value)) {
                    cell.setCellValue(Integer.parseInt(value));
                } else {
                    cell.setCellValue(value);
                }
            }
        }
        
        String raportDate = LocalDate.now().toString();
        String raportType = raport.getClass().getName();
        String fileName = raportType + "_" + raportDate + ".xlsx";
        
        try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
            workbook.write(outputStream);
            System.out.println("Raport zapisany do pliku " + fileName);
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
