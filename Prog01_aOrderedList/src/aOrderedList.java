import java.util.Iterator;
/**
 * The Ordered List class takes the Array List of car objects and creates a list of objects to be
 * converted to a string for printing/file writing.
 *
 * CSC 1351 Programming Project No 1
 * Section 2, 0930-1020
 *
 * @author Ashton Fox
 * @since 3/16/24
 * @due 3/17/24
 */
public class aOrderedList<T extends Comparable<T>> implements Iterable<T> {
    private final int SIZE_INCREMENTS = 20;
    private T[] oList;
    private int listSize;
    private int numObjects;
    private int curr; //index of current element accessed via iterator methods

    /**
     * This is a constructor method to create an array of car objects
     *
     * CSC 1351 Programming Project No 1
     * Section 2, 930-1030
     *
     * @author Ashton Fox
     * @since 3/16/24
     * @due 3/17/24
     */
    public aOrderedList() {
        numObjects = 0;
        listSize = SIZE_INCREMENTS;
        oList = (T[]) new Comparable[listSize];
        curr = -1;
    }

    /**
     * Adds a generic object to the list and increases the numObjects variable
     *
     * CSC 1351 Programming Project No 1
     * Section 2, 930-1030
     *
     * @author Ashton Fox
     * @since 3/16/24
     * @due 3/17/24
     */
    public void add(T newObject) {
        if (numObjects == listSize) {
            increaseListSize();
        }

        int index = 0;
        while (index < numObjects && (oList[index] == null || oList[index].compareTo(newObject) < 0)) {
            index++;
        }

        for (int i = numObjects; i > index; i--) {
            oList[i] = oList[i - 1];
        }

        oList[index] = newObject;
        numObjects++;
    }

    /**
     * If an object were to be added to the list after it reaches it's maximum size (starting 20),
     * this will add 20 more indexes to be filled and move the contents of the first array
     * into the new one
     *
     * CSC 1351 Programming Project No 1
     * Section 2, 930-1030
     *
     * @author Ashton Fox
     * @since 3/16/24
     * @due 3/17/24
     */
    private void increaseListSize() {
        T[] newList = (T[]) new Comparable[listSize + SIZE_INCREMENTS];
        System.arraycopy(oList, 0, newList, 0, numObjects);
        oList = newList;
        listSize += SIZE_INCREMENTS;
    }

    //gives the number of objects in the list
    public int size() {
        return numObjects;
    }

    /**
     * Returns the object at a given index, as long as the index is within the list
     *
     * CSC 1351 Programming Project No 1
     * Section 2, 930-1030
     *
     * @author Ashton Fox
     * @since 3/16/24
     * @due 3/17/24
     */
    public T get(int index) {
        if (index < 0 || index >= numObjects) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + numObjects);
        }
        return oList[index];
    }

    //Could set the number of objects to 0
    public boolean isEmpty() {
        return numObjects == 0;
    }

    /**
     * This method would remove an object from the ordered list, but I
     * remove the items during the carCollection Array phase
     *
     * CSC 1351 Programming Project No 1
     * Section 2, 930-1030
     *
     * @author Ashton Fox
     * @since 3/16/24
     * @due 3/17/24
     */
    public void remove() {
        if (curr < 0 || curr >= numObjects) {
            throw new IndexOutOfBoundsException("Index: " + curr + ", Size: " + numObjects);
        }
        for (int i = curr; i < numObjects - 1; i++) {
            oList[i] = oList[i + 1];
        }
        numObjects--;
    }

    //Resets the current iteration element
    public void reset() {
        curr = -1;
    }

    //Checks if there's another object
    public boolean hasNext() {
        return curr < numObjects - 1;
    }

    //Generic to move to the next iteration
    public T next() {
        if (!hasNext()) {
            throw new IndexOutOfBoundsException("No more elements in the list");
        }
        curr++;
        return oList[curr];
    }

    /**
     * This methods allows the iterating of different elements in the ordered list in sequence
     *
     * CSC 1351 Programming Project No 1
     * Section 2, 930-1030
     *
     * @author Ashton Fox
     * @since 3/16/24
     * @due 3/17/24
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = -1;

            @Override
            public boolean hasNext() {
                return currentIndex < numObjects - 1;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new IndexOutOfBoundsException("No more elements in the list");
                }
                currentIndex++;
                return oList[currentIndex];
            }
        };
    }

    /**
     * This method converts the ordered list into an organized string with each object having its
     * own line and each feature separated by a comma
     *
     * CSC 1351 Programming Project No 1
     * Section 2, 930-1030
     *
     * @author Ashton Fox
     * @since 3/16/24
     * @due 3/17/24
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numObjects; i++) {
            sb.append(oList[i].toString());
            if (i < numObjects - 1) {
                sb.append(", \n");
            }
        }
        return sb.toString();
    }
}
