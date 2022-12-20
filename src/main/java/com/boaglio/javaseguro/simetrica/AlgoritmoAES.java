package com.boaglio.javaseguro.simetrica;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;

import com.boaglio.javaseguro.util.ByteUtil;
import com.boaglio.javaseguro.util.StringUtil;

public class AlgoritmoAES implements CriptografiaSimetrica {

//  https://docs.oracle.com/en/java/javase/17/docs/specs/security/standard-names.html#cipher-algorithm-names
//	private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";
	private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
	
	private static final int KEY_SIZE = 128; //  128, 192 ou 256
	
	private static final int IV_LENGTH_BYTE = 16;
    private byte[] iv = new byte[IV_LENGTH_BYTE]; 
	
	private Key key;
	private Cipher cipher;

	@Override
	public void generateKey() {
		try {
			KeyGenerator generator = KeyGenerator.getInstance(SymmetricEncryptionAlgorithms.AES.algorithm);
			generator.init(KEY_SIZE); 
			key = generator.generateKey();
			System.out.println("  valor da chave: " + ByteUtil.bytesToHexa(key.getEncoded()));
			System.out.println("tamanho da chave: " + key.getEncoded().length * 8 + " bit"); 
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Algoritmo incorreto");
		}
	}

	@Override
	public String encrypt(String mensagem) {

		byte[] encryptedOutput = null;
		try {
			byte[] input = mensagem.getBytes();
			cipher = Cipher.getInstance(TRANSFORMATION);
			cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
			encryptedOutput = cipher.doFinal(input);

		} catch (NoSuchAlgorithmException e) {
			System.out.println("Algoritmo incorreto");
			System.exit(0);
		} catch (Exception e) {
			System.out.println("Outro erro:" + e.getMessage());
			System.exit(0);
		}

		return ByteUtil.bytesToHexa(encryptedOutput);
	}

	@Override
	public String decrypt(String mensagemCriptografada) {

		byte[] decryptedOutput = null;
		try {
			cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
			decryptedOutput = cipher.doFinal(ByteUtil.hexaToBytes(mensagemCriptografada));
		} catch (InvalidKeyException e) {
			System.out.println("Chave Inválida usada");
			System.exit(0);
		} catch (Exception e) {
			System.out.println("Outro erro:" + e.getMessage());
			System.exit(0);
		}

		return new String(decryptedOutput);
	}

	public static void main(String[] args) {

		StringUtil.line();
		System.out.println(" Criptografia Simétrica - AES");
		StringUtil.line();
		System.out.println(" - dois sentidos (do resultado dá para descobrir a entrada)");
		System.out.println(" - determinístico - a mesma entrada sempre dá o mesmo resultado");
		System.out.println(" - pseudorandom - mensagens parecidas geram criprografias diferentes");
		System.out.println(" - tamanho proporcional - uma entrada diferente gera tamanho proporcional");
		StringUtil.line();
		
		var cifrador = new AlgoritmoAES();
		
		cifrador.generateKey();

		String msg = "Se você não consegue ver a diferença, ela realmente importa? - Bernard Lowe (Westworld)";

		String mensagemCriptografada = cifrador.encrypt(msg);
		System.out.println(" mensagem criptografada = " + mensagemCriptografada);
		System.out.println("     qtde de caracteres = " + mensagemCriptografada.length());

		String mensagemOriginal = cifrador.decrypt(mensagemCriptografada);
		System.out.println("      mensagem original = " + mensagemOriginal);
		System.out.println("     qtde de caracteres = " + mensagemOriginal.length());

		if (msg.equals(mensagemOriginal)) {
			System.out.println(" - Teste de algoritmo reversível: Ok");
		}
		StringUtil.line();

		String mensagemCriptografada2 = cifrador.encrypt(msg);
		System.out.println(" mensagem criptografada = " + mensagemCriptografada2);
		System.out.println("     qtde de caracteres = " + mensagemCriptografada2.length());

		if (mensagemCriptografada.equals(mensagemCriptografada2)) {
			System.out.println(" - Teste determinístico: Ok");
		}
		StringUtil.line();

		String msgSimplesRepetida = "Eu".repeat(16);
		String msgSimplesRepetidaCriptografada = cifrador.encrypt(msgSimplesRepetida);
		System.out.println("      mensagem original simples repetida = " + msgSimplesRepetida);
		System.out.println(" mensagem criptografada simples repetida = " + msgSimplesRepetidaCriptografada);
		
		StringUtil.line();

		System.out.println(" - pseudorandom");
		System.out.println(" - tamanho proporcional");

		String msgSimples = "Eu";
		String mensagemCriptografadaSimples = cifrador.encrypt(msgSimples);
		System.out.println("      mensagem original simples 1 = " + msgSimples);
		System.out.println(" mensagem criptografada simples 1 = " + mensagemCriptografadaSimples);
		System.out.println("     qtde de caracteres simples 1 = " + mensagemCriptografadaSimples.length());

		String msgSimples2 = "Eu2";
		String mensagemCriptografadaSimples2 = cifrador.encrypt(msgSimples2);
		System.out.println("      mensagem original simples 2 = " + msgSimples2);
		System.out.println(" mensagem criptografada simples 2 = " + mensagemCriptografadaSimples2);
		System.out.println("     qtde de caracteres simples 2 = " + mensagemCriptografadaSimples2.length());

		StringUtil.line();
	}

}
