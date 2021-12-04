package com.dbtesting.demo.repository;

import com.dbtesting.demo.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    
    @Query("SELECT u FROM User  u where length(u.name) > :number")
    List<User> findUsersByNameLongerThan(@Param("number") int number);   

    User findByEmail(String email);

    List<User> findByRole(String role);
}
