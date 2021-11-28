package br.com.luish.cursomc.domain.enums;

public enum EstadoPagamento {
	
	PEDENTE (1, "Pendente"),
	QUITADO (2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int codigo;
	private String descricao;
	
	private EstadoPagamento (int cod, String descricao) {
		this.codigo 	= cod;
		this.descricao 	= descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	
	public static EstadoPagamento toEnum (Integer codigo) {
		
		if(codigo == null) {
			return null;
		}
		
		for (EstadoPagamento tipo : EstadoPagamento.values()) {
			
			if(codigo.equals(tipo.getCodigo())) {
				return tipo;
			}
			
		}
		
		throw new IllegalArgumentException("Id inválido" + codigo);
		
	}

}
