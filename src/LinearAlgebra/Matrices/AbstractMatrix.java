package LinearAlgebra.Matrices;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import LinearAlgebra.Vectors.Vector;

/**
 * Abstract class for implementing methods for handling a row major matrix
 */
public abstract class AbstractMatrix implements Collection<Double> {
    /**
     * Stores all the elements of the matrix in row major form
     */
    protected Double[][] elements;

    /**
     * Returns the number of columns
     * @return The number of columns in the matrix
     */
    public int columns() {
        return elements[0].length;
    }

    /**
     * Returns the number of rows
     * @return The number of rows in the matrix
     */
    public int rows() {
        return elements.length;
    }

    /**
     * Gets the value at the specified position
     * @param row row of the element
     * @param column column of the element
     * @return the element at that position
     */
    public Double get(int row, int column) {
        return this.elements[row][column];
    }

    /**
     * Changes the value at a specfied position
     * @param row row of the element
     * @param column column of the element
     * @param value The new value for the element
     */
    public void alter(int row, int column, Double value) {
        this.elements[row][column] = value;
    }

    /**
     * Tries to convert the matrix to a vector
     * @return Vector with elements of the matrix
     * @throws MatrixRowColumnMismatch if the matrix doesn't have 1 row, 1 column matrices are not supported for conversions
     */
    public Vector toVector() {
        if (this.rows() != 1) throw new MatrixRowColumnMismatch("Rows must be 1 to convert a matrix to a vector");

        return new Vector(this.elements[0]);
    }

    @Override
    public String toString() {
        int columnLength = elements[0].length;
        int rowLength = elements.length;

        StringBuilder sb = new StringBuilder(300);

        sb.append("[");
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < columnLength; j++) {
                sb.append(elements[i][j]);
                if (j + 1 < columnLength) sb.append(" ");
            }

            if (i + 1 < rowLength) sb.append("\n ");
        }

        sb.append("]");

        return sb.toString();
    }


    //----------------------------------------------------- COLLECTION METHODS -----------------------------------------------------//

    @Override
    public int size() {
        return elements[0].length * elements.length;
    }

    @Override
    public boolean isEmpty() {
        return false;  // Matrices will not be empty
    }

    @Override
    public boolean contains(Object o) {
        if (!(o instanceof Double)) {
            return false;
        }

        for (int i = 0; i < elements.length; i++) {
            for (int j = 0; j < elements[0].length; j++) {
                if (o.equals(elements[i][j])) {
                    return true;
                }
            }
        }

        return false;
    }

    @NotNull
    @Override
    public Object[] toArray() {
        Object[] array = new Object[elements.length * elements[0].length];

        for (int i = 0; i < elements.length; i++) {
            for (int j = 0; j < elements[0].length; j++) {
                array[j * elements[0].length + i] = elements[j][i];
            }
        }

        return array;
    }

    @NotNull
    @Override
    public <T> T[] toArray(@NotNull T[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(Double aDouble) {
        throw new UnsupportedOperationException("Cannot add to a matrix");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Cannot remove from a matrix");
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        // Strong contender for the stupidest code I've ever written
        // 7/31/2026 1:49AM: it wasn't
        List<Object> list = new ArrayList<>(List.copyOf(c));  // Pretty sure this doesn't work if the Collection doesn't have an Iterator

        for (int i = 0; i < elements.length; i++) {
            for (int j = 0; j < elements[0].length; j++) {
                list.remove(elements[i][j]);
            }
        }

        return list.isEmpty();
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends Double> c) {
        throw new UnsupportedOperationException("Cannot add to a matrix");
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        throw new UnsupportedOperationException("Cannot remove from a matrix");
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        throw new UnsupportedOperationException("Cannot remove from a matrix");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Cannot remove from a matrix");
    }

    @NotNull
    @Override
    public Iterator<Double> iterator() {
        return new Iterator<Double>() {
            private int column = 0;
            private int row = 0;

            @Override
            public boolean hasNext() {
                if (column == elements[0].length) {
                    // We've done the whole row now we move to the next
                    row++;
                    column = 0;

                    return row != elements.length;
                }

                return true;
            }

            @Override
            public Double next() {
                if (!hasNext()) throw new NoSuchElementException("No more elements");

                return elements[row][column++];
            }
        };
    }
}
