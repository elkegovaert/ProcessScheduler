import java.util.*;

public class ShortestJobFirst{

    public ShortestJobFirst() {
    }

    public List<Process> SJF(List<Process> processList) {

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
                

            //process uitvoeren als er één klaar is om uitgevoerd te worden, vervolgens de count aanpassen
            if (!readyJobs.isEmpty()) {
                //Uit te voeren process uit de wachtrij halen
                process=readyJobs.poll();

                //Parmeters instellen
                process.setStartTime(currentTime);
                currentTime =currentTime + process.getServiceTime();
                process.setEndTime(currentTime);

                process.calculateStats();

                doneJobs.add(process);

                /*
                //globale parameters updaten
                waittime += temp.getWaitTime();
                normtat += temp.getNormTAT();
                tat += temp.getTAT();

                 */
            }else {
                currentTime++;
            }

        }

        /*
        waittime = waittime / processList.size();
        normtat = normtat / processList.size();
        tat = tat / processList.size();

        StringBuffer sb = new StringBuffer();

        sb.append("Glob parameters SJF ");
        sb.append(waittime + " " + normtat + " " + tat + " ");

        System.out.println(sb.toString());
        */

        return doneJobs;
    }
}
