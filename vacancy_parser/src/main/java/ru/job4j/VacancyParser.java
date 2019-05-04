package ru.job4j;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import ru.job4j.controller.LoadController;
import ru.job4j.service.Config;

public class VacancyParser {
    public static void main(String[] args) throws SchedulerException {
        String prop = "app.properties";
        if (args.length > 0) {
            prop = args[0];
        } else {
            System.out.println("Выбрано значение по-умолчанию: ".concat(prop));
        }
        Config config = new Config(prop);
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("Config", config);
        JobDetail jobDetail = JobBuilder.newJob(LoadController.class)
                .withIdentity("jobsLoader", "loaderGroup")
                .usingJobData(jobDataMap)
                .build();
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("jobTrigger", "loaderGroup")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule(config.get("cron.time"))
                )
                .build();
        scheduler.start();
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
