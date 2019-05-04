package ru.job4j.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.dao.VacancyDao;
import ru.job4j.dao.VacancyDaoImpl;
import ru.job4j.service.Config;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Сlass DBModel.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 28.04.2019
 */
public class DBModel implements Model {
    private static final Logger LOG = LogManager.getLogger(DBModel.class);
    private VacancyDao vacancyDao;

    public DBModel(Config config) {
        this.vacancyDao = new VacancyDaoImpl(config);
    }

    /**
     * Loads all vacancies from websites.
     */
    @Override
    public void downloadJobs() {
        getJobOfferSqlRu();
    }

    /**
     * Loads vacancies from sql.ru for the current year.
     */
    private void getJobOfferSqlRu() {
        boolean isCancel = false;
        String url = "https://www.sql.ru/forum/job-offers/";
        int pageNumber = 1;
        LocalDateTime lastDBDate = this.vacancyDao.getLastDate();
        LocalDateTime lastDateLastYear = LocalDateTime.of(LocalDateTime.now().getYear(), 1, 1, 0, 0).minusSeconds(1);
        List<Vacancy> vacancyList = new ArrayList<>();

        while (!isCancel) {
            try {
                Document doc = getCurrentDocument(url.concat(String.valueOf(pageNumber)));
                Elements tableRows = doc.select("table.forumTable").select("tr");
                for (Element row : tableRows) {
                    if (row.select("td").size() > 0) {
                        boolean isAdmText = row.select("td").text().contains("Важно");
                        if (isAdmText) {
                            continue;
                        }
                        String dateStr = row.select("td").get(5).text();
                        LocalDateTime dateTime = parseDate(dateStr);
                        if (lastDateLastYear.isAfter(dateTime)) {
                            isCancel = true;
                            continue;
                        } else if (lastDBDate != null && (lastDBDate.isAfter(dateTime) || lastDBDate.equals(dateTime))) {
                            continue;
                        }
                        String link = row.select("td").get(1).select("a").attr("href");
                        String name = row.select("td").get(1).select("a").text();
                        String author = row.select("td").get(2).text();
                        boolean isJava = name.toLowerCase().contains("java") && !name.toLowerCase().contains("script");
                        if (isJava) {
                            String text = getTextFromCurrentJob(link);
                            Vacancy vacancy = new Vacancy(author, name, text, link, "sql.ru", dateTime);
                            vacancyList.add(vacancy);
                        }
                    }
                }
                pageNumber++;
            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        searchAndAddJobs(vacancyList);
    }

    /**
     * Get the document by url.
     *
     * @param url type String
     * @return doc type Document
     * @throws IOException if the document cannot be retrieved.
     */
    private Document getCurrentDocument(String url) throws IOException {
        return Jsoup.connect(url)
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();
    }

    /**
     * Get the text of current vacancy.
     *
     * @param url type String
     * @return vacancy text
     */
    private String getTextFromCurrentJob(String url) {
        String massage = null;
        try {
            Document doc = getCurrentDocument(url);
            Elements elements = doc.getElementsByClass("msgBody");
            if (elements.size() > 1) {
                massage = elements.get(1).text();
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return massage;
    }

    /**
     * Parse date_time from text.
     *
     * @param str type String
     * @return dateTime type LocalDateTime
     */
    private LocalDateTime parseDate(String str) {
        LocalDateTime dateTime = null;
        String[] strings = str.split(" ");
        if (strings.length == 4) {
            StringBuilder sb = new StringBuilder();
            String day = strings[0].length() != 2 ? "0".concat(strings[0]) : strings[0];
            sb.append(day).append(" ").append(getMonth(strings[1])).append(" ").append(strings[2]).append(" ").append(strings[3]).append(":00");
            dateTime = LocalDateTime.parse(sb.toString(), DateTimeFormatter.ofPattern("dd MM yy, HH:mm:ss"));
        } else if (strings.length > 1) {
            if ("сегодня,".equals(strings[0])) {
                dateTime = LocalDateTime.now()
                        .toLocalDate()
                        .atTime(LocalTime.parse(strings[1]));
            } else {
                dateTime = LocalDateTime.now()
                        .toLocalDate()
                        .minusDays(1)
                        .atTime(LocalTime.parse(strings[1]));
            }
        }
        return dateTime;
    }

    /**
     * Returns the numeric representation of the month.
     *
     * @param strMonth type String
     * @return rsl type String
     */
    private String getMonth(String strMonth) {
        String numMonth;
        switch (strMonth) {
            case "янв":
                numMonth = "01";
                break;
            case "фев":
                numMonth = "02";
                break;
            case "мар":
                numMonth = "03";
                break;
            case "апр":
                numMonth = "04";
                break;
            case "май":
                numMonth = "05";
                break;
            case "июн":
                numMonth = "06";
                break;
            case "июл":
                numMonth = "07";
                break;
            case "авг":
                numMonth = "08";
                break;
            case "сен":
                numMonth = "09";
                break;
            case "окт":
                numMonth = "10";
                break;
            case "ноя":
                numMonth = "11";
                break;
            default:
                numMonth = "12";
                break;
        }
        return numMonth;
    }

    /**
     * Creates new elements in the database.
     *
     * @param vacancies type List<Vacancy>
     */
    private void searchAndAddJobs(List<Vacancy> vacancies) {
        List<Vacancy> jobsFromDb = getJobs();
        vacancies.stream().filter(key -> !jobsFromDb.contains(key)).forEach(key -> {
            try {
                this.vacancyDao.create(key);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Returns all entries from the database.
     *
     * @return vacancies type List<Vacancy>
     */
    @Override
    public List<Vacancy> getJobs() {
        return this.vacancyDao.findAll();
    }

    /**
     * Returns a record from the database with an id.
     *
     * @return vacancy type Vacancy
     */
    @Override
    public Vacancy findById(String id) {
        return this.vacancyDao.findById(id);
    }

    /**
     * Returns a list of vacancies from the database with an name.
     *
     * @return vacancies type List<Vacancy>
     */
    @Override
    public List<Vacancy> findByName(String name) {
        return this.vacancyDao.findByName(name);
    }

    /**
     * Returns a list of vacancies from the database with an author.
     *
     * @return vacancies type List<Vacancy>
     */
    @Override
    public List<Vacancy> findByAuthor(String author) {
        return this.vacancyDao.findByAuthor(author);
    }

    /**
     * Returns a list of vacancies from a database with dates between the start date and the end date.
     *
     * @return vacancies type List<Vacancy>
     */
    @Override
    public List<Vacancy> findJobsBetweenDates(LocalDate start, LocalDate end) {
        return this.vacancyDao.findJobsBetweenDates(start, end);
    }

    /**
     * Deletes a vacancy with specified id.
     * @param id type String
     * @return result type boolean
     */
    @Override
    public boolean deleteVacancy(String id) {
        return this.vacancyDao.delete(id);
    }

    /**
     * Updates a vacancy with specified id.
     * @param id type String
     * @return result type boolean
     */
    @Override
    public boolean updateVacancy(String id, Vacancy vacancy) {
        return this.vacancyDao.update(id, vacancy);
    }

    /**
     * Removes all vacancies.
     */
    @Override
    public void deleteAll() {
        this.vacancyDao.deleteAll();
    }
}
