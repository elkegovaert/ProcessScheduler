import java.util.List;

public class FirstComeFirstServed {

    public FirstComeFirstServed() {}

    //First Come First Served Scheduling Algorithme
    static public List<Process> FCFS(List<Process> processList) {

        //Process 1 wordt steeds als eerste uitgevoerd
        Process process1 = processList.get(0);
        int serviceTime1=process1.getServiceTime();
        int endTime1=process1.getServiceTime()+process1.getArrivalTime();
        process1.setEndTime(endTime1);
        process1.setWaitTime(0);
        process1.setStartTime(process1.getArrivalTime());
        process1.setTAT(0+serviceTime1);
        process1.setNormTAT(process1.getTAT()/serviceTime1);

        for(int i=1;i<processList.size();i++) {

            Process processVorige = processList.get(i-1);
            Process process=processList.get(i);
            int arrivalTime = process.getArrivalTime();
            int serviceTime = process.getServiceTime();
            int waitTime = processVorige.getEndTime()-arrivalTime;
            if(waitTime<0)waitTime=0;
            int startTime;
            if(processVorige.getEndTime()<arrivalTime) {
                startTime=arrivalTime;
            }
            else {
                startTime=processVorige.getEndTime();
            }
            int endTime = startTime+serviceTime;
            int TAT = serviceTime + waitTime;
            double normTAT = (double)TAT/(double)serviceTime;

            process.setStartTime(startTime);
            process.setEndTime(endTime);
            process.calculateStats();

        }

        return processList;
    }

}
