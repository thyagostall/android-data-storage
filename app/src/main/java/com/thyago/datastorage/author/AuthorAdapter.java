package com.thyago.datastorage.author;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.thyago.datastorage.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by thyago on 6/12/16.
 */
public class AuthorAdapter extends BaseAdapter implements Filterable {

    private final LayoutInflater mInflater;
    private final List<AuthorEntity> mData;

    private List<AuthorEntity> mFilteredData;

    public AuthorAdapter(@NonNull LayoutInflater inflater, @NonNull List<AuthorEntity> data) {
        mData = data;
        mFilteredData = new LinkedList<>(data);

        mInflater = inflater;
    }

    @Override
    public int getCount() {
        return mFilteredData.size();
    }

    @Override
    public Object getItem(int position) {
        return mFilteredData.get(position);
    }

    @Override
    public long getItemId(int position) {
        try {
            return mFilteredData.get(position).getId();
        } catch (ArrayIndexOutOfBoundsException e) {
            return position;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_author, parent, false);
            holder = new ViewHolder();
            holder.id = (TextView) convertView.findViewById(R.id.id);
            holder.name = (TextView) convertView.findViewById(R.id.name);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        AuthorEntity entity = mFilteredData.get(position);
        holder.id.setText("" + entity.getId());
        holder.name.setText(entity.getName());

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new AuthorFilter();
    }

    private class ViewHolder {
        public TextView id;
        public TextView name;
    }

    private class AuthorFilter extends Filter {

        private boolean doesMeetCriteria(AuthorEntity item, String constraint) {
            return item.getName().toLowerCase().startsWith(constraint);
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<AuthorEntity> filtered = new LinkedList<>();

            if (constraint == null) {
                filtered.addAll(mData);
            } else {
                String search = constraint.toString().toLowerCase();
                if (search.isEmpty()) {
                    filtered.addAll(mData);
                } else {
                    for (AuthorEntity item : mData) {
                        if (doesMeetCriteria(item, search)) {
                            filtered.add(item);
                        }
                    }
                }
            }

            results.count = filtered.size();
            results.values = filtered;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mFilteredData = (List<AuthorEntity>) results.values;
            notifyDataSetChanged();
        }
    }
}
