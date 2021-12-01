package br.com.luish.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luish.cursomc.domain.Categoria;
import br.com.luish.cursomc.repositories.CategoriaRepository;
import br.com.luish.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria find(Integer id) {
		
		Optional<Categoria> obj = categoriaRepository.findById(id);
		
		if (obj.isEmpty()) {
			throw new ObjectNotFoundException("Objeto não encontardo! Id: " + id + ", Tipo: " + Categoria.class.getName());
		}
	
		return obj.orElse(null);
		
	}

	public Categoria insert(Categoria obj) {
		
		obj.setId(null);
		return categoriaRepository.save(obj);
		
	}
	
}
