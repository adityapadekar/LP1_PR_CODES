import java.util.Scanner;

public class PS {

    /**
     * This method implements the Shortest Job First (SJF) scheduling algorithm.
     * It takes input from the user, calculates wait time and turnaround time
     * for each process, and prints the results.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int noProcess;

        // Prompt user for number of processes
        System.out.print("Enter the number of processes : ");
        noProcess = input.nextInt();

        // Initialize arrays for process numbers, burst times, priorities, wait times,
        // and turnaround times
        int[] processNumber = new int[50];
        int[] burstTime = new int[50];
        int[] priority = new int[50];
        int[] waitTime = new int[50];
        int[] turnAroundTime = new int[50];
        waitTime[0] = 0;

        float avgWaitTime = 0;
        float avgTurnAroundTime = 0;

        // Prompt user for burst time and priority of each process
        System.out.println("Enter the Burst time and the priority of the process");
        for (int i = 0; i < noProcess; i++) {
            processNumber[i] = i + 1;
            System.out.print("For process P" + (i + 1) + " : ");
            burstTime[i] = input.nextInt();
            System.out.print("Priority of P" + (i + 1) + " : ");
            priority[i] = input.nextInt();
        }

        // Sort processes based on priority in ascending order
        for (int i = 0; i < noProcess; i++) {
            int pos = i;
            for (int j = i + 1; j < noProcess; j++) {
                if (priority[j] < priority[pos]) {
                    pos = j;
                }
            }
            // Swap burst time, priority, and process number for sorted positions
            int temp = burstTime[i];
            burstTime[i] = burstTime[pos];
            burstTime[pos] = temp;

            temp = priority[i];
            priority[i] = priority[pos];
            priority[pos] = temp;

            temp = processNumber[i];
            processNumber[i] = processNumber[pos];
            processNumber[pos] = temp;
        }

        // Calculate wait time for each process
        for (int i = 1; i < noProcess; i++) {
            waitTime[i] = waitTime[i - 1] + burstTime[i - 1];
            avgWaitTime += waitTime[i];
        }

        // Calculate turnaround time for each process
        for (int i = 0; i < noProcess; i++) {
            turnAroundTime[i] = waitTime[i] + burstTime[i];
            avgTurnAroundTime += turnAroundTime[i];
        }

        // Calculate average wait time and turnaround time
        avgTurnAroundTime /= noProcess;
        avgWaitTime /= noProcess;

        // Print process details, average wait time, and average turnaround time
        for (int i = 0; i < noProcess; i++) {
            System.out.println(processNumber[i] + "\t" + burstTime[i] + "\t" + turnAroundTime[i] + "\t" + priority[i]);
        }
        System.out.println("Average Wait Time : " + avgWaitTime);
        System.out.println("Average Turn Around Time : " + avgTurnAroundTime);

        input.close();
    }
}
