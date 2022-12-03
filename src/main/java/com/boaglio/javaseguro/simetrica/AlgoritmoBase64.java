package com.boaglio.javaseguro.simetrica;

import java.util.Base64;

import com.boaglio.javaseguro.util.StringUtil;

public class AlgoritmoBase64 implements CriptografiaSimetrica {

	@Override
	public String encrypt(String mensagem) {
		return Base64.getEncoder().encodeToString(mensagem.getBytes());
	}

	@Override
	public String decrypt(String mensagemCriptografada) {
		return new String (Base64.getDecoder().decode(mensagemCriptografada));
	}

	@Override
	public void generateKey() {
		// não tem chave		
	}

	public static void main(String[] args) {
		
		var cifrador = new AlgoritmoBase64();
		
		StringUtil.line();
		System.out.println(" Criptografia (quase) Simétrica - Base 64");
		StringUtil.line();
		System.out.println(" - dois sentidos (do resultado dá para descobrir a entrada)");
		System.out.println(" - determinístico - a mesma entrada sempre dá o mesmo resultado");
		System.out.println(" - não é pseudorandom - mensagens parecidas geram criprografias parecidas");
		System.out.println(" - tamanho proporcional - uma entrada diferente gera tamanho proporcional");
		StringUtil.line();
		
		String msg = "Se você não consegue ver a diferença, ela realmente importa? - Bernard Lowe (Westworld)";
		
		String mensagemCriptografada = cifrador.encrypt(msg);
		System.out.println(" mensagem criptografada = "+mensagemCriptografada);
		System.out.println("     qtde de caracteres = "+mensagemCriptografada.length());
		
		String mensagemOriginal = cifrador.decrypt(mensagemCriptografada);
		System.out.println("      mensagem original = "+mensagemOriginal);
		System.out.println("     qtde de caracteres = "+mensagemOriginal.length());
		
		if (msg.equals(mensagemOriginal)) {
			System.out.println(" - Teste de algoritmo reversível: Ok");
		}
		StringUtil.line();
		
		String mensagemCriptografada2 = cifrador.encrypt(msg);
		System.out.println(" mensagem criptografada = "+mensagemCriptografada2);
		System.out.println("     qtde de caracteres = "+mensagemCriptografada2.length());
		
		if (mensagemCriptografada.equals(mensagemCriptografada2)) {
			System.out.println(" - Teste determinístico: Ok");
		}
		StringUtil.line();
		
		System.out.println(" - não é pseudorandom");
		System.out.println(" - tamanho proporcional");
		
		String msgSimples = "Eu";
		String mensagemCriptografadaSimples = cifrador.encrypt(msgSimples);
		System.out.println("      mensagem original simples 1 = "+msgSimples);
		System.out.println(" mensagem criptografada simples 1 = "+mensagemCriptografadaSimples);
		System.out.println("     qtde de caracteres simples 1 = "+mensagemCriptografadaSimples.length());

		
		String msgSimples2 = "Eu2";
		String mensagemCriptografadaSimples2 = cifrador.encrypt(msgSimples2);
		System.out.println("      mensagem original simples 2 = "+msgSimples2);
		System.out.println(" mensagem criptografada simples 2 = "+mensagemCriptografadaSimples2);
		System.out.println("     qtde de caracteres simples 2 = "+mensagemCriptografadaSimples2.length());

		StringUtil.line();
	}

}
