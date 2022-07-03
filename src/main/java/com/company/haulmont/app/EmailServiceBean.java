package com.company.haulmont.app;

import com.company.haulmont.entity.Contract;
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

    /*@Autowired
    protected Resources resources;*/

    public void sendByEmail(Contract editedEntity) throws IOException {
        /*InputStream resourceAsStream = resources.getResourceAsStream("email/ex1/logo.png");
        byte[] bytes = IOUtils.toByteArray(resourceAsStream);
        EmailAttachment emailAtt = new EmailAttachment(bytes,
                "logo.png", "logoId");*/
        EmailInfo emailInfo = EmailInfoBuilder.create()
                .setAddresses(editedEntity.getClient().getEmail())
                .setSubject("Insurance contract")
                .setFrom(null)
                .setBody(editedEntity.toString())
                //.setAttachments(emailAtt)
                .build();
        emailer.sendEmailAsync(emailInfo);
    }
}