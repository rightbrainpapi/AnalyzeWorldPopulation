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
//        Scanner sc = new Scanner(System.in);
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
        System.out.println("\nYear\tWorld Population");
        System.out.println("========================");
        for (int i = 0; i < count; i++) {
            // Printing the two arrays side by side
            System.out.printf("%d\t%,d\n", year[i], population[i]);
        }

//        /////////////////////////////////////////
//        /// Binary Search for Mellenial Years ///
//        /////////////////////////////////////////
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
        /////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////
        ///////////MEAN MEDIAN AND STANDARD DEVIATION SECTION////////////
        /////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////

        ///////////////////////////////////////////////////////////////
        ///// Binary Search for Years between -5000 and -1000 Years ///
        ///////////////////////////////////////////////////////////////
        int[] alternativeYearsArray = new int[MAXNUMBER];
        long[] alternativePopulationArray = new long[MAXNUMBER];
        int currentCount = 0;

        printTitle();
        for (int value = -5000; value <= -1000; value = value + 1) {
            found = binarySearch(year, count, value);
            if (found == -1) {
                // Do nothing
            } else {

                alternativeYearsArray[currentCount] = year[found];
                alternativePopulationArray[currentCount] = population[found];
                currentCount++;
            }
        }
        for (int i = 0; i < currentCount; i++) {

            System.out.printf("%d\t%,d\n", alternativeYearsArray[i], alternativePopulationArray[i]);
        }
        System.out.printf("\nThe current count of the list above is %d \n", currentCount);
        long mean = calculateMean(currentCount, alternativePopulationArray);
        System.out.printf("The Mean population for the list above is %,d\n", mean);
        calculateMedian(currentCount, alternativePopulationArray);
        calculateStandardDeviation(currentCount, mean, alternativePopulationArray);

//        System.out.printf("The Mean population for the Millenial years is %,d.\n", mean);
        ///////////////////////////////////////////////////////////////
        ///// Binary Search for Years between 1000 and 1900 Years ///
        ///////////////////////////////////////////////////////////////
        currentCount = 0;
        printTitle();
        for (int value = 1000; value <= 1900; value = value + 1) {
            found = binarySearch(year, count, value);
            if (found == -1) {
                // Do nothing
            } else {

                alternativeYearsArray[currentCount] = year[found];
                alternativePopulationArray[currentCount] = population[found];
                currentCount++;
            }
        }

        for (int i = 0; i < currentCount; i++) {
            // Printing the two arrays side by side
            System.out.printf("%d\t%,d\n", alternativeYearsArray[i], alternativePopulationArray[i]);
        }
        System.out.printf("\nThe current count of the list above is %d \n", currentCount);
        mean = calculateMean(currentCount, alternativePopulationArray);
        System.out.printf("The Mean population for the list above is %,d\n", mean);
        calculateMedian(currentCount, alternativePopulationArray);
        calculateStandardDeviation(currentCount, mean, alternativePopulationArray);

        ///////////////////////////////////////////////////////////////
        ///// Binary Search for Years between 2000 and 2020 Years ///
        ///////////////////////////////////////////////////////////////
        currentCount = 0;
        printTitle();
        for (int value = 2000; value <= 2020; value = value + 1) {
            found = binarySearch(year, count, value);
            if (found == -1) {
                // Do nothing
            } else {
                alternativeYearsArray[currentCount] = year[found];
                alternativePopulationArray[currentCount] = population[found];
                currentCount++;
            }
        }

        for (int i = 0; i < currentCount; i++) {
            // Printing the two arrays side by side
            System.out.printf("%d\t%,d\n", alternativeYearsArray[i], alternativePopulationArray[i]);
        }

        System.out.printf("\nThe current count of the list above is %d \n", currentCount);
        mean = calculateMean(currentCount, alternativePopulationArray);
        System.out.printf("The Mean population for the list above is %,d\n", mean);
        calculateMedian(currentCount, alternativePopulationArray);
        calculateStandardDeviation(currentCount, mean, alternativePopulationArray);

    } // end main

    //////////////////////////////
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

    /////////////////////////////////
    //////// Binary Search //////////
    /////////////////////////////////
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

    public static long calculateMean(int amountOfNumbers, long x[]) {

        long sum = 0;
        int i = 0;
        while (i < amountOfNumbers) {

            sum += x[i];

            i++;
        }

        long mean = sum / amountOfNumbers;
//        System.out.printf("The Mean population for the Millenial years is %,d.\n", mean);
//        calculateStandardDeviation(amountOfNumbers, mean, x);

        return mean;
    }

    public static void calculateMedian(int amountOfNumbers, long[] x) {
        int low = 0;
        int high = amountOfNumbers - 1;
        int middle = (low + high) / 2;
        System.out.printf("The Median of the list above is: %,d\n", x[middle]);
    }

    public static void calculateStandardDeviation(int amountOfNumbers, long mean, long[] x) {
        long standardDeviation;
//        ====================
//        SUBTRACTING THE MEAN
//        ====================
        for (int i = 0; i < amountOfNumbers; i++) {
            x[i] = x[i] - mean;
        }
//        =======================
//        SQUARING THE NEW VALUES
//        =======================
        for (int i = 0; i < amountOfNumbers; i++) {
            x[i] = (long) Math.pow(x[i], 2);
        }
//        =====================
//        MEAN OF SQUARED VALUE
//        =====================
        mean = calculateMean(amountOfNumbers, x);
        standardDeviation = (long) Math.sqrt(mean);

        System.out.printf("The Standard Deviation of the list above is: %,d\n", standardDeviation);
    }

    public static void printTitle() {
        System.out.println("\n%%%%%%%%**********************");
        System.out.println("%%%%%%%%**********************");
        System.out.println("\nSpecified Years Search");
        System.out.println("=======================");
        System.out.println("Years   Populations");
    }
} // end of AnalyzeWorldPopulation 

