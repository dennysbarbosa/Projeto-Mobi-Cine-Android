package br.com.stormsecurity.mobicine.servlet.data;


import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

import br.com.stormsecurity.mobicine.common.task.Cliente;
import br.com.stormsecurity.mobicine.presentation.activity.SplashScreenUI;
import br.com.stormsecurity.mobicine.servlet.operation.CargaInicialOp;


public class CargaInicialData extends AbstractData implements OnClickListener {

	private SplashScreenUI activity;

	
	public CargaInicialData(SplashScreenUI activity) {
		this.activity = activity;
	}

	public void processarCargaInicialData() {
		onClick(null, 0);
		
	}
	
	public void onClick(DialogInterface dialog, int which) {
		
		CargaInicialOp op = new CargaInicialOp(activity);
		Cliente.sendRequest(responseListener, errorListener, op, activity);

	}


}
