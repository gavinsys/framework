package com.test.serversocket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
	public static void main(String[] args) throws Exception {
		ServerSocket ss = new ServerSocket(5000);
		Socket socket = ss.accept();
		System.out.println("Connected Successfully!");
		 // ��÷������˵����������ӿͻ��˽�����Ϣ
        InputStream is = socket.getInputStream();
        // �������˵����������ͻ��˷�����Ϣ
        OutputStream os = socket.getOutputStream();

        byte[] buffer = new byte[200];

        int length = 0;
        length = is.read(buffer);
        String str = new String(buffer, 0, length);
        System.out.println(str);

        // �������˵����
        os.write("Welcome".getBytes());

        // �ر���Դ
        is.close();
        os.close();
        socket.close();
	}
}
