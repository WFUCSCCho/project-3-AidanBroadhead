/*****************************************************************************************
        * @file: Proj3.java
        * @description: This program implements merge sort, quick sort, bubble sort, heap sort, and odd-even
                            transposition sort. The main method takes in command line arguments and runs the
                            corresponding sorting algorithms with N inputs and outputs the running time and comparison
                            analysis as well as the sorted list.
        * @author: Aidan Broadhead
        * @date: November 13, 2025
******************************************************************************************/

import java.io.IOException;
import java.util.ArrayList;
import java.io.*;
import java.util.*;

public class Proj3 {
    // Sorting Method declarations
    // Merge Sort
    public static <T extends Comparable<? super T>> void mergeSort(ArrayList<T> a, int left, int right) {
        // Finish Me
        // base case
        if (left >= right) {
            return;
        }

        // splot array and recursively sort both halves
        int mid = left + (right - left) / 2;
        mergeSort(a, left, mid);
        mergeSort(a, mid + 1, right);

        // merge the sorted halves
        merge(a, left, mid, right);

    }

    public static <T extends Comparable<? super T>> void merge(ArrayList<T> a, int left, int mid, int right) {
        // Finish Me
        ArrayList<T> temp = new ArrayList<>(right - left +1);
        int i = left;
        int j = mid + 1;

        // merge halves in sorted order
        while (i <= mid && j <= right) {
            if (a.get(i).toString().compareToIgnoreCase(a.get(j).toString()) <= 0) {
                temp.add(a.get(i++));
            } else {
                temp.add(a.get(j++));
            }
        }

        // add left half
        while (i <= mid) {
            temp.add(a.get(i++));
        }

        // add right half
        while (j <= right) {
            temp.add(a.get(j++));
        }

        // put merged elements into initial array
        for (int k = 0; k < temp.size(); k++) {
            a.set(left + k, temp.get(k));
        }
    }

    // Quick Sort
    public static <T extends Comparable<? super T>> void quickSort(ArrayList<T> a, int left, int right) {
        // Finish Me
        // base case
        if (left >= right) {
            return;
        }

        // get pivot and partition
        int p = partition(a, left, right);

        // recursively sort each side of pivot
        quickSort(a, left, p - 1);
        quickSort(a, p + 1, right);
    }

    public static <T extends Comparable<? super T>> int partition (ArrayList<T> a, int left, int right) {
        // Finish Me
        // set pivot to far right element
        T pivot = a.get(right);
        int i = left;

        // compare to pivot and rearrange
        for (int j = left; j < right; j++) {
            if (a.get(j).toString().compareToIgnoreCase(pivot.toString()) <= 0) {
                swap(a, i, j);
                i++;
            }
        }

        // move pivot to correct index
        swap(a, i, right);
        return i;
    }

    static <T> void swap(ArrayList<T> a, int i, int j) {
        // swap two elements
        T temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }

    // Heap Sort
    public static <T extends Comparable<? super T>> void heapSort(ArrayList<T> a, int left, int right) {
        // Finish Me
        int n = right - left + 1;
        if (n <= 1) return;

        // build max heap
        heapify(a, left, right);

        // get largest element and maintain heap properties
        for (int end = right; end > left; end--) {
            swap(a, left, end);
            int parent = left;

            while (true) {

                int l = (parent - left) * 2 + 1 + left;
                int r = l + 1;
                int largest = parent;

                // compare children and prent
                if (l <= end - 1 && a.get(l).toString().compareToIgnoreCase(a.get(largest).toString()) > 0) {
                    largest = l;
                }
                if (r <= end - 1 && a.get(r).toString().compareToIgnoreCase(a.get(largest).toString()) > 0) {
                    largest = r;
                }

                // break when heap property is met
                if (largest == parent) {
                    break;
                }

                swap(a, parent, largest);
                parent = largest;
            }
        }

    }

    public static <T extends Comparable<? super T>> void heapify (ArrayList<T> a, int left, int right) {
        // Finish Me
        int n = right - left + 1;
        if (n <= 1) return;

        // sift down each node
        for (int i = left + (n / 2) - 1; i >= left; i--) {
            int parent = i;

            while (true) {
                // child indices
                int l = (parent - left) * 2 + 1 + left;
                int r = l + 1;
                int largest = parent;

                // compare children to parent
                if (l <= right && a.get(l).toString().compareToIgnoreCase(a.get(largest).toString()) > 0) {
                    largest = l;
                }
                if (r <= right && a.get(r).toString().compareToIgnoreCase(a.get(largest).toString()) > 0) {
                    largest = r;
                }

                if (largest == parent) {
                    break;
                }

                // swap if child is larger
                swap(a, parent, largest);
                parent = largest;
            }
        }

    }

    // Bubble Sort
    public static <T extends Comparable<? super T>> int bubbleSort(ArrayList<T> a, int size) {
        // Finish Me
        int comparisons = 0;
        boolean swapped;

        // nested loop to pass through array and make swaps
        for (int i = 0; i < size - 1; i++) {
            swapped = false;
            for (int j = 0; j < size - 1 - i; j++) {
                comparisons++;
                if (a.get(j).toString().compareToIgnoreCase(a.get(j + 1).toString()) > 0) {
                    swap(a, j, j + 1);
                    swapped = true;
                }
            }
            // if its already sorted
            if (!swapped) {
                break;
            }
        }

        // return number of comparisons
        return comparisons;
    }

    // Odd-Even Transposition Sort
    public static <T extends Comparable<? super T>> int transpositionSort(ArrayList<T> a, int size) {
        // Finish Me
        boolean sorted = false;
        int phases = 0;

        // alternate between odd and even comparisons
        while (!sorted) {
            sorted = true;

            // odd loop
            boolean swappedOdd = false;
            for (int i = 1; i <= size - 2; i += 2) {
                if (a.get(i).toString().compareToIgnoreCase(a.get(i + 1).toString()) > 0) {
                    swap(a, i, i + 1);
                    swappedOdd = true;
                }
            }

            if (size > 1) phases++;
            if (swappedOdd) sorted = false;

            // even loop
            boolean swappedEven = false;
            for (int i = 0; i <= size - 2; i += 2) {
                if (a.get(i).toString().compareToIgnoreCase(a.get(i + 1).toString()) > 0) {
                    swap(a, i, i + 1);
                    swappedEven = true;
                }
            }

            if (size > 1) {
                phases++;
            }
            if (swappedEven) {
                sorted = false;
            }
        }

        return phases;
    }

    public static void main(String [] args)  throws IOException {
        // Finish Me

        // check number of command line arguments
        if (args.length != 3) {
            System.out.println("Usage: java Proj3 {dataset-file} {sorting-algorithm-type} {number-of-lines}");
            return;
        }

        String filename = args[0];
        String algorithm = args[1].toLowerCase();
        int N = Integer.parseInt(args[2]);

        ArrayList<String> base = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));

        // read header
        String header = br.readLine();
        if (header == null) {
            System.out.println("Empty file.");
            br.close();
            return;
        }

        // split header by commas
        String[] cols = header.split(",");
        int nameIndex = -1;
        for (int i = 0; i < cols.length; i++) {
            if (cols[i].trim().equalsIgnoreCase("name")) {
                nameIndex = i;
                break;
            }
        }

        if (nameIndex == -1) {
            System.out.println("Couldn't find 'name' column.");
            br.close();
            return;
        }

        // read N song names into list
        String line;
        int count = 0;
        while ((line = br.readLine()) != null && count < N) {
            String[] parts = line.split(",", -1);
            if (nameIndex < parts.length) {
                base.add(parts[nameIndex].trim());
                count++;
            }
        }
        br.close();

        if (base.isEmpty()) {
            System.out.println("No names found.");
            return;
        }

        // create sorted, shuffled, and reversed versions of input
        ArrayList<String> sorted = new ArrayList<>(base);
        Collections.sort(sorted, String.CASE_INSENSITIVE_ORDER);

        ArrayList<String> shuffled = new ArrayList<>(sorted);
        Collections.shuffle(shuffled);

        ArrayList<String> reversed = new ArrayList<>(sorted);
        Collections.sort(reversed, String.CASE_INSENSITIVE_ORDER.reversed());

        // run algorithm on each input type
        runByCase(algorithm, "sorted", sorted, N);
        runByCase(algorithm, "shuffled", shuffled, N);
        runByCase(algorithm, "reversed", reversed, N);

        // write sorted outputs of each input type into txt file
        BufferedWriter out = new BufferedWriter(new FileWriter("sorted.txt"));
        out.write("--- Sorted Input => Sorted Output ---");
        out.newLine();
        for (String s : sorted) { out.write(s); out.newLine(); }

        out.newLine();
        out.write("--- Shuffled Input => Sorted Output ---");
        out.newLine();
        for (String s : shuffled) { out.write(s); out.newLine(); }

        out.newLine();
        out.write("--- Reversed Input => Sorted Output ---");
        out.newLine();
        for (String s : reversed) { out.write(s); out.newLine(); }

        out.close();

    }

    // method to run the specific algorithm inputted
    private static void runByCase(String algorithm, String type, ArrayList<String> data, int N) throws IOException {
        long start, end, time;
        int comps = -1;

        // checks which algorithm user inputs and runs and times it/counts comparisons
        switch (algorithm) {
            case "merge":
                start = System.nanoTime();
                mergeSort(data, 0, data.size() - 1);
                end = System.nanoTime();
                time = end - start;
                recordResult(algorithm, N, type, time, "");
                break;

            case "quick":
                start = System.nanoTime();
                quickSort(data, 0, data.size() - 1);
                end = System.nanoTime();
                time = end - start;
                recordResult(algorithm, N, type, time, "");
                break;

            case "heap":
                start = System.nanoTime();
                heapSort(data, 0, data.size() - 1);
                end = System.nanoTime();
                time = end - start;
                recordResult(algorithm, N, type, time, "");
                break;

            case "bubble":
                start = System.nanoTime();
                comps = bubbleSort(data, data.size());
                end = System.nanoTime();
                time = end - start;
                recordResult(algorithm, N, type, time, String.valueOf(comps));
                break;

            case "transposition":
                comps = transpositionSort(data, data.size());
                recordResult(algorithm, N, type, 0, String.valueOf(comps));
                break;

            default:
                System.out.println("Unknown algorithm: " + algorithm);
                return;
        }
    }

    // records the timing/comparison stats into the txt file
    private static void recordResult(String algorithm, int N, String type, long time, String comps) throws IOException {
        System.out.printf("%-12s  N=%-6d  %-10s  time(ns)=%-12d  comps=%s%n",
                algorithm, N, type, time, (comps.isEmpty() ? "-" : comps));
        BufferedWriter bw = new BufferedWriter(new FileWriter("analysis.txt", true));
        bw.write(algorithm + "," + N + "," + type + "," + (time == 0 ? "" : time) + "," + comps);
        bw.newLine();
        bw.close();
    }

}
