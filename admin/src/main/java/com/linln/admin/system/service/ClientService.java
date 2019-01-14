package com.linln.admin.system.service;

import java.util.List;

import com.linln.admin.system.domain.Client;

public interface ClientService {

	List<Client> getallClient();

	List<Client> getmeClient();

	List<Client> getameClient();

	void addclient(Client client);

	Client getclientid(int id);

	Client getclientname(String name);

	void deleteclientid(int id);

	void updateclient(Client client);

	List<Client> getpublicclient();

	void usersaveclient(int id);

}
