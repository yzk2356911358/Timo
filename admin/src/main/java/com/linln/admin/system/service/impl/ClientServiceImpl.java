package com.linln.admin.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linln.admin.system.domain.Client;
import com.linln.admin.system.domain.Role;
import com.linln.admin.system.domain.User;
import com.linln.admin.system.repository.ClientRepository;
import com.linln.admin.system.repository.UserRepository;
import com.linln.admin.system.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<Client> getallClient() {
		Session session = SecurityUtils.getSubject().getSession();

		return clientRepository.findAll();
	}

	@Override
	public List<Client> getameClient() {
		return null;
	}

	@Override
	public void addclient(Client client) {
		clientRepository.save(client);
	}

	@Override
	public Client getclientid(int id) {

		return clientRepository.getOne(id);
	}

	@Override
	public Client getclientname(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteclientid(int id) {
		Client client = clientRepository.getOne(id);
		client.setUser(null);
		clientRepository.save(client);
	}

	@Override
	public void updateclient(Client client) {
		Session session = SecurityUtils.getSubject().getSession();
		Client temp = clientRepository.getOne(client.getId());
		temp = client;
		temp.setUser(userRepository.getOne((Long) session.getAttribute("id")));
		clientRepository.save(temp);
	}

	@Override
	public List<Client> getpublicclient() {
		List<Client> clients = new ArrayList<>();
		for (Client client : clientRepository.findAll()) {
			if (client.getUser() == null) {
				clients.add(client);
			}
		}
		return clients;
	}

	@Override
	public void usersaveclient(int id) {
		Session session = SecurityUtils.getSubject().getSession();
		Client client = clientRepository.getOne(id);
		System.out.println("id" + session.getAttribute("id"));
		client.setUser(userRepository.getOne((Long) session.getAttribute("id")));
		clientRepository.save(client);
	}

	@Override
	public List<Client> getmeClient() {

		Session session = SecurityUtils.getSubject().getSession();
		User user = userRepository.getOne((Long) session.getAttribute("id"));
		List<Client> list = new ArrayList<>();
		List<User> users = userRepository.findAll();
		for (Role role : user.getRoles()) {
			if (role.getName().equals("Employee")) {
				List<Client> clients = new ArrayList<>(user.getClientList());
				return clients;
			}
			if (role.getName().equals("admin")) {
				for (Client client : clientRepository.findAll()) {
					if (client.getUser() != null) {
						list.add(client);
					}
				}
				return list;
			}
			if (role.getName().equals("Departmentmanager")) {
				if (user.getDept().getTitle().equals("资质部")) {
					for (User temp : users) {
						if (temp.getDept().getTitle().equals("资质部")) {
							list.addAll(temp.getClientList());
						}
					}
					return list;
				}
				if (user.getDept().getTitle().equals("工商部")) {
					for (User temp : users) {
						if (temp.getDept().getTitle().equals("工商部")) {
							list.addAll(temp.getClientList());
						}
					}
					return list;
				}
			}
		}
		return list;
	}
}