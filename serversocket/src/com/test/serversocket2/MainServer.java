package com.test.serversocket2;

import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(4000);

		while (true) {
			// һֱ���ڼ���״̬,�������Դ������û�
			Socket socket = serverSocket.accept();

			// ������д�߳�
			new ServerInputThread(socket).start();
			new ServerOutputThread(socket).start();

		}

	}

}
