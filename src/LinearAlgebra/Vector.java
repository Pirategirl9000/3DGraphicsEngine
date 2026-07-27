package LinearAlgebra;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

//TODO: Implement Normalize method
//TODO: Synchronize methods
//TODO: Implement method for Dot Product
//TODO: Implement method for cross product
//TODO: Implement method for multiplication by matrix
public class Vector implements Collection<Double> {
    private Double[] elements;

    /**
     * Refers to the length of the Vector not the number of elements
     */
    private Double length;

    /**
     * Whether we need to requery the length on next getLength() call
     */
    private boolean requeryLength = true;

    /**
     * Creates a new Vector with a set size and initial values for all elements
     * @param size The size of the Vector
     * @param initialValue The value to initialize all elements to
     * @throws IllegalArgumentException If Vector has size less than or equal to 1
     */
    public Vector(int size, Double initialValue) {
        if (size <= 1) throw new IllegalArgumentException("Vectors must have size greater than 1");

        this.elements = new Double[size];
        Arrays.fill(elements, initialValue);

        this.length = size * initialValue;
        requeryLength = false;
    }

    /**
     * Creates a new Vector with a set size and 0.0 starting values
     * @param size The size of the Vector
     * @throws IllegalArgumentException If Vector has size less than or equal to 1
     */
    public Vector(int size) {
        this(size, 0.0);
    }

    /**
     * Creates a new Vector from an array of Doubles
     * @param elements The elements for the new Vector
     * @throws IllegalArgumentException If Vector has size less than or equal to 1
     */
    public Vector(Double[] elements) {
        if (elements.length <= 1) throw new IllegalArgumentException("Vectors must have size greater than 1");
        this.elements = elements;
    }

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
        requeryLength = true;
        this.elements[index] = newValue;
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
        requeryLength = true;
        this.elements[0] = newValue;
    }

    /**
     * Shorthand for changing the second element of the Vector
     * @param newValue The new value for the element
     */
    public void y(Double newValue) {
        requeryLength = true;
        this.elements[1] = newValue;
    }

    /**
     * Shorthand for changing the third element of the Vector
     * @param newValue The new value for the element
     */
    public void z(Double newValue) {
        requeryLength = true;
        this.elements[2] = newValue;
    }

    /**
     * Returns the length of the Vector<br>
     * This refers to the length of the Vector (all elements summed) not the size of the underlying array
     * @return The length of the Vector
     */
    public Double getLength() {
        if (!requeryLength) return length;  // Use the cached length

        Double newLength = 0.0;

        for (Double d : elements) newLength += d;

        length = newLength;
        requeryLength = false;

        return length;
    }

    /**
     * Returns a String representation of the Vector<br>
     * This is not a thread safe operation
     * @return String representation of the Vector
     */
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

    // Tester code
    public static void main(String[] args) {
        Vector v = new Vector(5, 1.0);


    }
}