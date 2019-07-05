package ru.job4j.controller;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import ru.job4j.model.DBModel;
import ru.job4j.model.Model;
import ru.job4j.service.Config;
import ru.job4j.view.JobsView;
import ru.job4j.view.View;

/**
 * Сlass LoadController.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 28.04.2019
 */
public class LoadController implements Job {
    private Model model;
    private View view = new JobsView();

    public LoadController() {
    }

    public void execute(Config config) {
        this.model = new DBModel(config);
        this.model.downloadJobs();
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Config config = (Config) jobExecutionContext.getJobDetail().getJobDataMap().get("Config");
        this.model = new DBModel(config);
        this.model.downloadJobs();
    }
}
