import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class CheckImportValues
{
    String fileName;
    String worksheetName;
    int rowIndex;
    String message;

    public CheckImportValues(String fileName, String worksheetName, int rowIndex)
    {
        this.fileName = fileName;
        this.worksheetName = worksheetName;
        this.rowIndex = rowIndex ;
        message = "Błąd w pliku " + fileName + " w arkuszu " + worksheetName + " w wierszu " + rowIndex + ".";
    }

    public void isCorrectNumberValue(Cell cell){
        try {
            double cellValue = cell.getNumericCellValue();
        } catch (Exception e) {
            System.out.println(message + " Niepoprawny format liczby");
        }
    }

    public void isCorrectDate(Row row, int columnIndex){

        try {
            row.getCell(columnIndex).getLocalDateTimeCellValue().toLocalDate();
        } catch (Exception e) {
            System.out.println(message + " Niepoprawny format daty.");
        }
    }

    public void printErrorMessage(boolean haveData, boolean haveDescription, boolean haveDuration){

        if(!haveData){
            message += " Brak daty.";
        }

        if(!haveDescription){
            message += " Brak opisu zadania.";
        }

        if(!haveDuration){
            message += " Brak długości trwania zadania.";
        }
        System.out.println(message);
    }
}
