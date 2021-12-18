package br.com.luish.cursomc.services;

import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.luish.cursomc.domain.ItemPedido;
import br.com.luish.cursomc.domain.PagamentoComBoleto;
import br.com.luish.cursomc.domain.Pedido;
import br.com.luish.cursomc.domain.enums.EstadoPagamento;
import br.com.luish.cursomc.repositories.ItemPedidoRepository;
import br.com.luish.cursomc.repositories.PagamentoRepository;
import br.com.luish.cursomc.repositories.PedidoRepository;
import br.com.luish.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	public Pedido find(Integer id) {
		
		Optional<Pedido> obj = pedidoRepository.findById(id);
		
		if (obj.isEmpty()) {
			throw new ObjectNotFoundException("Objeto não encontardo! Id: " + id + ", Tipo: " + Pedido.class.getName());
		}
	
		return obj.orElse(null);
		
	}

	@Transactional
	public Pedido insert(Pedido obj) {
		
		obj.setId(null);
		obj.setInstante(Calendar.getInstance().getTime());
		obj.getPagamento().setEstado(EstadoPagamento.PEDENTE);
		obj.getPagamento().setPedido(obj);
		
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preenherPagamentoComBoleto(pagto, obj.getInstante()); 
		}
		
		obj = pedidoRepository.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		
		for(ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.00);
			ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		
		itemPedidoRepository.saveAll(obj.getItens());
		
		return obj;
		
	} 

}
