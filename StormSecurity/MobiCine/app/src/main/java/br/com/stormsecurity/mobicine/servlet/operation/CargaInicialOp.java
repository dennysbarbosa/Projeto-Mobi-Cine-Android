package br.com.stormsecurity.mobicine.servlet.operation;

import android.content.Context;
import br.com.stormsecurity.mobicine.common.BaseServlet;
import br.com.stormsecurity.mobicine.constantes.Server;

public class CargaInicialOp extends BaseServlet {

	public CargaInicialOp(Context context)  {
		super(context, Server.REQUISICAO);
		addParametro("action", "cargapreliminar");
	}

}
