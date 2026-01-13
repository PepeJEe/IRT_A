import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class Simulation {
    public static void main(String[] args) {

        ArrayList<DataCenter> dataCenters = new ArrayList<DataCenter>();
        ArrayList<Task> totalTasks = new ArrayList<Task>();
        double totalTaskDelay = 0, totalEnergy = 0, count = 0;
        DecimalFormat df = new DecimalFormat("##.##");
        DecimalFormat df2 = new DecimalFormat("##.####");

        DataCenter dc1 = new DataCenter(1);
        DataCenter dc2 = new DataCenter(2);
        DataCenter dc3 = new DataCenter(3);

        TaskAllocator allocator = new TaskAllocator(dataCenters);

        // add datacenters into array
        dataCenters.add(dc1);
        dataCenters.add(dc2);
        dataCenters.add(dc3);

        for (int i = 0; i < 168; i++) {

            // update the weather hourly for each datacenter randomly
            for (int j = 0; j < dataCenters.size(); j++) {
                dataCenters.get(j).updateWeather();
            }

            for (int j = 0; j < 3600; j++) {
                // Have more realistic amount of tasks arriving per sec (random)
                Random r = new Random();
                int tasksThisSec = r.nextInt(5, 20);

                for (int k = 0; k < tasksThisSec; k++) {
                    Task t = new Task();
                    totalTasks.add(t);
                    allocator.allocate(t);
                    totalTaskDelay = allocator.getTotalTaskDelay();
                }

                // After processing cycles remove the processed ones from remaining cycles
                for (DataCenter dc : dataCenters) {
                    dc.updateCyclesInSec();
                }
            }
            for (DataCenter dc : dataCenters) {
                double energy = dc.computeHourlyEnergy();
                System.out.println("energy: " + energy);
                totalEnergy += energy;
                count++;
            }
        }

        double avgDelay = totalTaskDelay / totalTasks.size();
        System.out.println("===== Simulation Results =====");
        System.out.println("\n");
        System.out.println("AVG TASK COMPLETION TIME");
        System.out.println("Average task completion time: " + avgDelay * 100 + " ms, where min and max is: min: "
                + df2.format(allocator.getMin() * 100) + " and max: " + df2.format(allocator.getMax() * 100));
        System.out.println("CLEAR   avg amount: " + df.format(allocator.clear_i / totalTasks.size() * 100) + " %");
        System.out.println("CLOUDY avg amount: " + df.format(allocator.cloudy_i / totalTasks.size() * 100) + " %");
        System.out.println("RAINY  avg amount: " + df.format(allocator.rainy_i / totalTasks.size() * 100) + " %");
        System.out.println("\n");
        System.out.println("AVG TASK COMPLETION TIME PER WEATHER:");
        System.out.println(
                "CLEAR   avg task completion: " + df2.format(allocator.getClearT() / allocator.getClearI() * 100)
                        + " ms");
        System.out.println(
                "CLOUDY avg task completion: " + df2.format(allocator.getCloudyT() / allocator.getCloudyI() * 100)
                        + " ms");
        System.out.println(
                "RAINY  avg task completion: " + df2.format(allocator.getRainyT() / allocator.getRainyI() * 100)
                        + " ms");
        System.out.println("\n");
        System.out.println("AVG HOURLY CONSUMPTION");
        System.out.println(df.format((totalEnergy / count) / 1000000) + " MJ");
    }
}
