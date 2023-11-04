import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class LRU {
    /**
     * Implements LRU (Least Recently Used) page replacement algorithm.
     *
     * @param arr      Array of page references.
     * @param capacity Number of frames in memory.
     * @return Array of page faults and page hits.
     */
    public static int[] lru(int arr[], int capacity) {

        // Set to hold page references in memory.
        HashSet<Integer> slot = new HashSet<>();

        // Map to hold page references and their indices.
        HashMap<Integer, Integer> index = new HashMap<>();

        // Counter for page faults.
        int pageFaults = 0;

        // Counter for page hits.
        int pageHits = 0;

        for (int i = 0; i < arr.length; i++) {
            if (slot.size() < capacity) {
                // If memory is not full, add new page reference.
                if (!slot.contains(arr[i])) {
                    slot.add(arr[i]);
                    pageFaults++;
                } else {
                    pageHits++;
                }
                index.put(arr[i], i);
            } else {
                // If memory is full, replace least recently used page.
                if (!slot.contains(arr[i])) {
                    int lrv = Integer.MAX_VALUE;
                    int val = Integer.MIN_VALUE;
                    Iterator<Integer> itr = slot.iterator();

                    // Find the least recently used page.
                    while (itr.hasNext()) {
                        int temp = itr.next();
                        if (index.get(temp) < lrv) {
                            lrv = index.get(temp);
                            val = temp;
                        }
                    }

                    // Replace the least recently used page.
                    slot.remove(val);
                    index.remove(val);
                    slot.add(arr[i]);
                    pageFaults++;
                } else {
                    // If page is already in memory, increment page hit counter.
                    pageHits++;
                }
                index.put(arr[i], i);
            }
        }

        // Return page faults and hits.
        int[] ans = { pageHits, pageFaults };
        return ans;
    }

    /**
     * This is the main function that runs the LRU simulation.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Define the pages to be simulated
        int[] pages = { 7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2 };

        // Define the capacity of the cache
        int capacity = 4;

        // Run the LRU simulation
        int[] result = lru(pages, capacity);

        // Print the results
        System.out.println("Page Hits : " + result[0] +
                "\nPage Faults : " + result[1]);
    }
}
