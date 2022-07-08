package com.company.haulmont.screen.contract;

import com.company.haulmont.entity.Contract;
import io.jmix.email.EmailException;
import io.jmix.emailtemplates.EmailTemplates;
import io.jmix.emailtemplates.exception.ReportParameterTypeChangedException;
import io.jmix.emailtemplates.exception.TemplateNotFoundException;
import io.jmix.ui.component.DateField;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@UiController("Contract.edit")
@UiDescriptor("contract-edit.xml")
@EditedEntityContainer("contractDc")
public class ContractEdit extends StandardEditor<Contract> {

    /*
            Contains field names and values that have been changed in the record
            KEY - names changed attributes
            VALUE - an array containing the {old, new} attribute values
             */
    private final Map<String, Object[]> mapChangesInTheRecord = new HashMap<>();

    //contract creation flag
    private boolean justCreated;

    @Inject
    EmailTemplates emailTemplates;

    @Subscribe
    public void onInitEntity(InitEntityEvent<Contract> event) {
        justCreated = true;
    }

    @Subscribe(target = Target.DATA_CONTEXT)
    public void onPostCommit(DataContext.PostCommitEvent event) throws TemplateNotFoundException, EmailException, ReportParameterTypeChangedException {
        if (!justCreated) {
            emailTemplates.buildFromTemplate("edit-contract2")
                    .setTo(getEditedEntity().getClient().getEmail())
                    .setBodyParameter("contract", getEditedEntity())
                    .setBodyParameter("str", editContractToString(mapChangesInTheRecord))
                    .sendEmail();
        }
    }

    @Autowired
    private DateField<LocalDateTime> dateStartField;

    @Autowired
    private DateField<LocalDate> dateEndField;

    @Subscribe(id = "contractDc", target = Target.DATA_CONTAINER)
    public void onContractDcItemPropertyChange(InstanceContainer.ItemPropertyChangeEvent<Contract> event) {
        mapChangesInTheRecord.put(event.getProperty(), new Object[]{event.getPrevValue(),event.getValue()});
    }

    @Subscribe("dateStartField")
    public void onDateStartFieldValueChange(HasValue.ValueChangeEvent<LocalDateTime> event) {
        dateEndField.setRangeStart(Objects.requireNonNull(event.getValue()).toLocalDate().plus(Period.ofDays(1)));
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        if(dateStartField.isEmpty())
            dateStartField.setValue(LocalDateTime.now());
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