package com.springangularcourse.repository;

import org.springframework.data.repository.CrudRepository;

import com.springangularcourse.entity.Cliente;

public interface IClienteRepository extends CrudRepository<Cliente, Long> {

}
