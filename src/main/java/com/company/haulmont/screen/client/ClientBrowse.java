package com.company.haulmont.screen.client;

import com.company.haulmont.entity.Client;
import com.company.haulmont.screen.contract.ContractClientBrowse;
import io.jmix.ui.Notifications;
import io.jmix.ui.Screens;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.Table;
import io.jmix.ui.download.DownloadFormat;
import io.jmix.ui.download.Downloader;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Autowired
    private Button showContractsBtn;

    @Autowired
    private Screens screens;

    @Autowired
    private Notifications notifications;

    @Subscribe("downloadPassportBtn")
    public void onDownloadPassportBtnClick(Button.ClickEvent event) {
        downloader.download(
                clients.getFirst().getPassport(),
                clients.getFirst().getFullName() + "-photo",
                DownloadFormat.JPG
        );
    }

    @Subscribe("showContractsBtn")
    public void onShowContractsBtnClick(Button.ClickEvent event) {
        ContractClientBrowse fancyScreen = screens.create(ContractClientBrowse.class);
        fancyScreen.setClientField(clients.getFirst());
        fancyScreen.setEnabledClientField(false);
        screens.show(fancyScreen);
    }

    @Subscribe("clientsTable")
    public void onClientsTableSelection(Table.SelectionEvent<Client> event) {
        downloadPassportBtn.setEnabled(true);
        showContractsBtn.setEnabled(true);
        clients = new LinkedList<>(event.getSelected());
    }
}