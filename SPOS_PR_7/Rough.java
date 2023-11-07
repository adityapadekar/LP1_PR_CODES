import java.util.HashSet;
import java.util.Iterator;

public class Rough {
    public static int[] func(int arr[], int capacity) {

        HashSet<Integer> slot = new HashSet<>();

        int pageFaults = 0;
        int pageHits = 0;

        for (int i = 0; i < arr.length; i++) {
            if (slot.size() < capacity) {
                if (!slot.contains(arr[i])) {
                    slot.add(arr[i]);
                    pageFaults++;
                } else {
                    pageHits++;
                }
            } else {
                if (!slot.contains(arr[i])) {

                    int res = -1;
                    int idx = -1;

                    Iterator<Integer> itr = slot.iterator();

                    while (itr.hasNext()) {
                        int temp = itr.next();
                        int j;
                        for (j = i + 1; j < arr.length; j++) {
                            if (arr[j] == temp) {
                                if (j > idx) {
                                    res = temp;
                                    idx = j;
                                }
                                break;
                            }
                        }

                        if (j == arr.length) {
                            res = temp;
                            break;
                        }
                    }
                    if (res == -1)
                        res = itr.next();

                    slot.remove(res);
                    slot.add(arr[i]);
                    pageFaults++;
                } else {
                    pageHits++;
                }
            }
        }

        int[] ans = { pageHits, pageFaults };

        return ans;

    }

    public static void main(String[] args) {
        int[] pages = { 7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2 };

        int capacity = 4;

        int[] ans = func(pages, capacity);

        System.out.println("Page Hits : " + ans[0] + "\nPage Faults : " + ans[1]);
    }
}
