
public class TestDemoClass {

    public static void main(String[] args) {
        
        TestRaport raport = new TestRaport();
        ConsoleDataRaportPrinter consolePrinter = new ConsoleDataRaportPrinter();

        consolePrinter.printRaport(raport);
    }

}
