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

public class FinalDAda extends ArrayAdapter<ReviMode> {
    public ArrayList<ReviMode> MainList;
    public ArrayList<ReviMode> SubjectListTemp;
    public FinalDAda.SubjectDataFilter subjectDataFilter;

    public FinalDAda(Context context, int id, ArrayList<ReviMode> subjectArrayList) {

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
            convertView = vi.inflate(R.layout.tabulated, null);
            holder = new ViewHolder();
            holder.named = convertView.findViewById(R.id.custName);
            holder.amount = convertView.findViewById(R.id.custID);
            holder.stat = convertView.findViewById(R.id.custStatus);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ReviMode subject = SubjectListTemp.get(position);
        int pos = position + 1;
        holder.named.setText(pos + ". " + subject.getCust_name());
        pos++;
        holder.amount.setText(subject.getLocation());
        holder.stat.setText(subject.getCustom());
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
                ArrayList<ReviMode> arrayList1 = new ArrayList<>();

                for (int i = 0, l = MainList.size(); i < l; i++) {
                    ReviMode subject = MainList.get(i);

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

            SubjectListTemp = (ArrayList<ReviMode>) filterResults.values;

            notifyDataSetChanged();

            clear();

            for (int i = 0, l = SubjectListTemp.size(); i < l; i++)
                add(SubjectListTemp.get(i));
            notifyDataSetInvalidated();
        }
    }
}