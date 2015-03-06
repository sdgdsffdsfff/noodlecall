package org.fl.noodlecall.util.tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class HttpConnect {

	private int connectTimeout;
	private int readTimeout;
	
	private String url;
	private String encoding = "utf-8";

	public HttpConnect(String url, int connectTimeout, int readTimeout) {
		this.url = url;
		this.connectTimeout = connectTimeout;
		this.readTimeout = readTimeout;
	}
	
	public HttpConnect(String url, int connectTimeout, int readTimeout, String encoding) {
		this.url = url;
		this.connectTimeout = connectTimeout;
		this.readTimeout = readTimeout;
		this.encoding = encoding;
	}
	
	public void connect() throws Exception {
		URL httpUrl = new URL(this.url);
		HttpURLConnection httpURLConnection = (HttpURLConnection) httpUrl.openConnection();
		httpURLConnection.setDoOutput(true);
		httpURLConnection.setDoInput(true);
		httpURLConnection.setRequestMethod("POST");
		httpURLConnection.setUseCaches(false);  
		httpURLConnection.setConnectTimeout(connectTimeout);
		httpURLConnection.setReadTimeout(readTimeout);             
		httpURLConnection.setRequestProperty("Accept-Charset", encoding);
		httpURLConnection.setRequestProperty("Connection", "close");
		httpURLConnection.setRequestProperty("Keep-Alive", "close");
		httpURLConnection.connect();
	}
	
	public void close() {
	}
	
	public <T> T send(String name, Object object, Class<T> clazz) throws Exception {
		
		String sendJson = URLEncoder.encode(ObjectJsonTranslator.toString(object), encoding);
		String request = new StringBuilder().append(name).append("=").append(sendJson).toString();
		
		String recvJson = requestTo(request);
		
		T response = ObjectJsonTranslator.fromString(recvJson, clazz);
		
		return response;
	}
	
	public <T> T sends(String[] names, Object[] objects, Class<T> clazz) throws Exception {
		
		StringBuilder stringBuilder = new StringBuilder();
		
		for (int i=0; i<names.length; i++) {
			stringBuilder.append(names[i]).append("=").append(URLEncoder.encode(ObjectJsonTranslator.toString(objects[i]), encoding));
			if(i != names.length - 1) stringBuilder.append("&");
		}
		
		String request = stringBuilder.toString();
		
		String recvJson = requestTo(request);
		
		T response = ObjectJsonTranslator.fromString(recvJson, clazz);
		
		return response;
	}
	
	public String send(String name, String string) throws Exception {
		return requestTo(new StringBuilder().append(name).append("=").append(URLEncoder.encode(string, encoding)).toString());
	}
	
	public String request(String string) throws Exception {
		return requestTo(URLEncoder.encode(string, encoding));
	}
	
	private String requestTo(String request) throws Exception {
		URL httpUrl = new URL(this.url);
		HttpURLConnection httpURLConnection = (HttpURLConnection) httpUrl.openConnection();
		httpURLConnection.setDoOutput(true);
		httpURLConnection.setDoInput(true);
		httpURLConnection.setRequestMethod("POST");
		httpURLConnection.setUseCaches(false);  
		httpURLConnection.setConnectTimeout(connectTimeout);
		httpURLConnection.setReadTimeout(readTimeout);             
		httpURLConnection.setRequestProperty("Accept-Charset", encoding);
		httpURLConnection.setRequestProperty("Connection", "keepalive");
		httpURLConnection.setRequestProperty("Keep-Alive", "30");
		httpURLConnection.connect();
		
		PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
		printWriter.print(request);
		printWriter.flush();
		printWriter.close();
        
		String line;
		StringBuilder response = new StringBuilder();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
		while ((line = bufferedReader.readLine()) != null) {
			response.append(line);
		}
		
		return response.toString();
	}
}
