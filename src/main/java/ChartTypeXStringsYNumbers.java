import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
//import org.knowm.xchart.SwingWrapper;

public class ChartTypeXStringsYNumbers implements ChartType {

    String chartTitle;
    String chartStringsAxisTitle;
    String chartNumbersAxisTitle;
    ArrayList<String> axisOfStrings;
    ArrayList<Number> axisOfNumbers;

    @Override
    public void printChart(Raport raport) {
     
        this.chartTitle = raport.getName();
        this.axisOfStrings = parseRaportDataToXAxis(raport.getRaport());
        this.axisOfNumbers = parseRaportDataToYAxis(raport.getRaport());
        CategoryChart chart = this.getChart();
//        new SwingWrapper<CategoryChart>(chart).displayChart();
        
        try {
            String raportDate = LocalDate.now().toString();
            String raportType = raport.getClass().getName();
            String fileName = raportType + "_" + raportDate + "_chart";
            
            BitmapEncoder.saveBitmap(getChart(), fileName, BitmapFormat.JPG);
            System.out.println("Wykres został zapisany do pliku: " + fileName + ".jpg");
        } catch (IOException e) {
            System.out.println("Nie udalo sie zapisac wykresu do pliku.");
            e.printStackTrace();
        }
    }

    public ArrayList<String> parseRaportDataToXAxis(String[][] givenData) {
        this.chartStringsAxisTitle = givenData[0][0];
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 1; i < givenData.length; i++) {
            strings.add(givenData[i][0]);
        }
        return strings;
    }

    public ArrayList<Number> parseRaportDataToYAxis(String[][] givenData) {
        this.chartNumbersAxisTitle = givenData[0][1];
        ArrayList<Number> numbers = new ArrayList<>();
        for (String[] row : givenData) {
            for (String cell : row) {
                if (isNumeric(cell)) {
//                    numbers.add(Integer.parseInt(cell));
                    numbers.add(Double.parseDouble(cell));
                }
            }
        }
        return numbers;
    }

    @Override
    public CategoryChart getChart() {

        CategoryChart chart = new CategoryChartBuilder().width(1300).height(750).title(chartTitle).xAxisTitle(chartStringsAxisTitle)
                .yAxisTitle(chartNumbersAxisTitle).build();

        chart.getStyler().setPlotGridVerticalLinesVisible(false);

        chart.addSeries("Przepracowane\ngodziny", axisOfStrings, axisOfNumbers);

        return chart;
    }

    @Override
    public String getExampleChartName() {

        return getClass().getSimpleName() + " - Stacked Bar Chart";
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
