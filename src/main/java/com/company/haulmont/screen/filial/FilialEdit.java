package com.company.haulmont.screen.filial;

import io.jmix.ui.screen.*;
import com.company.haulmont.entity.Filial;

@UiController("Filial.edit")
@UiDescriptor("filial-edit.xml")
@EditedEntityContainer("filialDc")
public class FilialEdit extends StandardEditor<Filial> {
}