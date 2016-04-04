package br.com.stormsecurity.mobicine.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.stormsecurity.mobicine.domain.InfUsuario;
import br.com.stormsecurity.mobicine.domain.VideoItem;
import br.com.stormsecurity.mobicine.helper.AppHelper;
import br.com.stormsecurity.mobicine.presentation.R;
import br.com.stormsecurity.mobicine.presentation.adapter.EscolhaCategoriaAdapter;

public class EscolhaCategoriaUI extends AbstractActivity {

    private ListView listView;
    private int[] mFlags;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_escolha_categoria);
        listView = (ListView) findViewById(R.id.listView);
        configurarImagensCategorias();
        montarAdpter();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AppHelper.getInstance().setVideoItemList(listItensCategoria((int)id));
                startActivity();
            }
        });


    }

    private List<VideoItem> listItensCategoria(int id){

        List<VideoItem> listItensCategoria = new ArrayList<>();
        for(VideoItem videoItem :  AppHelper.getInstance().getCargaInicialVO().getListVideoItens()){
            if(id == videoItem.getIdCategoria()){
                listItensCategoria.add(videoItem);
            }
        }
        return listItensCategoria;
    }

    private void configurarImagensCategorias(){
        mFlags = new int[]{
                R.drawable.infantil,
                R.drawable.vida_selvagem,
                R.drawable.tecnologia,
                R.drawable.documentario,
                R.drawable.engracados,
        };
    }

    private void startActivity(){

        Intent intent = new Intent(this, VideoSelectionActivity.class);
        startActivity(intent);
    }

    private void montarAdpter(){

        EscolhaCategoriaAdapter adapter = new EscolhaCategoriaAdapter(this, listCategoriaItens());
        listView.setAdapter(adapter);
    }

    private List<InfUsuario> listCategoriaItens(){

        for(int i = 0; i <  AppHelper.getInstance().getCargaInicialVO().getListInfUsuario().size() ; i++){
            AppHelper.getInstance().getCargaInicialVO().getListInfUsuario().get(i).setImagemCategoria(mFlags[i]);
        }
        return  AppHelper.getInstance().getCargaInicialVO().getListInfUsuario();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.getItem(1).setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.sair) {
            finish();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

}
