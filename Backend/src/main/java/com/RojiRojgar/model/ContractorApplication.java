package com.RojiRojgar.model;

import jakarta.persistence.*;

@Entity
public class ContractorApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long companyJobId;

    private String contractorEmail;

    private String status;

    public ContractorApplication() {
    }

    public Long getId() {
        return id;
    }

    public Long getCompanyJobId() {
        return companyJobId;
    }

    public void setCompanyJobId(Long companyJobId) {
        this.companyJobId = companyJobId;
    }

    public String getContractorEmail() {
        return contractorEmail;
    }

    public void setContractorEmail(String contractorEmail) {
        this.contractorEmail = contractorEmail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}