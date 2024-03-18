import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * The main class of the project, taking in inputs from a text file, creating an array of cars,
 * organizing them into an ordered list, and finally allowing that list to be exported into a new text file
 *
 * CSC 1351 Programming Project No 1
 * Section 2, 0930-1020
 *
 * @author Ashton Fox
 * @since 2/16/24
 * @due 3/17/24
 */
public class Prog01_aOrderedList {
    public static void main(String[] args) {

        Scanner fileNameScan = new Scanner(System.in);

        aOrderedList orderedList = new aOrderedList();
        ArrayList<Car> carCollection = new ArrayList<>();

        //This boolean is used in the following do while loop to see if the user needs to input a new file name
        boolean needNewName = true;

        /**
         * Do while loop has many functions, comments added inside. Generally, this
         * will keep asking the user if they want to enter a file name or exit
         *
         * CSC 1351 Programming Project No 1
         * Section 2, 930-1030
         *
         * @author Ashton Fox
         * @since 3/16/24
         * @due 3/17/24
         */
        do {
            System.out.println("Enter input filename and ensure you include '.txt': ");

            String fileName = fileNameScan.nextLine();

            File inFile = new File(fileName);

            //Checks if the file name exists and asks the user if they want to retry
            if (!inFile.exists()) {
                System.out.println("File specified <" + fileName + "> does not exist. Would you like to continue? <Y/N> (Any other input will be considered N)");
                String choice = fileNameScan.nextLine();

                if (!choice.equalsIgnoreCase("Y")) {
                    needNewName = false;
                }
            }
            else {
                /**
                 * Scans the file coming in and adds each line to the car array, adding for A and
                 * deleting for D, then inputting the specific variables to each Car object
                 *
                 * CSC 1351 Programming Project No 1
                 * Section 2, 930-1030
                 *
                 * @author Ashton Fox
                 * @since 3/16/24
                 * @due 3/17/24
                 */
                try {
                    Scanner fileScanner = new Scanner(inFile);
                    while (fileScanner.hasNextLine()) {
                        String addCar = fileScanner.nextLine();
                        String[] carVars = addCar.split(",");
                        String command = carVars[0].trim();
                        if (command.equals("A")) {
                            String make = carVars[1].trim();
                            int year = Integer.parseInt(carVars[2].trim());
                            int price = Integer.parseInt(carVars[3].trim());
                            carCollection.add(new Car(make, year, price));
                        }

                        //If the make and year match one in the array, this command will delete that object
                        else if (command.equals("D")) {
                            String makeToDelete = carVars[1].trim();
                            int yearToDelete = Integer.parseInt(carVars[2].trim());
                            // Find the index of the car with the specified make and year in the carCollection
                            int index = -1;
                            for (int i = 0; i < carCollection.size(); i++) {
                                Car car = carCollection.get(i);
                                if (car.getMake().equals(makeToDelete) && car.getYear() == yearToDelete) {
                                    index = i;
                                    break;
                                }
                            }
                            // Remove the car if found
                            if (index != -1) {
                                carCollection.remove(index);
                            }
                        }
                    }

                    //Will end the do while loop after the arraylist is created
                    needNewName = false;
                }

                //Simple file not found exception
                catch (FileNotFoundException e) {
                    System.out.println("File not found.");
                }
            }
        } while (needNewName);

        //Adds each Car object from the carCollection array to the ordered list array
        for (Car car : carCollection) {
            orderedList.add(car);
        }

        /**
         * A loop that prints the number of and list of cars in the ordered list into the console for checking
         *
         * CSC 1351 Programming Project No 1
         * Section 2, 930-1030
         *
         * @author Ashton Fox
         * @since 3/16/24
         * @due 3/17/24
         */
        System.out.println("Number of cars: " + orderedList.size());
        for (int i = 0; i < orderedList.size(); i++) {
            Car car = (Car) orderedList.get(i);
            System.out.println("Make: " + car.getMake());
            System.out.println("Year: " + car.getYear());
            System.out.println("Price: $" + car.getPrice());
            System.out.println();
        }

        /**
         * Prints the string created from each object in the ordered list into an organized text file
         *
         * CSC 1351 Programming Project No 1
         * Section 2, 930-1030
         *
         * @author Ashton Fox
         * @since 3/16/24
         * @due 3/17/24
         */
        PrintWriter outFile = null;
        try {
            String outputFile = getOutFileName("Enter output file name and ensure your include '.txt': ");
            outFile = new PrintWriter(outputFile);
            outFile.println(orderedList.toString());
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        finally {
            if (outFile != null) {
                outFile.close();
            }
        }

    }

    /**
     * Asks the user to enter name for a file to print
     *
     * CSC 1351 Programming Project No 1
     * Section 2, 930-1030
     *
     * @author Ashton Fox
     * @since 3/16/24
     * @due 3/17/24
     */
    public static String getOutFileName(String userPrompt) {
        Scanner newFileName = new Scanner(System.in);
        while (true) {
            System.out.print(userPrompt);
            String fileName = newFileName.nextLine();
            File file = new File(fileName);
            if (file.exists()) {
                System.out.println("File already exists. Please choose a different filename.");
            }
            else {
                newFileName.close();
                return fileName;
            }
        }
    }
}