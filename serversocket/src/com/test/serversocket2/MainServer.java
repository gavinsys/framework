package com.test.serversocket2;

import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(4000);

		while (true) {
			// 一直处于监听状态,这样可以处理多个用户
			Socket socket = serverSocket.accept();

			// 启动读写线程
			new ServerInputThread(socket).start();
			new ServerOutputThread(socket).start();

		}

	}

}
