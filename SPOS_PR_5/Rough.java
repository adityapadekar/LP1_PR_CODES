public class Rough {
    public static void main(String[] args) {
        int[] processIds = { 1, 2, 3, 4 };
        int[] arrivalTimes = { 0, 1, 2, 3 };
        int[] burstTimes = { 6, 8, 7, 3 };

        SJFScheduler(processIds, arrivalTimes, burstTimes);
    }

    public static void SJFScheduler(int[] processIds, int[] arrivalTimes, int[] burstTimes) {
        int n = processIds.length;
        int[] remainingTimes = burstTimes.clone();
        int currentTime = 0;
        int completedCount = 0;

        while (completedCount < n) {
            int minRemainingTime = Integer.MAX_VALUE;
            int shortestJobIndex = -1;

            for (int i = 0; i < n; i++) {
                if (arrivalTimes[i] <= currentTime && remainingTimes[i] < minRemainingTime && remainingTimes[i] > 0) {
                    minRemainingTime = remainingTimes[i];
                    shortestJobIndex = i;
                }
            }

            if (shortestJobIndex == -1) {
                currentTime++;
                continue;
            }

            remainingTimes[shortestJobIndex]--;
            currentTime++;

            if (remainingTimes[shortestJobIndex] == 0) {
                System.out.println("Process " + processIds[shortestJobIndex] +
                        " completed at time " + currentTime);
                completedCount++;
            }
        }
    }
}
