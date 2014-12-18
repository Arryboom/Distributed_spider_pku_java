package com.shengsiyuan.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UrlConnection {

	public  void getInput() throws IOException {
		 URL url = new URL("www.zhibo8.cc");
		 BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
		 String line = null;
		 while(null != (line = br.readLine()))
		 {
			 System.out.println(line);
		 }
		 br.close();
	}
	
	// optimize: print error
	public  void getInput() {
		URL url = new URL("www.zhibo8.cc");
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
		String line = null;
		try {
			while(null != (line = br.readLine()))
			{
			 System.out.println(line);
			}
			br.close();
		}catch (Exception e) {
			System.out.println("get input error");
		}
		 
	}
	
	public  void getInputAndOutput(String[] args) throws Exception {
		URL url = new URL("www.zhibo8.cc");
		URLConnection conn = url.openConnection();
		
		InputStream is = conn.getInputStream();
		OutputStream os = conn.getOutputStream();
		
		byte[] buffer = new byte[2048];
		int length = 0;
		while(-1 != (length = is.read(buffer,0,buffer.length)))
		{
			os.write(buffer,0,length);
		}
		is.close();
		os.close();
	}
}

