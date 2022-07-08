package com.company.haulmont.app.listner;

import com.company.haulmont.entity.Contract;
import io.jmix.core.DataManager;
import io.jmix.core.event.EntitySavingEvent;
import io.jmix.email.EmailException;
import io.jmix.emailtemplates.EmailTemplates;
import io.jmix.emailtemplates.exception.ReportParameterTypeChangedException;
import io.jmix.emailtemplates.exception.TemplateNotFoundException;
import io.jmix.reports.exception.TemplateGenerationException;
import io.jmix.reports.runner.ReportRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.IOException;

@Component
public class ContractEventListener {

    @Autowired
    protected DataManager dataManager;

    @Inject
    EmailTemplates emailTemplates;

    @Autowired
    protected ReportRunner reportRunner;

    @EventListener
    public void onContractSaving(EntitySavingEvent<Contract> event) throws TemplateNotFoundException, EmailException, ReportParameterTypeChangedException, TemplateGenerationException, IOException {
        if (event.isNewEntity()) {
            emailTemplates.buildFromTemplate("create-contract1")
                    .setTo(event.getEntity().getClient().getEmail())
                    .setBodyParameter("contract", event.getEntity())
                    .sendEmail();
        }
    }
}