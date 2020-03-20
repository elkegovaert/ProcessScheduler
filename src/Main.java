import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.List;


public class Main {
    public static void main(String argv[]) throws ParserConfigurationException, SAXException, IOException {

        //xmlfiles inlezen
        ReadXMLFile readXMLFile = new ReadXMLFile();
        List<Process> processlist1 = readXMLFile.leesProcessen("processen10000.xml");
        List<Process> processlist2 = readXMLFile.leesProcessen("processen20000.xml");
        List<Process> processlist5 = readXMLFile.leesProcessen("processen50000.xml");


        //Alle algorithmes uitvoeren

        //FCFS
        FirstComeFirstServed fcfs = new FirstComeFirstServed();

        List<Process> processlist1FCFS=fcfs.schedule(processlist1);
        List<Process> processlist2FCFS=fcfs.schedule(processlist2);
        List<Process> processlist5FCFS=fcfs.schedule(processlist5);


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

        //SJF
        ShortestJobFirst sjf = new ShortestJobFirst();

        List<Process> processlist1SJF= sjf.schedule(processlist1);
        List<Process> processlist2SJF=sjf.schedule(processlist2);
        List<Process> processlist5SJF=sjf.schedule(processlist5);


		//SRT
		ShortestRemainingTime srt = new ShortestRemainingTime();

		List<Process> processlist1SRT= srt.schedule(processlist1);
		List<Process> processlist2SRT=srt.schedule(processlist2);
		List<Process> processlist5SRT=srt.schedule(processlist5);


        //HRRN
        HighestResponseRatioNext hrrn = new HighestResponseRatioNext();

        List<Process> processlist1HRRN= hrrn.schedule(processlist1);
        List<Process> processlist2HRRN=hrrn.schedule(processlist2);
        List<Process> processlist5HRRN=hrrn.schedule(processlist5);

        //MLFB
        MultiLevelFeedBack mlfb = new MultiLevelFeedBack();

        //lineair
        List<Process> processlist1MLFBL = mlfb.schedule(processlist1, "constant");
        List<Process> processlist2MLFBL = mlfb.schedule(processlist2, "constant");
        List<Process> processlist5MLFBL = mlfb.schedule(processlist5, "constant");

        //stijgend
        List<Process> processlist1MLFBS = mlfb.schedule(processlist1, "2^i");
        List<Process> processlist2MLFBS = mlfb.schedule(processlist2, "2^i");
        List<Process> processlist5MLFBS = mlfb.schedule(processlist5, "2^i");


        //Plotten van grafieken Tw (Time Wait) in functie van Ts (Time Service)
        // en genom. TAT in functie van T sevrice

        plotNormTAT(processlist1FCFS, processlist1RR2, processlist1RR4, processlist1RR8, processlist1SJF, processlist1SRT, processlist1HRRN, processlist1MLFBL, processlist1MLFBS);
        plotTimeWait(processlist1FCFS, processlist1RR2, processlist1RR4, processlist1RR8, processlist1SJF, processlist1SRT, processlist1HRRN, processlist1MLFBL, processlist1MLFBS);

        plotNormTAT(processlist2FCFS, processlist2RR2, processlist2RR4, processlist2RR8, processlist2SJF, processlist2SRT, processlist2HRRN, processlist2MLFBL, processlist2MLFBS);
        plotTimeWait(processlist2FCFS, processlist2RR2, processlist2RR4, processlist2RR8, processlist2SJF, processlist2SRT, processlist2HRRN, processlist2MLFBL, processlist2MLFBS);

        plotNormTAT(processlist5FCFS, processlist5RR2, processlist5RR4, processlist5RR8, processlist5SJF, processlist5SRT, processlist5HRRN, processlist5MLFBL, processlist5MLFBS);
        plotTimeWait(processlist5FCFS, processlist5RR2, processlist5RR4, processlist5RR8, processlist5SJF, processlist5SRT, processlist5HRRN, processlist5MLFBL, processlist5MLFBS);


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