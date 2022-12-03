package com.boaglio.javaseguro.simetrica;

public interface CriptografiaSimetrica {

	String encrypt(String mensagem);
	
	String decrypt(String mensagemCriptografada); 
	
	void generateKey();
}
