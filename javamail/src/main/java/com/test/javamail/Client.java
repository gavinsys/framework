package com.test.javamail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Client {
	public static void main(String[] args) {

		// 创建一个属性对象

		Properties props = new Properties();

		// 指定SMTP服务器
		// 发送邮件服务器：smtp.exmail.qq.com ，使用SSL，端口号465
		props.put("mail.smtp.host", "smtp.qq.com");

		// 指定是否需要ＳＭＴＰ验证

		props.put("mail.smtp.auth", "true");

		props.put("mail.smtp.port", "25");

		props.put("mail.transport.protocol", "smtp");

		// 创建会话

		Session session = Session.getInstance(props);

		session.setDebug(true);

		// 创建一个Message对象

		Message mess = new MimeMessage(session);

		try {

			mess.setFrom(new InternetAddress("***"));// 发件人

			// 收件人

			mess.setRecipient(RecipientType.TO, new InternetAddress("***"));

			mess.setSentDate(new Date());

			mess.setSubject("my java mail");

			mess.setText(new String("您好，Tao".getBytes(), "UTF-8"));// 发送内容

			// 指定邮件的优先级 1：紧急 3:普通 5：缓慢

			mess.setHeader("X-Priority", "1");

			// 创建一个传输对象

			Transport trans = session.getTransport("smtp");

			// 连接SMTP服务器

			trans.connect("smtp.qq.com", "***", "***");// 这里是发件人的邮箱用户名和密码 需要企业邮箱（我的密码就先隐藏啦）

			trans.sendMessage(mess, mess.getAllRecipients());

			trans.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
