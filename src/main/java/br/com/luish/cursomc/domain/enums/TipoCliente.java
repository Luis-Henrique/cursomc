package br.com.luish.cursomc.domain.enums;

public enum TipoCliente {
	
	PESSOAFISICA(1, "Pessoa Fisica"),
	PESSOAJURIDICA (2, "Pessoa Juridica");
	
	private int codigo;
	private String descricao;
	
	private TipoCliente (int cod, String descricao) {
		this.codigo 	= cod;
		this.descricao 	= descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	
	public static TipoCliente toEnum (Integer codigo) {
		
		if(codigo == null) {
			return null;
		}
		
		for (TipoCliente tipo : TipoCliente.values()) {
			
			if(codigo.equals(tipo.getCodigo())) {
				return tipo;
			}
			
		}
		
		throw new IllegalArgumentException("Id inválido" + codigo);
		
	}

}
