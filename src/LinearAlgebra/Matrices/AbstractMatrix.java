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
    protected volatile Double[][] elements;

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
     * Gets the value at the specified position <br>
     * Is not synchronized so use with caution
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
    public synchronized void alter(int row, int column, Double value) {
        this.elements[row][column] = value;
    }

    /**
     * Returns the trace of this matrix
     * @throws InvalidMatrixLengthException if it's not a square matrix
     * @return the sum of the diagonals
     */
    public synchronized Double trace() {
        if (!isSquare()) throw new InvalidMatrixLengthException("Trace operation requires a square matrix");

        double total = 0d;

        for (int i = 0; i < this.rows(); i++) {
            total += this.get(i, i);
        }

        return total;
    }

    /**
     * Adds the matrix onto this one<br><br>
     * For a non-destructive version view {@link #sum(AbstractMatrix m2)}
     * @param m2 The matrix to add onto this one
     */
    public synchronized void add(@NotNull AbstractMatrix m2) {
        if (this.rows() != m2.rows() || this.columns() != m2.columns()) throw new InvalidMatrixLengthException("Matrices must have matching dimensions to be added");

        for (int i = 0; i < this.rows(); i++) {
            for (int j = 0; j < this.columns(); j++) {
                this.elements[i][j] += m2.get(i, j);
            }
        }
    }

    /**
     * Tries to convert the matrix to a vector
     * @return Vector with elements of the matrix
     * @throws MatrixRowColumnMismatch if the matrix doesn't have 1 row, 1 column matrices are not supported for conversions
     */
    public Vector toVector() {
        if (this.rows() != 1) throw new MatrixRowColumnMismatch("Rows must be 1 to convert a matrix to a vector");

        synchronized (this) {
            return new Vector(this.elements[0]);
        }
    }

    /**
     * Checks whether this is a square matrix
     * @return true or false
     */
    public boolean isSquare() {
        return this.columns() == this.rows();
    }

    @Override
    public String toString() {
        int columnLength = elements[0].length;
        int rowLength = elements.length;

        StringBuilder sb = new StringBuilder(300);

        sb.append("[");

        synchronized (this) {
            for (int i = 0; i < rowLength; i++) {
                for (int j = 0; j < columnLength; j++) {
                    sb.append(elements[i][j]);
                    if (j + 1 < columnLength) sb.append(" ");
                }

                if (i + 1 < rowLength) sb.append("\n ");
            }
        }

        sb.append("]");

        return sb.toString();
    }

    public abstract AbstractMatrix multiply(@NotNull AbstractMatrix m2) throws Exception;

    /**
     * Returns a new matrix that is the sum of the two vectors
     * @param m2 the matrix to add onto this one
     * @return The new matrix which is the sum
     */
    public abstract AbstractMatrix sum(@NotNull AbstractMatrix m2);




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

        synchronized (this) {
            for (int i = 0; i < elements.length; i++) {
                for (int j = 0; j < elements[0].length; j++) {
                    if (o.equals(elements[i][j])) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @NotNull
    @Override
    public Object[] toArray() {
        Object[] array = new Object[elements.length * elements[0].length];

        synchronized (this) {
            for (int i = 0; i < elements.length; i++) {
                for (int j = 0; j < elements[0].length; j++) {
                    array[j * elements[0].length + i] = elements[j][i];
                }
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

        synchronized (this) {
            for (int i = 0; i < elements.length; i++) {
                for (int j = 0; j < elements[0].length; j++) {
                    list.remove(elements[i][j]);
                }
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
    public synchronized Iterator<Double> iterator() {
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
