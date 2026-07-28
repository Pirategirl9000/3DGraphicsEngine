package LinearAlgebra.Vectors;

//TODO: Add shortcuts for performing linear transformations on these vectors

/**
 * Vector for managing 3D objects
 */
public class Vector3 extends Vector {
    /**
     * Creates a new 3D vector from an array of doubles
     * @param elements The array of doubles for the vector to use
     */
    public Vector3(Double[] elements) {
        this(elements[0], elements[1], elements[2]);

        if (elements.length > 3) throw new InvalidVectorLengthException("Vector3 must have length of 3");
    }

    /**
     * Creates a new 3D vector with x, y, and z values
     * @param x The value for the first element
     * @param y The value for the second element
     * @param z The value for the third element
     */
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
     * Returns the cross product of this Vector3 and another
     * @param v2 The other Vector3 to calculate the cross product of
     * @return resultant Vector3
     */
    public Vector3 crossProduct(Vector3 v2) {
        return new Vector3(
                this.y() * v2.z() + this.z() * v2.y(),
                this.z() * v2.x() + this.x() * v2.z(),
                this.x() * v2.y() + this.y() * v2.x()
        );
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
