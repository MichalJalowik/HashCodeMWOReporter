import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;

public class ChartTypeXStringsYNumbersZStrings implements ChartType {

    String chartTitle;
    String chartStringsAxisTitle;
    String chartNumbersAxisTitle;
    String[][] givenData;

    @Override
    public void printChart(Raport raport) {

        this.chartTitle = raport.getName();
        this.givenData = raport.getRaport();
        CategoryChart chart = this.getChart();
        this.chartStringsAxisTitle = givenData[0][0];
        this.chartNumbersAxisTitle = givenData[0][2];


        try {
            String raportDate = LocalDate.now().toString();
            String raportType = raport.getClass().getName();
            String fileName = raportType + "_" + raportDate + "_chart";

            BitmapEncoder.saveBitmap(getChart(), fileName, BitmapFormat.JPG);
            System.out.println("Wykres zapisany do pliku: " + fileName + ".jpg");
        } catch (IOException e) {
            System.out.println("Nie udalo sie zapisac wykresu.");
            e.printStackTrace();
        }
    }

    @Override
    public CategoryChart getChart() {

        CategoryChart chart = new CategoryChartBuilder().width(1300).height(750).title(chartTitle)
                .xAxisTitle(chartStringsAxisTitle).yAxisTitle(chartNumbersAxisTitle).build();

        chart.getStyler().setPlotGridVerticalLinesVisible(false);

        for (int i = 1; i < givenData.length; i++) {
            String taskName = givenData[i][0] + " (" + givenData[i][1] + ")";
            Number taskHoures = Double.parseDouble(givenData[i][2]);
            chart.addSeries(taskName, new ArrayList<String>(Arrays.asList(new String[] { " " })),
                    new ArrayList<Number>(Arrays.asList(new Number[] { taskHoures })));
        }
        return chart;
    }

    @Override
    public String getExampleChartName() {
        return getClass().getSimpleName() + " - Stacked Bar Chart";
    }
}
