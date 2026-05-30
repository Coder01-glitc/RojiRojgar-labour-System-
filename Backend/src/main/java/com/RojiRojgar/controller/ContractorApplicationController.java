package com.RojiRojgar.controller;

import com.RojiRojgar.model.ContractorApplication;
import com.RojiRojgar.repository.ContractorApplicationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contractor")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ContractorApplicationController {

    @Autowired
    private ContractorApplicationRepository contractorApplicationRepository;

    // Contractor Apply For Company Job
    @PostMapping("/apply")
    public ContractorApplication applyForJob(
            @RequestBody ContractorApplication application) {

        application.setStatus("PENDING");

        return contractorApplicationRepository.save(application);
    }

    // View All Applications
    @GetMapping("/applications")
    public List<ContractorApplication> getApplications() {

        return contractorApplicationRepository.findAll();
    }

}