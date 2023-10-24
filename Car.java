/**
* Implementation of a car to demonstrate the function of aOrderedList
*
* CSC 1351 Programming Project No 1
*
* Section 2
*
* @author Ricky Liang
* @since 10/23
*
*/

public class Car implements Comparable<Car> {
    private String make;
    private int year;
    private int price;

/**
* Constructor for a Car
*
* CSC 1351 Programming Project No 1
*
* Section 2
*
* @author Ricky Liang
* @since 10/23
*
*/
    public Car(String Make, int Year, int Price) {
        this.make = Make;
        this.year = Year;
        this.price = Price;
    }
/**
* returns the make of a car
*
* CSC 1351 Programming Project No 1
*
* Section 2
*
* @author Ricky Liang
* @since 10/23
*
*/
    public String getMake() {
        return make;
    }

/**
* returns the year of a car
*
* CSC 1351 Programming Project No 1
*
* Section 2
*
* @author Ricky Liang
* @since 10/23
*
*/
    public int getYear() {
        return year;
    }
/**
* returns the price of a car
*
* CSC 1351 Programming Project No 1
*
* Section 2
*
* @author Ricky Liang
* @since 10/23
*
*/
    public int getPrice() {
        return price;
    }
/**
* override of Comparable's compareTo. makes the car with a larger alphabetical value (a > z) come first.
* if makes are the same, then the bigger year will be counted as the larger one.
*
* CSC 1351 Programming Project No 1
*
* Section 2
*
* @author Ricky Liang
* @since 10/23
*
*/
    @Override
    public int compareTo(Car other) {
        int out = -1 * (this.make.compareTo(other.make));
        if (out == 0) { //same make
            if (this.year > other.year) {
                out = 1;
            } else {
                out = -1;
            }
        }
        return out;
    }
/**
* prints the details of a car
*
* CSC 1351 Programming Project No 1
*
* Section 2
*
* @author Ricky Liang
* @since 10/23
*
*/
    public String toString() {
        return "Make: " + make + ", Year: " + year + ", Price: " + price + ";";
    }
}
