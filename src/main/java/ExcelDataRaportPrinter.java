import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataRaportPrinter extends DataRaportPrinter {

    public ExcelDataRaportPrinter(Raport raport) {
        super(raport);
    }

    @Override
    public void printRaport() throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Raport");

        String[][] raportToPrint = this.getRaportObject().getRaport();
        String raportName = this.getRaportName();
        String periodOfData = "Dane za okres: " + this.getTimeRange();

        int rowCount = -1;

        Row raportNameRow = sheet.createRow(++rowCount);
        Cell raportNameCell = raportNameRow.createCell(0);
        raportNameCell.setCellValue(raportName);

        Row periodOfDataRow = sheet.createRow(++rowCount);
        Cell periodOfDataCell = periodOfDataRow.createCell(0);
        periodOfDataCell.setCellValue(periodOfData);

        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        CellStyle headStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 12);
        headStyle.setFont(font);
        headStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        for (String[] record : raportToPrint) {
            Row row = sheet.createRow(++rowCount);

            int columnCount = -1;

            for (String value : record) {
                Cell cell = row.createCell(++columnCount);
                cell.setCellValue(value);
                if (isNumeric(value)) {
                    cell.setCellValue(Double.parseDouble(value));
                } else {
                    cell.setCellValue(value);
                    if (rowCount == 2) {
                        cell.setCellStyle(headStyle);
                    } else {
                        cell.setCellStyle(style);
                    }
                }
            }
        }

        for (int i = 0; i < raportToPrint[0].length; i++) {
            sheet.autoSizeColumn(i);
        }

        String raportDate = LocalDate.now().toString();
        String raportType = this.getRaportObject().getClass().getName();
        String fileName = raportType + "_" + raportDate + ".xlsx";

        try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
            workbook.write(outputStream);
            System.out.println("Raport zapisany do pliku " + fileName);
        } catch (IOException e) {
            System.err.print("Error: nie można zapisać pliku!\n" + "Sprawdz czy plik " + fileName
                    + " nie jest otwarty w innym programie.");
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
