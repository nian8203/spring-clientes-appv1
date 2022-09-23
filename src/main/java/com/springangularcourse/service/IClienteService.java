package com.springangularcourse.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.springangularcourse.entity.Cliente;
import com.springangularcourse.entity.Region;

public interface IClienteService {

	public List<Cliente> listar();
	public Page<Cliente> listar(Pageable pageable);
	public Cliente buscarPorId(Long id);
	public Cliente crear(Cliente cliente);
	public void eliminar(Long id);
	public List<Region> listarRegiones();
}
