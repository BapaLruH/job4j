package ru.job4j.condition;

/**
 * Сlass Triangle.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 08.11.2018
 */
public class Triangle {
    private Point a;
    private Point b;
    private Point c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Method period.
     * Calculates half of the perimeter.
     * @param ab type double.
     * @param ac type double.
     * @param bc type double.
     * @return period type double.
     */
    public double period(double ab, double ac, double bc) {
        return (ab + ac + bc) / 2;
    }

    /**
     * Method area.
     * Calculates the area of a triangle.
     * @return area or -1 if triangle not exist. type double.
     */
    public double area() {
        double rsl = -1;
        double ab = this.a.distanceTo(this.b);
        double ac = this.a.distanceTo(this.c);
        double bc = this.b.distanceTo(c);
        double p = this.period(ab, ac, bc);
        if (this.exist(ab, ac, bc)) {
            rsl = Math.sqrt(p * (p - ab) * (p - ac) * (p - bc));
        }
        return rsl;
    }

    /**
     * Method exist.
     * Checking the existence of a triangle.
     * @param ab type double.
     * @param ac type double.
     * @param bc type double.
     * @return result type boolean.
     */
    private boolean exist(double ab, double ac, double bc) {
        return ab + ac > bc
                && bc + ab > ac
                && ac + bc > ab;
    }
}
