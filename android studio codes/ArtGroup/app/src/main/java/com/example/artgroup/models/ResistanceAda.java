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

public class ResistanceAda extends ArrayAdapter<CartMod> {
    public ArrayList<CartMod> MainList;
    public ArrayList<CartMod> SubjectListTemp;
    public SubjectDataFilter subjectDataFilter;

    public ResistanceAda(Context context, int id, ArrayList<CartMod> subjectArrayList) {

        super(context, id, subjectArrayList);

        this.SubjectListTemp = new ArrayList<>();

        this.SubjectListTemp.addAll(subjectArrayList);

        this.MainList = new ArrayList<>();

        this.MainList.addAll(subjectArrayList);
    }

    @Override
    public Filter getFilter() {

        if (subjectDataFilter == null) {

            subjectDataFilter = new SubjectDataFilter();
        }
        return subjectDataFilter;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {

            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.loader, null);
            holder = new ViewHolder();
            holder.Detail = convertView.findViewById(R.id.rtItem);
            holder.UnitPrice = convertView.findViewById(R.id.rtDesc);
            holder.Quantity = convertView.findViewById(R.id.rtAmount);
            holder.count=convertView.findViewById(R.id.rdx);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        CartMod subject = SubjectListTemp.get(position);
        int pos = position + 1;
        holder.count.setText(pos+".");
        pos++;
        holder.Detail.setText(subject.getCategory());
        holder.UnitPrice.setText(subject.getQuantity() + "@ KSH" + subject.getPrice());
        holder.Quantity.setText("KSH" + String.format("%.0f", Float.parseFloat(subject.getPrice()) * Float.parseFloat(subject.getQuantity())));

        return convertView;

    }

    public class ViewHolder {
        TextView Detail;
        TextView Quantity;
        TextView UnitPrice, count;

    }

    private class SubjectDataFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            charSequence = charSequence.toString().toLowerCase();

            FilterResults filterResults = new FilterResults();

            if (charSequence != null && charSequence.toString().length() > 0) {
                ArrayList<CartMod> arrayList1 = new ArrayList<>();

                for (int i = 0, l = MainList.size(); i < l; i++) {
                    CartMod subject = MainList.get(i);

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

            SubjectListTemp = (ArrayList<CartMod>) filterResults.values;

            notifyDataSetChanged();

            clear();

            for (int i = 0, l = SubjectListTemp.size(); i < l; i++)
                add(SubjectListTemp.get(i));

            notifyDataSetInvalidated();
        }
    }

}
