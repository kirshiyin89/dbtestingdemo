package com.dbtesting.demo.repository;

import com.dbtesting.demo.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

@Testcontainers
@DataJpaTest
@Sql(scripts = "/insert-data.sql")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTestContainersTest {
    
    @BeforeEach
    void init() {
        new MySQLContainer("mysql")
                .withDatabaseName("test")
                .withUsername("usr")
                .withPassword("pass")
                .start();
    }

    @Autowired
    private UserRepository userRepository;

    @Test
    void findUserByEmailTest() {
        List<User> users = (List<User>) userRepository.findAll();
        Assertions.assertFalse(users.isEmpty());
        Assertions.assertTrue(users.size() == 2);
        Assertions.assertEquals("Kirshi", users.stream().filter(user -> user.getEmail().equals("kirshi@example.org")).findFirst().get().getName());
    }

    @Test
    void findUsersByRoleTest() {
        List<User> developers = userRepository.findUsersByRole("tester");
        Assertions.assertFalse(developers.isEmpty());
    }

}
