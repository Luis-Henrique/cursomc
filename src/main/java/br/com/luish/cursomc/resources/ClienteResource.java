package br.com.luish.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.luish.cursomc.domain.Cliente;
import br.com.luish.cursomc.dto.ClienteDTO;
import br.com.luish.cursomc.services.ClienteService;

//Fornecendo serviços Rest com resposta JSON
@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {

		Cliente obj = clienteService.find(id);

		return ResponseEntity.ok().body(obj);

		// handler intercepta exception
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDTO, @PathVariable Integer id) {

		Cliente obj = clienteService.froDTO(objDTO);

		obj.setId(id);
		obj = clienteService.update(obj);

		return ResponseEntity.noContent().build();

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {

		clienteService.delete(id);

		return ResponseEntity.noContent().build();

		// handler intercepta exception
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll() {

		List<Cliente> list = clienteService.findAll();

		// passa list de clientes para list de dto
		List<ClienteDTO> dtos = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());

		return ResponseEntity.ok().body(dtos);

		// handler intercepta exception
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<Cliente> list = clienteService.findPage(page, linesPerPage, orderBy, direction);

		Page<ClienteDTO> dtos = list.map(obj -> new ClienteDTO(obj));

		return ResponseEntity.ok().body(dtos);

	}

}
