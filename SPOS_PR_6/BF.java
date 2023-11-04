import java.util.Arrays;

public class BF {
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
     * This method implements the best-fit memory allocation strategy.
     *
     * @param memoryBlock The available memory blocks.
     * @param processSize The size of the processes to be allocated.
     */
    public static void bestFit(int[] memoryBlock, int[] processSize) {

        // Initialize an array to store the allocated block numbers
        int[] allocated = new int[processSize.length];
        Arrays.fill(allocated, -1);

        // Iterate over the process sizes
        for (int i = 0; i < processSize.length; i++) {

            // Initialize variables for the best fit block number and difference
            int blockNo = -1;
            int dif = Integer.MAX_VALUE;

            // Iterate over the memory blocks
            for (int j = 0; j < memoryBlock.length; j++) {

                // Check if the memory block is large enough for the process
                if (memoryBlock[j] >= processSize[i]) {

                    // Calculate the difference between the memory block size
                    // and process size
                    int tempDif = memoryBlock[j] - processSize[i];

                    // Check if the current difference is larger than the
                    // previous difference
                    if (tempDif < dif) {

                        // Update the block number and difference
                        dif = tempDif;
                        blockNo = j;
                    }
                }
            }

            // If a suitable block is found, allocate the process to it
            if (blockNo != -1) {
                memoryBlock[blockNo] -= processSize[i];
                allocated[i] = blockNo;
            }
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

        // Call bestFit method
        bestFit(blockSize, processSize);
    }
}
