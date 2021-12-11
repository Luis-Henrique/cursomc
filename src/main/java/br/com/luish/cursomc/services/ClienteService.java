package br.com.luish.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.luish.cursomc.domain.Cliente;
import br.com.luish.cursomc.dto.ClienteDTO;
import br.com.luish.cursomc.repositories.ClienteRepository;
import br.com.luish.cursomc.services.exceptions.DataIntegrityException;
import br.com.luish.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente find(Integer id) {
		
		Optional<Cliente> obj = clienteRepository.findById(id);
		
		if (obj.isEmpty()) {
			throw new ObjectNotFoundException("Objeto não encontardo! Id: " + id + ", Tipo: " + Cliente.class.getName());
		}
	
		return obj.orElse(null);
		
	}
	
public Cliente insert(Cliente obj) {
		
		obj.setId(null);
		return clienteRepository.save(obj);
		
	}

	public Cliente update(Cliente obj) {
		
		Cliente newObj = find(obj.getId());
		
		updateData(newObj, obj);
	
		return clienteRepository.save(obj);
		
	}

	public void delete(Integer id) {
		
		find(id);
		
		try {
			clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir cliente que possuí produtos");
		}
		
		
	}

	public List<Cliente> findAll() {
		
		return clienteRepository.findAll();
		
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return clienteRepository.findAll(pageRequest);
		
	}
	
	public Cliente froDTO(ClienteDTO obj) {
		
		return new Cliente(obj.getId(), obj.getNome(), obj.getEmail(), null, null);
		
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		
	}
	
}
