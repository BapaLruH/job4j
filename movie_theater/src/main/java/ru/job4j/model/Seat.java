package ru.job4j.model;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Сlass Seat.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 14.07.2019
 */
public class Seat {
    int id;
    String name;
    int row;
    double price;
    boolean occupied;
    Movie movie;

    public Seat() {
    }

    public Seat(String name, int row, Movie movie) {
        this.name = name;
        this.row = row;
        this.movie = movie;
    }

    public Seat(String name, int row, double price, boolean occupied, Movie movie) {
        this(name, row, movie);
        this.price = price;
        this.occupied = occupied;
    }

    public Seat(int id, String name, int row, double price, boolean occupied, Movie movie) {
        this(name, row, price, occupied, movie);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Seat seat = (Seat) o;
        return id == seat.id
                && row == seat.row
                && Double.compare(seat.price, price) == 0
                && occupied == seat.occupied
                && Objects.equals(name, seat.name)
                && Objects.equals(movie, seat.movie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, row, price, occupied, movie);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Seat.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("row=" + row)
                .add("price=" + price)
                .add("occupied=" + occupied)
                .add("movie=" + movie)
                .toString();
    }
}
