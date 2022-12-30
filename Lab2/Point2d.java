package Lab2;

public class Point2d {
    // Поля координат
    private double xCoord;
    private double yCoord;
    // Конструктор инициализации
    public Point2d (double x, double y) {
        xCoord = x;
        yCoord = y;
    }
    // Конструктор по умолчанию
    public Point2d () {
    }
    // Возвращение координат
    public double getX () {

        return xCoord;
    }
    public double getY () {

        return yCoord;
    }
    // Установка значений
    public void setX (double val) {

        xCoord = val;
    }
    public void setY (double val) {

        yCoord = val;
    }
}
