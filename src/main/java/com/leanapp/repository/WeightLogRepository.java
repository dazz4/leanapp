package com.leanapp.repository;

import com.leanapp.domain.Weight;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface WeightLogRepository extends CrudRepository<Weight, Long> {
}
