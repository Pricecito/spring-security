package com.security.spring_security.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.security.spring_security.persistence.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

}
