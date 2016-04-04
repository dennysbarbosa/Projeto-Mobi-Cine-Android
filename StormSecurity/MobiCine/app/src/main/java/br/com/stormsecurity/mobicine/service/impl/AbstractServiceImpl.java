package br.com.stormsecurity.mobicine.service.impl;

import com.google.gson.Gson;

public class AbstractServiceImpl {

	private static Gson gson = null;

	public static Gson getGson() {
		if (gson == null) {
			gson = new Gson();
		}
		return gson;
	}

	public boolean processarRespostaServidor(boolean sucesso, String data) {

		if (!sucesso || data.startsWith("Erro")
				|| data.contains("Error report")
				|| data.contains("Host name may not be null")
				|| data.contains("refused") || data.contains("ROAPError")) {
			return false;
		} else {
			return true;
		}

	}
}