package br.com.concrete.utils;

public enum EnumMessage {
	EMAIL_EXISTENTE("E-mail já existente"),
    CAMPOS_NULOS("Campos Obrigatórios"),
    USUARIO_OU_SENHA_INVALIDOS("Usuário e/ou senha inválidos"),
    NAO_AUTORIZADO("Não autorizado"),
    VALID_SESSION("Sessão inválida");
  
	 private String mensagem;
	 

	 public String getMensagemString() {
		 return mensagem;
	 }
	 
	 private EnumMessage(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return "{\"mensagem\":\"" + mensagem + "\"}";
	}
	 

}
	    
	    
	  
