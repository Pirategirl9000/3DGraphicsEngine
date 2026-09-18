package Rendering.Scene.Components;

import Rendering.Scene.Window3D;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * Class for handling 3D scenes using a swing JPanel
 */
public class Scene3D extends Component implements Runnable {
    /**
     * The camera for this scene
     */
    private Camera3D camera;

    /**
     * Refresh rate for the scene
     */
    private final long FRAMERATE;

    /**
     * Width of the scene component
     */
    private final int WIDTH;

    /**
     * Height of the scene component
     */
    private final int HEIGHT;

    /**
     * The adjusted clip bounds for this component, used every repaint
     */
    private final Shape CLIPBOUNDS;

    /**
     * Creates a new 3D scene with a configured camera
     * @param camera The camera for this scene
     * @param FPS The refresh rate for the scene
     * @param height The height of this component
     * @param width The width of this component
     * @deprecated Due to window clipping and insets standard users should instead use {@link Window3D#Window3D} for building and displaying their 3D scenes
     */
    @Deprecated
    public Scene3D(@NotNull Camera3D camera, int FPS, int width, int height) {
        this.camera = camera;
        this.FRAMERATE = 1000 / FPS;
        this.WIDTH = width;
        this.HEIGHT = height;

        this.CLIPBOUNDS = new Rectangle(0, 0, WIDTH + 1, HEIGHT + 1);  // Normally clip bounds don't include the bottom portion but we want it to in our case

        this.setSize(WIDTH, HEIGHT);

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

    public void addProp() {
        // TODO: implement
    }

    @Override
    public void paint(Graphics g) {
        g.clearRect(0, 0, this.WIDTH, this.HEIGHT);
        g.setClip(CLIPBOUNDS);

        g.setColor(Color.blue);
        g.drawLine(0, HEIGHT, WIDTH, HEIGHT);

        // TODO: Project all points in the meshes and then render them as triangles here
    }


    /**
     * Repaints the canvas at a rate of {@link #FRAMERATE}
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


    }
}
