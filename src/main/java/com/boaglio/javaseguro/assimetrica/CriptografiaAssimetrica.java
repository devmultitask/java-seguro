package com.boaglio.javaseguro.assimetrica;

public interface CriptografiaAssimetrica {

	byte[] encrypt(String mensagem);
	
	String decrypt(byte[] mensagemCriptografada); 
	
	void generateKeyPair();
}
