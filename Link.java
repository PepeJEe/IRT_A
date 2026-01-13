public class Link {
    private double hopDelay = 0.001; // s
    private double maxCapacity = 1000; // Mbps
    private int satAltitude = 550000; // m
    private int speedOfLight = 300000000; // m/s
    double t_delay;

    public double GetTransmissionTime(Weather weather) {
        Task t = new Task();
        double weatherFactor = 1 - weather.GetCloudCoverage();
        this.t_delay = t.getTaskSize() / (maxCapacity * weatherFactor);
        double t_prop = satAltitude / speedOfLight;
        double t_hop = hopDelay * 1; // num of hop = 1
        double transmissionTime = t_delay + t_hop + t_prop;
        return transmissionTime;
    }

    public double GetTransmissionDelay() {
        return t_delay;
    }

}
