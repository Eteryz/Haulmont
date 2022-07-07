package com.company.haulmont.app;

import com.company.haulmont.entity.Contract;
import com.company.haulmont.screen.contract.ContractEdit;
import io.jmix.core.event.EntitySavingEvent;
import io.jmix.email.EmailException;
import io.jmix.emailtemplates.EmailTemplates;
import io.jmix.emailtemplates.exception.ReportParameterTypeChangedException;
import io.jmix.emailtemplates.exception.TemplateNotFoundException;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Map;

@Component
public class ContractEventListener {

    @Inject
    EmailTemplates emailTemplates;

    public static final String TEMPLATE1_CODE = "create-contract";
    public static final String TEMPLATE2_CODE = "edit-contract";

    @EventListener
    public void onContractSaving(EntitySavingEvent<Contract> event) throws TemplateNotFoundException, EmailException, ReportParameterTypeChangedException {
        if (event.isNewEntity()) {
            emailTemplates.buildFromTemplate(TEMPLATE1_CODE)
                    .setTo(event.getEntity().getClient().getEmail())
                    .setBodyParameter("contract", event.getEntity())
                    .sendEmail();
        }else{
            emailTemplates.buildFromTemplate(TEMPLATE2_CODE)
                    .setTo(event.getEntity().getClient().getEmail())
                    .setBodyParameter("contract", event.getEntity())
                    .setBodyParameter("str", editContractToString(ContractEdit.getMapChangesInTheRecord()))
                    .sendEmail();
        }
    }

    private String editContractToString(Map<String, Object[]> map){
        StringBuilder stringBuilder = new StringBuilder();
        map.forEach((key, value) ->
                stringBuilder
                        .append(key)
                        .append("\t'")
                        .append(value[1])
                        .append("'\t(")
                        .append("previous value '")
                        .append(value[0])
                        .append("')")
                        .append("\n"));
        return stringBuilder.toString();
    }
}