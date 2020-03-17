import java.util.*;

public class RoundRobin {
    int q;
    public RoundRobin(int q) { this.q = q; }

    public List<Process> schedule(List<Process> procs) throws NullPointerException {
        //kopietje om gegevens niet te wijzigen
        Queue<Process> processen = new LinkedList<>();

        //resultaat dat we terug geven
        List<Process> resultaat = new ArrayList<Process>();

        for (Process p : procs) {
            processen.add(new Process(p));
        }

        //queue waarin processen wachten als ze al toegekomen zijn maar nog niet bezig zijn
        Queue<Process> queue = new LinkedList<>();
        int timer = 0;
        Process tmp;

        while (resultaat.size() != procs.size()) {
            // als er niks in de queue zit, maar wel nog processen bijkomen
            if (!processen.isEmpty() && queue.isEmpty()) {
                tmp = processen.poll();
                timer = tmp.getArrivalTime();
                tmp.setStartTime(timer);
                tmp.setRemainingServiceTime(tmp.getServiceTime());
            } else {
                tmp = queue.poll();
            }

            //als we het in 1 keer kunnen doen
            if (tmp.getRemainingServiceTime() <= q) {
                timer = timer + tmp.getRemainingServiceTime();
                //voor de processen die toekomen terwijl het huidige proces draait
                while (!processen.isEmpty() && processen.peek().getArrivalTime() <= timer) {
                    Process tmp2 = processen.poll();
                    tmp2.setStartTime(timer);
                    queue.add(tmp2);
                }
                tmp.setEndTime(timer);
                tmp.setWaitTime((tmp.getEndTime() - tmp.getArrivalTime()) - tmp.getServiceTime());
                tmp.setTAT(tmp.getEndTime()-tmp.getArrivalTime());
                tmp.setNormTAT((double)tmp.getTAT()/tmp.getServiceTime());
                resultaat.add(tmp);

            } else {
                timer = timer + q;
                while (!processen.isEmpty() && processen.peek().getArrivalTime() <= timer) {
                    Process p = processen.poll();
                    p.setStartTime(timer);
                    queue.add(p);
                }
                tmp.setRemainingServiceTime(tmp.getRemainingServiceTime() - q);
                queue.add(tmp);
            }
        }

        return resultaat;
    }

}
