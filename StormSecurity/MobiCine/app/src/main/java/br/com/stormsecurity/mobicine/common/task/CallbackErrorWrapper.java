package br.com.stormsecurity.mobicine.common.task;


public class CallbackErrorWrapper implements Runnable {
 
	private ErrorListener callbackActivity;
	private String data;
 
	public CallbackErrorWrapper(ErrorListener callbackActivity) {
		this.callbackActivity = callbackActivity;
	}
 
	public void run() {
		callbackActivity.onResponseReceived(data);
	}
 
	public void setData(String data) {
		this.data = data;
	}
 
}