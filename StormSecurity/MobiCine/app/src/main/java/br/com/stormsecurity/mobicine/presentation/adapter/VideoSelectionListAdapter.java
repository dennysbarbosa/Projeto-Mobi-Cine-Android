package br.com.stormsecurity.mobicine.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.stormsecurity.mobicine.domain.VideoItem;
import br.com.stormsecurity.mobicine.presentation.R;

public class VideoSelectionListAdapter extends ArrayAdapter<VideoItem> implements Filterable {

    private List<VideoItem> items;
    private LayoutInflater inflater;
    private List<VideoItem> data;
    private List<VideoItem> origData;
    private Filter videoFilter;


    public VideoSelectionListAdapter(Context context, List<VideoItem> listItens) {
        super(context, R.layout.simple_text_item, listItens);
        items = listItens;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        origData = listItens;
    }


    public int getCount() {
        return items.size();
    }

    public VideoItem getItem(int position) {
        return items.get(position);
    }

    public long getItemId(int position) {
        return items.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;


         this.data = Arrays.asList(items.get(position));

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.simple_text_item, null);

            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.simple_text_text_view);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text.setText(items.get(position).getTitulo());
        return convertView;
    }

    private static class ViewHolder {
        TextView text;
    }

    @Override
    public Filter getFilter() {
        if (videoFilter == null)
            videoFilter = new VideoFilter();

        return videoFilter;
    }

    private class VideoFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            // We implement here the filter logic
            if (constraint == null || constraint.length() == 0) {
                // No filter implemented we return all the list
                results.values = origData;
                results.count = items.size();
            } else {
                // We perform filtering operation
                List<VideoItem> list = new ArrayList<>();

                for (VideoItem i : items) {
                    if (i.getTitulo().toUpperCase()
                            .startsWith(constraint.toString().toUpperCase()))
                        list.add(i);
                }

                results.values = list;
                results.count = list.size();

            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {

            // Now we have to inform the adapter about the new list filtered
            if (results.count == 0)
                notifyDataSetInvalidated();
            else {
                items = (List<VideoItem>) results.values;
                notifyDataSetChanged();
            }

        }

    }

    public void resetData() {
        items = origData;
    }
}