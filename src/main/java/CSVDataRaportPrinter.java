import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

public class CSVDataRaportPrinter implements DataRaportPrinter {

    @Override
    public void printRaport(Raport raport) throws IOException {

        String raportDate = LocalDate.now().toString();
        String raportType = raport.getClass().getName();
        String fileName = raportType + "_" + raportDate + ".csv";
        
        try (PrintWriter csvFileWriter = new PrintWriter(new File(fileName))) {
            StringBuilder raportToPrintInStringFormat = new StringBuilder();
            
            String raportName = "Raport: " + raport.getName();
            String periodOfData = "Dane za okres: " + raport.getMinDate() + " - " + raport.getMaxDate();
            raportToPrintInStringFormat.append(raportName);
            raportToPrintInStringFormat.append("\n");
            raportToPrintInStringFormat.append(periodOfData);
            raportToPrintInStringFormat.append("\n");

            for (String[] record : raport.getRaport()) {
                for (String value : record) {
                    raportToPrintInStringFormat.append(value);
                    raportToPrintInStringFormat.append(";");
                }
                raportToPrintInStringFormat.append("\n");
            }
            csvFileWriter.write(raportToPrintInStringFormat.toString());
            csvFileWriter.close();
            System.out.println("Raport zapisany do pliku " + fileName);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}