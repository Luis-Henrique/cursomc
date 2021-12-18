package br.com.luish.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.luish.cursomc.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	public void preenherPagamentoComBoleto(PagamentoComBoleto pagto, Date instante) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(instante);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataPagamento(calendar.getTime());
		
	}

}
