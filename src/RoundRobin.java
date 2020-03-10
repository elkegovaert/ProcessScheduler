import java.util.*;

public class RoundRobin {
    int q;
    public RoundRobin(int q) { this.q = q; }

    public PriorityQueue<Process> schedule(List<Process> procs) throws NullPointerException {
        Queue<Process> processen = new LinkedList<>();
        List<Process> resultaat = new ArrayList<Process>();
        for (Process p : procs) {
            processen.add(new Process(p));
        }

        Queue<Process> queue = new LinkedList<>();
        int timer = 0;
        Process tmp;

        while (!processen.isEmpty() || !queue.isEmpty()) {
            if (!processen.isEmpty() && queue.isEmpty()) {
                tmp = processen.poll();
                timer = tmp.getArrivalTime();
                tmp.setStartTime(timer);
            } else {
                tmp = queue.poll();
            }

            if (tmp.getServiceTime() <= q) {
                timer = timer + tmp.getServiceTime();
                while (!processen.isEmpty() && processen.peek().getArrivalTime() <= timer) {
                    Process tmp2 = processen.poll();
                    tmp2.setStartTime(timer);
                    queue.add(tmp2);
                }
                tmp.setEndTime(timer);
                tmp.setTAT(tmp.getEndTime()-tmp.getArrivalTime());
                tmp.setNormTAT((double)tmp.getTAT()/tmp.getServiceTime());
                resultaat.add(tmp);
            }
        }


            return null;
    }

}
