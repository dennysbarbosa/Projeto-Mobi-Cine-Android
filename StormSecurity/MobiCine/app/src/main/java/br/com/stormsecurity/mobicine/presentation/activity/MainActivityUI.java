package br.com.stormsecurity.mobicine.presentation.activity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import br.com.stormsecurity.mobicine.domain.OpcaoUsuario;
import br.com.stormsecurity.mobicine.helper.AppHelper;
import br.com.stormsecurity.mobicine.presentation.R;
import br.com.stormsecurity.mobicine.presentation.adapter.FavoritoAdapter;
import br.com.stormsecurity.mobicine.presentation.fragment.Fragment1;
import br.com.stormsecurity.mobicine.presentation.fragment.Fragment2;


public class MainActivityUI extends AbstractActivity implements Fragment1.OnFragmentInteractionListener, Fragment2.OnFragmentInteractionListener {

    private OpcaoUsuario opcaoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ui);
        AppHelper.getInstance().setMainActivityUI(this);
        AppHelper.getInstance().getFragment2().montarAdpter();
        AppHelper.getInstance().getFragment1().configuracaoFacebook();
        opcaoUsuario = AppHelper.getInstance().getOpcaoUsuario();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        AppHelper.getInstance().getFragment1().onActivityResult(requestCode, resultCode, data);
    }
        @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.getItem(0).setVisible(false);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.favoritos){
            showFavoritos();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showFavoritos() {

        if(!AppHelper.getInstance().getVideoItemListFavoritos().isEmpty()) {
            final Dialog dialog = processarDialog(getString(R.string.videosFavoritos));
            ListView lvFavoritos = (ListView) dialog.findViewById(R.id.lvConteudo);
            FavoritoAdapter favoritoAdapter = new FavoritoAdapter(getApplicationContext(),
                    AppHelper.getInstance().getVideoItemListFavoritos());
            lvFavoritos.setAdapter(favoritoAdapter);
            lvFavoritos.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
                                        long arg3) {
                    executarVideo(pos);
                    dialog.hide();
                }
            });

            dialog.show();
        }else{
            mensagem(getString(R.string.videosFavoritos), getString(R.string.favoritosListaVazia));
        }
    }

    public void executarVideo(int pos){
        createOpacaoUsuario(AppHelper.getInstance().getVideoItemListFavoritos().get(pos), pos);
        startVideoPlayerActivity();
    }

    public void startVideoPlayerActivity() {
        Intent intent = new Intent(AppHelper.getInstance().getMainActivityUI(), FullScreenVideoPlayerActivity.class);
        intent.putExtra(VideoPlayerActivity.EXTRA_INDEX, opcaoUsuario.getIndex());
        startActivity(intent);
    }
    private Dialog processarDialog(String titulo) {

        Dialog dialog = new Dialog(AppHelper.getInstance().getMainActivityUI());
        dialog.requestWindowFeature(Window.FEATURE_CONTEXT_MENU);
        dialog.setTitle(titulo);
        dialog.setContentView(R.layout.dialog_listview);
        return dialog;
    }

    public void showFavoritoAdicionado(){
        mensagemToastShort(getString(R.string.favoritosAdicionado));
    }



}
