package com.company.haulmont.screen.contract;

import io.jmix.ui.screen.*;
import com.company.haulmont.entity.Contract;

@UiController("Contract.edit")
@UiDescriptor("contract-edit.xml")
@EditedEntityContainer("contractDc")
public class ContractEdit extends StandardEditor<Contract> {
}