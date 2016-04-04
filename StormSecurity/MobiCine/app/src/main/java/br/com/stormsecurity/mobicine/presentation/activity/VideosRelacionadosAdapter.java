package br.com.stormsecurity.mobicine.presentation.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import br.com.stormsecurity.mobicine.domain.VideoItem;
import br.com.stormsecurity.mobicine.presentation.R;

/**
 * Created by Meta on 01/04/2016.
 */


public class VideosRelacionadosAdapter extends ArrayAdapter<VideoItem> {

    private List<VideoItem> itens;
    private Context context;
    private TextView txtAssistido;
    private LinearLayout linearVideosRelacionados;

    public VideosRelacionadosAdapter(Context context, List<VideoItem> itens) {
        super(context, R.layout.adpter_videos_relacionados,itens);
        this.itens = itens;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        VideoItem item = itens.get(position);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.adpter_videos_relacionados, parent, false);

        txtAssistido = (TextView) convertView.findViewById(R.id.txtAssistido);
        //teste dos videos
        txtAssistido.setVisibility(View.VISIBLE);
        txtAssistido.setText(item.getTitulo().toString());
        linearVideosRelacionados = (LinearLayout) convertView.findViewById(R.id.linearVideosRelacionados);

        return convertView;
    }
}
