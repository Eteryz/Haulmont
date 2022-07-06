package com.company.haulmont.entity;

import io.jmix.core.annotation.DeletedBy;
import io.jmix.core.annotation.DeletedDate;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@JmixEntity
@Table(name = "CONTRACT", indexes = {
        @Index(name = "IDX_CONTRACT_CLIENT_ID", columnList = "CLIENT_ID")
})
@Entity
public class Contract {
    @InstanceName
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @NotNull
    @Column(name = "DATE_START", nullable = false)
    private LocalDateTime dateStart;

    @NotNull
    @Column(name = "DATE_END", nullable = false)
    private LocalDate dateEnd;

    @Column(name = "SUM_INSURED", precision = 19, scale = 2)
    private BigDecimal sumInsured;

    @Column(name = "TYPE_INSURED", nullable = false)
    @NotNull
    private String typeInsured;

    @Column(name = "TARIFF_RATE", nullable = false)
    @NotNull
    private Double tariffRate;

    @Column(name = "RISKS_INSURED")
    @Lob
    private String risksInsured;

    @JoinColumn(name = "FILIAL_ID")
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Filial filial;

    @JoinColumn(name = "CLIENT_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Client client;

    @Column(name = "VERSION", nullable = false)
    @Version
    private Integer version;

    @CreatedBy
    @Column(name = "CREATED_BY")
    private String createdBy;

    @CreatedDate
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_BY")
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @DeletedBy
    @Column(name = "DELETED_BY")
    private String deletedBy;

    @DeletedDate
    @Column(name = "DELETED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedDate;

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }

    public String getRisksInsured() {
        return risksInsured;
    }

    public void setRisksInsured(String risksInsured) {
        this.risksInsured = risksInsured;
    }

    public Double getTariffRate() {
        return tariffRate;
    }

    public void setTariffRate(Double tariffRate) {
        this.tariffRate = tariffRate;
    }

    public String getTypeInsured() {
        return typeInsured;
    }

    public void setTypeInsured(String typeInsured) {
        this.typeInsured = typeInsured;
    }

    public BigDecimal getSumInsured() {
        return sumInsured;
    }

    public void setSumInsured(BigDecimal sumInsured) {
        this.sumInsured = sumInsured;
    }

    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Contract{" +"\n"+
                "dateStart=" + dateStart +"\n"+
                "dateEnd=" + dateEnd +"\n"+
                "sumInsured=" + sumInsured +"\n"+
                "typeInsured=" + typeInsured +"\n"+
                "tariffRate=" + tariffRate +"\n"+
                "risksInsured=" + risksInsured +"\n"+
                "filial=" + filial.getName() +"\n"+
                "client=" + client.getFullName() +"\n"+
                '}';
    }
}