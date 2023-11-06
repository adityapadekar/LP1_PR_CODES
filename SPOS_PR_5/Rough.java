import java.util.Scanner;

public class Rough {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the number of Processes : ");
        int noProcess = input.nextInt();

        int[] processNumber = new int[noProcess];
        int[] arrivalTime = new int[noProcess];
        int[] burstTime = new int[noProcess];
        int[] waitTime = new int[noProcess];
        int[] turnAroundTime = new int[noProcess];
        waitTime[0] = 0;

        float avgWaitTime = 0, avgTurnAroundTime = 0;
        System.out.println("Enter the burst time and priority for the processes");
        for (int i = 0; i < noProcess; i++) {
            System.out.print("Burst time of Process " + (i + 1) + " : ");
            burstTime[i] = input.nextInt();
            System.out.print("Arrival time of Process " + (i + 1) + " : ");
            arrivalTime[i] = input.nextInt();
            processNumber[i] = i + 1;
        }

        int[] remainingTimes = burstTime.clone();
        int completedTask = 0;
        int counter = 0;

        while (completedTask < noProcess) {
            int minRemainingTime = Integer.MAX_VALUE;
            int idx = -1;
            for (int i = 0; i < noProcess; i++) {
                if (arrivalTime[i] <= counter && remainingTimes[i] < minRemainingTime && remainingTimes[i] > 0) {
                    minRemainingTime = remainingTimes[i];
                    idx = i;
                }
            }

            counter++;

            if (idx == -1)
                continue;

            remainingTimes[idx]--;

            if (remainingTimes[idx] == 0) {
                waitTime[idx] = counter - burstTime[idx];
                completedTask++;
                avgWaitTime += waitTime[idx];
            }

        }

        for (int i = 0; i < noProcess; i++) {
            turnAroundTime[i] = waitTime[i] + burstTime[i];
            avgTurnAroundTime += turnAroundTime[i];
        }

        avgWaitTime /= noProcess;
        avgTurnAroundTime /= noProcess;

        // Print process details, average wait time, and average turnaround time
        for (int i = 0; i < noProcess; i++) {
            System.out.println(processNumber[i] + "\t" + burstTime[i] + "\t" + turnAroundTime[i]);
        }
        // Print average wait time and turnaround time
        System.out.println("Average wait time is : " + avgWaitTime);
        System.out.println("Average turn around time is : " + avgTurnAroundTime);

        input.close();
        input.close();
    }
}
