/**
* Application of program
*
* CSC 1351 Programming Project No 1
*
* Section 2
*
* @author Ricky Liang
* @since 10/23
*
*/

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
public class Prog01_aOrderedList {
    
    public static void main(String[] args) throws FileNotFoundException{
        Scanner sysInput = new Scanner(System.in); //takes in input from user
        Scanner fileInput = new Scanner(System.in); //defning file input to appease the ide

        aOrderedList oList = new aOrderedList();

        boolean validName = false; //stores if a valid file name has been input. mostly for readability purposes instead of function

        while (!validName) { //handles proper input file
            System.out.print("Enter Input File Path: ");
            String fileName = sysInput.next();
            validName = true; //assumes correct name has been input
            
            try {
                fileInput = GetInputFile(fileName);

            } catch(FileNotFoundException e) {

                boolean validChoice = false; //true if user responds with Y or N is input

                while (!validChoice) { //repeats prompt if response is not Y or N
                    System.out.println("File specified <" + fileName  + "> does not exist. Would you like to continue? <Y/N>");
                    String choice = sysInput.next();

                    validName = false;
                    if (choice.equals("Y")) {
                        validChoice = true;
                    } else if (choice.equals("N")) {
                        validName = true; //for the sake of breaking out of while
                        validChoice = true;
                        throw new FileNotFoundException("Invalid File!");
                    } else {
                        System.out.println("Please choose Y or N");
                    }
                }
            }
        }
                
        while (fileInput.hasNextLine()) { //process input
            String inp = fileInput.nextLine();
            String[] inputs = inp.split(","); //divides input into processable steps
            if (inputs[0].equals("A")) { //handles adding
                Car car = new Car(inputs[1], Integer.parseInt(inputs[2]), Integer.parseInt(inputs[3]));
                oList.add(car);
            } else if (inputs[0].equals("D")) { //handles deletion
                if (inputs.length == 2) { //handles deletion case of deleting at given index
                    oList.remove(Integer.parseInt(inputs[1]));
                } else if (inputs.length == 3) { //handles deletion case of deleting specifc make and year
                    oList.remove(inputs[1], Integer.parseInt(inputs[2]));
                }
            }
        }
        fileInput.close(); //close file input

        PrintWriter fileOutput = new PrintWriter(System.out); //fully define a file output to appease ide

        validName = false; //reset variables

        while (!validName) { //handles proper output file (same code as handling a proper input file)
            System.out.print("Enter Output File Path: ");
            String fileName = sysInput.next();
            validName = true;
            
            try {
                fileOutput = GetOutputFile(fileName);

            } catch(FileNotFoundException e) {

                boolean validChoice = false;

                while (!validChoice) { //repeats prompt if response is not Y or
                    System.out.println("File specified <" + fileName  + "> does not exist. Would you like to continue? <Y/N>");
                    String choice = sysInput.next();

                    validName = false;
                    if (choice.equals("Y")) {
                        validChoice = true;
                    } else if (choice.equals("N")) {
                        validName = true; //for the sake of breaking out of while
                        validChoice = true;
                        throw new FileNotFoundException("Invalid File!");
                    } else {
                        System.out.println("Please choose Y or N");
                    }
                }
            }
        }
        
        sysInput.close(); //close user input

        //output
        fileOutput.println("Number of cars: " + oList.size()); //prints header
        String oListString = oList.toString(); //stores string version of olist
        String[] listStrings = oListString.split(";]"); //breaks olist string into each individual element string
        for (int i = 0; i < listStrings.length; i++) {
            String[] carStrings = listStrings[i].split(", "); //further breaks down the car string into properties
            carStrings[0] = carStrings[0].substring(1, carStrings[0].length()); //get rid of the [ in make
            
            fileOutput.println();
            for (int j = 0; j < carStrings.length; j++) {
                String[] property = carStrings[j].split(" ");
                if (j == 2) { //handles printing prices
                    String processedPrice = "$" + String.format("%,d", Integer.parseInt(property[1])); //handles adding commas and a dollar sign to the price
                    fileOutput.printf("%-8s\t%8s%n", property[0], processedPrice);

                } else {
                    fileOutput.printf("%-8s\t%8s%n", property[0], property[1]);
                }
            }

            fileOutput.println();
        }
        
        fileOutput.close();
    }

/**
* Attempts to locate an input file of a given name. If the file cannot be found, it throws filenotfound exception
*
* CSC 1351 Programming Project No 1
*
* Section 2
*
* @author Ricky Liang
* @since 10/23
*
*/
    public static Scanner GetInputFile(String UserPrompt) throws FileNotFoundException {
        File f = new File(UserPrompt);
        
        if (!f.exists()) { //searches for a file that meets given path
            throw new FileNotFoundException("Invalid File!");
        }

        Scanner out = new Scanner(f);
        return out;
    }
/**
* Attempts to locate an output file of a given name. If the file cannot be found, it throws filenotfound exception
*
* CSC 1351 Programming Project No 1
*
* Section 2
*
* @author Ricky Liang
* @since 10/23
*
*/
    public static PrintWriter GetOutputFile(String UserPrompt) throws FileNotFoundException { //same code as findInput
        File f = new File(UserPrompt);
        
        if (!f.exists()) {
            throw new FileNotFoundException("Invalid File!");
        }
        
        PrintWriter writer = new PrintWriter(f);
        return writer;
    }

}
