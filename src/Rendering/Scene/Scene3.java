package Rendering.Scene;

import javax.swing.*;
import java.awt.*;

public class Scene3 extends JPanel {
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
