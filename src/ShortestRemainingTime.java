import java.util.*;

public class ShortestRemainingTime {


    public ShortestRemainingTime() {
    }

    public List<Process> SRT(List<Process> processList) {

        Queue<Process> toDoJobs = new LinkedList<>();

        for (int i = 0; i<processList.size(); i++) {
            toDoJobs.add(new Process(processList.get(i)));
        }


        List<Process> doneProcesses = new ArrayList<>();


        PriorityQueue<Process> incomingProcesses = new PriorityQueue<Process>(100, (p1, p2) -> p1.getRemainingServiceTime() - p2.getRemainingServiceTime());

        //processes that are ready to handel
        PriorityQueue<Process> readyProcesses = new PriorityQueue<>(100, (p1, p2) -> p1.getRemainingServiceTime() - p2.getRemainingServiceTime());
        Process currentRunningProcess;

        int curentTime = 0;

        //Loop blijft gaan tot alle processen afgerond zijn
        while (doneProcesses.size() != processList.size()) {

            if (!readyProcesses.isEmpty()) {

                currentRunningProcess = readyProcesses.peek();
                int remainingTime = currentRunningProcess.getRemainingServiceTime();
                currentRunningProcess.setRemainingServiceTime(remainingTime - 1);

                //check if process is done
                if (currentRunningProcess.getRemainingServiceTime() == 0) {

                    //take process out of ready processes
                    currentRunningProcess = readyProcesses.poll();

                    currentRunningProcess.setEndTime(curentTime);
                    currentRunningProcess.calculateStats();

                    doneProcesses.add(currentRunningProcess);
                }
            }

            //shedule next process/job in queue
            //see if there are incoming job/process
            while (toDoJobs.peek() != null && toDoJobs.peek().getArrivalTime() == curentTime){
                incomingProcesses.add(toDoJobs.poll());
            }


            if (readyProcesses.peek()==null && incomingProcesses.peek()!=null) {

                currentRunningProcess = incomingProcesses.poll();


                currentRunningProcess.setStartTime(curentTime);


                readyProcesses.add(currentRunningProcess);

            }
            else if (readyProcesses.peek()!=null && incomingProcesses.peek()!=null) {


                if (readyProcesses.peek().getRemainingServiceTime() > incomingProcesses.peek().getRemainingServiceTime()) {
                    currentRunningProcess = readyProcesses.poll();
                    Process process = incomingProcesses.peek();

                    //set Start Time
                    if (process.getStartTime() == 0) {
                        process.setStartTime(curentTime);
                    }


                    readyProcesses.add(incomingProcesses.poll());
                    incomingProcesses.add(currentRunningProcess);
                }
            }

            curentTime++;
        }

        //uncoment volgende voor dropbox
        return doneProcesses;
    }
}


