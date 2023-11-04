import java.util.Arrays;

public class NF {
    /**
     * Prints an array of integers.
     *
     * @param arr The array to be printed.
     * @param n   The size of the array.
     */
    public static void printArray(int[] arr, int n) {
        // Loop through the array
        for (int i = 0; i < n; i++) {
            // Print each element
            System.out.print(arr[i] + " ");
        }
        // End the line
        System.out.println();
    }

    /**
     * This method implements the next-fit memory allocation strategy.
     *
     * @param memoryBlock The available memory blocks.
     * @param processSize The size of the processes to be allocated.
     */
    public static void nextFit(int[] memoryBlock, int[] processSize) {
        // Initialize array to store the allocated blocks
        int[] allocated = new int[processSize.length];
        Arrays.fill(allocated, -1);

        int idx = 0;
        int endIdx = 0; // Initialize endIdx to 0

        // Iterate over the processes
        for (int i = 0; i < processSize.length; i++) {
            // Iterate over the memory blocks
            while (idx < memoryBlock.length) {
                // Check if the current block is large enough
                if (memoryBlock[idx] >= processSize[i]) {
                    allocated[i] = idx;
                    memoryBlock[idx] -= processSize[i];
                    break;
                }
                idx++;
                // Reset idx to 0 if it reaches the end of the array
                if (idx == memoryBlock.length) {
                    idx = 0;
                }
                // Break the loop if idx reaches endIdx
                if (idx == endIdx) {
                    break;
                }
            }
            // Update endIdx after each process
            endIdx = idx;
        }

        // Print the memory blocks, process sizes, and allocated blocks
        printArray(memoryBlock, memoryBlock.length);
        printArray(processSize, processSize.length);
        printArray(allocated, allocated.length);
    }

    /**
     * Main method to run the program.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        // Define block and process sizes
        int blockSize[] = { 100, 500, 200, 300, 600 };
        int processSize[] = { 212, 417, 112, 426 };

        // Call nextFit method
        nextFit(blockSize, processSize);
    }
}
