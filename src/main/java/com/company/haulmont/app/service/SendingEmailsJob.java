package com.company.haulmont.app.service;

import io.jmix.core.security.Authenticated;
import io.jmix.email.EmailException;
import io.jmix.emailtemplates.EmailTemplates;
import io.jmix.emailtemplates.exception.ReportParameterTypeChangedException;
import io.jmix.emailtemplates.exception.TemplateNotFoundException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class SendingEmailsJob implements Job {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(SendingEmailsJob.class);

    @Autowired
    private ContractServiceBean contractServiceBean;

    @Inject
    EmailTemplates emailTemplates;

    public static final String TEMPLATE1_CODE = "ending-contract";

    @Authenticated // authenticates the entire method
    @ManagedOperation
    @Override
    public void execute(JobExecutionContext context) {
        contractServiceBean.getContracts().forEach(x-> {
            try {
                emailTemplates.buildFromTemplate(TEMPLATE1_CODE)
                        .setTo(x.getClient().getEmail())
                        .setBodyParameter("contract", x)
                        .sendEmail();
            } catch (TemplateNotFoundException | ReportParameterTypeChangedException | EmailException e) {
                log.error("Error", e);
            }
        });
    }
}
