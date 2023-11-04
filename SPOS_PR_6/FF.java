import java.util.Arrays;

public class FF {

    /**
     * Prints an array of integers.
     *
     * @param arr The array to print.
     * @param n   The number of elements to print.
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
     * This method implements the first-fit memory allocation strategy.
     *
     * @param memoryBlocks Array of available memory blocks
     * @param processSize  Array of process sizes
     */
    public static void firstFit(int[] memoryBlocks, int[] processSize) {
        // Initialize array to keep track of allocated memory blocks
        int[] allocated = new int[processSize.length];
        Arrays.fill(allocated, -1);

        // Iterate over all processes
        for (int i = 0; i < processSize.length; i++) {
            // Iterate over all memory blocks
            for (int j = 0; j < memoryBlocks.length; j++) {
                // Check if memory block is big enough for process
                if (memoryBlocks[j] >= processSize[i]) {
                    // Allocate memory block to process
                    allocated[i] = j;
                    // Update memory block size
                    memoryBlocks[j] -= processSize[i];
                    // Break out of inner loop
                    break;
                }
            }
        }

        // Print memory blocks, process sizes, and allocated blocks
        printArray(memoryBlocks, memoryBlocks.length);
        printArray(processSize, processSize.length);
        printArray(allocated, allocated.length);
    }

    /**
     * This is the main method which initializes the block size and process size
     * arrays.
     * It then calls the firstFit method to allocate the memory.
     * 
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        // Define block size array
        int blockSize[] = { 100, 500, 200, 300, 600 };

        // Define process size array
        int processSize[] = { 212, 417, 112, 426 };

        // Call the firstFit method to allocate memory
        firstFit(blockSize, processSize);
    }
}
