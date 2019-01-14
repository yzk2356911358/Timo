package com.linln.admin.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.linln.admin.system.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
