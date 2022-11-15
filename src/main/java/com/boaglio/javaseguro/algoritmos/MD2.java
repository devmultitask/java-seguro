package com.boaglio.javaseguro.algoritmos;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.boaglio.javaseguro.MessageDigestAlgorithms;

public class MD2 {

	public static byte[] hashText(String data)  {
		
		byte[] digest = null;
		
		try {
			
			MessageDigest algoritmoHash = MessageDigest.getInstance(MessageDigestAlgorithms.MD2.algorithm);
			
			System.out.println("-----------------------------[ "+algoritmoHash.getAlgorithm()+" ]-----------------------------------------");
			System.out.println("Entrada: " + data);
			
			digest = algoritmoHash.digest(data.getBytes());
			
			System.out.println("Tamanho: " + digest.length * 8 + " bit message"); 
			
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Algoritmo incorreto");
		}
		
		return digest;
	} 
}
