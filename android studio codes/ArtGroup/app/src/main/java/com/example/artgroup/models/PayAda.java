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

public class PayAda extends ArrayAdapter<PayMod> {
    public ArrayList<PayMod> MainList;
    public ArrayList<PayMod> SubjectListTemp;
    public PayAda.SubjectDataFilter subjectDataFilter;

    public PayAda(Context context, int id, ArrayList<PayMod> subjectArrayList) {

        super(context, id, subjectArrayList);

        this.SubjectListTemp = new ArrayList<>();

        this.SubjectListTemp.addAll(subjectArrayList);

        this.MainList = new ArrayList<>();

        this.MainList.addAll(subjectArrayList);
    }

    @Override
    public Filter getFilter() {
        if (subjectDataFilter == null) {
            subjectDataFilter = new PayAda.SubjectDataFilter();
        }
        return subjectDataFilter;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PayAda.ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.paid, null);
            holder = new PayAda.ViewHolder();
            holder.named = convertView.findViewById(R.id.myName);
            holder.amount = convertView.findViewById(R.id.myAmt);
            holder.stat = convertView.findViewById(R.id.mySta);
            convertView.setTag(holder);
        } else {
            holder = (PayAda.ViewHolder) convertView.getTag();
        }
        PayMod subject = SubjectListTemp.get(position);
        int pos = position + 1;
        holder.named.setText(pos + ". " + subject.getName());
        pos++;
        holder.amount.setText("KES" + subject.getAmount());
        holder.stat.setText(subject.getStatus());
        return convertView;
    }

    public class ViewHolder {
        TextView named, amount, stat;
    }

    private class SubjectDataFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            charSequence = charSequence.toString().toLowerCase();

            FilterResults filterResults = new FilterResults();

            if (charSequence != null && charSequence.toString().length() > 0) {
                ArrayList<PayMod> arrayList1 = new ArrayList<>();

                for (int i = 0, l = MainList.size(); i < l; i++) {
                    PayMod subject = MainList.get(i);

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

            SubjectListTemp = (ArrayList<PayMod>) filterResults.values;

            notifyDataSetChanged();

            clear();

            for (int i = 0, l = SubjectListTemp.size(); i < l; i++)
                add(SubjectListTemp.get(i));
            notifyDataSetInvalidated();
        }
    }
}
