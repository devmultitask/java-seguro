package com.boaglio.javaseguro;

import java.security.NoSuchAlgorithmException;

import com.boaglio.javaseguro.algoritmos.MD2;
import com.boaglio.javaseguro.util.ByteUtil;
import com.boaglio.javaseguro.util.StringUtil;

/**
 * message digest (hash) demo
 */
public class TesteHashingMD2 {
 
	public static void main(String[] args) throws NoSuchAlgorithmException {

		StringUtil.line();
		System.out.println(" Algoritmos Hash - Message Digest");
		StringUtil.line();
		System.out.println(" - um sentido (do hash não dá para descobrir a entrada)");
		System.out.println(" - determinístico - a mesma entrada sempre dá o mesmo resultado");
		System.out.println(" - pseudorandom - um hash/digest novo é bem diferente de um antigo");
		System.out.println(" - tamanho fixo - uma entrada diferente gera um digest com o mesmo tamanho");

		String hash1 = ByteUtil.bytesToHexa(MD2.hashText("Eu tenho uma espada - disse Merry, saltando da cadeira."));
		System.out.println(hash1);
		String hash2 = ByteUtil.bytesToHexa(MD2.hashText("Eu tenho uma espada - disse Merry, saltando da cadeira."));
		System.out.println(hash2);
		String hash3 = ByteUtil.bytesToHexa(MD2.hashText("Eu tenho uma espada - disse Merry, saltando da cadeira."));
		System.out.println(hash3);
		String hash4 = ByteUtil.bytesToHexa(MD2.hashText("Eu tenho uma espada - disse Merry, saltando dA cadeira."));
		System.out.println(hash4);
		String hash5 = ByteUtil.bytesToHexa(MD2.hashText("Eu"));
		System.out.println(hash5);
		String hash6 = ByteUtil.bytesToHexa(MD2.hashText("Eu tenho uma espada - disse Merry, saltando da cadeira.Eu tenho uma espada - disse Merry, saltando da cadeira.Eu tenho uma espada - disse Merry, saltando da cadeira."));
		System.out.println(hash6);
	}
 
}