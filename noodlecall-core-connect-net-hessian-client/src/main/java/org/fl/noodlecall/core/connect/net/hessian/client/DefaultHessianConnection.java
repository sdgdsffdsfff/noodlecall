package org.fl.noodlecall.core.connect.net.hessian.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.caucho.hessian.client.HessianConnection;

public class DefaultHessianConnection implements HessianConnection {
	
	private StringBuilder request = new StringBuilder();
	
	private HttpURLConnection httpURLConnection;
	
	public DefaultHessianConnection(URL url, int connectTimeout, int readTimeout, String encoding) throws IOException {
		
		httpURLConnection = (HttpURLConnection) url.openConnection();
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
	}
	
	@Override
	public void addHeader(String key, String value) {
		if (request.length() > 0) request.append("&");
		request.append(key).append("=").append(value);
	}

	@Override
	public void close() throws IOException {
	}

	@Override
	public void destroy() throws IOException {
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return httpURLConnection.getInputStream();
	}

	@Override
	public OutputStream getOutputStream() throws IOException {
		return httpURLConnection.getOutputStream();
	}

	@Override
	public int getStatusCode() {
		try {
			return httpURLConnection.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public String getStatusMessage() {
		try {
			return httpURLConnection.getResponseMessage();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void sendRequest() throws IOException {
		
		PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
		printWriter.print(request);
		printWriter.flush();
		printWriter.close();
	}
}
