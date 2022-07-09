package com.company.haulmont.app.listner;

import com.company.haulmont.entity.Contract;
import io.jmix.core.event.EntitySavingEvent;
import io.jmix.email.EmailException;
import io.jmix.emailtemplates.EmailTemplates;
import io.jmix.emailtemplates.exception.ReportParameterTypeChangedException;
import io.jmix.emailtemplates.exception.TemplateNotFoundException;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class ContractEventListener {

    @Inject
    EmailTemplates emailTemplates;

    @EventListener
    public void onContractSaving(EntitySavingEvent<Contract> event) throws TemplateNotFoundException, EmailException, ReportParameterTypeChangedException {
        if (event.isNewEntity()) {
            emailTemplates.buildFromTemplate("create-contract1")
                    .setTo(event.getEntity().getClient().getEmail())
                    .setBodyParameter("contract", event.getEntity())
                    .sendEmail();
        }
    }
}