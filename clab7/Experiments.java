import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by hug.
 */
public class Experiments {

    public static void experiment1() {
        List<Double> yValues = new ArrayList<>();
        List<Double> y2Values = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();

        BST<String> bst = new BST<>();
        String s = "cat";
        for (int i = 1; i <= 5000; i++) {
            s = StringUtils.randomString(3);
            bst.add(s);
            xValues.add(i);
            yValues.add(ExperimentHelper.optimalAverageDepth(i));
            y2Values.add(bst.actualAverageDepth());
        }

        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("optimalAverageDepth", xValues, yValues);
        chart.addSeries("acutalAverageDepth", xValues, y2Values);

        new SwingWrapper(chart).displayChart();
    }

    public static void experiment2() {
        List<String> list = new ArrayList<>();
        List<Double> yValues = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();

        BST<String> bst = new BST<>();
        String s = "cat";
        int N = 5000;
        int M = 100000;
        for (int i = 1; i <= N; i++) {
            s = StringUtils.randomString(3);
            bst.add(s);
            list.add(s);
        }

        xValues.add(0);
        yValues.add(bst.actualAverageDepth());
        for (int i = 1; i <= M; i++) {
            ExperimentHelper.randomDeleteTakingSuccessor(bst, list);
            xValues.add(i);
            yValues.add(bst.actualAverageDepth());
        }
        XYChart chart = new XYChartBuilder().width(1600).height(1200).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("averageDepth", xValues, yValues);

        new SwingWrapper(chart).displayChart();
    }

    public static void experiment3() {
        List<String> list = new ArrayList<>();
        List<Double> yValues = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();

        BST<String> bst = new BST<>();
        String s = "cat";
        int N = 5000;
        int M = 50000;
        for (int i = 1; i <= N; i++) {
            s = StringUtils.randomString(3);
            bst.add(s);
            list.add(s);
        }

        xValues.add(0);
        yValues.add(bst.actualAverageDepth());
        for (int i = 1; i <= M; i++) {
            ExperimentHelper.randomDeleteTakingRandom(bst, list);
            xValues.add(i);
            yValues.add(bst.actualAverageDepth());
        }
        XYChart chart = new XYChartBuilder().width(1600).height(1200).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("averageDepth", xValues, yValues);

        new SwingWrapper(chart).displayChart();
    }

    public static void main(String[] args) {
//        experiment1();
//        experiment2();
        experiment3();
    }
}
