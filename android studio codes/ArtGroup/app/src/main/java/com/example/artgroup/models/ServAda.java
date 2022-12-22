package com.example.artgroup.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.example.artgroup.R;

import java.util.ArrayList;

public class ServAda extends ArrayAdapter<ServModel> {
    public ArrayList<ServModel> bidModels;
    public ArrayList<ServModel> bidModelArrayList;
    public ServAda.BidDateFilter bidDateFilter;

    public ServAda(Context context, int id, ArrayList<ServModel> subjectArrayList) {

        super(context, id, subjectArrayList);

        this.bidModelArrayList = new ArrayList<>();

        this.bidModelArrayList.addAll(subjectArrayList);

        this.bidModels = new ArrayList<>();

        this.bidModels.addAll(subjectArrayList);
    }

    @Override
    public Filter getFilter() {

        if (bidDateFilter == null) {

            bidDateFilter = new BidDateFilter();
        }
        return bidDateFilter;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {

            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = vi.inflate(R.layout.onjorithia, null);

            holder = new ViewHolder();
            holder.SubjectQuantity = convertView.findViewById(R.id.appPName);
            holder.subjecType = convertView.findViewById(R.id.appType);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ServModel subject = bidModelArrayList.get(position);
        holder.SubjectQuantity.setText(subject.getCategory());
        holder.subjecType.setText("Type: " + subject.getType());
        return convertView;

    }

    public class ViewHolder {
        TextView SubjectQuantity, subjecType;
    }

    private class BidDateFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            charSequence = charSequence.toString().toLowerCase();

            FilterResults filterResults = new FilterResults();

            if (charSequence != null && charSequence.toString().length() > 0) {
                ArrayList<ServModel> arrayList1 = new ArrayList<>();

                for (int i = 0, l = bidModels.size(); i < l; i++) {
                    ServModel subject = bidModels.get(i);

                    if (subject.toString().toLowerCase().contains(charSequence))

                        arrayList1.add(subject);
                }
                filterResults.count = arrayList1.size();

                filterResults.values = arrayList1;
            } else {
                synchronized (this) {
                    filterResults.values = bidModels;

                    filterResults.count = bidModels.size();
                }
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            bidModelArrayList = (ArrayList<ServModel>) filterResults.values;

            notifyDataSetChanged();

            clear();

            for (int i = 0, l = bidModelArrayList.size(); i < l; i++)
                add(bidModelArrayList.get(i));
            notifyDataSetInvalidated();
        }
    }
}
