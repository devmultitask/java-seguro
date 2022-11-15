package com.boaglio.javaseguro;
/**
 * Algoritmos disponiveis
 * 
 * https://docs.oracle.com/en/java/javase/17/docs/specs/security/standard-names.html
 * 
 * @author fb
 */

public enum MessageDigestAlgorithms {

	MD2("MD2","RFC 1319"),
	MD5("MD5","RFC 1321"),
	SHA_1("SHA-1", "RFC 3174"),
	SHA_256("SHA-256","RFC 6234"),
	SHA_384("SHA-384","RFC 6234"),
	SHA_512("SHA-512","RFC 6234"),
	SHA3_224("SHA3-224","RFC 8702"),
	SHA3_256("SHA3-256","RFC 8702"),
	SHA3_384("SHA3-384","RFC 8702"),
	SHA3_512("SHA3-512","RFC 8702");
	
	public String algorithm;
	public String rfc;
 
	MessageDigestAlgorithms(String algorithm, String rfc) {
		this.algorithm=algorithm;
		this.rfc=rfc;
	}
	
	public String algorithm(String algorithm) {
		return this.algorithm;
	}
}
