package edu.miu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.miu.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
