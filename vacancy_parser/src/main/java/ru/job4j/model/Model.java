package ru.job4j.model;

import java.time.LocalDate;
import java.util.List;

/**
 * Сlass Model.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 28.04.2019
 */
public interface Model {
    void downloadJobs();

    List<Vacancy> getJobs();

    Vacancy findById(String id);

    List<Vacancy> findByName(String name);

    List<Vacancy> findByAuthor(String author);

    List<Vacancy> findJobsBetweenDates(LocalDate start, LocalDate end);

    boolean deleteVacancy(String id);

    boolean updateVacancy(String id, Vacancy vacancy);

    void deleteAll();
}
