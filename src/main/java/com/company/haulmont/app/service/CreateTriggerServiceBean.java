package com.company.haulmont.app.service;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTriggerServiceBean {

    @Autowired
    private Scheduler scheduler;

    public void createJob(String nameJob, String groupJob,Class jobHandleClass, Trigger trigger) throws SchedulerException {
        if (!scheduler.checkExists(new JobKey(nameJob,groupJob))) {
            JobDetail jobDetail = JobBuilder.newJob(jobHandleClass)
                    .withIdentity(nameJob, groupJob)
                    .build();
            scheduler.scheduleJob(jobDetail, trigger);
        }
    }

    public Trigger createTrigger(String nameTrigger, String groupTrigger, ScheduleBuilder responseTime) throws SchedulerException {
        TriggerKey triggerKey = new TriggerKey(nameTrigger,groupTrigger);
        if(!scheduler.checkExists(triggerKey)){
            return TriggerBuilder.newTrigger()
                    .withIdentity(nameTrigger, groupTrigger)
                    .withSchedule(responseTime)
                    .build();
        } else {
            return scheduler.getTrigger(triggerKey);
        }
    }
}