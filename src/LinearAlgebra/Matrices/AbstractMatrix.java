package LinearAlgebra.Matrices;

/**
 * Abstract class for implementing methods for handling a row major matrix
 */
public class AbstractMatrix {
    /**
     * Stores all the elements of the matrix in row major form
     */
    protected Double[][] elements;

    /**
     * Returns the matrix in column major form
     * @return Double[][] containing all the elements of the matrix in column major form
     */
    public Double[][] columns() {
        // We must convert the row major elements to be column major
        Double[][] columns = new Double[elements[0].length][elements.length];

        for (int i = 0; i < elements[0].length; i++) {
            for (int j = 0; j < elements.length; j++) {
                columns[i][j] = elements[j][i];
            }
        }

        return columns;
    }

    /**
     * Returns the matrix in row major form
     * @return Double[][] containing all the elements of the matrix in row major form
     */
    public Double[][] rows() {
        return elements;  // Already in row-major form
    }
}
