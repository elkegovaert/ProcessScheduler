import java.util.*;

public class ShortestJobFirst{

    private static int totWaitTime = 0;
    private static int totTAT = 0;
    private static double totNormTAT = 0;

    public ShortestJobFirst() {
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
        PriorityQueue<Process> readyJobs = new PriorityQueue<Process>(10,(p1, p2)->p1.getServiceTime()-p2.getServiceTime());

        Process process;

        int currentTime = 0;

        while(doneJobs.size()!=processList.size()){

            //check of er processen zijn die aan de wachtrij mogen worden toegevoegd
            while(toDoJobs.size() != 0 && toDoJobs.peek().getArrivalTime()<=currentTime){
                readyJobs.add(toDoJobs.poll());
            }
                

            //processen uitvoeren als er zijn
            if (!readyJobs.isEmpty()) {

                process=readyJobs.poll();

                process.setStartTime(currentTime);
                currentTime =currentTime + process.getServiceTime();
                process.setEndTime(currentTime);

                process.calculateStats();

                doneJobs.add(process);

                //Globale Stats Aanpassen
                totWaitTime = totWaitTime + process.getWaitTime();
                totTAT = totTAT + process.getTAT();
                totNormTAT = totNormTAT + process.getNormTAT();

            }else {
                currentTime++;
            }

        }

        //Globale stats uitprinten van SJF
        int size = processList.size();
        System.out.println("----------Shortest Job First  for "+size+" processes"+"----------");
        System.out.println("Average Wait Time: "+totWaitTime/size);
        System.out.println("Average TAT: "+totTAT/size);
        System.out.println("Average Normalized TAT: "+totNormTAT/size);


        return doneJobs;
    }
}
