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
     * @throws InvalidVectorLengthException If Vector has size less than or equal to 1
     */
    public Vector(int size, Double initialValue) {
        if (size <= 1) throw new InvalidVectorLengthException("Vectors must have size greater than 1");

        this.elements = new Double[size];
        Arrays.fill(elements, initialValue);
    }

    /**
     * Creates a new Vector with a set size and 0.0 starting values
     * @param size The size of the Vector
     * @throws InvalidVectorLengthException If Vector has size less than or equal to 1
     */
    public Vector(int size) {
        this(size, 0.0);
    }

    /**
     * Creates a new Vector from an array of Doubles
     * @param elements The elements for the new Vector
     * @throws InvalidVectorLengthException If Vector has size less than or equal to 1
     */
    public Vector(Double[] elements) {
        if (elements.length <= 1) throw new InvalidVectorLengthException("Vectors must have size greater than 1");
        this.elements = elements;
    }

    /**
     * Gets the normal and returns it
     * @see #normalize()
     * @return Normalized vector
     * @throws ArithmeticException if it's a 0 length vector due to divide by zero error
     */
    public Vector getNormalized() {
        double inverseMag = 1 / getMag();  // We calculate the inverse so normalization can use multiplication instead of repeated division

        Double[] newVectorArray = new Double[elements.length];

        for (int i = 0; i < elements.length; i++) {
            newVectorArray[i] = inverseMag * elements[i];
        }

        return new Vector(newVectorArray);
    }
}