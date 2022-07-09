package com.company.haulmont.app.service;

import io.jmix.core.DataManager;
import io.jmix.core.FetchPlan;
import io.jmix.core.querycondition.PropertyCondition;
import io.jmix.emailtemplates.entity.JsonEmailTemplate;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class CreateTemplateServiceBean {

    @Autowired
    private DataManager dataManager;

    public void createTemplate(String templateName,
                               String templateSubject,
                               String templateCode,
                               String pathToHtml) throws IOException {
        JsonEmailTemplate jsonEmailTemplate = dataManager.create(JsonEmailTemplate.class);
        jsonEmailTemplate.setSubject(templateSubject);
        jsonEmailTemplate.setCode(templateCode);
        jsonEmailTemplate.setName(templateName);
        jsonEmailTemplate.setHtml(
                new String(IOUtils.toByteArray(Objects.requireNonNull(this.getClass().getResource(pathToHtml)))));
        List<JsonEmailTemplate> list = dataManager.load(JsonEmailTemplate.class)
                .condition(PropertyCondition.equal("code", jsonEmailTemplate.getCode()))
                .fetchPlan(fpb -> fpb.addFetchPlan(FetchPlan.BASE))
                .list();
        if(list.isEmpty())
            dataManager.save(jsonEmailTemplate);
    }
}