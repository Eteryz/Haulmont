package com.company.haulmont.screen.contract;

import com.company.haulmont.app.EmailServiceBean;
import io.jmix.email.EmailException;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import com.company.haulmont.entity.Contract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;


@UiController("Contract.edit")
@UiDescriptor("contract-edit.xml")
@EditedEntityContainer("contractDc")
public class ContractEdit extends StandardEditor<Contract> {

    private static final Logger log = LoggerFactory.getLogger(Contract.class);

    /*
    Contains field names and values that have been changed in the record
    KEY - names changed attributes
    VALUE - an array containing the {old, new} attribute values
     */
    private final Map<String, Object[]> mapChangesInTheRecord = new HashMap<>();

    //contract creation flag
    private boolean justCreated;

    @Autowired
    private EmailServiceBean emailServiceBean;

    @Subscribe
    public void onInitEntity(InitEntityEvent<Contract> event) {
        justCreated = true;
    }

    @Subscribe(target = Target.DATA_CONTEXT)
    public void onPostCommit(DataContext.PostCommitEvent event) throws EmailException {
        if (justCreated) {
            emailServiceBean.sendByEmail(getEditedEntity());
        }else {
            emailServiceBean.sendByEmail(mapChangesInTheRecord,getEditedEntity().getClient().getEmail());
        }
    }

    @Subscribe(id = "contractDc", target = Target.DATA_CONTAINER)
    public void onContractDcItemPropertyChange(InstanceContainer.ItemPropertyChangeEvent<Contract> event) {
        mapChangesInTheRecord.put(event.getProperty(), new Object[]{event.getPrevValue(),event.getValue()});
    }
}