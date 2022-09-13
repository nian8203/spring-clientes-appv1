package com.springangularcourse.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springangularcourse.entity.Cliente;
import com.springangularcourse.service.impl.ClienteServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ClienteController {
	
	@Autowired
	public ClienteServiceImpl service;
	
	@GetMapping("/clientes")
	public ResponseEntity<?> listar(){
		return ResponseEntity.ok(service.listar());
	}
	
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id){
		Cliente c = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			c = service.buscarPorId(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error de acceso a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (c == null) {
			response.put("mensaje", "No se ha encontrado ningun registro con el ID: ".concat(id.toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Cliente>(c, HttpStatus.OK);
	}
	
	@PostMapping("/clientes")
	public ResponseEntity<?> crear(@RequestBody Cliente cliente){
		Cliente cn = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			cn = service.crear(cliente);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el registro");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Usuario creado con éxito!");
		response.put("cliente", cn);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> editar(@PathVariable Long id, @RequestBody Cliente cliente){
		Cliente res = service.buscarPorId(id);
		Cliente actualizado = null; 
		Map<String, Object> response = new HashMap<>();
		
		if (res == null) {
			response.put("mensaje", "No se ha encontrado ningun registro con el ID: ".concat(id.toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			res.setNombre(cliente.getNombre().trim().toLowerCase());
			res.setApellido(cliente.getApellido().trim());
			res.setEmail(cliente.getEmail().trim());
			res.setCreateAt(cliente.getCreateAt());
			
			actualizado = service.crear(res);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error en el proceso de actualización de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Los datos han sido actualizados con éxito!");
		response.put("cliente", actualizado);		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id){
		Map<String, Object> response = new HashMap<>();
		
		try {
			service.eliminar(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar los datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
				
		response.put("mensaje", "Regsitro eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}

}
