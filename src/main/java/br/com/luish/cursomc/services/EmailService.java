package br.com.luish.cursomc.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import br.com.luish.cursomc.domain.Pedido;

@Service
public interface EmailService {
	
	public void sendOrderConfirmationEmail(Pedido pedido);
	
	public void sendEmail(SimpleMailMessage msg);

}
