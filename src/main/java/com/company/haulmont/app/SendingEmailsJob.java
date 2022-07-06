package com.company.haulmont.app;

import io.jmix.core.security.Authenticated;
import io.jmix.email.EmailException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.stereotype.Service;

@Service
public class SendingEmailsJob implements Job {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(SendingEmailsJob.class);
    @Autowired
    private ContractServiceBean contractServiceBean;

    @Autowired
    private EmailServiceBean emailServiceBean;

    @Authenticated // authenticates the entire method
    @ManagedOperation
    @Override
    public void execute(JobExecutionContext context) {
        contractServiceBean.getContracts().forEach(x-> {
            try {
                emailServiceBean.sendByEmail(x.getClient().getEmail(),"Contract", "We remind you that after 1 month the contract № will be invalid.");
            } catch (EmailException e) {
                log.error("Error", e);
            }
        });
    }
}
