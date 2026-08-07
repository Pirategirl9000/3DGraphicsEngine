package Rendering.Scene;

/**
 * Abstract class for handling cameras
 */
public class AbstractCamera {
    /**
     * The orientation of the camera relative to the y-axis
     */
    protected double pitch;

    /**
     * The orientation of the camera relative to the x-axis
     */
    protected double yaw;

    /**
     * The orientation of the camera relative to the z-axis
     */
    protected double roll;

    /**
     * constant value for 2PI radians
     */
    private final double TWOPI = 2 * Math.PI;


    /**
     * Retrieves the {@link #pitch} of the camera
     */
    public double pitch() {
        return this.pitch;
    }

    /**
     * Retrieves the {@link #yaw} of the camera
     */
    public double yaw() {
        return this.yaw;
    }

    /**
     * Retrieves the {@link #roll} of the camera
     */
    public double roll() {
        return this.roll;
    }

    /**
     * Adjusts the camera's {@link #pitch}
     * @param angle the amount in radians to increase it by
     */
    public void pitch(double angle) {
        this.pitch = (this.pitch + angle) % TWOPI;
    }

    /**
     * Adjusts the camera's {@link #yaw}
     * @param angle the amount in radians to increase it by
     */
    public void yaw(double angle) {
        this.yaw = (this.yaw + angle) % TWOPI;
    }

    /**
     * Adjusts the camera's {@link #roll}
     * @param angle the amount in radians to increase it by
     */
    public void roll(double angle) {
        this.roll = (this.roll + angle) % TWOPI;
    }
}
