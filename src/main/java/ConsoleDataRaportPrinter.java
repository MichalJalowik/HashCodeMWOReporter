
public class ConsoleDataRaportPrinter implements DataRaportPrinter {

    @Override
    public void printRaport(Raport raport) {
        
        System.out.println("Raport: " + raport.getName());
        for (String[] record : raport.getRaport()) {
            for (String value : record) {
                String line = String.format("%-25s", value);
                System.out.print(line);
            }
            System.out.println();
        }
    }

}

