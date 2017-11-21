package br.com.concrete.utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;


public final class Token {
	
	final static String  SECRET_KEY = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";
	final  static int MINUTOS = 30;

    public static String gerarToken() {
    	
		String jws = Jwts.builder().setIssuer("Helenice").setSubject("Desafio-Java-Concrete")
				.setIssuedAt(Date.from(Instant.now()))
				.setExpiration(Date.from(Instant.now().plus(MINUTOS, ChronoUnit.MINUTES)))
				.signWith(SignatureAlgorithm.HS256, TextCodec.BASE64.decode(SECRET_KEY)).compact();
		return jws;

	}
   
    
	public static boolean validarToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
				.parseClaimsJws(token).getBody();

//		System.out.println("ID: " + claims.getId() + "Subject: " + claims.getSubject() + "Issuer: " + claims.getIssuer()
//				+ "Date: " + Instant.now() + "Expiration: " + claims.getExpiration());

		Instant timestamp = claims.getExpiration().toInstant();

		return Instant.now().isBefore(timestamp);

	}
    
 

}
