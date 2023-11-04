import java.util.Scanner;

public class FCFS {
    /**
     * This method calculates average wait time and average turn around time
     * for a given set of process burst times.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        int noProcess;
        float avgWait = 0, avgTurnAroundTime = 0;
        Scanner input = new Scanner(System.in);

        // Get total number of processes
        System.out.print("Enter total number of processes : ");
        noProcess = input.nextInt();

        // Initialize arrays for burst times, wait times, and turnaround times
        int[] burstTime = new int[50];
        int[] turnAroundTime = new int[50];
        int[] waitTime = new int[50];
        waitTime[0] = 0;

        // Get burst times for each process
        System.out.println("Enter the burst time for each process");
        for (int i = 0; i < noProcess; i++) {
            System.out.print("for P" + (i + 1) + " : ");
            burstTime[i] = input.nextInt();
        }

        // Calculate wait times
        for (int i = 1; i < noProcess; i++) {
            waitTime[i] = waitTime[i - 1] + burstTime[i - 1];
            avgWait += waitTime[i];
        }
        avgWait /= noProcess;

        // Calculate turnaround times
        for (int i = 0; i < noProcess; i++) {
            turnAroundTime[i] = waitTime[i] + burstTime[i];
            avgTurnAroundTime += turnAroundTime[i];
        }
        avgTurnAroundTime /= noProcess;

        // Print average wait time and turnaround time
        System.out.println("Average wait time is : " + avgWait);
        System.out.println("Average turn around time is : " + avgTurnAroundTime);

        input.close();
    }
}
