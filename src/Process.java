import java.util.List;

public class Process implements Comparable {
    private int id;
    private int arrivalTime;
    private int serviceTime;
    private int remainingServiceTime;
    private int startTime;
    private int endTime;
    private int TAT;//serviceTime+waitTime
    private double normTAT;//(serviceTime+waitTime)/serviceTime
    private int waitTime;
    private double responseRatio; //Response Ratio

    //constructors
    public Process() {
        remainingServiceTime = -1;
        responseRatio = 1;
    }

    public Process(Process p) {
        id = p.id;
        arrivalTime = p.arrivalTime;
        serviceTime = p.serviceTime;
        startTime = p.startTime;
        endTime = p.endTime;
        TAT = p.TAT;
        normTAT = p.normTAT;
        waitTime = p.waitTime;
        remainingServiceTime = p.remainingServiceTime;
        responseRatio = p.responseRatio;
    }

    public Process(int id, int arrivalTime, int serviceTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        remainingServiceTime = serviceTime;
        responseRatio = (waitTime+serviceTime)/serviceTime;
    }

    public int compareTo(Object o) {
        Process p = (Process) o;
        return this.serviceTime < p.serviceTime ? -1 : 1;
    }
    
    //Override toString
    public String toString() {
    	return "id: "+id+" ArrivalTime: "+arrivalTime+" ServiceTime: "+serviceTime+" Remaining Service Time: "+remainingServiceTime+" StartTime: "+startTime+" EndTime: "+endTime+" TAT: "+TAT+" Genormaliseerde TAT: "+normTAT+" WaitTime: "+waitTime+" R: "+responseRatio;
    }

    public void calculateStats() {
        this.waitTime = endTime - arrivalTime - serviceTime;
        this.TAT = waitTime + serviceTime;
        this.normTAT = (double) this.TAT / serviceTime;
    }

    public void calculateResponseRatio() {
        responseRatio = ((double)waitTime+(double)serviceTime)/(double)serviceTime;
    }

    //getters en setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getStartTime() { return startTime; }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() { return endTime; }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getTAT() {
        return TAT;
    }

    public void setTAT(int TAT) {
        this.TAT = TAT;
    }

    public double getNormTAT() {
        return normTAT;
    }

    public void setNormTAT(double normTAT) {
        this.normTAT = normTAT;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public int getRemainingServiceTime() {
        return remainingServiceTime;
    }

    public void setRemainingServiceTime(int remainingServiceTime) {
        this.remainingServiceTime = remainingServiceTime;
    }


    public double getResponseRatio() {
        return responseRatio;
    }

    public void setResponseRatio(double responseRatio) {
        this.responseRatio = responseRatio;
    }

}
