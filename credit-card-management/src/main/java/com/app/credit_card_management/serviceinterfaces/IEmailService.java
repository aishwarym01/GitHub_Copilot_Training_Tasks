package com.app.credit_card_management.serviceinterfaces;

public interface IEmailService {
	
	public void sendEmail(String to, String subject, String body);
}
