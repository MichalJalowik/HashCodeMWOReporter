import java.io.IOException;
import java.util.ArrayList;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.demo.charts.ExampleChart;

public class ChartDataRaportPrinter implements DataRaportPrinter {

    ChartType chartType;
        
    @Override
    public void printRaport(Raport raport) throws IOException {
        String raportType = raport.getClass().getName();
        if (raportType.equals("Raport1") || raportType.equals("Raport2") || raportType.equals("Raport5")) {
            chartType = new ChartTypeXStringsYNumbers();
        }
        else {
            System.out.println(raportType + " jest nieobslugiwany. Pracujemy nad tym.");
        }
        chartType.printChart(raport);
    }
}