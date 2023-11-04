import java.util.*;

public class FIFO {

    /**
     * Implements the FIFO page replacement algorithm.
     *
     * @param arr      Array of page numbers to be replaced.
     * @param capacity Maximum number of pages to be stored.
     * @return An array with page hits and page faults.
     */
    public static int[] fifo(int arr[], int capacity) {

        // Set to store page numbers and Queue to store page indexes
        HashSet<Integer> set = new HashSet<>();
        Queue<Integer> index = new LinkedList<>();

        // Initialize page hit and fault counters
        int pageHits = 0, pageFaults = 0;

        // Iterate over the array of page numbers
        for (int i = 0; i < arr.length; i++) {
            // If the set size is less than capacity
            if (set.size() < capacity) {
                // If the page number is not in the set
                if (!set.contains(arr[i])) {
                    set.add(arr[i]);
                    index.add(arr[i]);
                    pageFaults++;
                } else {
                    // If the page number is in the set
                    pageHits++;
                }
            } else {
                // If the page number is not in the set
                if (!set.contains(arr[i])) {
                    // Remove the least recently used page
                    int val = index.peek();
                    index.poll();
                    set.remove(val);

                    // Add the new page number
                    set.add(arr[i]);
                    index.add(arr[i]);
                    pageFaults++;
                } else {
                    // Increment the page hit counter
                    pageHits++;
                }
            }
        }

        // Return the page hit and fault counters
        return new int[] { pageHits, pageFaults };
    }

    /**
     * This is the main function that runs the program.
     * It calculates page faults and hits using FIFO algorithm.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        // Define the pages to be managed
        int[] pages = { 7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2 };

        // Define the capacity of the memory
        int capacity = 4;

        // Calculate page faults and hits
        int[] results = fifo(pages, capacity);

        // Print the results
        System.out.println("Page Hits: " + results[0]);
        System.out.println("Page Faults: " + results[1]);
    }

}