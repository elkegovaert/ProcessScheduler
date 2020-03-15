import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Abstract class Scheduler. Contains three methods that expanding classes have to implement.
 * @author pellereyniers and wouterlegist
 */
public abstract class Scheduler {

    double tat;
    double normtat;
    double waittime;

    /**
     * Schedueling method based on one parameter.
     * @param q Que of input parameters.
     * @return Que of finished processes.
     */
    public abstract List<Process> schedule(List<Process> q);
}