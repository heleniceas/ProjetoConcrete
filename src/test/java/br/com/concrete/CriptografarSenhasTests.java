package br.com.concrete;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import br.com.concrete.utils.CriptografarSenhas;

public class CriptografarSenhasTests {
	
	CriptografarSenhas  cripto = new CriptografarSenhas();		
	
	
	 @Test
	    public void testarSenhasIguais(){
		 	String senha1Cripto = cripto.criptografarSenhas("teste");
			String senha2Cripto = cripto.criptografarSenhas("teste");
	        assertEquals(senha1Cripto, senha2Cripto);
	    }
	 
	 
	 
	 @Test
	     public void testSenhasDiferentes() {
		 String senha1Cripto = cripto.criptografarSenhas("HELENICE");
		 String senha2Cripto = cripto.criptografarSenhas("helenice");
	         assertFalse("Senhas Diferentes", (senha1Cripto.equals(senha2Cripto)));
	     }

	 
	 

	
}
