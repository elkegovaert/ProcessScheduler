import sun.awt.image.ImageWatched;

import java.util.*;

public class MultiLevelFeedBack {
    private static int totWaitTime = 0;
    private static int totTAT = 0;
    private static double totNormTAT = 0;
    public MultiLevelFeedBack() {}

    public void update(Process p) {
        totWaitTime = totWaitTime + p.getWaitTime();
        totTAT = totTAT + p.getTAT();
        totNormTAT = totNormTAT + p.getNormTAT();

    }

    public List<Process> schedule(List<Process> input, String timeslice) throws NullPointerException {
        int timeslice1, timeslice2, timeslice3, timeslice4, timeslice5;
        if (timeslice.equals("constant")) {
            timeslice1 = 10;
            timeslice2 = 10;
            timeslice3 = 10;
            timeslice4 = 10;
            timeslice5 = 10;
        } else {//10,20,40,80,160
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

            if (!q1.isEmpty()) {
                tmp = q1.poll();
                // mag enkel de 1e keer gezet worden
                if (tmp.getServiceTime() == tmp.getRemainingServiceTime()) {
                    tmp.setStartTime(timer);
                }
                if (tmp.getRemainingServiceTime() <= timeslice1) {
                    timer = timer + tmp.getRemainingServiceTime();
                    tmp.setEndTime(timer);
                    tmp.calculateStats();
                    update(tmp);
                    resultaat.add(tmp);
                } else {
                    timer = timer + timeslice1;
                    tmp.setRemainingServiceTime(tmp.getRemainingServiceTime()-timeslice1);
                    q2.add(tmp);
                }
            } else if (!q2.isEmpty()) {
                tmp = q2.poll();
                if (tmp.getRemainingServiceTime() <= timeslice2) {
                    timer = timer + tmp.getRemainingServiceTime();
                    tmp.setEndTime(timer);
                    tmp.calculateStats();
                    update(tmp);
                    resultaat.add(tmp);
                } else {
                    timer = timer + timeslice2;
                    tmp.setRemainingServiceTime(tmp.getRemainingServiceTime()-timeslice2);
                    q3.add(tmp);
                }
            } else if (!q3.isEmpty()) {
                tmp = q3.poll();
                if (tmp.getRemainingServiceTime() <= timeslice3) {
                    timer = timer + tmp.getRemainingServiceTime();
                    tmp.setEndTime(timer);
                    tmp.calculateStats();
                    update(tmp);
                    resultaat.add(tmp);
                } else {
                    timer = timer + timeslice3;
                    tmp.setRemainingServiceTime(tmp.getRemainingServiceTime()-timeslice3);
                    q4.add(tmp);
                }
            } else if (!q4.isEmpty()) {
                tmp = q4.poll();
                if (tmp.getRemainingServiceTime() <= timeslice4) {
                    timer = timer + tmp.getRemainingServiceTime();
                    tmp.setEndTime(timer);
                    tmp.calculateStats();
                    update(tmp);
                    resultaat.add(tmp);
                } else {
                    timer = timer + timeslice4;
                    tmp.setRemainingServiceTime(tmp.getRemainingServiceTime()-timeslice4);
                    q5.add(tmp);
                }
            } else if (!q5.isEmpty()) {
                tmp = q5.poll();
                if (tmp.getRemainingServiceTime() <= timeslice5) {
                    timer = timer + tmp.getRemainingServiceTime();
                    tmp.setEndTime(timer);
                    tmp.calculateStats();
                    update(tmp);
                    resultaat.add(tmp);
                } else {
                    timer = timer + timeslice5;
                    tmp.setRemainingServiceTime(tmp.getRemainingServiceTime()-timeslice5);
                    q5.add(tmp);
                }
            }

            while (!inputQ.isEmpty() && inputQ.peek().getArrivalTime() <= timer) {
                tmp = inputQ.poll();
                q1.add(tmp);
            }
        }

        //Globale stats uitprinten van MLFB
        int size = input.size();
        System.out.println("----------Multilevel Feedback " + timeslice + " for "+size+" processes----------");
        System.out.println("Average Wait Time: "+totWaitTime/size);
        System.out.println("Average TAT: "+totTAT/size);
        System.out.println("Average Normalized TAT: "+totNormTAT/size);

        return resultaat;
    }

}
