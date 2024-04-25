package com.DellamisDemoForm.LoginAndRegistration;

import com.DellamisDemoForm.LoginAndRegistration.dao.UserRepository;
import com.DellamisDemoForm.LoginAndRegistration.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repo;

    @Test
    public void testCreateUser(){

        User user= new User();
        user.setEmail("dellamis11@gmail.com");
        user.setPassword("dellamis1!");
        user.setFirstName("Arsene");
        user.setLastName("Nyirinkwaya");
        //now saving that user in the created database
        User savedUser= repo.save(user);
        User existUser= entityManager.find(User.class, savedUser.getId());
        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());

    }
}
