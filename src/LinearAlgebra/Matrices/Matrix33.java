package LinearAlgebra.Matrices;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Class for creating a 3x3 matrix
 */
public class Matrix33 extends Matrix {
    /**
     * Creates a new 3x3 matrix with an initial value
     * @param initialValue The initial value for all elements
     */
    public Matrix33(Double initialValue) {
        super(3, 3, initialValue);
    }

    /**
     * Create a new 3x3 matrix from a list of values
     * @param elements The values for the matrix
     * @throws InvalidMatrixLengthException if values is not of length 9
     */
    public Matrix33(@NotNull Double... elements) {
            super(getAsNewDoubleDouble(elements));
    }

    public Matrix33(@NotNull Double[]... elements) {
        super(getAsNewDoubleDouble(elements));
    }

    public Matrix33(@NotNull Collection<Double> elements) {
        super(getAsNewDoubleDouble(elements));
    }

    /**
     * Creates a new 3x3 matrix with default value
     */
    public Matrix33() {
        this(0d);
    }

    /**
     * Converts a Double varargs to a Double[][] so we can throw custom exceptions
     * @param values the varargs to convert
     * @return A Double[][] containing those elements
     */
    private static Double[][] getAsNewDoubleDouble(@NotNull Double... values) {
        if (values.length != 9) throw new InvalidMatrixLengthException("Matrix33 must have 9 elements");  // This is the whole point of this method

        return new Double[][] {
                {values[0], values[1], values[2]},
                {values[3], values[4], values[5]},
                {values[6], values[7], values[8]}
        };
    }

    /**
     * Converts a Double Collection to a Double[][] so we can throw custom exceptions
     * @param elements the Colletion to convert
     * @return A Double[][] containing those elements
     */
    private static Double[][] getAsNewDoubleDouble(@NotNull Collection<Double> elements) {
        if (elements.size() != 9) throw new InvalidMatrixLengthException("Matrix33 must have 9 elements");

        Double[][] realElements =  new Double[3][3];

        Iterator<Double> it = elements.iterator();

        int i = 0;

        while (it.hasNext()) {
            realElements[Math.floorDiv(i, 3)][i % 3] = it.next();
            i++;
        }

        return realElements;
    }

    /**
     * Converts a Double[] varargs to a Double[][] so we can throw custom exceptions
     * @param elements the elements to convert
     * @return A Double[][] containing those elements
     */
    private static Double[][] getAsNewDoubleDouble(@NotNull Double[]... elements) {
        if (elements.length != 3 || elements[0].length != 3) throw new InvalidMatrixLengthException("Matrix33 must have 9 elements");

        return new Double[][] {
                elements[0],
                elements[1],
                elements[2]
        };
    }

    /**
     * Testing method for this class
     * @param args None
     */
    public static void main(String[] args) {
        // Test construction
        // Valid
        System.out.println("Testing valid construction");
        Matrix33 m = new Matrix33(1d, 2d, 3d, 4d, 5d, 6d, 7d, 8d, 9d);
        Matrix33 m2 = new Matrix33(List.of(1d, 2d, 3d, 4d, 5d, 6d, 7d, 8d, 9d));
        Matrix33 m3 = new Matrix33(new Double[][] {
                {1d, 2d, 3d},
                {4d, 5d, 6d},
                {7d, 8d, 9d}
        });
        Matrix33 m4 = new Matrix33(new Double[] {  // Yeah, its redundant but just makings sure
                1d, 2d, 3d, 4d, 5d, 6d, 7d, 8d, 9d
        });

        Matrix33 m5 = new Matrix33(1d);
        Matrix33 m6 = new Matrix33();

        System.out.println(m);
        System.out.println(m2);
        System.out.println(m3);
        System.out.println(m4);
        System.out.println(m5);
        System.out.println(m6);

        // Invalid
        System.out.println("Testing invalid construction");
        try {
            new Matrix33(new Double[][] {
                    {1d, 2d, 3d}
            });
            System.out.println("No except");
        } catch (InvalidMatrixLengthException e) {
            System.out.println(e.getMessage());
        }

        try {
            new Matrix33(new Double[][] {
                    {1d},
                    {2d},
                    {3d}
            });
            System.out.println("No except");
        } catch (InvalidMatrixLengthException e) {
            System.out.println(e.getMessage());
        }

        try {
            new Matrix33(new Double[] {1d, 2d});
            System.out.println("No except");
        } catch (InvalidMatrixLengthException e) {
            System.out.println(e.getMessage());
        }

        try {
            new Matrix33(List.of(1d, 2d));
            System.out.println("No except");
        } catch (InvalidMatrixLengthException e) {
            System.out.println(e.getMessage());
        }


    }
}
