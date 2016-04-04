package br.com.stormsecurity.mobicine.presentation.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.inject.Inject;

import br.com.stormsecurity.mobicine.presentation.R;
import br.com.stormsecurity.mobicine.service.CargaInicialService;
import br.com.stormsecurity.mobicine.service.impl.CargaInicialServiceImpl;


/**
 * Created by Dennys on 28/03/2016.
 */
public class SplashScreenUI extends Activity implements Runnable{

    @Inject
    private CargaInicialService cargaInicialService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_splash);
        cargaInicialService = new CargaInicialServiceImpl();
        tempoDeVisualizacao();
    }

    private void tempoDeVisualizacao() {
        Handler h = new Handler();
        h.postDelayed((Runnable) this, 1500);
    }

    public void carregarTelaLogin() {
        Intent intent = new Intent(this, EscolhaCategoriaUI.class);
        startActivity(intent);
        finish();
    }

    public void run() {

        try {
            cargaInicialService.relizarCargaInicial(this);
            carregarTelaLogin();
        } catch (Exception e) {
            e.printStackTrace();
            mensagem(getString(R.string.falhaCargaInicial), e.getMessage());
        }

    }

    public void mensagem(String titulo, String msg) {

        final AlertDialog alertDialog = new AlertDialog.Builder(
                this).create();
        alertDialog.setTitle(titulo);
        alertDialog.setMessage(msg);

        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        };

        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
                onClickListener);

        alertDialog.show();
    }
}
