package LinearAlgebra.Vectors;


import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


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
    public Vector(@NotNull Double... elements) {
        if (elements.length <= 1) throw new InvalidVectorLengthException("Vectors must have size greater than 1");
        this.elements = elements;
    }

    /**
     * Creates a new Vector from a Collection of Doubles (can be used to create a copy of a vector)<br><br>
     * Uses the Iterator for the collection so some Collection data structures may not be optimized for this
     * @param elements The elements for the new vector
     */
    public Vector(@NotNull Collection<Double> elements) {
        // The polymorphism on display here feels like pure euphoria
        if (elements.size() <= 1) throw new InvalidVectorLengthException("Vectors must have size greater than 1");

        this.elements = new Double[elements.size()];

        Iterator<Double> it = elements.iterator();

        int i = 0;

        while (it.hasNext()) {
            this.elements[i] = it.next();
            i++;
        }
    }

    /**
     * Gets the normalized (unit) vector and returns it
     * @see #normalize()
     * @return Normalized vector
     * @throws ArithmeticException if it's a 0 magnitude vector due to divide by zero error
     */
    public synchronized Vector getNormalized() {
        double inverseMag = 1 / getMag();  // We calculate the inverse so normalization can use multiplication instead of repeated division

        if (Double.isInfinite(inverseMag)) {
            throw new ArithmeticException("Error magnitude is zero resulting in divide by zero");
        }

        Double[] newVectorArray = new Double[elements.length];

        for (int i = 0; i < elements.length; i++) {
            newVectorArray[i] = inverseMag * elements[i];
        }

        return new Vector(newVectorArray);
    }

    /**
     * Returns the sum vector of this vector and another without mutating the original
     * @param v2 The other vector to add to this one
     * @see #add(AbstractVector) 
     * @return A new vector that is the sum
     */
    @Override
    public Vector getSum(AbstractVector v2) {
        if (v2.size() != this.size()) throw new VectorLengthMismatch("Vectors must have equal length to add");

        Double[] newElements = new Double[this.size()];

        synchronized (this) {
            for (int i = 0; i < this.size(); i++) {
                newElements[i] = this.elements[i] + v2.get(i);
            }
        }


        return new Vector(newElements);
    }

    /**
     * Returns this vector scaled by some constant without mutating the original
     * @param scalar The value to scale by
     * @see #scale(Double) 
     * @return The scaled vector
     */
    @Override
    public synchronized Vector getScaled(Double scalar) {
        return new Vector(Arrays.stream(this.elements).map((n) -> n * scalar).toArray(Double[]::new));
    }

    /**
     * Testing method for this class
     * @param args None
     */
    public static void main(String[] args) {
        // Testing Vector construction
        System.out.println("Creating Vectors");
        Vector v = new Vector(3, 3.0);
        Vector v2 = new Vector(4);
        Vector v3 = new Vector(new Double[] {1d, 2d, 3d, 4d});
        Vector v4 = new Vector(List.of(1d, 2d, 3d));

        // Testing toString() method
        System.out.println("Testing toString method");
        System.out.println(v);
        System.out.println(v2);
        System.out.println(v3);
        System.out.println(v4);

        // Testing grabbing by index
        System.out.println("Testing get by index method");
        System.out.println("Grabbing by index for v");
        System.out.println(v.get(0));
        System.out.println(v.get(1));
        System.out.println(v.get(2));

        // Testing Out-Of-Bounds
        System.out.println("Testing out of bounds error");
        try {
            v.get(5);
            System.out.println("No except");
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }

        // Testing iterable
        System.out.println("Testing iteration by printing each vector element");
        for (Double d : v) {
            System.out.println(d);
        }

        // Testing getting the normalized vector
        System.out.println("Testing the getNormalized method of Vector");
        System.out.println(v.getNormalized());
        System.out.println(v3.getNormalized());

        // Test for the divide by zero error caused by zero value magnitude during normalization
        System.out.println("Testing the getNormalized on a zero magnitude vector");
        try {
            System.out.println(v2.getNormalized());
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }

        // Test normalizing a vector with destructive call
        System.out.println("Testing the AbstractVector.normalize method");
        v.normalize();
        System.out.println(v);

        // Check to see if the magnitude works
        System.out.println("Checking magnitude of vectors");
        System.out.println(v.getMag());   // This one should be 1.0 since it's been normalized
        System.out.println(v2.getMag());  // This is a zero vector so it should be 0.0
        System.out.println(v3.getMag());

        // Test alter method
        System.out.println("Testing alter() method");
        System.out.println(v);
        v.alter(0, 5d);
        System.out.println(v);

        // Test valid dot product
        System.out.println("Testing dot product");
        System.out.println(Vector.dotProduct(v2, v3));

        // Test vector length mismatch dot product
        System.out.println("Testing invalid dot product");
        try {
            Vector.dotProduct(v, v3);
            System.out.println("No except");
        } catch (VectorLengthMismatch e) {
            System.out.println(e.getMessage());
        }

        // Try converting the vector to a matrix
        System.out.println("Getting matrix from vector");
        System.out.println(v.toMatrix());

        // Test vector addition
        System.out.println("Testing vector addition");
        Vector va = new Vector(1d, 2d, 3d);
        va.add(new Vector(1d ,2d, 3d));

        System.out.println(va);

        // Test vector scaling
        System.out.println("Testing vector scaling");
        Vector vs = new Vector(3, 1d);
        vs.scale(5d);
        System.out.println(vs);

        // Testing angleBetween
        System.out.println("Testing angle between two vectors");
        System.out.println(new Vector(1d, 0d, 0d).angleBetween(new Vector(0d, 1d, 0d)));  // This is the angle between the x unit vector and y unit vector, should be pi / 2
    }
}