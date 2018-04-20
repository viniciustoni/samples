package com.vinicius.twitter.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.vinicius.twitter.model.entity.User;

public interface UserRepository extends CrudRepository<User, String>
{

}
