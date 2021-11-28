package br.com.luish.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luish.cursomc.domain.Pedido;
import br.com.luish.cursomc.repositories.PedidoRepository;
import br.com.luish.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido find(Integer id) {
		
		Optional<Pedido> obj = pedidoRepository.findById(id);
		
		if (obj.isEmpty()) {
			throw new ObjectNotFoundException("Objeto não encontardo! Id: " + id + ", Tipo: " + Pedido.class.getName());
		}
	
		return obj.orElse(null);
		
	}

}
