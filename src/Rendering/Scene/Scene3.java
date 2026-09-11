package Rendering.Scene;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

/**
 * Class for handling 3D scenes using a swing JPanel
 */
public class Scene3 extends JPanel {
    /**
     * The camera for this scene
     */
    private Camera3D camera;

    /**
     * Creates a new 3D scene with a configured camera
     * @param camera The camera for this scene
     */
    public Scene3(@NotNull Camera3D camera) {
        this.camera = camera;
    }

    /**
     * Creates a new 3D scene with a default camera
     */
    public Scene3() {
        this(new Camera3D());
    }

    /**
     * Sets a new camera for this scene
     * @param camera The new camera
     */
    public void setCamera(@NotNull Camera3D camera) {
        this.camera = camera;
    }

    /**
     * Gets the camera for this scene
     */
    public Camera3D getCamera() {
        return this.camera;
    }



    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(new Color(0 ,0, 0));
        g.fillRect(50, 50, 50, 50);
    }

    public static void main(String[] args) {
        Scene3 scene = new Scene3();
        JFrame frame = new JFrame();

        frame.add(scene);
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.repaint();
    }
}
