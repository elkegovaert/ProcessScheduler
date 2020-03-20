import java.util.*;

public class HighestResponseRatioNext {
    private static int totWaitTime = 0;
    private static int totTAT = 0;
    private static double totNormTAT = 0;

    public HighestResponseRatioNext() {
    }

    public List<Process> schedule(List<Process> processList) {

        totWaitTime = 0;
        totTAT = 0;
        totNormTAT = 0;

        Queue<Process> toDoJobs = new LinkedList<>();

        for (int i=0; i<processList.size(); i++) {
            toDoJobs.add(new Process(processList.get(i)));
        }

        List<Process> doneJobs = new ArrayList<>();
        PriorityQueue<Process> readyJobs = new PriorityQueue<Process>(10,(p1, p2)->{
            if(p1.getResponseRatio()-p2.getResponseRatio()>0) return -1;
            else if(p1.getResponseRatio()-p2.getResponseRatio()<0) return 1;
            else return 0;
        });

        Process currentRunningProcess;

        int currentTime = 0;

        while(doneJobs.size()!=processList.size()){

            //check if there are jobs to add to ready queue
            while(toDoJobs.size() != 0 && toDoJobs.peek().getArrivalTime()<=currentTime){
                readyJobs.add(toDoJobs.poll());
            }


            //als er process is om uit te voeren -> uitvoeren voor 1 time slice en stats aanpassen
            if (!readyJobs.isEmpty()) {

                currentRunningProcess = readyJobs.poll();

                //Start Time
                if(currentRunningProcess.getStartTime()==0){
                    currentRunningProcess.setStartTime(currentTime);
                }

                currentTime = currentTime + currentRunningProcess.getServiceTime();

                currentRunningProcess.setEndTime(currentTime);
                currentRunningProcess.calculateStats();
                totWaitTime = totWaitTime + currentRunningProcess.getWaitTime();
                totTAT = totTAT + currentRunningProcess.getTAT();
                totNormTAT = totNormTAT + currentRunningProcess.getNormTAT();

                doneJobs.add(currentRunningProcess);

                //check of er process is om aan ready queue toe te voegen
                while(toDoJobs.size() != 0 && toDoJobs.peek().getArrivalTime()<=currentTime){
                    readyJobs.add(toDoJobs.poll());
                }

                //alle andere hun wachttijd verhogen en ratio aanpassen
                if(!readyJobs.isEmpty()){
                    for(Process process: readyJobs){
                        process.setWaitTime(currentTime-process.getArrivalTime());
                        process.calculateResponseRatio();
                    }
                }

            } else {
                currentTime++;
            }
        }

        //Globale stats uitprinten van HRRN
        int size = processList.size();
        System.out.println("----------Highest Response Ratio Next for "+size+" processes"+"----------");
        System.out.println("Average Wait Time: "+totWaitTime/size);
        System.out.println("Average TAT: "+totTAT/size);
        System.out.println("Average Normalized TAT: "+totNormTAT/size);

        return doneJobs;
    }
}
