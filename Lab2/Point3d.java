package Lab2;

public class Point3d extends Point2d {
    // Поля координат
    private double zCoord;

    // Конструктор инициализации
    public Point3d(double x, double y, double z) {
        super(x, y);
        zCoord = z;
    }
    // Конструктор по умолчанию
    public Point3d() {
        super(0,0);
        zCoord = 0;
    }
    //Метод сравнение двух значений
    public static boolean equals(Point3d Obj1, Point3d Obj2) {
        if ((Obj1.getX() == Obj2.getX()) && (Obj1.getY() == Obj2.getY()) && (Obj1.getZ() == Obj2.getZ()))
            return true;
        else
            return false;

    }
    //Вычисление расстояния между двумя точками
    public static double distanceTo(Point3d Obj1, Point3d Obj2) {
    double r = (Math. sqrt(
            Math.pow(Obj2.getX() - Obj1.getX(), 2) +
            Math.pow(Obj2.getY() - Obj1.getY(), 2) +
            Math.pow(Obj2.getZ() - Obj1.getZ(), 2)));
        return r;
    }
    // Возвращение координат
    public double getZ () {
        return zCoord;
    }
    // Установка значений
    public void setZ (double val) {
        zCoord = val;
    }
}