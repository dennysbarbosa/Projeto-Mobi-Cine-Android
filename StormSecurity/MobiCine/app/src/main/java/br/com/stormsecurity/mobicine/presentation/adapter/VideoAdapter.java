package br.com.stormsecurity.mobicine.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.stormsecurity.mobicine.domain.InfUsuario;
import br.com.stormsecurity.mobicine.presentation.R;

/**
 * Created by Dennys on 29/03/2016.
 */
public class VideoAdapter extends ArrayAdapter<InfUsuario> {

    private Context context;
    private List<InfUsuario> listInfUsuarios;
    private TextView txtTitulo;
    private Button btnFacebook;
    private Button btnTwitter;

    public VideoAdapter(Context context, List<InfUsuario> listVideos) {
        super(context, R.layout.adapter_videos, listVideos);
        this.context = context;
        this.listInfUsuarios = listVideos;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.adapter_videos, null);

        txtTitulo = (TextView) v.findViewById(R.id.txtTitulo);
        btnFacebook = (Button) v.findViewById(R.id.btnFacebook);
        btnTwitter = (Button) v.findViewById(R.id.btnTwittet);

        final InfUsuario infUsuario = listInfUsuarios.get(position);

        txtTitulo.setText(infUsuario.getTitulo());


        return v;
    }

    @Override
    public int getCount() {
        return listInfUsuarios.size();
    }

    @Override
    public InfUsuario getItem(int position) {
        return listInfUsuarios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}
