package Rendering.Scene;

import Rendering.Scene.Components.Camera3D;
import Rendering.Scene.Components.Scene3D;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

/**
 * Main class for 3D rendering. Is a displayable JWindow with the Scene3D placed inside it
 */
public class Window3D extends JWindow {
    /**
     * The 3DScene tied to this 3D window
     */
    private final Scene3D scene;

    /**
     * Creates a new Window3D with a scene with matching parameters
     * @param camera The camera for this scene
     * @param FPS The refresh rate for the scene
     * @param height The height of this component
     * @param width The width of this component
     */
    @SuppressWarnings("deprecation")
    public Window3D(@NotNull Camera3D camera, int FPS, int width, int height) {
        this.scene = new Scene3D(camera, FPS, width, height);

        super.setSize(width, height);
        super.setBounds(0, 0, width + 1, height + 1);
        super.add(this.scene);
    }

    /**
     * Gets the Scene3D that is rendering this screen, Scene3D should be used for manipulation of the rendering context or camera
     * @return The 3D scene component tied to this Window
     */
    public Scene3D getScene() {
        return scene;
    }

    /**
     * @deprecated Adding objects to the 3D scene should be done through the {@link Scene3D#addProp} method
     * @throws UnsupportedOperationException since this is not supported by Window3D
     */
    @Override
    @Deprecated
    public Component add(Component component) {
        throw new UnsupportedOperationException("This method is unavailable for Window3D");
    }

    public static void main(String[] args) {
        Window3D window = new Window3D(new Camera3D(0, 0, 0, 0, 0, 0, 1, 1000, 2), 30, 500, 500);

        window.setVisible(true);
    }
}
