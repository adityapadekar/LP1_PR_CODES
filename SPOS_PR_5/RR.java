import java.util.Scanner;

public class RR {
    /**
     * This method implements the Round Robin Scheduling algorithm
     * 
     * @param args
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int noProcess;
        int quantum;

        // Get the number of processes and quantum time from user
        System.out.print("Enter the number of processes : ");
        noProcess = input.nextInt();
        System.out.print("Enter the quantum tume for the processes : ");
        quantum = input.nextInt();

        // Initialize arrays and variables
        int[] burstTime = new int[50];
        int[] waitTime = new int[50];
        int[] turnAroundTime = new int[50];
        waitTime[0] = 0;
        float avgWaitTime = 0;
        float avgTurnAroundTime = 0;
        int totalTime = 0;

        // Get burst time of each process from user
        System.out.println("Enter the Burst time of the process");
        for (int i = 0; i < noProcess; i++) {
            System.out.print("For process P" + (i + 1) + " : ");
            burstTime[i] = input.nextInt();
        }

        // Calculate remaining burst time
        int[] remainingBurstTime = burstTime.clone();
        while (true) {
            boolean flag = true;
            for (int i = 0; i < noProcess; i++) {
                if (remainingBurstTime[i] > 0) {
                    flag = false;
                    if (remainingBurstTime[i] > quantum) {
                        totalTime += quantum;
                        remainingBurstTime[i] -= quantum;
                    } else {
                        totalTime += remainingBurstTime[i];
                        waitTime[i] = totalTime - burstTime[i];
                        avgWaitTime += waitTime[i];
                        remainingBurstTime[i] = 0;
                    }
                }
            }
            if (flag)
                break;
        }

        // Calculate turnaround time and average turnaround time
        for (int i = 0; i < noProcess; i++) {
            turnAroundTime[i] = waitTime[i] + burstTime[i];
            avgTurnAroundTime += turnAroundTime[i];
        }
        avgTurnAroundTime /= noProcess;
        avgWaitTime /= noProcess;

        // Print the results
        for (int i = 0; i < noProcess; i++) {
            System.out.println(burstTime[i] + "\t" + waitTime[i] + "\t" + turnAroundTime[i]);
        }
        System.out.println("Average Wait Time : " + avgWaitTime);
        System.out.println("Average Turn Around Time : " + avgTurnAroundTime);

        input.close();
    }
}
