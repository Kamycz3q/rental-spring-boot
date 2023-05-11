package com.kamycz3q.rentalspringboot.Worker;


import com.kamycz3q.rentalspringboot.Logging.Log;
import com.kamycz3q.rentalspringboot.Logging.LogService;
import com.kamycz3q.rentalspringboot.User.UserController;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/workers")
public class WorkerController {

    private final WorkerService workerService;
    private final LogService logService;

    @Autowired
    public WorkerController(WorkerService workerService, LogService logService) {
        this.workerService = workerService;
        this.logService = logService;
    }

    @GetMapping()
    public List<Worker> GetWorkers() {
        return workerService.GetWorkers();
    }

    @GetMapping("/{id}")
    public Worker GetWorkerById(@PathVariable(name = "id") String workerId) {
        return workerService.GetWorkerById(workerId);
    }


    @PostMapping
    public Worker CreateWorker(@RequestBody UserController.CreateUserRequest createUserRequest) {
        return workerService.CreateWorker(
                createUserRequest.name(),
                createUserRequest.surname(),
                createUserRequest.email()
        );
    }

    @DeleteMapping("/{id}")
    public boolean DeleteWorker(@PathVariable(name = "id") String workerId) {
        return workerService.DeleteWorker(workerId);
    }
    //update worker
    @PatchMapping("/{id}")
    public Worker UpdateWorker(@PathVariable(name = "id")String workerId, @RequestBody UserController.CreateUserRequest createUserRequest) {
        return workerService.UpdateWorker(workerId,
                createUserRequest.name(),
                createUserRequest.surname(),
                createUserRequest.email());
    }

    //worker logs

    record AddLogRecord(
            String workerId,
            String actionDescription
    ) {}
    @PostMapping("/logs")
    public Log AddLog(@RequestBody AddLogRecord addLogRecord) {
        return logService.AddLog(
                addLogRecord.workerId(),
                addLogRecord.actionDescription()
        );

    }

    @DeleteMapping("/logs/{id}")
    public boolean DeleteLog(@PathVariable(name = "id") String logId) {
        return logService.DeleteLog(logId);
    }

    @GetMapping("/logs/{id}")
    public List<Log> GetLogsForUseR(@PathVariable(name = "id") String workerId) {
        return logService.GetLogsForWorkerId(workerId);
    }




}
