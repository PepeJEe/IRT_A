import java.util.ArrayList;
import java.util.Collections;
import java.util.zip.DataFormatException;

public class TaskAllocator {
    private ArrayList<DataCenter> dataCenters;
    private Link link;
    double bestDelay;
    double totalDelay = 0;
    double clear_i, rainy_i, cloudy_i;
    double clear_t, rainy_t, cloudy_t;
    double min, max;
    public double hourlyEnergy = 0;
    ArrayList<Double> numbers = new ArrayList<Double>();
    private DataCenter BestDcThisHour;

    public TaskAllocator(ArrayList<DataCenter> dataCenters) {
        this.dataCenters = dataCenters;
        link = new Link();
    }

    // allocate tasks and figure out the best datacenter based on weather
    public void allocate(Task task) {
        bestDelay = Double.MAX_VALUE;
        DataCenter BestDc = null;

        for (int i = 0; i < dataCenters.size(); i++) {
            DataCenter dc = dataCenters.get(i);
            double transmissionTime = link.GetTransmissionTime(dc.getWeather());
            double processingTime = dc.GetProcessingTime(task);
            double initialDelay = transmissionTime + processingTime;
            if (initialDelay < bestDelay) {
                bestDelay = initialDelay;
                BestDc = dc;
            }
        }
        numbers.add(bestDelay);

        BestDc.assignTask(task);
        totalDelay += bestDelay;

        hourlyEnergy = BestDc.computeHourlyEnergy();

        // Get information for each weather
        switch (BestDc.getWeather()) {
            case CLOUDY:
                cloudy_i++;
                cloudy_t += bestDelay;
                break;
            case CLEAR:
                clear_i++;
                clear_t += bestDelay;
                break;
            case RAINY:
                rainy_i++;
                rainy_t += bestDelay;
                break;
            default:
                break;
        }
        BestDcThisHour = BestDc;
    }

    public DataCenter getBestDcThisHour() {
        return BestDcThisHour;
    }

    public double getHourlyEnergy() {
        return hourlyEnergy;
    }

    public double getTotalTaskDelay() {
        return totalDelay;
    }

    public double getCloudyI() {
        return cloudy_i;
    }

    public double getRainyI() {
        return rainy_i;
    }

    public double getClearI() {
        return clear_i;
    }

    public double getCloudyT() {
        return cloudy_t;
    }

    public double getRainyT() {
        return rainy_t;
    }

    public double getClearT() {
        return clear_t;
    }

    public double getMin() {
        min = Collections.min(numbers);
        return min;
    }

    public double getMax() {
        max = Collections.max(numbers);
        return max;
    }

}
