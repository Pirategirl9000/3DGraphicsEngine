package Rendering.Scene;

import Rendering.Math.Vectors.Vector;
import Rendering.Math.Vectors.Vector3;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

/**
 * Class for handling 3D scenes using a swing JPanel
 */
public class Scene3 extends JPanel implements Runnable {
    /**
     * The camera for this scene
     */
    private Camera3D camera;

    /**
     * Refresh rate for the scene
     */
    private long FRAMERATE;

    /**
     * Creates a new 3D scene with a configured camera
     * @param camera The camera for this scene
     */
    public Scene3(@NotNull Camera3D camera, int FPS) {
        this.camera = camera;
        this.FRAMERATE = 1000 / FPS;

        // This is responsible for repainting the scene
        Thread painter = new Thread(this);


        painter.start();

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
        g.clearRect(0, 0, 10000, 10000);
    }

    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        long lastPaint = System.currentTimeMillis();

        while (true) {
            if (System.currentTimeMillis() - lastPaint >= this.FRAMERATE) {
                this.repaint();
                lastPaint = System.currentTimeMillis();
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Scene3 scene = new Scene3(new Camera3D(0, 0, 0, 0, 0, 0, 0, 1000, 2), 30);


        frame.setSize(800, 800);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(scene);


    }
}
