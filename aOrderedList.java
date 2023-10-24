/**
* A sorted, partially filled array
*
* CSC 1351 Programming Project No 1
*
* Section 2
*
* @author Ricky Liang
* @since 10/23
*
*/

import java.util.Arrays;

public class aOrderedList {
    final int SIZEINCREMENTS = 20;

    private Comparable[] oList;
    private int listSize;
    private int numObjects;
    private int curr;

/**
* default constructor of aOrderedList
*
* CSC 1351 Programming Project No 1
*
* Section 2
*
* @author Ricky Liang
* @since 10/23
*
*/
    public aOrderedList() {
        numObjects = 0;
        curr = 0;
        listSize = SIZEINCREMENTS;
        oList = new Car[SIZEINCREMENTS];
    }

/**
* adds a new object into the list at its appropriate sorted location
*
* CSC 1351 Programming Project No 1
*
* Section 2
*
* @author Ricky Liang
* @since 10/23
*
*/
    public void add(Comparable newObject) {
        if (numObjects == 0) { //initialization (if array is empty)
            oList[numObjects] = newObject;
            numObjects++;
            // System.out.println("intialized");
            return;
        }
        
        boolean insert = false;
        for (int i = 0; i < numObjects; i++) { //places the new object into its correct positioning
            if (newObject.compareTo(oList[i]) >= 0) { //means found correct space in the middle of the array
                if (numObjects + 1 > oList.length) { //make new array if no space left
                    Comparable[] copy = Arrays.copyOf(oList, listSize + SIZEINCREMENTS);
                    listSize += SIZEINCREMENTS;
                    oList = copy;
                    System.out.println("made new array");
                }
                for (int j = numObjects; j > i; j--) { //swap all elements down after insertion
                    Comparable temp = oList[j];
                    oList[j] = oList[j - 1];
                    oList[j - 1] = temp;
                }
                oList[i] = newObject; //insert the element
                numObjects++;
                insert = true;
                break;
            }
        }
        if (!insert) { //handles the case where new object is the lowest alphabetically
            oList[numObjects] = newObject;
            numObjects++;
        }
    }

/**
* returns how many objects are in the list
*
* CSC 1351 Programming Project No 1
*
* Section 2
*
* @author Ricky Liang
* @since 10/23
*
*/
    public int size() {
        return numObjects;
    }
/**
* returns an element in the list at a given index
*
* CSC 1351 Programming Project No 1
*
* Section 2
*
* @author Ricky Liang
* @since 10/23
*
*/
    public Comparable get(int index) {
        return oList[index];
    }
/**
* returns if the list is empty or not
*
* CSC 1351 Programming Project No 1
*
* Section 2
*
* @author Ricky Liang
* @since 10/23
*
*/
    public boolean isEmpty() {
        if (listSize == 0) {
            return true;
        } else {
            return false;
        }
    }
/**
* removes the element at a given index
*
* CSC 1351 Programming Project No 1
*
* Section 2
*
* @author Ricky Liang
* @since 10/23
*
*/
    public void remove(int index) {
        oList[index] = null;
        if (index < numObjects - 1) { //if removed element was not the latest element
            for (int i = index; i < numObjects; i++) { //swap all elements down after deletion to fill up space
                Comparable temp = oList[i];
                oList[i] = oList[i + 1];
                oList[i + 1] = temp;
            }
        } 
        numObjects--;
    }
/**
* resets the iterator value such that the value points to the beginning of the list
*
* CSC 1351 Programming Project No 1
*
* Section 2
*
* @author Ricky Liang
* @since 10/23
*
*/
    public void reset() {
        curr = 0;
    }
/**
* returns the element at the index the iterator points to and increments the iterator
*
* CSC 1351 Programming Project No 1
*
* Section 2
*
* @author Ricky Liang
* @since 10/23
*
*/
    public Comparable next() {
        Comparable out = oList[curr]; //need to define out here before changing curr
        curr++;
        return out;
    }
/**
* returns if the element at the iterator's value exists 
*
* CSC 1351 Programming Project No 1
*
* Section 2
*
* @author Ricky Liang
* @since 10/23
*
*/
    public boolean hasNext() {
        if (oList[curr] != null) {
            return true;
        } else {
            return false;
        }
    }
/**
* removes the last element returned by the iterator. if nothing has been returned yet, there is nothing to return and therefore nothing to delete
*
* CSC 1351 Programming Project No 1
*
* Section 2
*
* @author Ricky Liang
* @since 10/23
*
*/
    public void remove() {
        if (curr == 0) { //if curr is 0, then next() has never been called and therefore nothing to delete
            System.out.println("cannot remove"); //debugging statement
        } else {
            remove(curr - 1);
        }
    }
/**
* if oList is of type car, then we can target delete a car of a given make and year
*
* CSC 1351 Programming Project No 1
*
* Section 2
*
* @author Ricky Liang
* @since 10/23
*
*/
    public void remove(String make, int year) {
        if (numObjects == 0) { //if list is empty, do nothing and break
            // System.out.println("no objects exists with " + make + " " + year); //debugging statement
            return;
        }
        if (!(oList[0] instanceof Car)) { //if list is not made of cars then do nothing and break
            // System.out.println("not a car list");  //debugging statement
            return;
        }
        for (int i = 0; i < numObjects; i++) {
            Car car = (Car) oList[i];
            if (car.getMake().equals(make) && car.getYear() == year) { //if current car matches description, delete and break out
                remove(i);
                return;
            }
        }
            // System.out.println("no objects exists with " + make + " " + year); //debugging statement  
    }

/**
* prints out information of objects stored in the list 
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
        String out = "";
        for (int i = 0; i < numObjects; i++) {
            out = out + "[" + oList[i].toString() + "]";
        }
        return out;
    }
}