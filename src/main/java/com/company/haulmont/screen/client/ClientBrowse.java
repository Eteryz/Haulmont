package com.company.haulmont.screen.client;

import io.jmix.ui.component.*;
import io.jmix.ui.download.DownloadFormat;
import io.jmix.ui.download.Downloader;
import io.jmix.ui.screen.*;
import com.company.haulmont.entity.Client;
import io.jmix.ui.screen.LookupComponent;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.util.LinkedList;

@UiController("Client.browse")
@UiDescriptor("client-browse.xml")
@LookupComponent("clientsTable")
public class ClientBrowse extends StandardLookup<Client> {

    private LinkedList<Client> clients;

    @Autowired
    private Downloader downloader;

    @Autowired
    private Button downloadPassportBtn;

    @Subscribe("downloadPassportBtn")
    public void onDownloadPassportBtnClick(Button.ClickEvent event) {
        downloader.download(
                clients.getFirst().getPassport(),
                clients.getFirst().getFullName() + "-photo",
                DownloadFormat.JPG
        );
    }

    @Subscribe("clientsTable")
    public void onClientsTableSelection(Table.SelectionEvent<Client> event) {
        downloadPassportBtn.setEnabled(true);
        clients = new LinkedList<>(event.getSelected());
    }

}