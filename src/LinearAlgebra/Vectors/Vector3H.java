package LinearAlgebra.Vectors;

//TODO: Add methods for linear transformations

/**
 * A class for managing 3D homogenous vectors
 */
public class Vector3H extends Vector {

    /**
     * Creates a new homogenous vector with initial x, y, and z value and a seperate unique homogenous value
     * @param initialValue The value for x, y, and z
     * @param homogenousValue The value for the homogenous coordinate
     */
    public Vector3H(Double initialValue, Double homogenousValue) {
        super(4, initialValue);
        this.elements[3] = homogenousValue;
    }

    /**
     * Creates a new homogenous 3D vector from an array of elements
     * @param elements The elements for the Vector
     * @throws InvalidVectorLengthException if array is not 4 elements long
     */
    public Vector3H(Double[] elements) {
        super(elements);

        if (elements.length != 4) {
            throw new InvalidVectorLengthException("Vector3H must have 4 elements, three coordinate points and a homogenous coordinate");
        }
    }

    /**
     * Creates an empty homogenous 3D vector using default values
     */
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

    /**
     * Method for testing the class
     * @param args None
     * For testing of superclass and its methods see {@link Vector#main(String[])}
     */
    public static void main(String[] args) {
        // Testing construction
        System.out.println("Testing valid construction");
        Vector3H v = new Vector3H();
        Vector3H v2 = new Vector3H(3d, 1d);
        Vector3H v3 = new Vector3H(new Double[] {1d, 2d, 3d, 1d});

        // Testing invalid construction
        System.out.println("Testing invalid construction");
        try {
            new Vector3H(new Double[] {1d, 1d, 1d, 1d, 1d});
        } catch (InvalidVectorLengthException e) {
            System.out.println(e.getMessage());
        }

        // Testing specialized getters
        System.out.println("Testing specialized getters");
        System.out.println(v.x());
        System.out.println(v.y());
        System.out.println(v.z());
        System.out.println(v.w());

        // Testing specialized setters
        System.out.println("Testing specialized setters: setting all 6's");
        v.x(6d);
        v.y(6d);
        v.z(6d);
        v.w(6d);
        System.out.println(v);
    }
}
