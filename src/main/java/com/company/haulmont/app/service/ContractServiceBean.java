package com.company.haulmont.app.service;

import com.company.haulmont.entity.Contract;
import io.jmix.core.DataManager;
import io.jmix.core.FetchPlan;
import io.jmix.core.querycondition.PropertyCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class ContractServiceBean {

    @Autowired
    private DataManager dataManager;

    //return all contracts that expire in a month
    public List<Contract> getContracts(){
        return dataManager.load(Contract.class)
                .condition(PropertyCondition.equal("dateEnd", LocalDate.now().plus(Period.ofMonths(1))))
                .fetchPlan(fpb -> fpb.addFetchPlan(FetchPlan.BASE))
                .list();
    }
}