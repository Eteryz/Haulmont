package com.company.haulmont.screen.contract;

import com.company.haulmont.app.EmailServiceBean;
import io.jmix.email.EmailException;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.screen.*;
import com.company.haulmont.entity.Contract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


@UiController("Contract.edit")
@UiDescriptor("contract-edit.xml")
@EditedEntityContainer("contractDc")
public class ContractEdit extends StandardEditor<Contract> {

    private static final Logger log = LoggerFactory.getLogger(Contract.class);

    //contract creation flag
    private boolean justCreated;

    @Autowired
    private EmailServiceBean emailServiceBean;

    @Subscribe
    public void onInitEntity(InitEntityEvent<Contract> event) {
        justCreated = true;
    }

    @Subscribe(target = Target.DATA_CONTEXT)
    public void onPostCommit(DataContext.PostCommitEvent event) {
        if (justCreated) {
            try {
                emailServiceBean.sendByEmail(getEditedEntity());
            } catch (EmailException e) {
                log.error("Error sending email", e);
            }
        }
    }
}