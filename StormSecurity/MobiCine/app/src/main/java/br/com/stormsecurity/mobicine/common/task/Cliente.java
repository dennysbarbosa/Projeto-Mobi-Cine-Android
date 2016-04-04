package br.com.stormsecurity.mobicine.common.task;

import android.content.Context;
import android.os.Handler;

import br.com.stormsecurity.mobicine.common.BaseServlet;


public class Cliente {

	public static void sendRequest(ResponseListener callback,
			ErrorListener errorCallback, BaseServlet baseServlet,
			Context context) {
		new AsynchronousSender(new Handler(), new CallbackWrapper(callback),
				new CallbackErrorWrapper(errorCallback), baseServlet, context)
				.start();
	}

}