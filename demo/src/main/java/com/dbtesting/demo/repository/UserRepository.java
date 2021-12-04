package com.dbtesting.demo.repository;

import com.dbtesting.demo.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT u FROM User u where u.email = :email AND u.age > 18")
    User findAdultUserByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u where u.role = :role")
    List<User> findUsersByRole(@Param("role") String role);
}
