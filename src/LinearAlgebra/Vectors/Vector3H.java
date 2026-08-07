package LinearAlgebra.Vectors;

//TODO: Add methods for linear transformations

import org.jetbrains.annotations.NotNull;

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
    public Vector3H(@NotNull Double[] elements) {
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
    public synchronized Double x() {
        return this.elements[0];
    }

    /**
     * Shorthand for retrieving the second element of the Vector
     * @return element at index 1
     */
    public synchronized Double y() {
        return this.elements[1];
    }

    /**
     * Shorthand for retrieving the third element of the Vector
     * @return element at index 2
     */
    public synchronized Double z() {
        return this.elements[2];
    }

    /**
     * Shorthand for retrieving the fourth, homogenous, element of the Vector
     * @return element at index 3
     */
    public synchronized Double w() {
        return this.elements[3];
    }

    /**
     * Shorthand for changing the first element of the Vector
     * @param newValue The new value for the element
     */
    public synchronized void x(Double newValue) {
        requeryMag = true;
        this.elements[0] = newValue;
    }

    /**
     * Shorthand for changing the second element of the Vector
     * @param newValue The new value for the element
     */
    public synchronized void y(Double newValue) {
        requeryMag = true;
        this.elements[1] = newValue;
    }

    /**
     * Shorthand for changing the third element of the Vector
     * @param newValue The new value for the element
     */
    public synchronized void z(Double newValue) {
        requeryMag = true;
        this.elements[2] = newValue;
    }

    /**
     * Shorthand for changing the fourth, homegenous, element of the Vector
     * @param newValue The new value for the element
     */
    public synchronized void w(double newValue) {
        requeryMag = true;
        this.elements[3] = newValue;
    }

    /**
     * Rotates the vector about the x-axis
     * @param angle The angle in radians
     */
    public synchronized void rotateX(Double angle) {
        this.y(this.y() * Math.cos(angle) + this.z() * Math.sin(angle));
        this.z(-this.y() * Math.sin(angle) + this.z() * Math.cos(angle));
    }

    /**
     * Rotates the vector about the y-axis
     * @param angle The angle in radians
     */
    public synchronized void rotateY(Double angle) {
        this.x(this.x() * Math.cos(angle) - this.z() * Math.sin(angle));
        this.z(this.x() * Math.sin(angle) + this.z() * Math.cos(angle));
    }

    /**
     * Rotates the vector about the z-axis
     * @param angle The angle in radians
     */
    public synchronized void rotateZ(Double angle) {
        this.x(this.x() * Math.cos(angle) + this.y() * Math.sin(angle));
        this.y(-this.x() * Math.sin(angle) + this.y() * Math.cos(angle));
    }

    /**
     * Scales the x component by the given amount
     * @param scalar The scalar multiple
     */
    public synchronized void scaleX(Double scalar) {
        this.scaleEl(0, scalar);
    }

    /**
     * Scales the y component by the given amount
     * @param scalar The scalar multiple
     */
    public synchronized void scaleY(Double scalar) {
        this.scaleEl(1, scalar);
    }

    /**
     * Scales the z component by the given amount
     * @param scalar The scalar multiple
     */
    public synchronized void scaleZ(Double scalar) {
        this.scaleEl(2, scalar);
    }

    /**
     * Translates the x value the given amount, note that you do not need to account for your homogenous value in your translation
     * @param amount The amount to translate
     * @throws ArithmeticException if the homogenous value is 0
     * @throws NullPointerException if x or w is null
     */
    public synchronized void translateX(Double amount) {
        // Technically we can translate without a homogenous coordinate since this isn't actual linear algebra, however it's my goal to emulate it for the most part
        // So we'll make this operation illegal
        if (this.w() == 0) throw new ArithmeticException("Cannot translate with 0 homogenous value");

        // I will however allow them to pass their amount directly since you could technically translate by any non-zero value regardless of your homogenous value
        // i.e. w = 1/3 :: this.x(this.x() + amount * (w / w)) = this.x(this.x() + amount)
        this.x(this.x() + amount);
    }

    /**
     * Translates the y value the given amount, note that you do not need to account for your homogenous value in your translation
     * @param amount The amount to translate
     * @throws ArithmeticException if the homogenous value is 0
     * @throws NullPointerException if y or w is null
     */
    public synchronized void translateY(Double amount) {
        // Technically we can translate without a homogenous coordinate since this isn't actual linear algebra, however it's my goal to emulate it for the most part
        // So we'll make this operation illegal
        if (this.w() == 0) throw new ArithmeticException("Cannot translate with 0 homogenous value");

        // I will however allow them to pass their amount directly since you could technically translate by any non-zero value regardless of your homogenous value
        // i.e. w = 1/3 :: this.x(this.x() + amount * (w / w)) = this.x(this.x() + amount)
        this.y(this.y() + amount);
    }

    /**
     * Translates the z value the given amount, note that you do not need to account for your homogenous value in your translation
     * @param amount The amount to translate
     * @throws ArithmeticException if the homogenous value is 0
     * @throws NullPointerException if z or w is null
     */
    public synchronized void translateZ(Double amount) {
        // Technically we can translate without a homogenous coordinate since this isn't actual linear algebra, however it's my goal to emulate it for the most part
        // So we'll make this operation illegal
        if (this.w() == 0) throw new ArithmeticException("Cannot translate with 0 homogenous value");

        // I will however allow them to pass their amount directly since you could technically translate by any non-zero value regardless of your homogenous value
        // i.e. w = 1/3 :: this.x(this.x() + amount * (w / w)) = this.x(this.x() + amount)
        this.z(this.z() + amount);
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
