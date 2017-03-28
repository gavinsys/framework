package com.easytonent.tools;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.apache.log4j.Logger;

/**
 * HTTP请求相关
 * 
 * @author 董华健
 */
public class ToolHttp {
	private static Logger logger = Logger.getLogger(ToolHttp.class.getName());
	/**
	 * 
	 * @param strURL
	 * @param req
	 * @return
	 */
	public static String doPostQueryCmd(String strURL, String req, String key, String value, String type) {
		String result = null;
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			URL url = new URL(strURL);
			URLConnection con = url.openConnection();
			if (con instanceof HttpsURLConnection) {
				((HttpsURLConnection) con).setHostnameVerifier(new HostnameVerifier() {
					@Override
					public boolean verify(String hostname, SSLSession session) {
						return true;
					}
				});
			}
			con.addRequestProperty("Content-Type", type);
			if(key != null){
				con.addRequestProperty(key, value);
			}
			con.setUseCaches(false);
			con.setDoInput(true);
			con.setDoOutput(true);
			out = new BufferedOutputStream(con.getOutputStream());
			byte outBuf[] = req.getBytes("UTF-8");
			out.write(outBuf);
			out.close();
			in = new BufferedInputStream(con.getInputStream());
			result = ReadByteStream(in);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			return "";
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
		if (result == null)
			return "";
		else
			return result;
	}

	/**
	 * 
	 * @param in
	 * @return String
	 * @throws IOException
	 */
	private static String ReadByteStream(BufferedInputStream in) throws IOException {
		LinkedList<Mybuf> bufList = new LinkedList<Mybuf>();
		int size = 0;
		byte buf[];
		do {
			buf = new byte[128];
			int num = in.read(buf);
			if (num == -1)
				break;
			size += num;
			bufList.add(new Mybuf(buf, num));
		} while (true);
		buf = new byte[size];
		int pos = 0;
		for (ListIterator<Mybuf> p = bufList.listIterator(); p.hasNext();) {
			Mybuf b = p.next();
			for (int i = 0; i < b.size;) {
				buf[pos] = b.buf[i];
				i++;
				pos++;
			}

		}
		return new String(buf, "UTF-8");
	}
}

/**
 *
 * @author carson.liang
 *
 */
class Mybuf {
	public byte buf[];
	public int size;

	public Mybuf(byte b[], int s) {
		buf = b;
		size = s;
	}
}
