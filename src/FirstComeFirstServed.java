import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FirstComeFirstServed {

    private static int totWaitTime = 0;
    private static int totTAT = 0;
    private static double totNormTAT = 0;

    public FirstComeFirstServed() {}

    //First Come First Served Scheduling Algorithme
    static public List<Process> schedule(List<Process> processList) {

        totWaitTime = 0;
        totTAT = 0;
        totNormTAT = 0;

        Queue<Process> toDoProcesses = new LinkedList<>();

        for (int i=0; i<processList.size(); i++) {
            toDoProcesses.add(new Process(processList.get(i)));
        }

        List<Process> doneProcesses = new ArrayList<Process>();

        Queue<Process> readyProcesses = new LinkedList<Process>();
        int currentTime = 0;
        Process currentRunningProcess;

        while(doneProcesses.size()!=processList.size()){

            //check for incoming processes
            while(toDoProcesses.size() != 0 && toDoProcesses.peek().getArrivalTime()<=currentTime){
                readyProcesses.add(toDoProcesses.poll());
            }

            if (!readyProcesses.isEmpty()) {

                currentRunningProcess = readyProcesses.poll();

                //Start Time
                if(currentRunningProcess.getStartTime()==0){
                    currentRunningProcess.setStartTime(currentTime);
                }

                currentTime = currentTime + currentRunningProcess.getServiceTime();

                currentRunningProcess.setEndTime(currentTime);
                currentRunningProcess.calculateStats();

                //Globale stats aanpassen
                totWaitTime = totWaitTime + currentRunningProcess.getWaitTime();
                totTAT = totTAT + currentRunningProcess.getTAT();
                totNormTAT = totNormTAT + currentRunningProcess.getNormTAT();

                doneProcesses.add(currentRunningProcess);

            }
            else{
                currentTime++;
            }
        }

        //Globale stats uitprinten van FCFS
        int size = doneProcesses.size();
        System.out.println("----------First Come First Served for "+size+" processes"+"----------");
        System.out.println("Average Wait Time: "+totWaitTime/size);
        System.out.println("Average TAT: "+totTAT/size);
        System.out.println("Average Normalized TAT: "+totNormTAT/size);

        return doneProcesses;
    }

}