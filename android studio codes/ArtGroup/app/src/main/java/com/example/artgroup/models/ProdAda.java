package com.example.artgroup.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.artgroup.R;

import java.util.ArrayList;

public class ProdAda extends ArrayAdapter<ProdModel> {
    public ArrayList<ProdModel> MainList;
    public ArrayList<ProdModel> SubjectListTemp;
    public ProdAda.SubjectDataFilter subjectDataFilter;

    public ProdAda(Context context, int id, ArrayList<ProdModel> subjectArrayList) {

        super(context, id, subjectArrayList);

        this.SubjectListTemp = new ArrayList<>();

        this.SubjectListTemp.addAll(subjectArrayList);

        this.MainList = new ArrayList<>();

        this.MainList.addAll(subjectArrayList);
    }

    @Override
    public Filter getFilter() {
        if (subjectDataFilter == null) {
            subjectDataFilter = new ProdAda.SubjectDataFilter();
        }
        return subjectDataFilter;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProdAda.ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.bad_day, null);
            holder = new ProdAda.ViewHolder();
            holder.SubjectQuantity = convertView.findViewById(R.id.appPName);
            holder.imageView = convertView.findViewById(R.id.theProduct);
            convertView.setTag(holder);
        } else {
            holder = (ProdAda.ViewHolder) convertView.getTag();
        }
        ProdModel subject = SubjectListTemp.get(position);
        holder.SubjectQuantity.setText(subject.getCategory() + " " + subject.getType() + "\n@ KES: " + subject.getPrice());
        Glide.with(convertView).load(subject.getImage()).into(holder.imageView);
        return convertView;
    }

    public class ViewHolder {
        TextView SubjectQuantity;
        ImageView imageView;
    }

    private class SubjectDataFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            charSequence = charSequence.toString().toLowerCase();

            FilterResults filterResults = new FilterResults();

            if (charSequence != null && charSequence.toString().length() > 0) {
                ArrayList<ProdModel> arrayList1 = new ArrayList<>();

                for (int i = 0, l = MainList.size(); i < l; i++) {
                    ProdModel subject = MainList.get(i);

                    if (subject.toString().toLowerCase().contains(charSequence))

                        arrayList1.add(subject);
                }
                filterResults.count = arrayList1.size();

                filterResults.values = arrayList1;
            } else {
                synchronized (this) {
                    filterResults.values = MainList;

                    filterResults.count = MainList.size();
                }
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            SubjectListTemp = (ArrayList<ProdModel>) filterResults.values;

            notifyDataSetChanged();

            clear();

            for (int i = 0, l = SubjectListTemp.size(); i < l; i++)
                add(SubjectListTemp.get(i));
            notifyDataSetInvalidated();
        }
    }
}