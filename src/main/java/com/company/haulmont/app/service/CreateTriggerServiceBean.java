package com.company.haulmont.app.service;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTriggerServiceBean {

    @Autowired
    private Scheduler scheduler;

    public void addTaskWithTrigger(String nameJob, String groupJob, String nameTrigger, String groupTrigger, ScheduleBuilder responseTime) throws SchedulerException {
        // define the job and tie it to our SendingEmailJob class
        JobDetail job = JobBuilder.newJob(SendingEmailsJob.class)
                .withIdentity(nameJob, groupJob)
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(nameTrigger, groupTrigger)
                .withSchedule(responseTime)
                .build();
        scheduler.scheduleJob(job,trigger);

    }
}