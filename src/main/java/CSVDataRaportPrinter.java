import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVDataRaportPrinter implements DataRaportPrinter {

    @Override
    public void printRaport(TestRaport raport) throws IOException {
        // TODO Auto-generated method stub

        List<String[]> listFromArray = Arrays.asList(raport.getRaport());

//        for (String[] record : raport.getRaport()) {
//            String[] recordArray = new String[record.length];
//            for (String value : record) {
//                String line = String.format("%15s", value);
//                recordLine += line;
//            }
//            listFromArray.add(recordLine);
//        }
//        System.out.println(listFromArray);

        File csvOutputFile = new File("raport1.csv");
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            listFromArray.stream().forEach(pw::println);
        }
//        assertTrue(csvOutputFile.exists());
    }
}


//
//File csvOutputFile = new File(CSV_FILE_NAME);
//try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
//    dataLines.stream()
//      .map(this::convertToCSV)
//      .forEach(pw::println);
//}
//assertTrue(csvOutputFile.exists());