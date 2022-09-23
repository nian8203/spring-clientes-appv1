package com.springangularcourse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springangularcourse.entity.Cliente;
import com.springangularcourse.entity.Region;

public interface IClienteRepository extends JpaRepository<Cliente, Long> {

	@Query("from Region")
	public List<Region> listarRegiones();
}
