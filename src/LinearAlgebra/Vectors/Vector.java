package LinearAlgebra.Vectors;


import java.util.Arrays;

//TODO: Implement Normalize method
//TODO: Synchronize methods
//TODO: Implement method for Dot Product
//TODO: Implement method for cross product
//TODO: Implement method for multiplication by matrix

/**
 * Class for creating and managing Vectors
 */
public class Vector extends AbstractVector {
    /**
     * Creates a new Vector with a set size and initial values for all elements
     * @param size The size of the Vector
     * @param initialValue The value to initialize all elements to
     * @throws IllegalArgumentException If Vector has size less than or equal to 1
     */
    public Vector(int size, Double initialValue) {
        if (size <= 1) throw new IllegalArgumentException("Vectors must have size greater than 1");

        this.elements = new Double[size];
        Arrays.fill(elements, initialValue);

        this.length = size * initialValue;
        requeryLength = false;
    }

    /**
     * Creates a new Vector with a set size and 0.0 starting values
     * @param size The size of the Vector
     * @throws IllegalArgumentException If Vector has size less than or equal to 1
     */
    public Vector(int size) {
        this(size, 0.0);
    }

    /**
     * Creates a new Vector from an array of Doubles
     * @param elements The elements for the new Vector
     * @throws IllegalArgumentException If Vector has size less than or equal to 1
     */
    public Vector(Double[] elements) {
        if (elements.length <= 1) throw new IllegalArgumentException("Vectors must have size greater than 1");
        this.elements = elements;
    }

    /**
     * Shorthand for retrieving the first element of the Vector
     * @return element at index 0
     */
    public Double x() {
        return this.elements[0];
    }

    /**
     * Shorthand for retrieving the second element of the Vector
     * @return element at index 1
     */
    public Double y() {
        return this.elements[1];
    }

    /**
     * Shorthand for retrieving the third element of the Vector
     * @return element at index 2
     */
    public Double z() {
        return this.elements[2];
    }

    /**
     * Shorthand for changing the first element of the Vector
     * @param newValue The new value for the element
     */
    public void x(Double newValue) {
        requeryLength = true;
        this.elements[0] = newValue;
    }

    /**
     * Shorthand for changing the second element of the Vector
     * @param newValue The new value for the element
     */
    public void y(Double newValue) {
        requeryLength = true;
        this.elements[1] = newValue;
    }

    /**
     * Shorthand for changing the third element of the Vector
     * @param newValue The new value for the element
     */
    public void z(Double newValue) {
        requeryLength = true;
        this.elements[2] = newValue;
    }




    // Tester code
    public static void main(String[] args) {
        Vector v = new Vector(5, 1.0);


    }
}