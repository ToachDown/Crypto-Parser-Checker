package com.example.demo.services.sheduler;

import com.example.demo.services.job.ExchangeJob;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CryptoScheduler {

    private final List<ExchangeJob> jobs;

    public CryptoScheduler(List<ExchangeJob> jobs) {
        this.jobs = jobs;
    }

    @Scheduled(cron = "0 * * * * *")
    public void parse() {
        jobs.forEach(ExchangeJob::loadCoinsData);
    }
}
