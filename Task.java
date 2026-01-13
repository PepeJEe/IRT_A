public class Task {
    private int taskSize = 5;
    private double taskCycles = 10; // 10 cycles (this * task rate = how many tasks can be processed in s)

    public Task() {

    }

    public int getTaskSize() {
        return this.taskSize;
    }

    public double getCyclesRequired() {
        return taskCycles;
    }
}
