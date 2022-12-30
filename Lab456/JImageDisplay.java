package Lab456;


import java.awt.*;
public class JImageDisplay extends javax.swing.JComponent {

    private java.awt.image.BufferedImage image;

    public JImageDisplay(int width, int height) {
        image = new java.awt.image.BufferedImage(width, height, java.awt.image.BufferedImage.TYPE_INT_RGB);
        super.setPreferredSize(new java.awt.Dimension(width, height));
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
        
    }
    public void clearImage() {
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                image.setRGB(i, j, 0);
            }
        }
    }
    public java.awt.image.BufferedImage getBufferedImage() {
        return image;
    }

    public void drawPixel(int x, int y, int rgbColor) {
        image.setRGB(x ,y, rgbColor);
    }

}