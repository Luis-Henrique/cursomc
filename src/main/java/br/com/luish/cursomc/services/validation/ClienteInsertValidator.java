package br.com.luish.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.luish.cursomc.domain.Cliente;
import br.com.luish.cursomc.domain.enums.TipoCliente;
import br.com.luish.cursomc.dto.ClienteNewDTO;
import br.com.luish.cursomc.repositories.ClienteRepository;
import br.com.luish.cursomc.resources.exceptions.FieldMessage;
import br.com.luish.cursomc.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	ClienteRepository clienteRepository;

	@Override
	public void initialize(ClienteInsert ann) {

	}

	@Override
	public boolean isValid(ClienteNewDTO objDTO, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		if (objDTO.getTipo().equals(TipoCliente.PESSOAFISICA.getCodigo()) && !BR.isValidCPF(objDTO.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}

		if (objDTO.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCodigo()) && !BR.isValidCNPJ(objDTO.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}
		
		Cliente cli = clienteRepository.findByEmail(objDTO.getEmail());
		
		if(cli != null) {
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
