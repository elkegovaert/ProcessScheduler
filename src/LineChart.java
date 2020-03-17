import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.Collections;
import java.util.List;


public class LineChart extends JFrame {

    public LineChart() {}

    public void initUIWaitTime(List<Process> firstComeFirstServed, List<Process> roundRobinq2, List<Process> roundRobinq4, List<Process> roundRobinq8, List<Process> shortestJobFirst, List<Process> shortestRemainingTime, List<Process> highestResponseRatioNext, List<Process> multiLevelFeedBackq1, List<Process> multiLevelFeedBackq2) {

        int numberOfProcesses = firstComeFirstServed.size();

        XYDataset datasetWaitTime = createDatasetWaitTime(firstComeFirstServed, roundRobinq2, roundRobinq4, roundRobinq8, shortestJobFirst, shortestRemainingTime, highestResponseRatioNext, multiLevelFeedBackq1, multiLevelFeedBackq2);
        JFreeChart chart = createChartWaitTime(datasetWaitTime, "Scheduling Algorithmes Simulated With "+numberOfProcesses+" Processes");

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);


        pack();
        setTitle("Scheduling Algorithmes For Simulation With "+numberOfProcesses);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initUINormTAT(List<Process> firstComeFirstServed, List<Process> roundRobinq2, List<Process> roundRobinq4, List<Process> roundRobinq8, List<Process> shortestJobFirst, List<Process> shortestRemainingTime, List<Process> highestResponseRatioNext, List<Process> multiLevelFeedBackq1, List<Process> multiLevelFeedBackq2) {
        int numberOfProcesses = firstComeFirstServed.size();


        XYDataset datasetNormTAT = createDatasetNormTAT(firstComeFirstServed, roundRobinq2, roundRobinq4, roundRobinq8, shortestJobFirst, shortestRemainingTime, highestResponseRatioNext, multiLevelFeedBackq1, multiLevelFeedBackq2);
        JFreeChart chart = createChartGenTAT(datasetNormTAT, "Scheduling Algorithmes Simulated With "+numberOfProcesses+" Processes");


        XYPlot xyPlot = (XYPlot) chart.getPlot();

        NumberAxis domain = (NumberAxis) xyPlot.getDomainAxis();
        domain.setRange(0.00, 200.00);

        NumberAxis range = (NumberAxis) xyPlot.getRangeAxis();
        range.setRange(0.0, 100.0);


        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle("Scheduling Algorithmes For Simulation With "+numberOfProcesses);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private XYDataset createDatasetWaitTime(List<Process> firstComeFirstServed, List<Process> roundRobinq2, List<Process> roundRobinq4, List<Process> roundRobinq8, List<Process> shortestJobFirst, List<Process> shortestRemainingTime, List<Process> highestResponseRatioNext, List<Process> multiLevelFeedBackq1, List<Process> multiLevelFeedBackq2) {


        XYSeriesCollection dataset = new XYSeriesCollection();


        XYSeries seriesFCFS = new XYSeries("First Come First Served");
        XYSeries seriesRR2 = new XYSeries("Round Robin q=2");
        XYSeries seriesRR4 = new XYSeries("Round Robin q=4");
        XYSeries seriesRR8 = new XYSeries("Round Robin q=8");
        XYSeries seriesSJF = new XYSeries("Shortest Job First");
        XYSeries seriesSRT = new XYSeries("Shortest Remaining Time");
        XYSeries seriesHRRN = new XYSeries("Highest Response Ratio Next");
        XYSeries seriesMLFB1 = new XYSeries("Multi Level Feedback q=i");
        XYSeries seriesMLFB2 = new XYSeries("Multi Level Feedback q=2^i");

        //uncomment the algorithmes that you want to see on the graph
        seriesFCFS = calculateDatasetWaitTime(seriesFCFS, firstComeFirstServed);
        seriesRR2 = calculateDatasetWaitTime(seriesRR2, roundRobinq2);
        seriesRR4 = calculateDatasetWaitTime(seriesRR4, roundRobinq4);
        seriesRR8 = calculateDatasetWaitTime(seriesRR8, roundRobinq8);
        seriesSJF = calculateDatasetWaitTime(seriesSJF, shortestJobFirst);
        seriesSRT = calculateDatasetWaitTime(seriesSRT, shortestRemainingTime);
        seriesHRRN = calculateDatasetWaitTime(seriesHRRN, highestResponseRatioNext);
        seriesMLFB1 = calculateDatasetWaitTime(seriesMLFB1, multiLevelFeedBackq1);
        seriesMLFB2 = calculateDatasetWaitTime(seriesMLFB2, multiLevelFeedBackq2);

        dataset.addSeries(seriesFCFS);
        dataset.addSeries(seriesRR2);
        dataset.addSeries(seriesRR4);
        dataset.addSeries(seriesRR8);
        dataset.addSeries(seriesSJF);
        dataset.addSeries(seriesSRT);
        dataset.addSeries(seriesHRRN);
        dataset.addSeries(seriesMLFB1);
        dataset.addSeries(seriesMLFB2);

        return dataset;
    }

    private XYDataset createDatasetNormTAT(List<Process> firstComeFirstServed, List<Process> roundRobinq2, List<Process> roundRobinq4, List<Process> roundRobinq8, List<Process> shortestJobFirst, List<Process> shortestRemainingTime, List<Process> highestResponseRatioNext, List<Process> multiLevelFeedBackq1, List<Process> multiLevelFeedBackq2) {


        XYSeriesCollection dataset = new XYSeriesCollection();


        XYSeries seriesFCFS = new XYSeries("First Come First Served");
        XYSeries seriesRR2 = new XYSeries("Round Robin q=2");
        XYSeries seriesRR4 = new XYSeries("Round Robin q=4");
        XYSeries seriesRR8 = new XYSeries("Round Robin q=8");
        XYSeries seriesSJF = new XYSeries("Shortest Job First");
        XYSeries seriesSRT = new XYSeries("Shortest Remaining Time");
        XYSeries seriesHRRN = new XYSeries("Highest Response Ratio Next");
        XYSeries seriesMLFB1 = new XYSeries("Multi Level Feedback q=i");
        XYSeries seriesMLFB2 = new XYSeries("Multi Level Feedback q=2^i");

        //uncomment the algorithmes that you want to see on the graph
        seriesFCFS = calculateDatasetNormTAT(seriesFCFS, firstComeFirstServed);
        seriesRR2 = calculateDatasetNormTAT(seriesRR2, roundRobinq2);
        seriesRR4 = calculateDatasetNormTAT(seriesRR4, roundRobinq4);
        seriesRR8 = calculateDatasetNormTAT(seriesRR8, roundRobinq8);
        seriesSJF = calculateDatasetNormTAT(seriesSJF, shortestJobFirst);
        seriesSRT = calculateDatasetNormTAT(seriesSRT, shortestRemainingTime);
        seriesHRRN = calculateDatasetNormTAT(seriesHRRN, highestResponseRatioNext);
        seriesMLFB1 = calculateDatasetNormTAT(seriesMLFB1, multiLevelFeedBackq1);
        seriesMLFB2 = calculateDatasetNormTAT(seriesMLFB2, multiLevelFeedBackq2);

        dataset.addSeries(seriesFCFS);
        dataset.addSeries(seriesRR2);
        dataset.addSeries(seriesRR4);
        dataset.addSeries(seriesRR8);
        dataset.addSeries(seriesSJF);
        dataset.addSeries(seriesSRT);
        dataset.addSeries(seriesHRRN);
        dataset.addSeries(seriesMLFB1);
        dataset.addSeries(seriesMLFB2);

        return dataset;
    }

    private XYSeries calculateDatasetWaitTime(XYSeries series, List<Process> processes){

        Collections.sort(processes, new SortByServiceTime());
        int processesPerPercentiel = processes.size()/100;


        for(int percentielNummer = 1; percentielNummer != 100; percentielNummer++) {
            int totWaitTime=0;
            int totServiceTime=0;
            for(int i=0; i<processesPerPercentiel; i++) {
                totServiceTime = totServiceTime + processes.get(i+(percentielNummer-1)*processesPerPercentiel).getServiceTime();
                totWaitTime = totWaitTime + processes.get(i+(percentielNummer-1)*processesPerPercentiel).getWaitTime();

            }
            //System.out.println(totServiceTime/processesPerPercentiel);
            //System.out.println(totWaitTime/processesPerPercentiel);
            series.add(totServiceTime/processesPerPercentiel, totWaitTime/processesPerPercentiel);
        }

        return series;
    }

    private XYSeries calculateDatasetNormTAT(XYSeries series, List<Process> processes){

        Collections.sort(processes, new SortByServiceTime());
        int processesPerPercentiel = processes.size()/100;


        for(int percentielNummer = 1; percentielNummer != 100; percentielNummer++) {
            double totGenTAT=0;
            int totServiceTime=0;
            for(int i=0; i<processesPerPercentiel; i++) {
                totServiceTime = totServiceTime + processes.get(i+(percentielNummer-1)*processesPerPercentiel).getServiceTime();
                totGenTAT = totGenTAT + processes.get(i+(percentielNummer-1)*processesPerPercentiel).getNormTAT();

            }

            series.add(totServiceTime/processesPerPercentiel, totGenTAT/processesPerPercentiel);
        }

        return series;
    }

    private JFreeChart createChartWaitTime(XYDataset dataset, String title) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                title,
                "Service Time",
                "Wait Time",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        //chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle(title,
                        new Font("Serif", java.awt.Font.BOLD, 18)
                )
        );

        return chart;
    }

    private JFreeChart createChartGenTAT(XYDataset dataset, String title) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                title,
                "Service Time",
                "Normalized Turn Around Time",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();

        NumberAxis domainAxis = new NumberAxis("Service Time");
        NumberAxis rangeAxis = new LogarithmicAxis("Normalized Turn Around Time (Log scale)");
        plot.setDomainAxis(domainAxis);
        plot.setRangeAxis(rangeAxis);

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);


        chart.setTitle(new TextTitle(title,
                        new Font("Serif", java.awt.Font.BOLD, 18)
                )
        );

        return chart;
    }
}
