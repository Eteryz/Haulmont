package com.company.haulmont.screen.client;

import io.jmix.ui.component.*;
import io.jmix.ui.screen.*;
import com.company.haulmont.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;

@UiController("Client.edit")
@UiDescriptor("client-edit.xml")
@EditedEntityContainer("clientDc")
public class ClientEdit extends StandardEditor<Client> {

    @Autowired
    private FileUploadField passportField;

    @Autowired
    private Image imageField;

    @Subscribe("passportField")
    public void onPassportFieldValueChange(HasValue.ValueChangeEvent<byte[]> event) {
        imageField.setSource(StreamResource.class)
                .setStreamSupplier(() -> new ByteArrayInputStream(event.getValue()));
    }


}