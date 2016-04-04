package br.com.stormsecurity.mobicine.presentation.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import br.com.stormsecurity.mobicine.domain.InfUsuario;
import br.com.stormsecurity.mobicine.presentation.R;

/**
 * Created by Meta on 01/04/2016.
 */


public class EscolhaCategoriaAdapter extends ArrayAdapter<InfUsuario> {

    private List<InfUsuario> itens;
    private LayoutInflater mInflater;
    private Context context;
    private LinearLayout linearLayoutCategoria;

    public EscolhaCategoriaAdapter(Context context, List<InfUsuario> itens) {
        super(context, R.layout.adpter_escolha_categoriar,itens);
        this.itens = itens;
        this.context = context;
    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public InfUsuario getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public View getView(int position, View convertView, ViewGroup parent) {

        InfUsuario i = itens.get(position);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.adpter_escolha_categoriar, parent, false);

        linearLayoutCategoria = (LinearLayout) convertView.findViewById(R.id.linearLayoutCategoria);
        TextView texto = (TextView) convertView.findViewById(R.id.text);

        linearLayoutCategoria.setBackgroundDrawable(convertView.getResources().getDrawable(i.getImagemCategoria()));
        texto.setText(i.getTitulo().toString());

        return convertView;
    }

}
