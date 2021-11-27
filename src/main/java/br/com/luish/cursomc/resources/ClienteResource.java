package br.com.luish.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.luish.cursomc.domain.Cliente;
import br.com.luish.cursomc.services.ClienteService;

//Fornecendo serviços Rest com resposta JSON
@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService clienteService; 
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Cliente obj =  clienteService.find(id);
		
		return ResponseEntity.ok().body(obj);
		
		//handler intercepta exception
	}

}
