package LinearAlgebra.Vectors;

//TODO: Add shortcuts for performing linear transformations on these vectors

import org.jetbrains.annotations.NotNull;

/**
 * Vector for managing 3D objects
 */
public class Vector3 extends Vector {
    /**
     * Creates a new 3D vector from an array of doubles
     * @param elements The array of doubles for the vector to use
     */
    public Vector3(@NotNull Double[] elements) {
        super(elements);

        if (elements.length != 3) throw new InvalidVectorLengthException("Vector3 must have length of 3");
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
     * Creates a new 3D vector with all elements having an initial value
     * @param initialValue The initial value for the elements
     */
    public Vector3(Double initialValue) {
        super(3, initialValue);
    }

    /**
     * Creates a new 3D vector with initial values of 0.0
     */
    public Vector3() {
        super(3, 0.0);
    }

    /**
     * Calculates the cross product of this Vector3 with another
     * @param v2 The other Vector to calculate the cross product of
     * @return resultant Vector3
     */
    public Vector3 crossProduct(@NotNull Vector v2) {
        if (v2.size() != 3) throw new VectorLengthMismatch("Passed Vector does not have size 3");

        return new Vector3(
                this.y() * v2.get(2) - this.z() * v2.get(1),
                this.z() * v2.get(0) - this.x() * v2.get(2),
                this.x() * v2.get(1) - this.y() * v2.get(0)
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

    /**
     * Method for testing the class
     * @param args None
     * For testing of superclass and its methods see {@link Vector#main(String[])}
     */
    public static void main(String[] args) {
        // Test regular construction
        System.out.println("Testing valid vector constructions");
        Vector3 v = new Vector3(new Double[] {1d, 2d, 3d});
        Vector3 v2 = new Vector3(3d, 2d, 1d);
        Vector3 v3 = new Vector3();
        Vector3 v4 = new Vector3(4d);

        // Test invalid construction
        System.out.println("Testing invalid constructions");
        try {
            new Vector3(new Double[] {1d, 1d, 1d, 1d});
            System.out.println("No except");
        } catch (InvalidVectorLengthException e) {
            System.out.println(e.getMessage());
        }

        // Test indexing via specialized methods
        System.out.println("Testing indexing via Vector3 getter shortcuts");
        System.out.println(v.x());
        System.out.println(v.y());
        System.out.println(v.z());

        // Test alteration via specialized methods
        System.out.println("Testing alteration via Vector3 setter shortcuts: Setting all 6's");
        v.x(6d);
        v.y(6d);
        v.z(6d);

        System.out.println(v);

        // Test valid cross product
        System.out.println("Testing cross product");
        // v =  <6, 6, 6>
        // v2 = <3, 2, 1>
        System.out.println(v.crossProduct(v2));

        // Testing invalid cross product
        System.out.println("Testing invalid cross product");
        Vector bigvec = new Vector(new Double[] {1d,1d,1d,1d});
        try {
            v.crossProduct(bigvec);
        } catch (VectorLengthMismatch e) {
            System.out.println(e.getMessage());
        }
    }
}
