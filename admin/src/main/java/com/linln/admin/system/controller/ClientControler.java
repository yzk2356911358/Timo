package com.linln.admin.system.controller;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.linln.admin.system.domain.Client;
import com.linln.admin.system.service.ClientService;
import com.linln.admin.system.service.UserService;

@Controller
@RequestMapping("/client")
public class ClientControler {
	@Autowired
	private ClientService clientService;
	@Autowired
	private UserService userService;

	@RequestMapping("/add")
	public String addClient(Client client, Map<String, Object> map) {
		Session session = SecurityUtils.getSubject().getSession();
		client.setUser(userService.getId((Long) (session.getAttribute("id"))));
		clientService.addclient(client);
		getAllclient(map);
		return "/system/client/getAllclient";
	}

	@RequestMapping("/getallclient")
	public String getAllclient(Map<String, Object> map) {
		map.put("list", clientService.getmeClient());
		return "/system/client/getAllclient";
	}

	@RequestMapping("/delete")
	public String deleteclient(int id, Map<String, Object> map) {
		clientService.deleteclientid(id);
		getAllclient(map);
		return "/system/client/getAllclient";
	}

	@RequestMapping("/getpublicclient")
	public String getpublicclient(Map<String, Object> map) {
		map.put("list", clientService.getpublicclient());
		return "/system/client/getpublicclient";
	}

	@RequestMapping("/usersaveclient")
	public String usersaveclient(int id, Map<String, Object> map) {
		clientService.usersaveclient(id);
		map.put("list", clientService.getpublicclient());
		return "/system/client/getpublicclient";
	}

	@RequestMapping("/getclient")
	public String getclient(int id, Map<String, Object> map) {
		map.put("client", clientService.getclientid(id));
		return "/system/client/updateclient";
	}

	@RequestMapping("/updateclient")
	public String updateclient(Client client, Map<String, Object> map) {
		clientService.updateclient(client);
		getAllclient(map);
		return "/system/client/getAllclient";
	}

	@RequestMapping("/addPage")
	public String addClientPage() {
		return "/system/client/addclient";
	}
}
