package Rendering.Scene;

/**
 * Abstract class for handling cameras
 */
public class AbstractCamera {
    /**
     * The orientation of the camera relative to the y-axis
     */
    protected double yAngle;

    /**
     * The orientation of the camera relative to the x-axis
     */
    protected double xAngle;

    /**
     * The orientation of the camera relative to the z-axis
     */
    protected double zAngle;

    /**
     * constant value for 2PI radians
     */
    private final double TWOPI = 2 * Math.PI;

    /**
     * Retrieves the {@link #yAngle} of the camera
     */
    public double getyAngle() {
        return this.yAngle;
    }

    /**
     * Retrieves the {@link #xAngle} of the camera
     */
    public double getxAngle() {
        return this.xAngle;
    }

    /**
     * Retrieves the {@link #zAngle} of the camera
     */
    public double getzAngle() {
        return this.zAngle;
    }

    /**
     * Sets the camera's {@link #yAngle}
     * @param yAngle the new y angle in radians
     */
    public void setyAngle(double yAngle) {
        this.yAngle = yAngle;
    }

    /**
     * Sets the camera's {@link #xAngle}
     * @param xAngle the new x angle in radians
     */
    public void setxAngle(double xAngle) {
        this.xAngle = xAngle;
    }

    /**
     * Sets the camera's {@link #zAngle}
     * @param zAngle the new z angle in radians
     */
    public void setzAngle(double zAngle) {
        this.zAngle = zAngle;
    }

    /**
     * Adjusts the camera's {@link #yAngle}
     * @param amount the amount in radians to increase it by
     */
    public void pitch(double amount) {
        this.yAngle = (this.yAngle + amount) % TWOPI;
    }

    /**
     * Adjusts the camera's {@link #xAngle}
     * @param amount the amount in radians to increase it by
     */
    public void yaw(double amount) {
        this.xAngle = (this.xAngle + amount) % TWOPI;
    }

    /**
     * Adjusts the camera's {@link #zAngle}
     * @param amount the amount in radians to increase it by
     */
    public void roll(double amount) {
        this.zAngle = (this.zAngle + amount) % TWOPI;
    }
}
