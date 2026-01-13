public class DataCenter {

    public float energy;
    public float cpuPower = 1000; // rndm number
    public double remainingCycles = 0;
    public double processingTime;
    public Weather weather;
    public int id;
    public int distance = 300;
    double idlePower = 4000;
    double maxPower = 10000;
    double hourlyEnergy;
    double processedCyclesThisHour = 0;

    public DataCenter(int id) {
        this.weather = Weather.GetRandomWeather();
        this.id = id;
    }

    public void updateWeather() {
        this.weather = Weather.GetRandomWeather();
    }

    public Weather getWeather() {
        return this.weather;
    }

    public void assignTask(Task t) {
        remainingCycles += t.getCyclesRequired();
    }

    public double computeHourlyEnergy() {
        double utilization = processedCyclesThisHour / (cpuPower * 3600);
        utilization = Math.min(1.0, utilization);
        double power = idlePower + (maxPower - idlePower) * utilization;
        double energy = power * 3600; // Joules
        processedCyclesThisHour = 0; // reset for next hour

        return energy;
    }

    /*
     * public void updateCyclesInHour() {
     * remainingCycles -= cpuPower * 3600;
     * if (remainingCycles < 0) {
     * remainingCycles = 0;
     * }
     * }
     */

    // Calculate processingtime
    public double GetProcessingTime(Task t) {
        double processingDelay = t.getCyclesRequired() / cpuPower;
        double queueDelay = remainingCycles / cpuPower;
        return processingDelay + queueDelay;
    }

    public void updateCyclesInSec() {
        double processed = Math.min(cpuPower, remainingCycles);
        remainingCycles -= processed;
        processedCyclesThisHour += processed;
    }
}
