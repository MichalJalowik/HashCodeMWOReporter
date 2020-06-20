
public class ConsoleDataRaportPrinter implements DataRaportPrinter {

    @Override
    public void printRaport(TestRaport raport) {
        
        System.out.println("Raport nr 1 - Czas pracy poszczególnych pracowników:");
        for (String[] record : raport.getRaport()) {
            for (String value : record) {
                String line = String.format("%15s", value);
                System.out.print(line);
            }
            System.out.println();
        }
    }

}
