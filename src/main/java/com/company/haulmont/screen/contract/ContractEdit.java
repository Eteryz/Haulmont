package com.company.haulmont.screen.contract;

import com.company.haulmont.entity.Contract;
import io.jmix.ui.component.DateField;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

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
    private static final Map<String, Object[]> mapChangesInTheRecord = new HashMap<>();

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

    public static Map<String, Object[]> getMapChangesInTheRecord() {
        return new HashMap<>(mapChangesInTheRecord);
    }
}