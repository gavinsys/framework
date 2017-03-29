package com.test.serversocket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TcpClient {
	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("127.0.0.1", 5000);
		 // �ͻ��˵������
        OutputStream os = socket.getOutputStream();

        // ����Ϣд����,�������Ϣ���ݸ�������
        os.write("hello world".getBytes());


        // �ӷ������˽�����Ϣ

        InputStream is = socket.getInputStream();

        byte[] buffer = new byte[200];

        int length = is.read(buffer);
        String str = new String(buffer, 0, length);
        System.out.println(str);

        // �ر���Դ
        is.close();
        os.close();
        socket.close();
	}
}
