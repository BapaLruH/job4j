package ru.job4j.dao;

import ru.job4j.model.Vacancy;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Interface VacancyDao.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 28.04.2019
 */
public interface VacancyDao {
    Vacancy create(Vacancy vacancy) throws SQLException;

    boolean update(String id, Vacancy vacancy);

    boolean delete(String id);

    LocalDateTime getLastDate();

    Vacancy findById(String id);

    List<Vacancy> findJobsBetweenDates(LocalDate start, LocalDate end);

    List<Vacancy> findByName(String name);

    List<Vacancy> findByAuthor(String author);

    List<Vacancy> findAll();

    void deleteAll();
}
