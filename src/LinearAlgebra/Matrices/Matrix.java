package LinearAlgebra.Matrices;

import LinearAlgebra.Vectors.AbstractVector;
import LinearAlgebra.Vectors.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.Callable;
import java.util.List;

/**
 * Class for creating basic matrices
 */
public class Matrix extends AbstractMatrix {
    /**
     * Stores a dot product for the matrix multiplication, makes it easier to extract information from Futures
     * @param row The row of this dot product
     * @param col The column of this dot product
     * @param value The value of this dot product
     */
    private record MiniDotProduct(int row, int col, Double value) {}


    /**
     * Creates a Matrix from a multidimensional array of Doubles
     * @param elements The elements of the matrix as a nested Double array
     */
    public Matrix(@NotNull Double[]... elements) {
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

    /**
     * Multiplies this matrix by another and returns the result
     * @param m2 The second matrix to multiply this one by
     * @throws Exception is something goes wrong
     * @return new Matrix that is the product
     */
    public Matrix multiply(@NotNull AbstractMatrix m2) throws Exception {
        if (this.columns() != m2.rows()) throw new MatrixRowColumnMismatch("Invalid rows and columns to perform multiplication");

        Double[][] newMatrix = new Double[this.rows()][m2.columns()];

        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        ArrayDeque<Future<MiniDotProduct>> futures = new ArrayDeque<>();

        synchronized (this) {
            for (int mRow = 0; mRow < this.rows(); mRow++) {
                for (int m2Col = 0; m2Col < m2.columns(); m2Col++) {

                    // These need to be final for a lambda
                    final int fmRow = mRow;
                    final int fm2Col = m2Col;


                    Callable<MiniDotProduct> calculate = () -> {
                        double newValue = 0d;


                        for (int element = 0; element < this.columns(); element++) {
                            newValue += this.get(fmRow, element) * m2.get(element, fm2Col);
                        }

                        return new MiniDotProduct(fmRow, fm2Col, newValue);
                    };

                    futures.addLast(service.submit(calculate));
                }
            }


            // Grabs the result of the futures
            while (!futures.isEmpty()) {
                MiniDotProduct dp = futures.removeLast().get();
                newMatrix[dp.row()][dp.col()] = dp.value();
            }

        }

        service.shutdown();
        return new Matrix(newMatrix);

    }

    /**
     * Multiplies this matrix by the vector and returns the result<br>
     * @param vec The Vector to multiply this matrix by
     * @throws Exception if something goes wrong
     * @return new Vector that is the product
     */
    public Vector multiply(@NotNull AbstractVector vec) throws Exception {
        Double[][] vecMat = new Double[vec.size()][1];

        for (int i = 0; i < vec.size(); i++) {
            vecMat[i][0] = vec.get(i);
        }

        return this.multiply(new Matrix(vecMat)).toVector();
    }

    /**
     * Sums the two matrices and returns the result
     * @param m2 the other matrix to add onto this one
     * @return A new matrix that is the sum
     */
    public Matrix sum(@NotNull AbstractMatrix m2) {
        if (this.rows() != m2.rows() || this.columns() != m2.columns()) throw new InvalidMatrixLengthException("Matrices must have matching dimensions to be added");

        Double[][] newMatrix = new Double[this.rows()][this.columns()];

        for (int i = 0; i < this.rows(); i++) {
            for (int j = 0; j < this.columns(); j++) {
                newMatrix[i][j] = m2.get(i, j) + this.get(i, j);
            }
        }

        return new Matrix(newMatrix);
    }

    /**
     * Testing method for the Matrix and AbstractMatrix classes
     * @param args None
     */
    public static void main(String[] args) throws Exception {
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
            System.out.println("No except");
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

        // Test multiplication
        System.out.println("Test Matrix and Matrix Multiplication");
        Matrix m1 = new Matrix(new Double[][] {
                {1d, 2d, 3d},
                {4d, 5d, 6d}
        });

        Matrix m2 = new Matrix(new Double[][] {
                {1d, 2d},
                {3d, 4d},
                {5d, 6d}
        });


        System.out.println(m1.multiply(m2));

        // Test Matrix * Vector multiplication
        System.out.println("Testing Matrix and Vector multiplication");
        Vector v = new Vector(1d, 2d, 3d);

        Matrix m3 = new Matrix(new Double[][] {
                {1d, 2d, 3d},
                {4d, 5d, 6d}
        });

        System.out.println(m3.multiply(v));

        // Test invalid matrix multiplication

        // Matrix * Matrix
        System.out.println("Testing invalid matrix multiplication with matrix");
        try {
            m3.multiply(m1);
            System.out.println("No except");
        } catch (MatrixRowColumnMismatch e){
            System.out.println(e.getMessage());
        }

        // Matrix * Vector
        System.out.println("Testing invalid matrix multiplication with vector");
        try {
            m2.multiply(v);
            System.out.println("No except");
        } catch (MatrixRowColumnMismatch e) {
            System.out.println(e.getMessage());
        }

        // Test Trace
        System.out.println("Testing trace valid");
        Matrix mt = new Matrix(3, 3, 1d);
        System.out.println(mt.trace());

        // Test invalid trace
        System.out.println("Testing invalid trace");
        try {
            new Matrix(3, 2, 1d).trace();
        } catch (InvalidMatrixLengthException e) {
            System.out.println(e.getMessage());
        }

        // Test sum and add
        System.out.println("Testing valid add to matrix, should be a matrix of 2's");
        mt.add(new Matrix(3, 3, 1d));
        System.out.println(mt);

        System.out.println("Testing valid sum of matrices, should be all 3's");
        System.out.println(mt.sum(new Matrix(3, 3, 1d)));

        System.out.println("Testing invalid add to matrix");
        try {
            mt.add(new Matrix(3, 2, 1d));
        } catch (InvalidMatrixLengthException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Testing invalid sum of matrices");
        try {
            mt.sum(new Matrix(3, 2, 1d));
        } catch (InvalidMatrixLengthException e) {
            System.out.println(e.getMessage());
        }



    }
}
