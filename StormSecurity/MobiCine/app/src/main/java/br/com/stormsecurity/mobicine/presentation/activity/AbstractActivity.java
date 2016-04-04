package br.com.stormsecurity.mobicine.presentation.activity;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.Toast;

import java.util.List;

import br.com.stormsecurity.mobicine.domain.OpcaoUsuario;
import br.com.stormsecurity.mobicine.domain.VideoItem;
import br.com.stormsecurity.mobicine.helper.AppHelper;
import br.com.stormsecurity.mobicine.presentation.R;


/**
 * Created by Dennys on 28/03/2016.
 */
public class AbstractActivity extends AppCompatActivity {

    protected ProgressDialog progressDialog = null;
    private static AbstractActivity abstractyActivity;
    private List<VideoItem> listItensCategoria;
    private OpcaoUsuario opcaoUsuario;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        abstractyActivity = this;
//        actionBar = getActionBar();
//        alterarCorActionBar();
    }


	/**
	 * Cor do ActionBar
	 */

    public ColorDrawable getColorDrawable() {

        ColorDrawable colorDrawable = new ColorDrawable(
                Color.parseColor("#ffa500"));
        return colorDrawable;
    }

    public void alterarCorActionBar() {
        actionBar.setBackgroundDrawable(getColorDrawable());
    }

    /**
     * monta popup de Loading
     */
    public void createLoading() {
        progressDialog = ProgressDialog.show(this, "",
                getString(R.string.lbCarregando), true);
    }

    /**
     * fecha popup de Loading
     */
    public void closeLoading() {

        progressDialog.dismiss();
        progressDialog = null;

    }

    /**
     * Mensagem do tipo "Toast" de longa duração.
     *
     */
    public void mensagemToastLong(String msg) {
        int duracao = Toast.LENGTH_LONG;

        Toast t = Toast.makeText(getApplicationContext(), msg,
                duracao);

        t.setGravity(Gravity.CENTER, 0, 0);
        t.show();
    }

    /**
     * Mensagem do tipo "Toast" de curta duração.
     *
     */
    public void mensagemToastShort(String msg) {
        Toast t = Toast.makeText(getApplicationContext(), msg,
                Toast.LENGTH_SHORT);
        t.setGravity(Gravity.BOTTOM, 0, 0);
        t.show();
    }

    public void mensagem(String titulo, String msg) {

        final AlertDialog alertDialog = new AlertDialog.Builder(
                abstractyActivity).create();
        alertDialog.setTitle(titulo);
        alertDialog.setMessage(msg);

        OnClickListener onClickListener = new OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        };

        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
                onClickListener);

        alertDialog.show();
    }

    public List<VideoItem> getListItensCategoria() {
        return listItensCategoria;
    }

    public void setListItensCategoria(List<VideoItem> listItensCategoria) {
        this.listItensCategoria = listItensCategoria;
    }

    public OpcaoUsuario getOpcaoUsuario() {
        return opcaoUsuario;
    }

    public void setOpcaoUsuario(OpcaoUsuario opcaoUsuario) {
        this.opcaoUsuario = opcaoUsuario;
        AppHelper.getInstance().setOpcaoUsuario(opcaoUsuario);
    }
    public void createOpacaoUsuario(VideoItem videoOpcaoItem, int pos){
        setOpcaoUsuario(new OpcaoUsuario(videoOpcaoItem, AppHelper.getInstance().getVideoItemList(), pos));
    }
}
