package br.com.stormsecurity.mobicine.common;

import android.content.Context;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

import br.com.stormsecurity.mobicine.helper.AppHelper;


public class BaseServlet {

	protected String url;
	protected String metodo = "";
	protected ArrayList<NameValuePair> parametros;
	
	public BaseServlet(Context context, String metodo){
		
		this.url = AppHelper.getInstance().getUrl();
		this.metodo = metodo;
		parametros = new ArrayList<NameValuePair>();
		
	}

	public String getMetodo() {
		return metodo;
	}

	public void addParametro(String nome, String valor) {
		this.parametros.add(new BasicNameValuePair(nome, valor));
	}

	public ArrayList<NameValuePair> getParametros() {
		return parametros;
	}
	
	public String getUrl() {
		return url;
	}
	
}
