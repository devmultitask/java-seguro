package com.boaglio.javaseguro.util;

import java.math.BigInteger;
import java.util.HexFormat;

public class ByteUtil {

	public static byte[] hexaToBytes(String hexaStr) {
		
		return new BigInteger(hexaStr, 16).toByteArray();
		
	}

	public static String bytesToHexa(byte[] byteArray) {
		
		StringBuilder digestHexa = new StringBuilder();
		for (byte aByte : byteArray) {
			digestHexa.append(String.format("%02x", aByte));
		}
		
		return digestHexa.toString();
	}
	
	public static String bytesToHexaComHexFormat(byte[] byteArray) {
		
		return HexFormat.of().formatHex(byteArray);
				 
	}
}
