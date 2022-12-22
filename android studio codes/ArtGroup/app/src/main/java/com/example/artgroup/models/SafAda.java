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

public class SafAda extends ArrayAdapter<PayerSe> {
    public ArrayList<PayerSe> MainList;
    public ArrayList<PayerSe> SubjectListTemp;
    public SafAda.SubjectDataFilter subjectDataFilter;

    public SafAda(Context context, int id, ArrayList<PayerSe> subjectArrayList) {

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
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.sammy, null);
            holder = new ViewHolder();
            holder.named = convertView.findViewById(R.id.bezo);
            holder.coun = convertView.findViewById(R.id.counter);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        PayerSe subject = SubjectListTemp.get(position);
        int pos = position + 1;
        holder.coun.setText(pos + ".");
        pos++;
        holder.named.setText("Serial: " + subject.getSerid() + "\nname: " + subject.getName() + "\n" + subject.getReg_date());
        return convertView;
    }

    public class ViewHolder {
        TextView named, coun;
    }

    private class SubjectDataFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            charSequence = charSequence.toString().toLowerCase();

            FilterResults filterResults = new FilterResults();

            if (charSequence != null && charSequence.toString().length() > 0) {
                ArrayList<PayerSe> arrayList1 = new ArrayList<>();
                for (int i = 0, l = MainList.size(); i < l; i++) {
                    PayerSe subject = MainList.get(i);
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

            SubjectListTemp = (ArrayList<PayerSe>) filterResults.values;

            notifyDataSetChanged();

            clear();

            for (int i = 0, l = SubjectListTemp.size(); i < l; i++)
                add(SubjectListTemp.get(i));
            notifyDataSetInvalidated();
        }
    }
}