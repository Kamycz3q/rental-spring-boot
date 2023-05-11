package com.kamycz3q.rentalspringboot.Logging;

import com.kamycz3q.rentalspringboot.Exception.ApiRequestException;
import com.kamycz3q.rentalspringboot.Worker.Worker;
import com.kamycz3q.rentalspringboot.Worker.WorkerRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LogService {
    private final LogRepository logRepository;
    private final WorkerRepository workerRepository;

    @Autowired
    public LogService(LogRepository logRepository, WorkerRepository workerRepository) {
        this.logRepository = logRepository;
        this.workerRepository = workerRepository;
    }



    public Log AddLog(String workerId, String actionDescription) {
        Log log = new Log();
        log.setWorkerId(Integer.valueOf(workerId));
        log.setActionDescription(actionDescription);
        log.setDate(ZonedDateTime.now());
        logRepository.save(log);
        return log;
    }

    public boolean DeleteLog(String logId) {
        Optional<Log> optionalLog = logRepository.findById(Integer.valueOf(logId));
        if (!optionalLog.isPresent()) {
            throw new ApiRequestException("Log with id " + logId + " doesnt exist");
        }
        logRepository.delete(optionalLog.get());
        return true;
    }

    public List<Log> GetLogsForWorkerId(String _workerId) {
        int workerId = Integer.valueOf(_workerId);
        Optional<Worker> optionalWorker = workerRepository.findById(workerId);
        if (!optionalWorker.isPresent()) {
            throw new ApiRequestException("Worker with id  " + workerId + " doesnt exist");
        }
        List<Log> logList = new ArrayList<>();
        for (Log log : logRepository.findAll()) {
            if (log.getWorkerId() == workerId) {
                logList.add(log);
            }
        }
        return logList;
    }
}
