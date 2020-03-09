import java.util.Comparator;

public class SortByServiceTime implements Comparator<Process> {
    public int compare(Process p1, Process p2) {
        return p1.getServiceTime() - p2.getServiceTime();
    }
}

