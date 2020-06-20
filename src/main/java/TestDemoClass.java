import java.io.IOException;

public class TestDemoClass {

    public static void main(String[] args) throws IOException {
        
        Raport raport = new Raport();
        ConsoleDataRaportPrinter consolePrinter = new ConsoleDataRaportPrinter();
//        CSVDataRaportPrinter csvDataRaportPrinter = new CSVDataRaportPrinter();
        
        
//        csvDataRaportPrinter.printRaport(raport);
        consolePrinter.printRaport(raport);
    }

}
