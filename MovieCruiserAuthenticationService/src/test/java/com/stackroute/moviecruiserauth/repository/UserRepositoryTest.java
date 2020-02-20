package com.stackroute.moviecruiserauth.repository;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.stackroute.moviecruiserauth.domain.User;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class UserRepositoryTest {

	public UserRepositoryTest() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private UserRepository userRepository;
	
	private User user;
	
	@Before
	public void setUp()
	{
		user=new User("jhabrajesh", "Brajesh", "Jha", "jha12345", new Date());
	}
	
	@Test
	public void testRegisterUserSuccess()
	{
		userRepository.save(user);
		Optional<User> userOptional=userRepository.findById(user.getUserId());
		assertThat(userOptional.equals(user));
	}
	
	
}

