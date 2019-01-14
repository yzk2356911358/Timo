package com.linln.boot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.linln.admin.system.domain.Client;
import com.linln.admin.system.domain.User;
import com.linln.admin.system.repository.ClientRepository;
import com.linln.admin.system.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Test
	public void contextLoads() {
//		Client client = new Client(1, "admin", "admin", "admin", "admin", "admin", "admin", "admin", "admin", "admin",
//				"admin", userRepository.getOne((long) 1));
		for(int i=0;i<=50;i++)
		{
			Client client = new Client(1, "admin"+i, "admin"+i, "admin"+i, "admin"+i, "admin"+i, "admin"+i, "admin"+i, "admin"+i, "admin"+i,
					"admin"+i, userRepository.getOne((long) 1));
			clientRepository.save(client);
			System.out.println("保存:"+i);
		}
	}

}
