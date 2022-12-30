package Lab2;
import java.util.Scanner;

public class Lab1 {
    //Ввод координат
    public static void main(String[] args) {
        System.out.println("Введите координаты первой точки через пробел");
        Scanner input1 = new Scanner(System.in);
        int x1 =  input1.nextInt();
        int y1 =  input1.nextInt();
        int z1 =  input1.nextInt();
        System.out.println("Введите координаты второй точки через пробел");
        Scanner input2 = new Scanner(System.in);
        int x2 =  input2.nextInt();
        int y2 =  input2.nextInt();
        int z2 =  input2.nextInt();
        System.out.println("Введите координаты третьей точки через пробел");
        Scanner input3 = new Scanner(System.in);
        int x3 =  input3.nextInt();
        int y3 =  input3.nextInt();
        int z3 =  input3.nextInt();
        Point3d Point1 = new Point3d(x1, y1, z1);
        Point3d Point2 = new Point3d(x2, y2, z2);
        Point3d Point3 = new Point3d(x3, y3, z3);
        if (Point3d.equals(Point1, Point2) || Point3d.equals(Point2, Point3) || Point3d.equals(Point1, Point3))
            System.out.println("Ошибка. Две точки равны");
        else {
            System.out.println(computeArea(Point1, Point2, Point3));
        }
        }
    //Нахождение длин сторон треугольника и вычисление площади
    public static String computeArea(Point3d Point1, Point3d Point2, Point3d Point3) {
        double side12 = Point3d.distanceTo(Point1, Point2);
        double side23 = Point3d.distanceTo(Point2, Point3);
        double side13 = Point3d.distanceTo(Point1, Point3);
        double p = (0.5 * (side12 + side23 + side13));
        double s = (Math. sqrt(p*(p - side12)*(p - side23)*(p-side13)));
        String result = String.format("%.2f", s);
        return result;
    }
}

