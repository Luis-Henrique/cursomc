package br.com.luish.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.luish.cursomc.domain.Categoria;
import br.com.luish.cursomc.domain.Cliente;
import br.com.luish.cursomc.dto.CategoriaDTO;
import br.com.luish.cursomc.repositories.CategoriaRepository;
import br.com.luish.cursomc.services.exceptions.DataIntegrityException;
import br.com.luish.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria find(Integer id) {

		Optional<Categoria> obj = categoriaRepository.findById(id);

		if (obj.isEmpty()) {
			throw new ObjectNotFoundException(
					"Objeto não encontardo! Id: " + id + ", Tipo: " + Categoria.class.getName());
		}

		return obj.orElse(null);

	}

	public Categoria insert(Categoria obj) {

		obj.setId(null);
		return categoriaRepository.save(obj);

	}

	public Categoria update(Categoria obj) {

		Categoria newObj = find(obj.getId());

		updateData(newObj, obj);

		return categoriaRepository.save(obj);

	}

	public void delete(Integer id) {

		find(id);

		try {
			categoriaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir categoria que possuí produtos");
		}

	}

	public List<Categoria> findAll() {

		return categoriaRepository.findAll();

	}

	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		return categoriaRepository.findAll(pageRequest);

	}

	public Categoria froDTO(CategoriaDTO obj) {

		return new Categoria(obj.getId(), obj.getNome());

	}

	private void updateData(Categoria newObj, Categoria obj) {

		newObj.setNome(obj.getNome());

	}

}
