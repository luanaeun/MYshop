package com.myshop.common;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MyAuthentication extends Authenticator{
	
	PasswordAuthentication passAuth;

	public MyAuthentication() {
		String id = "cieloeun@naver.com";
		String pw = "sky7225!";
		passAuth = new PasswordAuthentication(id, pw);
	}
	
	public PasswordAuthentication getPasswordAuthentication() {
        return passAuth;
    }
}
