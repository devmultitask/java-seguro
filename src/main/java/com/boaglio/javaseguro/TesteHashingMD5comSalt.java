package com.boaglio.javaseguro;

import java.security.NoSuchAlgorithmException;

import com.boaglio.javaseguro.algoritmos.MD5;
import com.boaglio.javaseguro.algoritmos.MD5comSalt;
import com.boaglio.javaseguro.util.ByteUtil;
import com.boaglio.javaseguro.util.StringUtil;

/**
 * message digest (hash) demo
 */
public class TesteHashingMD5comSalt {
 
	public static void main(String[] args) throws NoSuchAlgorithmException {

		StringUtil.line();
		System.out.println(" Algoritmos Hash - Message Digest");
		StringUtil.line();
		System.out.println(" - um sentido (do hash não dá para descobrir a entrara)");
		System.out.println(" - determinístico - a mesma entrada sempre dá o mesmo resultado");
		System.out.println(" - pseudorandom - um hash/digest novo é bem diferente de um antigo");
		System.out.println(" - tamanho fixo - uma entrada diferente gera um digest com o mesmo tamanho");

		String salt = "QualquerValorDeSaltVale#@$@4&#%^$*";
		
		String hash1 = ByteUtil.bytesToHexa(MD5.hashText("Eu tenho uma espada - disse Merry, saltando da cadeira."));
		System.out.println(hash1);
		String hash2 = ByteUtil.bytesToHexa(MD5comSalt.hashText("Eu tenho uma espada - disse Merry, saltando da cadeira.",salt));
		System.out.println(hash2);
 
	}
 
}