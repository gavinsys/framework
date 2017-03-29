package com.test.serversocket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TcpClient {
	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("127.0.0.1", 5000);
		 // 客户端的输出流
        OutputStream os = socket.getOutputStream();

        // 将信息写入流,把这个信息传递给服务器
        os.write("hello world".getBytes());


        // 从服务器端接收信息

        InputStream is = socket.getInputStream();

        byte[] buffer = new byte[200];

        int length = is.read(buffer);
        String str = new String(buffer, 0, length);
        System.out.println(str);

        // 关闭资源
        is.close();
        os.close();
        socket.close();
	}
}
