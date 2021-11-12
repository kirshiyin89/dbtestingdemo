package com.dbtesting.demo.repository;

import com.dbtesting.demo.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@Sql(scripts = "/insert-data.sql")
@Sql(scripts = "/cleanup-data.sql", executionPhase = AFTER_TEST_METHOD)
public class UserRepositoryH2Test {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findUserByEmailTest() {
        Collection<User> users = userRepository.findUserByEmail("kirshi@example.org");
        Assertions.assertFalse(users.isEmpty());
        Assertions.assertEquals("Kirshi", users.stream().filter(user -> user.getEmail().equals("kirshi@example.org")).findFirst().get().getName());
    }
}
