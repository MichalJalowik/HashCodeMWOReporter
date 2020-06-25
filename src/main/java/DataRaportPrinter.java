import java.io.IOException;

public abstract class DataRaportPrinter {

    Raport raport;
    String raportName;
    String minDate;
    String maxDate;
    String timeRange = "brak danych";

    public DataRaportPrinter(Raport raport) {
        this.raport = raport;
        this.raportName = raport.getName();

        if ((raport.getMinDate() != null) && (raport.getMaxDate() != null)) {
            this.minDate = raport.getMinDate().toString();
            this.maxDate = raport.getMaxDate().toString();
            this.timeRange = getMinDate() + " - " + getMaxDate();
        }
    };

    public void printRaport() throws IOException {
    }

    public Raport getRaportObject() {
        return raport;
    }

    public String getRaportName() {
        return raportName;
    }

    public String getMinDate() {
        return minDate;
    }

    public String getMaxDate() {
        return maxDate;
    }

    public String getTimeRange() {
        return timeRange;
    };

}
