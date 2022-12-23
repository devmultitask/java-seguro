package com.boaglio.javaseguro.assimetrica;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

import com.boaglio.javaseguro.util.ByteUtil;
import com.boaglio.javaseguro.util.StringUtil;

public class AlgoritmoRSA implements CriptografiaAssimetrica {

	private static final String TRANSFORMATION = "RSA";

	private KeyPair keyPair;
	private Cipher cipher;

	@Override
	public byte[] encrypt(String mensagem) {

		byte[] encryptedOutput = null;
		try {
			cipher = Cipher.getInstance(TRANSFORMATION);
			cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPrivate());
			encryptedOutput = cipher.doFinal(mensagem.getBytes());
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Algoritmo incorreto");
			System.exit(0);
		} catch (Exception e) {
			System.out.println("Outro erro:" + e.getMessage());
			System.exit(0);
		}
		return encryptedOutput;
	}

	@Override
	public String decrypt(byte[] mensagemCriptografada) {

		byte[] plainText = null;
		try {
			cipher.init(Cipher.DECRYPT_MODE, keyPair.getPublic());
			plainText = cipher.doFinal(mensagemCriptografada);
		} catch (InvalidKeyException e) {
			System.out.println("Chave Inválida");
			System.exit(0);
		} catch (IllegalBlockSizeException e) {
			System.out.println("Tamanho de bloco inválido");
			System.exit(0);
		} catch (BadPaddingException e) {
			System.out.println("Erro de preenchimento");
			System.exit(0);
		}

		return new String(plainText);
	}

	@Override
	public void generateKeyPair() {
		KeyPairGenerator kpGen;
		try {
			kpGen = KeyPairGenerator.getInstance(TRANSFORMATION);
			kpGen.initialize(1024);
			keyPair = kpGen.generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Algoritmo incorreto");
			System.exit(0);
		} catch (Exception e) {
			System.out.println("Outro erro:" + e.getMessage());
			System.exit(0);
		}
	}

	public static void main(String[] args) throws Exception {

		var cifrador = new AlgoritmoRSA();

		cifrador.generateKeyPair();

		StringUtil.line();
		System.out.println(" Criptografia Assimétrica - RSA");
		StringUtil.line();
		System.out.println(" - dois sentidos (do resultado dá para descobrir a entrada)");
		System.out.println(" - determinístico - a mesma entrada sempre dá o mesmo resultado");
		System.out.println(" - pseudorandom - mensagens parecidas geram criprografias diferentes");
		System.out.println(" - tamanho fixo - uma entrada diferente gera tamanho fixo");
		StringUtil.line();

		String pvtKey = ByteUtil.bytesToHexa(cifrador.keyPair.getPrivate().getEncoded());
		System.out.println(" private key [" +pvtKey.length()+ "bytes]= "+ pvtKey);
		
		String pubKey = ByteUtil.bytesToHexa(cifrador.keyPair.getPublic().getEncoded());
		System.out.println(" public key [" +pubKey.length()+ " bytes] = " +pubKey );

		String msg = "Se você não consegue ver a diferença, ela realmente importa? - Bernard Lowe (Westworld)";

		byte[] mensagemCriptografada = cifrador.encrypt(msg);
		showByteString("mensagem criptografada",mensagemCriptografada);
		
		String mensagemOriginal = cifrador.decrypt(mensagemCriptografada);
		showString("mensagem original",mensagemOriginal);
		
		if (msg.equals(mensagemOriginal)) {
			System.out.println(" - Teste de algoritmo reversível: Ok");
		}
		StringUtil.line();

		byte[] mensagemCriptografada2 = cifrador.encrypt(msg);
		showByteString("mensagem criptografada2",mensagemCriptografada2);

		if (mensagemCriptografada.equals(mensagemCriptografada2)) {
			System.out.println(" - Teste determinístico: Ok");
		}
		StringUtil.line();

		String msgSimplesRepetida = "Eu".repeat(16);
		byte[] msgSimplesRepetidaCriptografada = cifrador.encrypt(msgSimplesRepetida);
		System.out.println("      mensagem original simples repetida = " + msgSimplesRepetida);
		System.out.println(" mensagem criptografada simples repetida = " + ByteUtil.bytesToHexa(msgSimplesRepetidaCriptografada));

		StringUtil.line();

		System.out.println(" - pseudorandom");
		System.out.println(" - tamanho fixo");

		String msgSimples = "Eu";
		byte[] mensagemCriptografadaSimples = cifrador.encrypt(msgSimples);
		showString("mensagem original simples 1",msgSimples);
		showByteString("mensagem criptografada simples 1",mensagemCriptografadaSimples);

		String msgSimples2 = "Eu2";
		byte[] mensagemCriptografadaSimples2 = cifrador.encrypt(msgSimples2);
		showString("mensagem original simples 2",msgSimples2);
		showByteString("mensagem criptografada simples 2",mensagemCriptografadaSimples2);

		StringUtil.line();

	}
	
	private static void showByteString(String titulo,byte[] msg) {
		System.out.println(" "+titulo+ " ["+msg.length+" bytes] = " + ByteUtil.bytesToHexa(msg));
	}

	private static void showString(String titulo,String msg) {
		System.out.println(" "+titulo+ " ["+msg.length()+" bytes] = " + msg);
	}
}
