package com.company.haulmont.screen.contract;

import com.company.haulmont.app.EmailServiceBean;
import io.jmix.ui.Dialogs;
import io.jmix.ui.action.DialogAction;
import io.jmix.ui.component.Component;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.screen.*;
import com.company.haulmont.entity.Contract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@UiController("Contract.edit")
@UiDescriptor("contract-edit.xml")
@EditedEntityContainer("contractDc")
public class ContractEdit extends StandardEditor<Contract> {

    private static final Logger log = LoggerFactory.getLogger(Contract.class);

    private boolean justCreated;

    @Autowired
    private EmailServiceBean emailServiceBean;

    @Inject
    private Dialogs dialogs;

    @Subscribe
    public void onInitEntity(InitEntityEvent<Contract> event) {
        justCreated = true;
    }

    @Subscribe(target = Target.DATA_CONTEXT)
    public void onPostCommit(DataContext.PostCommitEvent event) {
        if (justCreated) {
            dialogs.createOptionDialog()
                    .withCaption("Email")
                    .withMessage("Send the news item by email?")
                    .withActions(
                            new DialogAction(DialogAction.Type.YES) {
                                @Override
                                public void actionPerform(Component component) {
                                    try {
                                        emailServiceBean.sendByEmail(getEditedEntity());
                                    } catch (IOException e) {
                                        log.error("Error sending email", e);
                                    }
                                }
                            },
                            new DialogAction(DialogAction.Type.NO)
                    )
                    .show();
        }
    }
    
}