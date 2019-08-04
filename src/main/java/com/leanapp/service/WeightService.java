package com.leanapp.service;

import com.leanapp.domain.Weight;
import com.leanapp.domain.exceptions.WeightLogNotFoundException;
import com.leanapp.repository.WeightLogRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class WeightService {

    private WeightLogRepository weightLogRepository;

    public WeightService(WeightLogRepository weightLogRepository) {
        this.weightLogRepository = weightLogRepository;
    }

    public void saveOrUpdate(Weight weight) {
        weightLogRepository.save(weight);
    }

    public List<Weight> getAllWeights() {
        return weightLogRepository.findAll();
    }

    public Optional<Weight> getWeight(Long id) throws WeightLogNotFoundException {
        return weightLogRepository.findById(id);
    }

    public void delete(Long id) {
        weightLogRepository.deleteById(id);
    }

}
