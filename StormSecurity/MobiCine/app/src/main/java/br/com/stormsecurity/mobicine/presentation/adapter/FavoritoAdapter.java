package br.com.stormsecurity.mobicine.presentation.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.stormsecurity.mobicine.domain.VideoItem;
import br.com.stormsecurity.mobicine.helper.AppHelper;
import br.com.stormsecurity.mobicine.presentation.R;

@SuppressLint("InflateParams")
public class FavoritoAdapter extends ArrayAdapter<VideoItem> {

	public FavoritoAdapter(Context context,
						   List<VideoItem> itens) {
		super(context, R.layout.adapter_favorito, itens);

	}
	

	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = AppHelper.getInstance().getMainActivityUI().getLayoutInflater();
		
		View rowView = inflater.inflate(R.layout.adapter_favorito,null, true);

		TextView tvNome = (TextView) rowView.findViewById(R.id.tvTituloFavorito);

		VideoItem item = getItem(position);
		
		tvNome.setText(item.getTitulo().toString());

		return rowView;
	}
	
}
