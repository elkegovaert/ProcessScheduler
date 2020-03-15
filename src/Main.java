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
        
        //FCFS
        /*
        List<Process> processlist1FCFS=FCFS(processlist1);
        List<Process> processlist2FCFS=FCFS(processlist2);
        List<Process> processlist5FCFS=FCFS(processlist5);
        
        double totgenTAT = 0;
        
        for(int i=0; i<processlist1FCFS.size();i++) {
        	totgenTAT = processlist1FCFS.get(i).getNormTAT()+totgenTAT;
        	System.out.println(processlist1FCFS.get(i));
        }
        
        System.out.println(totgenTAT/processlist1FCFS.size());
        
        
        
        //Plotten van grafieken Tw (Time Wait) in functie van Ts (Time Service)
        // en genom. TAT in functie van T sevrice
        plotGenTAT(processlist1FCFS);
        plotTimeWait(processlist1FCFS);
        
        plotGenTAT(processlist2FCFS);
        plotTimeWait(processlist2FCFS);
        
        plotGenTAT(processlist5FCFS);
        plotTimeWait(processlist5FCFS);
        */

        //SJF
        /*ShortestJobFirst sjf = new ShortestJobFirst();

        List<Process> processlist1SJF= sjf.SJF(processlist1);
        List<Process> processlist2SJF=sjf.SJF(processlist2);
        List<Process> processlist5SJF=sjf.SJF(processlist5);

/*
        for(int i = 0; i < 20;i++){
            System.out.println(processlist1SJF.get(i));
        }
*//*
        //Plotten van grafieken Tw (Time Wait) in functie van Ts (Time Service)
        // en genom. TAT in functie van T sevrice
        plotGenTAT(processlist1SJF);
        plotTimeWait(processlist1SJF);

        plotGenTAT(processlist2SJF);
        plotTimeWait(processlist2SJF);

        plotGenTAT(processlist5SJF);
        plotTimeWait(processlist5SJF);

        */

		//SRT
		ShortestRemainingTime srt = new ShortestRemainingTime();


		List<Process> processlist1SRT= srt.SRT(processlist1);
		/*List<Process> processlist2SRT=srt.SRT(processlist2);
		List<Process> processlist5SRT=srt.SRT(processlist5);*/


        for(int i = 0; i < 20;i++){
            System.out.println(processlist1SRT.get(i));
        }

        //Plotten van grafieken Tw (Time Wait) in functie van Ts (Time Service)
        // en genom. TAT in functie van T sevrice
        plotGenTAT(processlist1SRT);
        plotTimeWait(processlist1SRT);

        /*plotGenTAT(processlist2SRT);
        plotTimeWait(processlist2SRT);

        plotGenTAT(processlist5SRT);
        plotTimeWait(processlist5SRT);*/

		//RR
		/*RoundRobin rr2 = new RoundRobin(10);
		List<Process> processlist1RR2 = rr2.schedule(processlist1);
		List<Process> processlist2RR2= rr2.schedule(processlist2);
		List<Process> processlist5RR2= rr2.schedule(processlist5);

		double totgenTAT = 0;
		for(int i=0; i<processlist1RR2.size();i++) {
			totgenTAT = processlist1RR2.get(i).getNormTAT()+totgenTAT;
			System.out.println(processlist1RR2.get(i));
		}

		//Plotten van grafieken Tw (Time Wait) in functie van Ts (Time Service)
		// en genom. TAT in functie van T service
		plotGenTAT(processlist1RR2);
		plotTimeWait(processlist1RR2);

		plotGenTAT(processlist2RR2);
		plotTimeWait(processlist2RR2);

		plotGenTAT(processlist5RR2);
		plotTimeWait(processlist5RR2);*/

    }
    
    
    static public void plotGenTAT(List<Process> processList) {
    	
    }
    
    static public void plotTimeWait(List<Process> processList) {
    	
        EventQueue.invokeLater(() -> {

        	LineChart ex = new LineChart(processList);
            ex.setVisible(true);
        });
    	
    }
    
}