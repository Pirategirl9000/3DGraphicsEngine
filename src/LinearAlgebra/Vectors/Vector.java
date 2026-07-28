package LinearAlgebra.Vectors;


import java.util.Arrays;

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
}