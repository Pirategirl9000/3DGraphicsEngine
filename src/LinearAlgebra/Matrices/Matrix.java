package LinearAlgebra.Matrices;

import java.util.Arrays;
import java.util.List;  // Used for testing

public class Matrix extends AbstractMatrix {
    public Matrix(Double[][] elements) {
        int rowSize = elements[0].length;

        for (int i = 1; i < elements.length; i++) {
            if (rowSize != elements[i].length) {
                throw new InvalidMatrixLengthException("Matrix does not have consistent row length");
            }
        }

        this.elements = elements;
    }

    //TODO: Add more constructors
    //TODO: Add more methods

    public static void main(String[] args) {
        // Create a valid matrix
        System.out.println("Creating a valid matrix");
        Matrix matrix = new Matrix(new Double[][] {
                {1d,2d,3d},
                {4d,5d,6d},
                {7d,8d,9d}
        });

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

    }
}
