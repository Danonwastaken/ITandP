package Lab456;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;



public class FractalExplorer {
    private int screenSize;
    private JFrame frame;
    private JImageDisplay display;
    private FractalGenerator fractalGenerator;
    private Rectangle2D.Double fractalRectangle;
    private JComboBox comboBox;
    private static final String SCREEN_TITLE = "Drawing Fractals";
    private static final String RESET = "Reset Display";
    private static final String SAVE = "Save Display";
    private JButton resetButton;
    private JButton saveButton;
    private int rowsRemaining = 0;

    public FractalExplorer(int ScreenSize) {
        screenSize = ScreenSize;
        fractalGenerator = new Mandelbrot();
        fractalRectangle = new Rectangle2D.Double();
        fractalGenerator.getInitialRange(fractalRectangle);
        display = new JImageDisplay(screenSize, screenSize);
    }

    class ActionHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String actionCommand = e.getActionCommand();
            if (actionCommand.equals("comboBoxChanged")) {
                fractalGenerator = (FractalGenerator) comboBox.getSelectedItem();
                fractalGenerator.getInitialRange(fractalRectangle);
                drawFractal();
            }
            else if (actionCommand.equals(RESET)){
                fractalGenerator.getInitialRange(fractalRectangle);
                drawFractal();
            }
            else if (actionCommand.equals(SAVE)) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images","png");
                chooser.setFileFilter(filter);
                chooser.setAcceptAllFileFilterUsed(false);
                if (chooser.showSaveDialog(display) == JFileChooser.APPROVE_OPTION) {
                    try {
                        ImageIO.write(display.getBufferedImage(), "png", chooser.getSelectedFile());
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(display, "Cant save image", SCREEN_TITLE, JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    class Mouse extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (rowsRemaining != 0) {
                return;
            }
            display.clearImage();
            double xCoord = FractalGenerator.getCoord(fractalRectangle.x, fractalRectangle.x +
                    fractalRectangle.width, screenSize, e.getX());
            double yCoord = FractalGenerator.getCoord(fractalRectangle.y, fractalRectangle.y +
                    fractalRectangle.width, screenSize, e.getY());
            fractalGenerator.recenterAndZoomRange(fractalRectangle, xCoord, yCoord, 0.5);
            drawFractal();
        }
    }

    public void createAndShowGUI() {
        frame = new JFrame(SCREEN_TITLE);
        display = new JImageDisplay(screenSize, screenSize);
        frame.add(display, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ActionHandler handler = new ActionHandler();
        Mouse clicking = new Mouse();
        display.addMouseListener(clicking);

        JPanel top = new JPanel(); //Объявим панели
        JPanel bottom = new JPanel();

        JLabel label = new JLabel("Current fractal: ");
        top.add(label);

        comboBox = new JComboBox(); // Выбор фрактала
        comboBox.addItem(new Mandelbrot());
        comboBox.addItem(new Tricorn());
        comboBox.addItem(new BurningShip());
        comboBox.addActionListener(handler);
        top.add(comboBox, BorderLayout.NORTH);


        resetButton = new JButton(RESET); // Reset
        resetButton.addActionListener(handler);
        bottom.add(resetButton, BorderLayout.WEST);

        saveButton = new JButton(SAVE); // Save Image
        saveButton.addActionListener(handler);
        bottom.add(saveButton, BorderLayout.EAST);

        frame.add(bottom, BorderLayout.SOUTH);
        frame.add(top, BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        drawFractal();
    }

    public void drawFractal() {
        rowsRemaining = screenSize;
        enableUI(false);
        for (int y = 0; y < screenSize; y++) {
            new FractalWorker(y).execute();
        }
    }
    private class FractalWorker extends SwingWorker<Object, Object> {
        private int yLine;
        private int[] colorsRGB;

        public FractalWorker(int yLine) {
            this.yLine = yLine;
        }
        @Override
        protected Object doInBackground() {
            colorsRGB = new int[screenSize];
            int rgbColor;
            for (int x = 0; x < screenSize; x++) {
                    double xCoord = fractalGenerator.getCoord(fractalRectangle.x, fractalRectangle.x +
                            fractalRectangle.width, screenSize, x);
                    double yCoord = fractalGenerator.getCoord(fractalRectangle.y, fractalRectangle.y +
                            fractalRectangle.height, screenSize, yLine);
                    int iteration = fractalGenerator.numIterations(xCoord, yCoord);
                    rgbColor = 0;
                    if (iteration > 0) {
                        float hue = 0.7f + (float) iteration / 200f;
                        rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    }
                    colorsRGB[x] = rgbColor;
            }
            return null;
        }
        @Override
        protected void done() {
            for (int x = 0; x < screenSize; x++){
                display.drawPixel(x, yLine, colorsRGB[x]);
            }
            display.repaint(0, yLine, screenSize, 1);
            rowsRemaining--;
            if (rowsRemaining == 0) {
                enableUI(true);
            }
        }
    }
    public void enableUI(boolean val) {
        resetButton.setEnabled(val);
        saveButton.setEnabled(val);
        comboBox.setEnabled(val);
    }
    public static void main(String[] args) {
        FractalExplorer fractalExplorer = new FractalExplorer(800);
        fractalExplorer.createAndShowGUI();
    }
}