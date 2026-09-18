package Rendering.Scene.Components;

import Rendering.Math.Vectors.Vector;
import Rendering.Math.Vectors.Vector3;

/**
 * Abstract class for handling cameras
 */
public class Camera3D {
    /**
     * The position of this camera
     */
    protected Vector3 position;

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
     * The distance from the camera before we start rendering things
     */
    protected double nearClip;

    /**
     * How far away before we stop rendering things
     */
    protected double farClip;

    /**
     * This is the z coordinate of the plane we are projecting the scene onto
     */
    protected double projectionPlaneZ;

    /**
     * constant value for 2PI radians
     */
    private final double TWOPI = 2 * Math.PI;

    public Camera3D(double x, double y, double z, double xAngle, double yAngle, double zAngle, double near, double far, double imagePlaneZ) {
        this.position = new Vector3(x, y, z);
        this.xAngle = xAngle;
        this.yAngle = yAngle;
        this.zAngle = zAngle;
        this.nearClip = near;
        this.farClip = far;
        this.projectionPlaneZ = imagePlaneZ;
    }

    /**
     * Returns the position of the camera in its native form of Vector3
     * @return Vector3 representing the camera's x, y, z position
     */
    public Vector3 getPositionVector() {
        return this.position;
    }

    /**
     * Returns the position of the camera as an array
     * @return Double[] representing the camera's x, y, z position
     */
    public Double[] getPosition() {
        return this.position.toArray();
    }

    /**
     * Sets the camera's position using a Vector3
     * @param position the new position for the camera
     */
    public void setPosition(Vector3 position) {
        this.position = position;
    }

    /**
     * Sets the camera's position to the new coordinates
     * @param x the x coord
     * @param y the y coord
     * @param z the z coord
     */
    public void setPosition(Double x, Double y, Double z) {
        this.position.x(x);
        this.position.y(y);
        this.position.z(z);
    }

    /**
     * Returns the camera's x position
     * @return the x position of the camera
     */
    public Double x() {
        return this.position.x();
    }

    /**
     * Returns the camera's y position
     * @return the y position of the camera
     */
    public Double y() {
        return this.position.y();
    }

    /**
     * Returns the camera's z position
     * @return the z position of the camera
     */
    public Double z() {
        return this.position.z();
    }

    /**
     * Shortcut for incrementing the x position
     * @param amount the amount to increment by
     */
    public void incX(double amount) {
        this.position.alter(0, this.position.x() + amount);
    }

    /**
     * Shortcut for incrementing the y position
     * @param amount the amount to increment by
     */
    public void incY(double amount) {
        this.position.alter(1, this.position.y() + amount);
    }

    /**
     * Shortcut for incrementing the z position
     * @param amount the amount to increment by
     */
    public void incZ(double amount) {
        this.position.alter(2, this.position.z() + amount);
    }

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

    /**
     * Returns the given point projected onto the image plane as a 2D vector
     */
    public Vector getProjectedPoint(Vector3 point) {
        // Get the position vector relative to the camera
        Vector positionVector = point.getDiff(this.position);

        double x = positionVector.get(0);
        double y = positionVector.get(1);
        double z = positionVector.get(2);

        final double sx = Math.sin(this.getxAngle());
        final double sy = Math.sin(this.getyAngle());
        final double sz = Math.sin(this.getzAngle());

        final double cx = Math.cos(this.getxAngle());
        final double cy = Math.cos(this.getyAngle());
        final double cz = Math.cos(this.getzAngle());


        // Apply the camera angle transforms per a left-handed system
        double dx = cy * (sz * y + cz * x) - sy * z;
        double dy = sx * (cy * z + sy * (sz * y + cz * x)) + cx * (cz * y - sz * x);
        double dz = cx * (cy * z + sy * (sz * y + cz * x)) - sx * (cz * y - sz * x);

        // Project the point onto the plane
        double zAvgInv = this.projectionPlaneZ / dz;

        x = zAvgInv * dx;
        y = zAvgInv * dy;

        System.out.println(x);
        System.out.println(y);
        System.out.println(dz);

        return new Vector(x, y);
    }

    /**
     * Returns whether the given point is in the camera's view frustrum
     * @return whether the point is in view of the camera
     */
    public boolean inView(Vector3 point) {
        Vector3 diffVec = (Vector3) point.getDiff(this.position);

        return !(diffVec.z() > farClip) && !(diffVec.z() < nearClip);
    }
}
