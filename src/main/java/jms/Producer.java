package jms;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Producer {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("vers :");
		String code = scanner.nextLine();
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		try {
			Connection connection = connectionFactory.createConnection();
			connection.start();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			//Destination destination = session.createQueue("queue");
			Destination destination = session.createTopic("topic");
			MessageProducer producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			TextMessage textMessage = session.createTextMessage();
			textMessage.setText("hello world !!!");
			textMessage.setStringProperty("code", code);
			producer.send(textMessage);
			session.close();
			connection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	
	}

}
