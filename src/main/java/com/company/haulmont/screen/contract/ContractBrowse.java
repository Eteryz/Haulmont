package com.company.haulmont.screen.contract;

import io.jmix.ui.screen.*;
import com.company.haulmont.entity.Contract;

@UiController("Contract.browse")
@UiDescriptor("contract-browse.xml")
@LookupComponent("contractsTable")
public class ContractBrowse extends StandardLookup<Contract> {
}