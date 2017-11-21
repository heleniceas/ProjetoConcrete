package br.com.concrete.utils;

import java.security.MessageDigest;

public class CriptografarSenhas {

	@SuppressWarnings("finally")
	public String criptografarSenhas(String senhaOriginal) {
		MessageDigest algorithm;
		String senha = senhaOriginal;
		try {
			algorithm = MessageDigest.getInstance("SHA-256");

			byte messageDigest[] = algorithm.digest(senhaOriginal.getBytes("UTF-8"));

			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
				hexString.append(String.format("%02X", 0xFF & b));
			}
			senha = hexString.toString();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return senha;

		}
	}

}
