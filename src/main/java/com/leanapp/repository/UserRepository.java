package com.leanapp.repository;

import com.leanapp.domain.User;
import org.springframework.data.repository.CrudRepository;

interface UserRepository extends CrudRepository<User, Long> {
}
