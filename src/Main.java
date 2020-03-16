import org.xml.sax.SAXException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.HistogramDataset;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import java.awt.EventQueue;

//import jdk.internal.org.xml.sax.SAXException;
//test voor git werkt het?
import java.io.IOException;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;


public class Main {
    public static void main(String argv[]) throws ParserConfigurationException, SAXException, IOException {

        //xmlfiles inlezen
        ReadXMLFile readXMLFile = new ReadXMLFile();
        List<Process> processlist1 = readXMLFile.leesProcessen("processen10000.xml");
        List<Process> processlist2 = readXMLFile.leesProcessen("processen20000.xml");
        List<Process> processlist5 = readXMLFile.leesProcessen("processen50000.xml");

        //sorteer op service time
        //Collections.sort(processlist1, new SortByServiceTime());


        //Alle algorithmes uitvoeren

        //FCFS

        FirstComeFirstServed fcfs = new FirstComeFirstServed();

        List<Process> processlist1FCFS=fcfs.FCFS(processlist1);
        List<Process> processlist2FCFS=fcfs.FCFS(processlist2);
        List<Process> processlist5FCFS=fcfs.FCFS(processlist5);


        //RR
        RoundRobin rr2 = new RoundRobin(2);
        List<Process> processlist1RR2 = rr2.schedule(processlist1);
        List<Process> processlist2RR2= rr2.schedule(processlist2);
        List<Process> processlist5RR2= rr2.schedule(processlist5);

        RoundRobin rr4 = new RoundRobin(4);
        List<Process> processlist1RR4 = rr4.schedule(processlist1);
        List<Process> processlist2RR4= rr4.schedule(processlist2);
        List<Process> processlist5RR4= rr4.schedule(processlist5);

        RoundRobin rr8 = new RoundRobin(8);
        List<Process> processlist1RR8 = rr8.schedule(processlist1);
        List<Process> processlist2RR8 = rr8.schedule(processlist2);
        List<Process> processlist5RR8 = rr8.schedule(processlist5);
/*
        double totgenTAT = 0;
        for(int i=0; i<processlist1RR2.size();i++) {
            totgenTAT = processlist1RR2.get(i).getNormTAT()+totgenTAT;
            System.out.println(processlist1RR2.get(i));
        }
 */

        //SJF
        ShortestJobFirst sjf = new ShortestJobFirst();

        List<Process> processlist1SJF= sjf.SJF(processlist1);
        List<Process> processlist2SJF=sjf.SJF(processlist2);
        List<Process> processlist5SJF=sjf.SJF(processlist5);


		//SRT
		ShortestRemainingTime srt = new ShortestRemainingTime();

		List<Process> processlist1SRT= srt.SRT(processlist1);
		List<Process> processlist2SRT=srt.SRT(processlist2);
		List<Process> processlist5SRT=srt.SRT(processlist5);

        /*for(int i = 0; i < 20;i++){
            System.out.println("2: " + processlist5RR2.get(i));
            System.out.println("4: " + processlist5RR4.get(i));
            System.out.println("8: " + processlist5RR8.get(i));
        }*/

        //HRRN
        HighestResponseRatioNext hrrn = new HighestResponseRatioNext();

        List<Process> processlist1HRRN= hrrn.HRRN(processlist1);
        List<Process> processlist2HRRN=hrrn.HRRN(processlist2);
        List<Process> processlist5HRRN=hrrn.HRRN(processlist5);


        //Plotten van grafieken Tw (Time Wait) in functie van Ts (Time Service)
        // en genom. TAT in functie van T sevrice

        List<Process> dummy = new ArrayList<Process>();
        Process process = new Process();
        dummy.add(process);

        plotNormTAT(processlist1FCFS, processlist1RR2, processlist1RR4, processlist1RR8, processlist1SJF, processlist1SRT, processlist1HRRN, dummy, dummy);
        plotTimeWait(processlist1FCFS, processlist1RR2, processlist1RR4, processlist1RR8, processlist1SJF, processlist1SRT, processlist1HRRN, dummy, dummy);

        plotNormTAT(processlist2FCFS, processlist2RR2, processlist2RR4, processlist2RR8, processlist2SJF, processlist2SRT, processlist2HRRN, dummy, dummy);
        plotTimeWait(processlist2FCFS, processlist2RR2, processlist2RR4, processlist2RR8, processlist2SJF, processlist2SRT, processlist2HRRN, dummy, dummy);

        plotNormTAT(processlist5FCFS, processlist5RR2, processlist5RR4, processlist5RR8, processlist5SJF, processlist5SRT, processlist5HRRN, dummy, dummy);
        plotTimeWait(processlist5FCFS, processlist5RR2, processlist5RR4, processlist5RR8, processlist5SJF, processlist5SRT, processlist5HRRN, dummy, dummy);


    }
    
    
    static public void plotNormTAT(List<Process> firstComeFirstServed, List<Process> roundRobinq2, List<Process> roundRobinq4, List<Process> roundRobinq8, List<Process> shortestJobFirst, List<Process> shortestRemainingTime, List<Process> highestResponseRatioNext, List<Process> multiLevelFeedBackq1, List<Process> multiLevelFeedBackq2) {

        EventQueue.invokeLater(() -> {

            LineChart lineChart = new LineChart();
            lineChart.initUINormTAT(firstComeFirstServed, roundRobinq2, roundRobinq4, roundRobinq8, shortestJobFirst, shortestRemainingTime, highestResponseRatioNext, multiLevelFeedBackq1, multiLevelFeedBackq2);
            lineChart.setVisible(true);
        });

    }
    
    static public void plotTimeWait(List<Process> firstComeFirstServed, List<Process> roundRobinq2, List<Process> roundRobinq4, List<Process> roundRobinq8, List<Process> shortestJobFirst, List<Process> shortestRemainingTime, List<Process> highestResponseRatioNext, List<Process> multiLevelFeedBackq1, List<Process> multiLevelFeedBackq2) {
    	
        EventQueue.invokeLater(() -> {

        	LineChart lineChart = new LineChart();
            lineChart.initUIWaitTime(firstComeFirstServed, roundRobinq2, roundRobinq4, roundRobinq8, shortestJobFirst, shortestRemainingTime, highestResponseRatioNext, multiLevelFeedBackq1, multiLevelFeedBackq2);
        	lineChart.setVisible(true);
        });
    	
    }
    
}