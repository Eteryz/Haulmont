package com.company.haulmont.screen.filial;

import io.jmix.ui.screen.*;
import com.company.haulmont.entity.Filial;

@UiController("Filial.browse")
@UiDescriptor("filial-browse.xml")
@LookupComponent("filialsTable")
public class FilialBrowse extends StandardLookup<Filial> {
}