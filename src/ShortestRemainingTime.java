import java.util.*;

public class ShortestRemainingTime {


    public ShortestRemainingTime() {
    }

    public List<Process> SRT(List<Process> processList) {



        //Drop box
/*        Queue<Process> que = new LinkedList<>();

        for (Process p : processList) {
            que.add(new Process(p));
        }


        //Verschillende que's aand de hand van waar het proces zich bevindt
        List<Process> finishedProcesses = new ArrayList<>();
        PriorityQueue<Process> waitingProcesses = new PriorityQueue<Process>(10, (a, b) -> a.getRemainingServiceTime() - b.getRemainingServiceTime());
        PriorityQueue<Process> currentProcess = new PriorityQueue<>();
        Process temp;

        //counter duidt op welk timeslot de processor zich bevindt
        int count = 0;

        //Loop blijft gaan tot alle processen afgerond zijn
        while (finishedProcesses.size() != processList.size()) {

            if (!currentProcess.isEmpty()) {
                //procces stond al van vorige doorgaan op de processor-> service time needed moet eerst verlaagd worden
                temp = currentProcess.peek();
                temp.setRemainingServiceTime(temp.getRemainingServiceTime() - 1);
                if (temp.getRemainingServiceTime() == 0) {

                    temp = currentProcess.poll();

                    //lokale parameter instellen en andere uitrekenen
                    temp.setEndTime(count);
                    temp.calculateStats();

                    finishedProcesses.add(temp);


                }
            }

            //check of er processen zijn die aan de wachtrij mogen worden toegevoegd
            while (que.peek() != null && que.peek().getArrivalTime() <= count)
                waitingProcesses.add(que.poll());

            if (currentProcess.isEmpty() && !waitingProcesses.isEmpty()) {
                //Uit te voeren process uit de wachtrij halen
                temp = waitingProcesses.poll();

                //Parmeters instellen
                temp.setStartTime(count);

                //SupportClasses.Process op de processor zetten
                currentProcess.add(temp);

            } else if (!currentProcess.isEmpty() && !waitingProcesses.isEmpty()) {
                temp = currentProcess.peek();

                //wanneer er processen wachten met lagere servicetime needed -> switchen
                if (temp.getRemainingServiceTime() > waitingProcesses.peek().getRemainingServiceTime()) {
                    temp = currentProcess.poll();
                    Process p = waitingProcesses.peek();
                    if (p.getStartTime() == 0)
                        p.setStartTime(count);

                    currentProcess.add(waitingProcesses.poll());
                    waitingProcesses.add(temp);
                }
            }

            count++;
*/










        //hulp que
        Queue<Process> toDoJobs = new LinkedList<>();

        for (int i=0; i<processList.size(); i++) {
            toDoJobs.add(new Process(processList.get(i)));
        }


        List<Process> doneJobs = new ArrayList<>();
        PriorityQueue<Process> incomingJobs = new PriorityQueue<Process>(100,(p1, p2)->p1.getRemainingServiceTime()-p2.getRemainingServiceTime());
        PriorityQueue<Process> readyJobs = new PriorityQueue<Process>(100,(p1, p2)->p1.getRemainingServiceTime()-p2.getRemainingServiceTime());


        Process incomingProcess;
        Process currentRunningProcess = new Process();
        boolean processIsRunning = false;

        int currentTime = 0;


        while(doneJobs.size()!=processList.size()){

            if(currentTime<1000){
                System.out.println(currentTime);
                System.out.println(currentRunningProcess);
            }

            //check for an incoming job/process
            while(toDoJobs.peek()!=null&&toDoJobs.peek().getArrivalTime()==currentTime){

                incomingProcess = toDoJobs.poll();

                incomingJobs.add(incomingProcess);
            }


            if(readyJobs.peek()==null&&incomingJobs.peek()!=null){
                currentRunningProcess = incomingJobs.poll();
                int remainingTime = currentRunningProcess.getRemainingServiceTime() - 1;
                currentRunningProcess.setRemainingServiceTime(remainingTime);

                readyJobs.add(currentRunningProcess);

            }
            else if(readyJobs.peek()!=null&&incomingJobs.peek()==null){
                currentRunningProcess = readyJobs.poll();
                int remainingTime = currentRunningProcess.getRemainingServiceTime() - 1;
                currentRunningProcess.setRemainingServiceTime(remainingTime);
            }
            else if(readyJobs.peek()!=null&&incomingJobs.peek()!=null){
                if(readyJobs.peek().getRemainingServiceTime()<=incomingJobs.peek().getRemainingServiceTime()){
                    currentRunningProcess = readyJobs.poll();
                    int remainingTime = currentRunningProcess.getRemainingServiceTime() - 1;
                    currentRunningProcess.setRemainingServiceTime(remainingTime);
                    readyJobs.add(incomingJobs.poll());
                }
                else {
                    currentRunningProcess = incomingJobs.poll();
                    int remainingTime = currentRunningProcess.getRemainingServiceTime() - 1;
                    currentRunningProcess.setRemainingServiceTime(remainingTime);
                }
            }
            if(readyJobs.peek()!=null||incomingJobs.peek()!=null){
                //check if process is done after this time slice


                if(currentRunningProcess.getRemainingServiceTime()==0){

                    currentRunningProcess.setEndTime(currentTime+1);
                    currentRunningProcess.calculateStats();


                    doneJobs.add(currentRunningProcess);

                }
                else if(currentRunningProcess.getRemainingServiceTime()>0){

                    readyJobs.add(currentRunningProcess);
                }
            }


            currentTime++;


        }

        return doneJobs;


        }

        //uncoment volgende voor dropbox
        //return finishedProcesses;
   // }
}


