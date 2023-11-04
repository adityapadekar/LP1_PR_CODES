import java.util.Scanner;

public class SJF {

    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);

        int noProcess;
        System.out.print("Enter the total number of processes : ");
        noProcess = input.nextInt();

        int[] burstTimes = new int[noProcess];
        int[] arrivalTimes = new int[noProcess];
        // int[] arrivalTimes = { 0, 1, 2, 3 };
        // int[] burstTimes = { 6, 8, 7, 3 };
        int[] waitTime = new int[noProcess];
        int[] turnAroundTime = new int[noProcess];

        waitTime[0] = 0;

        float avgWaitTime = 0;
        float avgTurnAroundTime = 0;

        System.out.println("Enter the Burst Time and The Arrival Time for each process");
        for (int i = 0; i < noProcess; i++) {
            System.out.print("Burst Time for process " + (i + 1) + " : ");
            burstTimes[i] = input.nextInt();
            System.out.print("Arrival time for Process " + (i + 1) + " : ");
            arrivalTimes[i] = input.nextInt();
        }

        int[] remainingTimes = burstTimes.clone();

        int counter = 0;
        int completedTasks = 0;

        while (completedTasks < noProcess) {
            int minRemainingTime = Integer.MAX_VALUE;
            int taskIndex = -1;

            for (int i = 0; i < noProcess; i++) {
                if (arrivalTimes[i] <= counter && remainingTimes[i] < minRemainingTime && remainingTimes[i] > 0) {
                    minRemainingTime = remainingTimes[i];
                    taskIndex = i;
                }
            }
            counter++;

            if (taskIndex == -1) {
                continue;
            }

            remainingTimes[taskIndex]--;

            if (remainingTimes[taskIndex] == 0) {
                waitTime[taskIndex] = counter - burstTimes[taskIndex];
                avgWaitTime += waitTime[taskIndex];
                completedTasks++;
            }
        }

        for (int i = 0; i < noProcess; i++) {
            turnAroundTime[i] = waitTime[i] + burstTimes[i];
            avgTurnAroundTime += turnAroundTime[i];
        }

        avgTurnAroundTime /= noProcess;
        avgWaitTime /= noProcess;

        // Print the results
        for (int i = 0; i < noProcess; i++) {
            System.out.println(burstTimes[i] + "\t" + waitTime[i] + "\t" + turnAroundTime[i]);
        }
        System.out.println("Average Wait Time : " + avgWaitTime);
        System.out.println("Average Turn Around Time : " + avgTurnAroundTime);

        input.close();
    }
}