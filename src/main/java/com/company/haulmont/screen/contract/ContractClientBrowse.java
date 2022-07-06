package com.company.haulmont.screen.contract;

import com.company.haulmont.entity.Client;
import com.company.haulmont.entity.Contract;
import groovyjarjarantlr4.v4.runtime.misc.Nullable;
import io.jmix.core.DataManager;
import io.jmix.core.FetchPlan;
import io.jmix.core.querycondition.PropertyCondition;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.EntityPicker;
import io.jmix.ui.component.GroupTable;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.component.data.GroupInfo;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@UiController("ContractClient.browse")
@UiDescriptor("contract-client-browse.xml")
@LookupComponent("contractsTable")
public class ContractClientBrowse extends StandardLookup<Contract> {

    @Autowired
    private EntityPicker<Client> clientField;

    @Autowired
    private Notifications notifications;

    @Autowired
    private CollectionContainer<Contract> contractsDc;

    @Autowired
    private DataManager dataManager;
    @Autowired
    private GroupTable<Contract> contractsTable;

    @Subscribe("clientField")
    public void onClientFieldValueChange(HasValue.ValueChangeEvent<Client> event) {
        Client customer = clientField.getValue();
        if (customer != null) {
            notifications.create()
                    .withCaption(customer.getFullName())
                    .show();
            contractsDc.setItems(dataManager.load(Contract.class)
                    .condition(PropertyCondition.equal("client.id",customer.getId()))
                    .fetchPlan(fpb -> fpb.addFetchPlan(FetchPlan.BASE))
                    .list());
            setRowStyleInTable();
        } else {
            notifications.create()
                    .withCaption("Choose a customer")
                    .show();
        }
    }

    private void setRowStyleInTable() {
        contractsTable.setStyleProvider(new GroupTable.GroupStyleProvider<Contract>() {
            @Nullable
            @Override
            public String getStyleName(Contract entity, @Nullable String property) {
                if (Boolean.TRUE.equals(entity.getDateEnd().isAfter(LocalDate.now()))) {
                    return "table-row-color";
                }
                return null;
            }

            @Nullable
            @Override
            public String getStyleName(GroupInfo info) {
                return null;
            }

        });
    }
}