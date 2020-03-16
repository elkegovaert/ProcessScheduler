import java.util.*;

public class HighestResponseRatioNext {

    public HighestResponseRatioNext() {
    }

    public List<Process> HRRN(List<Process> processList) {

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

                doneJobs.add(currentRunningProcess);

                //check if there are jobs to add to ready queue
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



        return doneJobs;
    }
}
