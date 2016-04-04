package br.com.stormsecurity.mobicine.servlet.data;

import br.com.stormsecurity.mobicine.common.task.ErrorListener;
import br.com.stormsecurity.mobicine.common.task.ResponseListener;

public class AbstractData {
	
	ResponseListener responseListener = new ResponseListener() {
		public void onResponseReceived(String data) {
			exibirResultado(data, true);
		}
	};
	ErrorListener errorListener = new ErrorListener() {
		
		public void onResponseReceived(String data) {
			exibirResultado(data, false);
		}
	};
	
	public void exibirResultado(String data, boolean sucesso) {
	}
	
}
