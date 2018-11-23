package ru.job4j.pseudo;

/**
 * Сlass Paint.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 23.11.2018
 */
public class Triangle implements Shape {
    /**
     * Method draw.
     * Draws a triangle.
     *
     * @return result type String.
     */
    @Override
    public String draw() {
        StringBuilder sb = new StringBuilder();
        sb.append("  *  ");
        sb.append(" * * ");
        sb.append("*****");
        return sb.toString();
    }
}
