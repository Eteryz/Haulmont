package com.company.haulmont.app;

import com.company.haulmont.entity.Contract;
import io.jmix.email.EmailException;
import io.jmix.email.EmailInfo;
import io.jmix.email.EmailInfoBuilder;
import io.jmix.email.Emailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EmailServiceBean {

    @Autowired
    private Emailer emailer;

    public void sendByEmail(Contract editedEntity) throws EmailException {
        sendByEmail(editedEntity.getClient().getEmail(),"Insurance contract", editedEntity.toString());
    }

    public void sendByEmail(Map<String, Object[]> map, String email) throws EmailException {
        //TODO сделать красивый текст. Возможно добавить уникальный номер договора.
        StringBuilder stringBuilder = new StringBuilder();
        map.forEach((key, value) ->
                stringBuilder
                .append(key)
                .append(":\t'")
                .append(value[1].toString())
                .append("'\t(")
                .append("previous value '")
                .append(value[0].toString())
                .append("')")
                .append("\n"));
        sendByEmail(email,"Changes in the insurance contract!", stringBuilder.toString());
    }

    public void sendByEmail(String email,String subject, String body) throws EmailException {
        EmailInfo emailInfo = EmailInfoBuilder.create()
                .setAddresses(email)
                .setSubject(subject)
                .setFrom(null)
                .setBody(body)
                .build();
        emailer.sendEmailAsync(emailInfo);
    }
}