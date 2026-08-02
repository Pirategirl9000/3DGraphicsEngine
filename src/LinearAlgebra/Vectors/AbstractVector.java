package LinearAlgebra.Vectors;

import LinearAlgebra.Matrices.Matrix;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Abstract class for representing a Vector
 */
public abstract class AbstractVector implements Collection<Double> {
    /**
     * The elements of this Vector
     */
    protected Double[] elements;

    /**
     * Refers to the length of the Vector not the number of elements
     */
    protected Double magnitude;

    /**
     * Whether we need to requery the length on next getLength() call
     */
    protected boolean requeryMag = true;

    /**
     * Gets the element at the given index
     * @param index The index of the element you wish to retrieve
     * @return The Double at that index
     */
    public synchronized Double get(int index) {
        return elements[index];
    }

    /**
     * Changes the value at a set index<br>
     * The method does not check if it is within the bounds of the vector for performance's sake
     * @param index The index to change
     * @param newValue The new value to replace it with
     * @throws IndexOutOfBoundsException if index exceeds the bounds of the Vector
     */
    public synchronized void alter(int index, Double newValue) {
        requeryMag = true;
        this.elements[index] = newValue;
    }

    /**
     * Returns the magnitude of the Vector<br>
     * @return The magnitude of the Vector
     */
    public synchronized Double getMag() {
        if (!requeryMag) return magnitude;  // Use the cached length

        double newLength = 0.0;

        for (Double d : elements) newLength += d * d;

        newLength = Math.sqrt(newLength);

        magnitude = newLength;
        requeryMag = false;

        return magnitude;
    }

    /**
     * Normalizes this vectors
     * @throws ArithmeticException if it's a 0 length vector due to divide by zero error
     */
    public synchronized void normalize() {
        Double inverseMag = 1 / getMag();  // We calculate the inverse so normalization can use multiplication instead of repeated division

        if (Double.isInfinite(inverseMag)) {
            throw new ArithmeticException("Error magnitude is zero resulting in divide by zero");
        }

        Double[] newVectorArray = new Double[elements.length];

        for (int i = 0; i < elements.length; i++) {
            newVectorArray[i] = inverseMag * elements[i];
        }

        this.elements = newVectorArray;
        requeryMag = true;
    }

    /**
     * Adds the vector onto this one
     * @param v2 The other vector to add onto this one
     */
    public void add(@NotNull AbstractVector v2) {
        if (v2.size() != this.size()) throw new VectorLengthMismatch("Vectors must have equal length to add");

        synchronized (this) {
            for (int i = 0; i < this.size(); i++) {
                this.elements[i] += v2.get(i);
            }
        }
    }

    /**
     * Scales this vector by the given scalar
     * @param scalar The value to scale by
     */
    public synchronized void scale(@NotNull Double scalar) {
        this.elements = Arrays.stream(this.elements).map((n) -> n * scalar).toArray(Double[]::new);
    }

    /**
     * Calculates the dot product of two vectors
     * @param v1 The first vector
     * @param v2 The second vector
     * @return The dot product of the two vectors
     * @throws VectorLengthMismatch if the vectors don't have matching length
     */
    public static Double dotProduct(@NotNull AbstractVector v1, @NotNull AbstractVector v2) {
        if (v1.size() != v2.size()) throw new VectorLengthMismatch("Vectors must have matching length to calculate dot product");

        double dp = 0.0;

        for (int i = 0; i < v1.size(); i++) {
            dp += v1.get(i) * v2.get(i);
        }

        return dp;
    }

    /**
     * Returns the dot product of this vector and another vector v2
     * @param v2 the vector to multiply this vector by
     * @return The dot product of the two vectors
     * @throws VectorLengthMismatch if the vectors don't have matching length
     */
    public synchronized Double dotProduct(@NotNull AbstractVector v2) {
        return dotProduct(this, v2);
    }

    /**
     * Converts the vector to a matrix of 1 x n dimensions
     * @return 1 x n matrix containing this vector's elements
     */
    public synchronized Matrix toMatrix() {
        return new Matrix(new Double[][] {this.elements});
    }

    /**
     * Returns a String representation of the Vector<br>
     * @return String representation of the Vector
     */
    @Override
    public String toString() {
        int length = elements.length;

        StringBuilder sb = new StringBuilder(500);

        sb.append("<");

        synchronized (this) {
            for (int i = 0; i < length; i++) {
                sb.append(elements[i]);
                if (i != length - 1) sb.append(", ");
            }
        }

        sb.append(">");

        return sb.toString();
    }

    /**
     * Returns this vector normalized without mutating the original
     * @return This vector normalized
     */
    public abstract AbstractVector getNormalized();

    /**
     * Returns the sum vector of this vector and another without mutating the original
     * @param v2 The other vector to add to this one
     * @return A new vector that is the sum
     */
    public abstract AbstractVector getSum(AbstractVector v2);

    /**
     * Returns this vector scaled by some constant without mutating the original
     * @param scalar The value to scale by
     * @return The scaled vector
     */
    public abstract AbstractVector getScaled(Double scalar);

    //----------------------------------------------------- COLLECTION METHODS -----------------------------------------------------//
    @Override
    public int size() {
        return elements.length;
    }

    @Override
    public boolean isEmpty() {
        return false;  // Vectors are never empty
    }

    @Override
    public synchronized boolean contains(Object o) {
        for (Double element : elements) {
            if (element.equals(o)) {
                return true;
            }
        }

        return false;
    }

    @NotNull
    public synchronized Double[] toArray() {
        return elements;
    }

    @NotNull
    @Override
    public <Double1> Double1[] toArray(@NotNull Double1[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(Double t) {
        throw new UnsupportedOperationException("Vectors do not support adding elements");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Vectors do not support removing elements");
    }

    @Override
    public synchronized boolean containsAll(@NotNull Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends Double> c) {
        throw new UnsupportedOperationException("Vectors do not support adding elements");
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        throw new UnsupportedOperationException("Vectors do not support removing elements");
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        throw new UnsupportedOperationException("Vectors do not support retaining elements");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Vectors do not support clearing elements");
    }

    @NotNull
    @Override
    public synchronized Iterator<Double> iterator() {
        return new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < elements.length;
            }

            @Override
            public Double next() {
                if (!hasNext()) throw new NoSuchElementException("No more elements");

                return elements[index++];
            }
        };
    }

}
