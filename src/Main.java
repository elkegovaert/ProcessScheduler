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


public class Main {
    public static void main(String argv[]) throws ParserConfigurationException, SAXException, IOException {
        System.out.println("Test voor git....");
        //xmlfiles inlezen
        ReadXMLFile readXMLFile = new ReadXMLFile();
        List<Process> processlist1 = readXMLFile.leesProcessen("processen10000.xml");
        System.out.println("Lijst met " + processlist1.size() + " processen gecreeerd.");

        List<Process> processlist2 = readXMLFile.leesProcessen("processen20000.xml");
        System.out.println("Lijst met " + processlist2.size() + " processen gecreeerd.");

        List<Process> processlist5 = readXMLFile.leesProcessen("processen50000.xml");
        System.out.println("Lijst met " + processlist5.size() + " processen gecreeerd.");

        List<Process> processlisttest = readXMLFile.leesProcessen("processentmp.xml");
        System.out.println("Lijst met " + processlisttest.size() + " processen gecreeerd.");

        //sorteer op service time
        //Collections.sort(processlist1, new SortByServiceTime());
        //Collections.sort(processlist2, new SortByServiceTime());
        //Collections.sort(processlist5, new SortByServiceTime());
        Collections.sort(processlisttest, new SortByServiceTime());
        
        //FCFS
        List<Process> processlist1FCFS= FirstComeFirstServed.FCFS(processlist1);
        List<Process> processlist2FCFS= FirstComeFirstServed.FCFS(processlist2);
        List<Process> processlist5FCFS= FirstComeFirstServed.FCFS(processlist5);
        
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