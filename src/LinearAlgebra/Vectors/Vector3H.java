package LinearAlgebra.Vectors;

//TODO: Add methods for linear transformations

/**
 * A class for managing 3D homogenous vectors
 */
public class Vector3H extends Vector {

    public Vector3H(Double initialValue, Double homogenousValue) {
        super(4, initialValue);
        this.elements[3] = homogenousValue;
    }

    public Vector3H(Double[] elements) {
        super(elements);

        if (elements.length != 4) {
            throw new InvalidVectorLengthException("Vector3H must have 4 elements, three coordinate points and a homogenous coordinate");
        }
    }

    public Vector3H() {
        super(4, 0.0);
        this.elements[3] = 1.0;
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
     * Shorthand for retrieving the fourth, homogenous, element of the Vector
     * @return element at index 3
     */
    public Double w() {
        return this.elements[3];
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

    /**
     * Shorthand for changing the fourth, homegenous, element of the Vector
     * @param newValue The new value for the element
     */
    public void w(double newValue) {
        requeryMag = true;
        this.elements[3] = newValue;
    }
}
