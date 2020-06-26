import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.demo.charts.ExampleChart;

public interface ChartType extends ExampleChart<CategoryChart> {

    public CategoryChart getChart();

    public String getExampleChartName();

    public void printChart(Raport raport);
}