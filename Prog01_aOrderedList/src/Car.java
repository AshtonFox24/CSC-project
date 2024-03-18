/**
 * The Car class constructs objects, puts them in an array, and uses the Comparable<Car> interface to organize them
 *
 * CSC 1351 Programming Project No 1
 * Section 2, 0930-1020
 *
 * @author Ashton Fox
 * @since 2/16/24
 * @due 3/17/24
 */
class Car implements Comparable<Car> {
    private String make;
    private int year;
    private int price;

    /**
     * Constructor to make a Car object
     *
     * CSC 1351 Programming Project No 1
     * Section 2, 930-1030
     *
     * @author Ashton Fox
     * @since 2/16/24
     * @due 3/17/24
     */
    public Car(String make, int year, int price) {
        this.make = make;
        this.year = year;
        this.price = price;
    }

    //returns the make of a specific object
    public String getMake() {
        return make;
    }

    //returns the year of a specific object
    public int getYear() {
        return year;
    }

    //returns the price of a specific object
    public int getPrice() {
        return price;
    }

    /**
     * Creates a string for the object, specifying make, year, and model
     *
     * CSC 1351 Programming Project No 1
     * Section 2, 930-1030
     *
     * @author Ashton Fox
     * @since 2/16/24
     * @due 3/17/24
     */
    @Override
    public String toString() {
        return "Make: " + make + ", Year: " + year + ", Price: $" + price;
    }

    /**
     * Compare method that orders the items in the array by make, then by year, ignoring price
     *
     * CSC 1351 Programming Project No 1
     * Section 2, 930-1030
     *
     * @author Ashton Fox
     * @since 2/16/24
     * @due 3/17/24
     */
    @Override
    public int compareTo(Car other) {
        if (make.compareTo(other.make) == 0) {
            return Integer.compare(year, other.year);
        } else {
            return make.compareTo(other.make);
        }
    }
}
