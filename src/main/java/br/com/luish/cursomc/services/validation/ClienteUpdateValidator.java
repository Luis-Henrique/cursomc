package br.com.luish.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.luish.cursomc.domain.Cliente;
import br.com.luish.cursomc.dto.ClienteDTO;
import br.com.luish.cursomc.repositories.ClienteRepository;
import br.com.luish.cursomc.resources.exceptions.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

	@Autowired
	ClienteRepository clienteRepository;

	// pegar id da uri
	@Autowired
	HttpServletRequest request;

	@Override
	public void initialize(ClienteUpdate ann) {

	}

	@Override
	public boolean isValid(ClienteDTO objDTO, ConstraintValidatorContext context) {
		
		//pegar os atributos na uri
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		Integer uriID = Integer.parseInt(map.get("id"));

		List<FieldMessage> list = new ArrayList<>();

		Cliente cli = clienteRepository.findByEmail(objDTO.getEmail());
		
		if(cli != null && !cli.getId().equals(uriID)) {
			list.add(new FieldMessage("email", "email já cadastrado"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getName()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}

		return list.isEmpty();

	}

}
