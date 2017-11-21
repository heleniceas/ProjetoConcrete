package br.com.concrete.utils;

public enum EnumMessage {
	EMAIL_EXISTENTE("E-mail j� existente"),
    CAMPOS_NULOS("Campos Obrigat�rios"),
    USUARIO_OU_SENHA_INVALIDOS("Usu�rio e/ou senha inv�lidos"),
    NAO_AUTORIZADO("N�o autorizado"),
    VALID_SESSION("Sess�o inv�lida");
  
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
	    
	    
	  
