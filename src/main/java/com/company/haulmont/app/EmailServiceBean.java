package com.company.haulmont.app;

import com.company.haulmont.entity.Contract;
import io.jmix.email.EmailException;
import io.jmix.email.EmailInfo;
import io.jmix.email.EmailInfoBuilder;
import io.jmix.email.Emailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class EmailServiceBean {

    @Autowired
    private Emailer emailer;

    public void sendByEmail(Contract editedEntity) throws EmailException {
        EmailInfo emailInfo = EmailInfoBuilder.create()
                .setAddresses(editedEntity.getClient().getEmail())
                .setSubject("Insurance contract")
                .setFrom(null)
                .setBody(editedEntity.toString())
                .build();
        emailer.sendEmail(emailInfo);
    }
}