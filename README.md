# IRT_A
[![Ask DeepWiki](https://devin.ai/assets/askdeepwiki.png)](https://deepwiki.com/PepeJEe/IRT_A)

This repository contains a Java-based simulation for the Tohoku University Individual Research Training A project. The simulation models a system where tasks are offloaded from a satellite to one of several ground-based data centers. The primary goal is to allocate tasks to the optimal data center to minimize total delay, considering the impact of local weather conditions on data transmission.

## How It Works

The simulation runs for a period of 168 hours (one week). During this time:
1.  **Task Generation**: A random number of tasks (between 5 and 20) are generated every second. Each task requires a specific size for transmission and a number of CPU cycles for processing.
2.  **Weather Simulation**: Each of the three data centers is assigned a random weather condition (`CLEAR`, `CLOUDY`, or `RAINY`) that is updated hourly.
3.  **Task Allocation**: For each task, the `TaskAllocator` calculates the expected total delay for sending it to every available data center. The total delay is the sum of transmission time and processing time.
    *   **Transmission Time**: Calculated by the `Link` class. It is affected by weather, specifically cloud coverage. Worse weather leads to a lower effective data rate and a higher transmission time.
    *   **Processing Time**: Calculated by the `DataCenter` class. It depends on the data center's CPU power and its current processing queue (remaining cycles from previously assigned tasks).
4.  **Optimal Allocation**: The task is assigned to the data center that offers the minimum total delay.
5.  **Metric Collection**: The simulation tracks the completion time for each task, the total energy consumed by the data centers, and statistics on how tasks are distributed based on weather conditions.
6.  **Results**: After the simulation completes, it prints a summary of the results, including average task completion times and energy consumption.

## Core Components

*   `Simulation.java`: The main entry point for the application. It initializes the data centers, runs the simulation loop for 168 hours, and prints the final results.
*   `TaskAllocator.java`: The core logic for the allocation strategy. It evaluates the delay for each data center and selects the one with the best performance for a given task.
*   `DataCenter.java`: Represents a ground-based data processing facility. It manages a task queue (in terms of CPU cycles), calculates processing times, and models energy consumption based on its CPU utilization.
*   `Task.java`: A simple class representing a unit of work, defined by its size (for transmission) and required CPU cycles (for processing).
*   `Link.java`: Models the communication link from the satellite to a data center. Its primary function is to calculate the transmission time, which is heavily influenced by weather.
*   `Weather.java`: An `enum` that defines the three possible weather states: `CLEAR`, `CLOUDY`, and `RAINY`. Each state has predefined parameters for cloud coverage that affect link performance.
*   `Satellite.java`: A placeholder class, not currently used in the simulation logic.

## How to Run

This is a standard Java project with no external dependencies. You can compile and run it from the command line.

1.  Navigate to the root directory of the project.
2.  Compile all the Java source files:
    ```sh
    javac *.java
    ```
3.  Run the simulation:
    ```sh
    java Simulation
    ```

## Output

The simulation will print the results to the console upon completion. The output includes:

*   **Average Task Completion Time**: The overall average delay for all tasks processed during the simulation, along with the minimum and maximum recorded delays.
*   **Task Distribution by Weather**: The percentage of tasks that were allocated to data centers experiencing `CLEAR`, `CLOUDY`, or `RAINY` weather.
*   **Average Task Completion Time by Weather**: A breakdown of the average task delay for each weather condition.
*   **Average Hourly Energy Consumption**: The average energy consumed per hour across all data centers, reported in Megajoules (MJ).
