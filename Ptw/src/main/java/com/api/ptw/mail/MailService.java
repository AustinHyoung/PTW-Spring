package com.api.ptw.mail;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailService {
	
	private final JavaMailSender javaMailSender;
	
	private String authNum;
	
	@Value(value = "${spring.mail.username}")
	private String username;
	
	//난수 생성
	public void createCode() {
		Random random = new Random();
		authNum = "";
		
		for(int i=0; i<6; i++) {
			authNum += Integer.toString(random.nextInt(10));
		}
		
	}
	
	//매일 양식
	public MimeMessage createEmailForm(String tomail) throws MessagingException, UnsupportedEncodingException {

        createCode(); //인증 코드 생성
        String setFrom = username; // 자신 메일주소
        String title = "회원가입 인증 번호"; //제목

        MimeMessage message = javaMailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, tomail); //보낼 이메일 설정
        message.setSubject(title); //제목 설정
        message.setFrom(setFrom); //보내는 이메일
        message.setText("인증번호 " + authNum + " 을 입력해주세요.");

        return message;
		
	}
	
	//메일 전송
    public String sendEmail(String toEmail) throws MessagingException, UnsupportedEncodingException {
    	
    	//메일전송에 필요한 정보 설정
    	MimeMessage emailForm = createEmailForm(toEmail);
    	
    	//실제 메일 전송
        javaMailSender.send(emailForm);
        
        return authNum; //인증 코드 반환
    }
}
