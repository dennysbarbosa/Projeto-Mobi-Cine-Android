package br.com.stormsecurity.mobicine.common.task;


public class CallbackWrapper implements Runnable {
 
	private ResponseListener callbackActivity;
	private String data;
 
	public CallbackWrapper(ResponseListener callbackActivity) {
		this.callbackActivity = callbackActivity;
	}
 
	public void run() {
		callbackActivity.onResponseReceived(data);
	}
 
	public void setData(String data) {
		this.data = data;
	}
 
}