import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

public class CSVDataRaportPrinter implements DataRaportPrinter {

    @Override
    public void printRaport(Raport raport) throws IOException {

        String raportDate = LocalDate.now().toString();
        String raportType = raport.getClass().getName();
        String fileName = raportType + raportDate + ".csv";
        
        try (PrintWriter csvFileWriter = new PrintWriter(new File(fileName))) {
            StringBuilder raportToPrintInStringFormat = new StringBuilder();
            
            for (String[] record : raport.getRaport()) {
                for (String value : record) {
                    raportToPrintInStringFormat.append(value);
                    raportToPrintInStringFormat.append(";");
                }
                raportToPrintInStringFormat.append("\n");
            }
            
            csvFileWriter.write(raportToPrintInStringFormat.toString());
            csvFileWriter.close();
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}