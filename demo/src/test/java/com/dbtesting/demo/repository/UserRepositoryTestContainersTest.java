package com.dbtesting.demo.repository;

import com.dbtesting.demo.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
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
@Sql(scripts = "/create-data.sql")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTestContainersTest {

    @BeforeAll
    static void init() {
        new MySQLContainer("mysql")
                .withDatabaseName("test")
                .withUsername("usr")
                .withPassword("pass")
                .start();
    }

    @Autowired
    private UserRepository userRepository;

    @Test
    void findUsersByNameLongerThanTest() {
        List<User> users = userRepository.findUsersByNameLongerThan(4);
        Assertions.assertEquals("Kirshi", users.stream().findAny().get().getName());
    }    

    @Test
    void findUserByEmailTest() {
        User user = userRepository.findByEmail("kirshi@example.org");
        Assertions.assertNotNull(user);
        Assertions.assertEquals("Kirshi", user.getName());
    }

    @Test
    void findUsersByRoleTest() {
        List<User> developers = userRepository.findByRole("developer");
        Assertions.assertFalse(developers.isEmpty());
    }

}
