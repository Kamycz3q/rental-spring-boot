package com.kamycz3q.rentalspringboot.Worker;

import com.kamycz3q.rentalspringboot.Exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.remote.rmi.RMIConnectionImpl_Stub;
import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    private final WorkerRepository workerRepository;

    @Autowired
    public WorkerService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    public Worker CreateWorker(String name, String surname, String email) {
        Worker worker = new Worker();
        worker.setName(name);
        worker.setSurname(surname);
        worker.setEmail(email);
        workerRepository.save(worker);
        return worker;
    }

    public List<Worker> GetWorkers() {
        return workerRepository.findAll();
    }

    public Worker GetWorkerById(String _workerId) {
        int workerId = Integer.valueOf(_workerId);
        Optional<Worker> optionalWorker = workerRepository.findById(workerId);
        if (!optionalWorker.isPresent()) {
            throw new ApiRequestException("Worker with id " + _workerId + " doesnt exist!");
        }
        return optionalWorker.get();
    }

    public Worker UpdateWorker(String _workerId, String name, String surname, String email) {
        int workerId = Integer.valueOf(_workerId);
        Optional<Worker> optionalWorker = workerRepository.findById(workerId);
        if (!optionalWorker.isPresent()) {
            throw new ApiRequestException("Worker with id " + _workerId + " doesnt exist!");
        }
        Worker worker = optionalWorker.get();
        worker.setName(name);
        worker.setEmail(email);
        worker.setSurname(surname);
        workerRepository.save(worker);
        return worker;
    }

    public boolean DeleteWorker(String _workerId) {
        int workerId = Integer.valueOf(_workerId);
        Optional<Worker> optionalWorker = workerRepository.findById(workerId);
        if (!optionalWorker.isPresent()) {
            throw new ApiRequestException("Worker with id " + _workerId + " doesnt exist!");
        }
        Worker worker = optionalWorker.get();
        workerRepository.delete(worker);
        return true;
    }
}


