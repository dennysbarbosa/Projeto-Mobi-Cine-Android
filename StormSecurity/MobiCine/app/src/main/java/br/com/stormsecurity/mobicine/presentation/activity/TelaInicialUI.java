package br.com.stormsecurity.mobicine.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;

import br.com.stormsecurity.mobicine.helper.AppHelper;
import br.com.stormsecurity.mobicine.presentation.R;
import br.com.stormsecurity.mobicine.presentation.adapter.StartupListAdapter;
import br.com.stormsecurity.mobicine.presentation.adapter.VideoAdapter;

/**
 * Created by Dennys on 29/03/2016.
 */
public class TelaInicialUI extends AbstractActivity implements AdapterView.OnItemClickListener{

    private LinearLayout linearLayoutBuscarVideo;
    private EditText edtBuscarViddeo;
    private ListView ltvVideos;
    private VideoAdapter videoAdapter;
    private List listInfUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_tela_inicial);
        carregarView();
        ltvVideos.setOnItemClickListener(this);
        }

    public void carregarView(){
        linearLayoutBuscarVideo = (LinearLayout) findViewById(R.id.linearBuscarVideo);
        edtBuscarViddeo = (EditText) findViewById(R.id.edtBuscarVideo);
        ltvVideos = (ListView) findViewById(R.id.ltvVideos);
        montaArrayAdapter();
    }

    public void montaArrayAdapter() {

        videoAdapter = new VideoAdapter(getApplicationContext(), AppHelper.getInstance().getCargaInicialVO().getListInfUsuario());
        ltvVideos.setAdapter(videoAdapter);

    }


// teste da chamada dos videos
   public void startHLS(){
      Intent intent = new Intent(this, StartupActivity.class);
      startActivity(intent);
      finish();
   }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_selecao_video, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

//            case R.id.buscarVideo:
//                linearLayoutBuscarVideo.setVisibility(View.VISIBLE);
//                break;

            case R.id.sair:
                break;

            default:
                break;
        }

        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case StartupListAdapter.INDEX_AUDIO_PLAYBACK:
              //  showAudioSelectionActivity();
                break;

            case StartupListAdapter.INDEX_VIDEO_PLAYBACK:
              //  showVideoSelectionActivity();
                break;

            default:
        }
    }
}
