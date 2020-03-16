import sun.awt.image.ImageWatched;

import java.util.*;

public class MultiLevelFeedBack {
    int timeslice;
    public MultiLevelFeedBack() {}

    public List<Process> schedule(List<Process> input, String timeslice) throws NullPointerException {
        int timeslice1, timeslice2, timeslice3, timeslice4, timeslice5;
        if (timeslice.equals("lineair")) {
            timeslice1 = 1;
            timeslice2 = 2;
            timeslice3 = 3;
            timeslice4 = 4;
            timeslice5 = 5;
        } else {
            timeslice1 = 1;
            timeslice2 = 2;
            timeslice3 = 4;
            timeslice4 = 8;
            timeslice5 = 16;
        }

        Queue<Process> inputQ = new LinkedList<>();
        for (Process p : input) {
            inputQ.add(new Process(p));
        }

        Queue<Process> q1 = new LinkedList<>();
        Queue<Process> q2 = new LinkedList<>();
        Queue<Process> q3 = new LinkedList<>();
        Queue<Process> q4 = new LinkedList<>();
        Queue<Process> q5 = new LinkedList<>();
        List<Process> resultaat = new ArrayList<>();

        int timer = 0;
        Process tmp = new Process();

        while(!inputQ.isEmpty() || !q1.isEmpty() || !q2.isEmpty() ||!q3.isEmpty() || !q4.isEmpty() || !q5.isEmpty()) {
            if (!inputQ.isEmpty() && q1.isEmpty() && q2.isEmpty() && q3.isEmpty() && q4.isEmpty() && q5.isEmpty()) {
                tmp = inputQ.poll();
                timer = tmp.getArrivalTime();
                q1.add(tmp);
            }
            while (!inputQ.isEmpty() && inputQ.peek().getArrivalTime() <= timer) {
                tmp = inputQ.poll();
                tmp.setStartTime(timer);
                q1.add(tmp);
            }
            if (!q1.isEmpty()) {
                tmp = q1.poll();
                if (tmp.getServiceTime() <= timeslice1) {
                    timer = timer + timeslice1;
                    tmp.setEndTime(timer);
                    tmp.calculateStats();
                    resultaat.add(tmp);
                } else {
                    timer = timer + timeslice1;
                    tmp.setRemainingServiceTime(tmp.getRemainingServiceTime()-timeslice1);
                    q2.add(tmp);
                }
            } else if (!q2.isEmpty()) {
                tmp = q2.poll();
                if (tmp.getServiceTime() <= timeslice2) {
                    timer = timer + timeslice2;
                    tmp.setEndTime(timer);
                    tmp.calculateStats();
                    resultaat.add(tmp);
                } else {
                    timer = timer + timeslice2;
                    tmp.setRemainingServiceTime(tmp.getRemainingServiceTime()-timeslice2);
                    q3.add(tmp);
                }
            } else if (!q3.isEmpty()) {
                tmp = q3.poll();
                if (tmp.getServiceTime() <= timeslice3) {
                    timer = timer + timeslice3;
                    tmp.setEndTime(timer);
                    tmp.calculateStats();
                    resultaat.add(tmp);
                } else {
                    timer = timer + timeslice3;
                    tmp.setRemainingServiceTime(tmp.getRemainingServiceTime()-timeslice3);
                    q4.add(tmp);
                }
            } else if (!q4.isEmpty()) {
                tmp = q4.poll();
                if (tmp.getServiceTime() <= timeslice4) {
                    timer = timer + timeslice4;
                    tmp.setEndTime(timer);
                    tmp.calculateStats();
                    resultaat.add(tmp);
                } else {
                    timer = timer + timeslice4;
                    tmp.setRemainingServiceTime(tmp.getRemainingServiceTime()-timeslice4);
                    q5.add(tmp);
                }
            } else if (!q5.isEmpty()) {
                tmp = q5.poll();
                if (tmp.getServiceTime() <= timeslice5) {
                    timer = timer + timeslice5;
                    tmp.setEndTime(timer);
                    tmp.calculateStats();
                    resultaat.add(tmp);
                } else {
                    timer = timer + timeslice5;
                    tmp.setRemainingServiceTime(tmp.getRemainingServiceTime()-timeslice5);
                    q5.add(tmp);
                }
            }
        }

        return resultaat;
    }

}
