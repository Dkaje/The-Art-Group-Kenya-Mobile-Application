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

public class CustAda extends ArrayAdapter<RecentMode> {
    public ArrayList<RecentMode> MainList;
    public ArrayList<RecentMode> SubjectListTemp;
    public CustAda.SubjectDataFilter subjectDataFilter;

    public CustAda(Context context, int id, ArrayList<RecentMode> subjectArrayList) {

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
        RecentMode subject = SubjectListTemp.get(position);
        int pos = position + 1;
        holder.coun.setText(pos + ".");
        pos++;
        holder.named.setText("Serial: " + subject.getPayid() + "\nname: " + subject.getName() + "\n" + subject.getReg_date());
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
                ArrayList<RecentMode> arrayList1 = new ArrayList<>();
                for (int i = 0, l = MainList.size(); i < l; i++) {
                    RecentMode subject = MainList.get(i);
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

            SubjectListTemp = (ArrayList<RecentMode>) filterResults.values;

            notifyDataSetChanged();

            clear();

            for (int i = 0, l = SubjectListTemp.size(); i < l; i++)
                add(SubjectListTemp.get(i));
            notifyDataSetInvalidated();
        }
    }
}
