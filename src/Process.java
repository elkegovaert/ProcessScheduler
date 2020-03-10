public class Process {
    private int id;
    private int arrivalTime;
    private int serviceTime;
    private int startTime;
    private int endTime;
    private int TAT;//serviceTime+waitTime
    private double normTAT;//(serviceTime+waitTime)/serviceTime
    private int waitTime;

    //constructors
    public Process() {}

    public Process(int id, int arrivalTime, int serviceTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;

    }

    public int compareTo(Object o) {
        Process p = (Process) o;
        return this.serviceTime < p.serviceTime ? -1 : 1;
    }
    
    //Override toString
    public String toString() {
    	return "id: "+id+" ArrivelTime: "+arrivalTime+" ServiceTime: "+serviceTime+" StartTime: "+startTime+" EndTime: "+endTime+" TAT: "+TAT+" Genormaliseerde TAT: "+normTAT+" WaitTime: "+waitTime;
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
}
