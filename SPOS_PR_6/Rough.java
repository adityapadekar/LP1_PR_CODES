import java.util.Arrays;

public class Rough {
    public static void printArray(int[] arr, int n) {
        // Loop through the array
        for (int i = 0; i < n; i++) {
            // Print each element
            System.out.print(arr[i] + " ");
        }
        // End the line
        System.out.println();
    }

    public static void func(int[] blockSize, int[] processSize) {
        int[] allocated = new int[processSize.length];
        Arrays.fill(allocated, -1);

        int idx = 0;
        int endIdx = 0;
        for (int i = 0; i < processSize.length; i++) {
            while (idx < blockSize.length) {
                if (blockSize[idx] >= processSize[i]) {
                    blockSize[idx] -= processSize[i];
                    allocated[i] = idx;
                    break;
                }
                idx++;
                if (idx == blockSize.length)
                    idx = 0;

                if (idx == endIdx)
                    break;
            }
            endIdx = idx;

        }

        printArray(blockSize, blockSize.length);
        printArray(processSize, processSize.length);
        printArray(allocated, processSize.length);
    }

    public static void main(String[] args) {
        int blockSize[] = { 100, 500, 200, 300, 600 };
        int processSize[] = { 212, 417, 112, 426 };

        func(blockSize, processSize);
    }
}
