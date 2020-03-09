import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
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

    public LineChart(List<Process> processes) {

        initUI(processes);
    }

    private void initUI(List<Process> processes) {

        XYDataset dataset = createDataset(processes);
        JFreeChart chart = createChart(dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle("Scheduling ALgorithmes");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private XYDataset createDataset(List<Process> processes) {

        XYSeries series = new XYSeries("First Come First Served");

        
    	Collections.sort(processes, new SortByServiceTime());
    	int processesPerPercentiel = processes.size()/100;
    	
    	
    	for(int percentielNummer = 1; percentielNummer != 100; percentielNummer++) {
    		int totWaitTime=0;
    		int totServiceTime=0;
    		for(int i=0; i<processesPerPercentiel; i++) {
    	        totServiceTime = totServiceTime + processes.get(i+(percentielNummer-1)*processesPerPercentiel).getServiceTime();
    	        totWaitTime = totWaitTime + processes.get(i+(percentielNummer-1)*processesPerPercentiel).getWaitTime();
    		
    		}
    		System.out.println(totServiceTime/processesPerPercentiel);
    		System.out.println(totWaitTime/processesPerPercentiel);
    		series.add(totServiceTime/processesPerPercentiel, totWaitTime/processesPerPercentiel);
    	}

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        return dataset;
    }

    private JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Scheduling Algorithmes",
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

        chart.setTitle(new TextTitle("First Come First Served",
                        new Font("Serif", java.awt.Font.BOLD, 18)
                )
        );

        return chart;
    }
}
