package br.com.stormsecurity.mobicine.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import br.com.stormsecurity.mobicine.domain.VideoItem;
import br.com.stormsecurity.mobicine.helper.AppHelper;
import br.com.stormsecurity.mobicine.presentation.R;
import br.com.stormsecurity.mobicine.presentation.adapter.VideoSelectionListAdapter;


/**
 * A simple activity that allows the user to select a
 * video to play
 */
public class VideoSelectionActivity extends AbstractActivity implements AdapterView.OnItemClickListener , SearchView.OnQueryTextListener{

    private List<VideoItem> videoItemList;
    private ListView exampleList;
    private VideoItem videoOpcaoItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_selection_activity);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getResources().getString(R.string.title_video_selection_activity));
        }

        exampleList = (ListView) findViewById(R.id.selection_activity_list);
        videoItemList = AppHelper.getInstance().getVideoItemList();
        exampleList.setAdapter(new VideoSelectionListAdapter(this, videoItemList));
        exampleList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        videoOpcaoItem =  videoItemList.get(position);
        createOpacaoUsuario(videoOpcaoItem,position);
        startMainActivityUI();
    }

    private void startMainActivityUI(){
        Intent intent = new Intent(this, MainActivityUI.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_selecao_video, menu);

        SearchView mSearchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        //Define um texto de ajuda:
        mSearchView.setQueryHint("Digite o filme desejado");

        // exemplos de utilização:
        mSearchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }


}