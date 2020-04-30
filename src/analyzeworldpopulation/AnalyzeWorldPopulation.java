/*
* @author Darnell Simon
* @since 04-27-2020
 */
package analyzeworldpopulation;

//import java.io.File;
import java.io.File;
import java.util.Scanner;

//import java.util.Scanner;
public class AnalyzeWorldPopulation {

    public static void main(String[] args) throws Exception {
        final int MAXNUMBER = 100;

        int count, found;
        int[] year = new int[MAXNUMBER]; //Array of ints for IDs
        long[] population = new long[MAXNUMBER]; //Array of Strings for populations

        count = populateArray(year, population); // Populate the array to determine the count

        //Unsorted Array
        System.out.println("Year\tWorld Population"); // Print heading of the arrays
        for (int i = 0; i < count; i++) {
            // Printing the two arrays side by side
            System.out.printf("%d\t%,d\n", year[i], population[i]);
        }

        // Invoking the bubble sort
        bubbleSort(year, population, count);
        //Sorted Array
        System.out.println("\nYear\tWorld Population"); // Print heading of the arrays
        System.out.println("____\t________________"); // Print heading of the arrays
        for (int i = 0; i < count; i++) {
            // Printing the two arrays side by side
            System.out.printf("%d\t%,d\n", year[i], population[i]);
        }

        //////////////////////////////////
        /// Search for Mellenial Years ///
        //////////////////////////////////
        System.out.println("\n Sequential Search");
        System.out.println("Millenial Populations"); // Print heading of the arrays
        for (int value = -5000; value <= 2000; value = value + 1000) {
            found = sequentialSearch(year, count, value);
            if (found == -1 && value == 0) {
                // Do nothing

            } else {
                System.out.printf("%d\t  %,d\n", year[found], population[found]);
            }

        }

        //////////////////////////////////
        /// Search for Mellenial Years ///
        //////////////////////////////////
        System.out.println("\n  Binary Search");
        System.out.println("Millenial Populations"); // Print heading of the arrays
        for (int value = -5000; value <= 2000; value = value + 1000) {
            found = binarySearch(year, count, value);
            if (found == -1) {
                // Do nothing
            } else {
                System.out.printf("%d\t  %,d\n", year[found], population[found]);
            }

        }

    } // end main

    //////////////////////////////
    ////////Populate Array////////
    //////////////////////////////
    public static int populateArray(int[] year, long[] population) throws Exception {
        Scanner sc = new Scanner(new File("WorldPopulation.txt"));
        int count = 0;
        while (sc.hasNext()) {
            sc.nextLine(); // This skips over the very first line.
            // Filling up two arrays in parallel to each other
            year[count] = sc.nextInt(); // read the int
            population[count] = sc.nextLong(); // read the string beside it

            count++;
        }
        return count;
    } // end populateArray

    /////////////////////////////////
    ////////Bubble Sort Array////////
    /////////////////////////////////
    public static void bubbleSort(int[] year, long[] population, int n) {
        int hold, j, pass;
        long temp;
        boolean switched = true;

        for (pass = 0; pass < n - 1 && switched; pass++) {
            // outer loop controles the number of passes
            switched = false; // initially no interchanges have been 
            // made one this pass
            for (j = 0; j < n - pass - 1; j++) {
                // inner loop governs each individual pass
                if (year[j] > year[j + 1]) {
                    // elements are out of order an interhcange is necessary 
                    switched = true;
                    // Switch the array of years
                    hold = year[j];
                    year[j] = year[j + 1];
                    year[j + 1] = hold;
                    // Switch the array of popultions
                    temp = population[j];
                    population[j] = population[j + 1];
                    population[j + 1] = temp;
                } // end if
            } // end for (j = 0 ...
        }//endfor(pass=0... } // end bubbleSort method
    } // end BubbleSort class

    public static int sequentialSearch(int[] x, int n, int item) {
        int i;
        for (i = 0; i < n; i++) {
            if (x[i] == item) {
                return i;
            }
        }
        return -1;
    } // end sequentialSearch

    // Binary Search
    public static int binarySearch(int[] x, int n, int item) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int middle = (low + high) / 2;
            if (item > x[middle]) {
                low = middle + 1;
            } else if (item < x[middle]) {
                high = middle - 1;
            } else { // the item has been found
                return middle;
            }
        }
        return -1;
    } // end binarySearch method  

} // end of AnalyzeWorldPopulation 

