package com.boaglio.javaseguro.simetrica;
/**
 * Algoritmos disponiveis
 * 
 * https://docs.oracle.com/en/java/javase/17/docs/specs/security/standard-names.html#keygenerator-algorithms
 * 
 * @author Fernando Boaglio
 */

public enum SymmetricEncryptionAlgorithms {

	AES("AES","RFC 3826"),
	ARCFOUR("ARCFOUR","RFC 4345"),
	Blowfish("Blowfish", "RFC 2451"),
	ChaCha20("ChaCha20","RFC 7634"),
	DES	("DES","RFC 4772"),
	DESede("DESede","RFC 2420"),
	HmacMD5	("HmacMD5","RFC 2085"),
	HmacSHA1("HmacSHA1","RFC 2104"),
	HmacSHA224("HmacSHA224","RFC 2104"),
	HmacSHA256("HmacSHA256","RFC 2104"),
	HmacSHA384("HmacSHA384","RFC 2104"),
	HmacSHA512("HmacSHA512","RFC 2104"),
	HmacSHA3_224("HmacSHA3-224","RFC 4231"),
	HmacSHA3_256("HmacSHA3-256","RFC 4231"),
	HmacSHA3_384("HmacSHA3-384","RFC 4231"),
	HmacSHA3_512("HmacSHA3-512","RFC 4231"),
	RC2	("RC2","RFC 2268");
	
	public String algorithm;
	public String rfc;
 
	SymmetricEncryptionAlgorithms(String algorithm, String rfc) {
		this.algorithm=algorithm;
		this.rfc=rfc;
	}
	
	public String algorithm(String algorithm) {
		return this.algorithm;
	}
}
