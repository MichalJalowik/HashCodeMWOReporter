import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;

public class ChartTypeXStringsYManyNumbersZStrings implements ChartType {

    String chartTitle;
    String chartStringsAxisTitle;
    String chartNumbersAxisTitle;
    String[][] givenData;
    ArrayList<String> projectsFromData;
    ArrayList<String> namesFromData;
    ArrayList<ArrayList<Number>> numbersFromData;

    @Override
    public void printChart(Raport raport) {

        this.chartTitle = raport.getName();
        this.givenData = raport.getRaport();
        this.chartStringsAxisTitle = givenData[0][0];
        this.chartNumbersAxisTitle = givenData[0][givenData[0].length - 1];
        dataParser();

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

    public void dataParser() {
        projectsFromData = new ArrayList<>();
        namesFromData = new ArrayList<>();
        numbersFromData = new ArrayList<>();

        for (int i = 1; i < givenData[0].length - 1; i++) {
            String projectName = givenData[0][i].substring(givenData[0][i].lastIndexOf(" ") + 1);
            projectsFromData.add(projectName);
        }
        for (int i = 1; i < givenData.length; i++) {
            namesFromData.add(givenData[i][0]);
        }
        for (int i = 1; i < givenData[0].length - 1; i++) {
            ArrayList<Number> houresInProject = new ArrayList<>();
            for (int j = 1; j < givenData.length; j++) {
                houresInProject.add(Double.parseDouble(givenData[j][i]));
            }
            numbersFromData.add(houresInProject);
        }
    }

    @Override
    public CategoryChart getChart() {

        CategoryChart chart = new CategoryChartBuilder().width(1300).height(750).title(chartTitle)
                .xAxisTitle(chartStringsAxisTitle).yAxisTitle(chartNumbersAxisTitle).build();

        chart.getStyler().setPlotGridVerticalLinesVisible(false);
        chart.getStyler().setStacked(true);
        chart.getStyler().setXAxisLabelRotation(65);

        for (int i = 0; i < projectsFromData.size(); i++) {
            chart.addSeries(projectsFromData.get(i), namesFromData, numbersFromData.get(i));
        }
        return chart;
    }

    @Override
    public String getExampleChartName() {
        return getClass().getSimpleName() + " - Stacked Bar Chart";
    }
}