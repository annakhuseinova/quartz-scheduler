package com.annakhuseinova;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class Lesson1 {

    public static void main(String[] args) throws SchedulerException {

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        /**
         * Once a scheduler is instantiated, it can be started, placed in stand-by mode, and shutdown.
         * Once a scheduler is shutdown, it cannot be restarted without being re-instantiated. Triggers don't fire
         * (jobs don't execute) until the scheduler has been started, nor while it is in the paused state.
         * */
        scheduler.start();
        /**
         * Define the job and tie it to our HelloJob class
         * */
        JobDetail job = newJob(HelloJob.class)
                .withIdentity("myjob", "group1")
                .build();

        /**
         * Trigger the job to run now and then every 40 seconds
         * */
        Trigger trigger = newTrigger()
                .withIdentity("myTrigger", "group1")
                .startNow()
                .withSchedule(simpleSchedule().withIntervalInSeconds(40).repeatForever())
                .build();

        /**
         * Tell Quartz to schedule the job using our trigger
         * */
        scheduler.scheduleJob(job, trigger);
    }
}
