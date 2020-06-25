
public class ConsoleDataRaportPrinter extends DataRaportPrinter {

    public ConsoleDataRaportPrinter(Raport raport) {
        super(raport);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void printRaport() {
        
        System.out.println("Raport: " + this.getRaportName());
        System.out.println("Dane za okres: " + this.getTimeRange());
        for (String[] record : this.getRaportObject().getRaport()) {
            for (String value : record) {
                String line = String.format("%-25s", value);
                System.out.print(line);
            }
            System.out.println();
        }
    }

}

