package LinearAlgebra.Matrices;

import java.util.Arrays;
import java.util.List;  // Used for testing

public class Matrix extends AbstractMatrix {
    /**
     * Creates a Matrix from a multidimensional array of Doubles
     * @param elements The elements of the matrix as a nested Double array
     */
    public Matrix(Double[][] elements) {
        if (elements.length == 0 || elements[0].length == 0) {
            throw new InvalidMatrixLengthException("Cannot create matrix with either no rows or no columns");
        }

        int columnSize = elements[0].length;

        for (int i = 1; i < elements.length; i++) {
            if (columnSize != elements[i].length) {
                throw new InvalidMatrixLengthException("Matrix does not have consistent row length");
            }
        }

        this.elements = elements;
    }

    /**
     * Creates a new matrix with a set number of rows and columns all having an initial value
     * @param rows The number of rows
     * @param columns The number of columns
     * @param initialValue The initial value for all items
     */
    public Matrix(int rows, int columns, Double initialValue) {
        if (rows < 0 || columns < 0) {
             throw new InvalidMatrixLengthException("Columns and rows must be greater than or equal to one");
        }

        this.elements = new Double[rows][columns];

        for (int i = 0; i < rows; i++) {
            Arrays.fill(elements[i], initialValue);
        }
    }

    /**
     * Creates a new matrix with a set number of rows and columns all initialized to 0d
     * @param rows The number of rows
     * @param columns The number of columns
     */
    public Matrix(int rows, int columns) {
        this(rows, columns, 0d);
    }




    //TODO: Add multiply method for vectors and matrices

    public static void main(String[] args) {
        // Create a valid matrix
        System.out.println("Creating valid matrices");
        Matrix matrix = new Matrix(new Double[][] {
                {1d,2d,3d},
                {4d,5d,6d},
                {7d,8d,9d}
        });

        Matrix matrix2 = new Matrix(5, 5);
        Matrix matrix3 = new Matrix(5, 4, 1d);

        // Test matrix iterator
        System.out.println("Testing iteration of Matrix");
        for (Double element : matrix) {
            System.out.println(element);
        }

        // Create an invalid matrix
        System.out.println("Creating an invalid matrix");
        try {
            new Matrix(new Double[][] {
                    {1d, 1d},
                    {2d},
                    {3d}
            });
        } catch (InvalidMatrixLengthException e) {
            System.out.println(e.getMessage());
        }

        // Check contains method
        // Valid
        System.out.println("Testing contains method for things it contains");
        System.out.println(matrix.contains(8d));
        System.out.println(matrix.contains(7d));

        // Invalid
        System.out.println("Testing contains method for things it doesn't contain");
        System.out.println(matrix.contains(10d));
        System.out.println(matrix.contains(8.5d));
        System.out.println(matrix.contains(8.9999999999d));  // testing floating point accuracy

        // Check containsAll method
        // Valid
        System.out.println("Testing containsAll method for things it contains");
        System.out.println(matrix.containsAll(List.of(1d, 2d, 3d)));
        System.out.println(matrix.containsAll(List.of(2d, 5d, 3d, 6d)));
        System.out.println(matrix.containsAll(List.copyOf(matrix)));

        // Invalid
        System.out.println("Testing containsAll method for things it doesn't contain");
        System.out.println(matrix.containsAll(List.of(-1d, 1d, 2d)));
        System.out.println(matrix.containsAll(List.of(1d, 2d, 3d, 3.4d)));

        // Test toArray Method
        System.out.println("Testing toArray method");
        System.out.println(Arrays.toString(matrix.toArray()));

        System.out.println("Testing toString() method");
        System.out.println(matrix);
        System.out.println(matrix2);
        System.out.println(matrix3);

    }
}
