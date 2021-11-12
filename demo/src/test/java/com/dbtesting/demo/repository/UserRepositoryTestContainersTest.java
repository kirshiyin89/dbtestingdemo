package com.dbtesting.demo.repository;

import com.dbtesting.demo.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Collection;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTestContainersTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findUserByEmailTest() {
        userRepository.save(new User(1l,"Kirshi", "developer", "kirshi@example.org"));
        Collection<User> users = userRepository.findUserByEmail("kirshi@example.org");
        Assertions.assertFalse(users.isEmpty());
        Assertions.assertEquals("Kirshi", users.stream().filter(user -> user.getEmail().equals("kirshi@example.org")).findFirst().get().getName());
    }

}