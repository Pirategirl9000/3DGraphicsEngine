package LinearAlgebra.Vectors;

/**
 * Vector for managing 3D objects
 */
public class Vector3 extends Vector {
    /**
     * Creates a new 3D vector with an initial value
     * @param initialValue The initial value of the 3 elements
     */
    public Vector3(Double initialValue) {
        super(3, initialValue);
    }

    /**
     * Creates a new 3D vector from an array of doubles
     * @param elements The array of doubles for the vector to use
     */
    public Vector3(Double[] elements) {
        super(elements);

        if (elements.length != 3) {
            throw new IllegalArgumentException("Vector3 must contain only 3 elements");
        }
    }

    public Vector3(Double x, Double y, Double z) {
        super(new Double[] {x,y,z});
    }

    /**
     * Creates a new 3D vector with initial values of 0.0
     */
    public Vector3() {
        super(3, 0.0);
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
        requeryMag = true;
        this.elements[0] = newValue;
    }

    /**
     * Shorthand for changing the second element of the Vector
     * @param newValue The new value for the element
     */
    public void y(Double newValue) {
        requeryMag = true;
        this.elements[1] = newValue;
    }

    /**
     * Shorthand for changing the third element of the Vector
     * @param newValue The new value for the element
     */
    public void z(Double newValue) {
        requeryMag = true;
        this.elements[2] = newValue;
    }
}
