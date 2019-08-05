package com.leanapp.repository;

import com.leanapp.domain.Weight;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WeightLogRepository extends CrudRepository<Weight, Long> {
    List<Weight> findAll();
}
