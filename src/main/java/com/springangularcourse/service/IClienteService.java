package com.springangularcourse.service;

import java.util.List;
import com.springangularcourse.entity.Cliente;

public interface IClienteService {

	public List<Cliente> listar();
	public Cliente buscarPorId(Long id);
	public Cliente crear(Cliente cliente);
	public void eliminar(Long id);
}
