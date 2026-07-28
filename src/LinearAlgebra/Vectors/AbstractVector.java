package LinearAlgebra.Vectors;

import LinearAlgebra.VectorLengthMismatch;
import org.jetbrains.annotations.NotNull;

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
    public Double get(int index) {
        return elements[index];
    }

    /**
     * Changes the value at a set index<br>
     * The method does not check if it is within the bounds of the vector for performance's sake
     * @param index The index to change
     * @param newValue The new value to replace it with
     * @throws IndexOutOfBoundsException if index exceeds the bounds of the Vector
     */
    public void alter(int index, Double newValue) {
        requeryMag = true;
        this.elements[index] = newValue;
    }

    /**
     * Returns the magnitude of the Vector<br>
     * @return The magnitude of the Vector
     */
    public Double getMag() {
        if (!requeryMag) return magnitude;  // Use the cached length

        double newLength = 0.0;

        for (Double d : elements) newLength += d * d;

        newLength = Math.sqrt(newLength);

        magnitude = newLength;
        requeryMag = false;

        return magnitude;
    }

    /**
     * Calculates the vectors normal
     */
    public void normalize() {
        Double inverseMag = 1 / getMag();  // We calculate the inverse so normalization can use multiplication instead of repeated division

        Double[] newVectorArray = new Double[elements.length];

        for (int i = 0; i < elements.length; i++) {
            newVectorArray[i] = inverseMag * elements[i];
        }

        this.elements = newVectorArray;
        requeryMag = true;
    }

    /**
     * Calculates the dot product of two vectors
     * @param v1 The first vector
     * @param v2 The second vector
     * @return The dot product of the two vectors
     */
    public static Double dotProduct(AbstractVector v1, AbstractVector v2) {
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
     */
    public Double dotProduct(AbstractVector v2) {
        return dotProduct(this, v2);
    }

    /**
     * Returns a String representation of the Vector<br>
     * This is not a thread safe operation
     * @return String representation of the Vector
     */
    @Override
    public String toString() {
        int length = elements.length;

        StringBuilder sb = new StringBuilder(length * 3);  // Allocating exactly how much space we will need

        sb.append("<");
        for (int i = 0; i < length; i++) {
            sb.append(elements[i]);
            if (i != length - 1) sb.append(", ");
        }
        sb.append(">");

        return sb.toString();
    }

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
    public boolean contains(Object o) {
        for (Double element : elements) {
            if (element.equals(o)) {
                return true;
            }
        }

        return false;
    }

    @NotNull
    public Double[] toArray() {
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
    public boolean containsAll(@NotNull Collection<?> c) {
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
    public Iterator<Double> iterator() {
        return new Iterator<Double>() {
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
