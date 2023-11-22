package com.example.FourFlowerServer;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootTest
class FourFlowerServerApplicationTests {

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/fourflowersdb?useSSL=false&serverTimezone=Asia/Seoul";
	private static final String USER = "mxxxdxxx";
	private static final String PASSWORD = "1234";


	@Test
	public void testConnections() throws Exception {
		Class.forName(DRIVER);
		try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			System.out.println(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
