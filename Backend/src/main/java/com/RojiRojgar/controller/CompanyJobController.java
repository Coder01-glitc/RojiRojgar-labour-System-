package com.RojiRojgar.controller;

import com.RojiRojgar.model.CompanyJob;
import com.RojiRojgar.repository.CompanyJobRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/company")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class CompanyJobController {

    @Autowired
    private CompanyJobRepository companyJobRepository;

    // POST JOB
    @PostMapping("/jobs")
    public CompanyJob postJob(@RequestBody CompanyJob job) {

        job.setStatus("OPEN");

        return companyJobRepository.save(job);
    }

    // GET ALL JOBS
    @GetMapping("/jobs")
    public List<CompanyJob> getAllJobs() {

        return companyJobRepository.findAll();
    }

    // DASHBOARD STATS
    @GetMapping("/stats")
    public Map<String, Object> getStats() {

        List<CompanyJob> jobs =
                companyJobRepository.findAll();

        int activeSites = jobs.size();

        int totalWorkers = jobs.stream()
                .mapToInt(CompanyJob::getWorkersRequired)
                .sum();

        double monthlySpending = jobs.stream()
                .mapToDouble(CompanyJob::getBudget)
                .sum();

        Map<String, Object> stats =
                new HashMap<>();

        stats.put("activeSites", activeSites);
        stats.put("totalWorkers", totalWorkers);
        stats.put("monthlySpending", monthlySpending);

        return stats;
    }
    @DeleteMapping("/jobs/{id}")
    public String deleteJob(@PathVariable Long id) {

        companyJobRepository.deleteById(id);

        return "Job Deleted Successfully";
    }
}