import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

public class CSVDataRaportPrinter extends DataRaportPrinter {

    public CSVDataRaportPrinter(Raport raport) {
        super(raport);
    }

    @Override
    public void printRaport() throws IOException {

        String raportDate = LocalDate.now().toString();
        String raportType = this.getRaportObject().getClass().getName();
        String fileName = raportType + "_" + raportDate + ".csv";

        try (PrintWriter csvFileWriter = new PrintWriter(new File(fileName))) {
            StringBuilder raportToPrintInStringFormat = new StringBuilder();

            String raportName = this.getRaportName();
            String periodOfData = "Dane za okres: " + this.getTimeRange();
            raportToPrintInStringFormat.append(raportName);
            raportToPrintInStringFormat.append("\n");
            raportToPrintInStringFormat.append(periodOfData);
            raportToPrintInStringFormat.append("\n");

            for (String[] record : this.getRaportObject().getRaport()) {
                for (String value : record) {
                    raportToPrintInStringFormat.append(value);
                    raportToPrintInStringFormat.append(";");
                }
                raportToPrintInStringFormat.append("\n");
            }
            csvFileWriter.write(raportToPrintInStringFormat.toString());
            csvFileWriter.close();
            System.out.println("Raport zapisany do pliku " + fileName);

        } catch (IOException e) {
            System.err.print("Error: nie można zapisać pliku!\n" + "Sprawdz czy plik " + fileName
                    + " nie jest otwarty w innym programie.");
        }
    }
}
