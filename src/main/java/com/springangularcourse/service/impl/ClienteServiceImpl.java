package com.springangularcourse.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.springangularcourse.entity.Cliente;
import com.springangularcourse.entity.Region;
import com.springangularcourse.repository.IClienteRepository;
import com.springangularcourse.service.IClienteService;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> listar() {
		// TODO Auto-generated method stub
		return (List<Cliente>) repository.findAll();
	}
	
	@Override
	public Page<Cliente> listar(Pageable pageable) {
		// TODO Auto-generated method stub
		return repository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Cliente crear(Cliente cliente) {
		// TODO Auto-generated method stub
		return repository.save(cliente);
	}

	@Override
	@Transactional
	public void eliminar(Long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Region> listarRegiones() {
		// TODO Auto-generated method stub
		return repository.listarRegiones();
	}



	

	

	
}
