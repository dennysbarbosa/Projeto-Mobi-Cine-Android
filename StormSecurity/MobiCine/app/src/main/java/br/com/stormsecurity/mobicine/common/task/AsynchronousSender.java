package br.com.stormsecurity.mobicine.common.task;

import android.content.Context;
import android.os.Handler;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;

import br.com.stormsecurity.mobicine.common.BaseServlet;


public class AsynchronousSender extends Thread {
	private Handler handler;
	private CallbackWrapper wrapper;
	private CallbackErrorWrapper errorWrapper;
	private BaseServlet baseServlet;

	protected AsynchronousSender(Handler handler, CallbackWrapper wrapper,
			CallbackErrorWrapper errorWrapper, BaseServlet baseServlet,
			Context context) {
		this.handler = handler;
		this.wrapper = wrapper;
		this.errorWrapper = errorWrapper;
		this.baseServlet = baseServlet;

	}

	private void initConfig() throws Exception {

	}

	public void run() {
		InputStream in = null;
		try {

			initConfig();
			String SERVLET_URL = "";
			String parametros = "";
			int posJson = -1;
			for (int i = 0; i < baseServlet.getParametros().size(); i++) {
				if (baseServlet.getParametros().get(i).getName().equals("json")) {
					posJson = i;
				} else if(baseServlet.getParametros().get(i).getName().equals("action")){
					String aux = baseServlet.getParametros().get(i).getValue();
					parametros = "/" + URLEncoder.encode(aux, "UTF-8") + parametros;
				} else {
					String aux = baseServlet.getParametros().get(i).getValue();
					parametros += "/" + URLEncoder.encode(aux, "UTF-8");
				}
			}

			SERVLET_URL = baseServlet.getUrl() + parametros;
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse response;
			HttpGet httpGet;
			HttpPost httpPost;
			if (baseServlet.getMetodo().equals("POST")) {
				httpPost = new HttpPost(SERVLET_URL);
				if (posJson >= 0) {
					httpPost.setHeader("Content-type", "application/json");
					StringEntity sEntity = new StringEntity(baseServlet
							.getParametros().get(posJson).getValue(), "UTF-8");
					httpPost.setEntity(sEntity);
				}
				response = httpclient.execute(httpPost);
			} else {
				httpGet = new HttpGet(SERVLET_URL);
				response = httpclient.execute(httpGet);

			}

			HttpEntity entity = response.getEntity();
			in = entity.getContent();
			InputStreamReader isr = new InputStreamReader(in);

			BufferedReader reader = new BufferedReader(isr, 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			String data = sb.toString();
			wrapper.setData(data);
			handler.post(wrapper);

		} catch (Exception e) {
			e.printStackTrace();
			errorWrapper.setData(e.getMessage());
			handler.post(errorWrapper);
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}