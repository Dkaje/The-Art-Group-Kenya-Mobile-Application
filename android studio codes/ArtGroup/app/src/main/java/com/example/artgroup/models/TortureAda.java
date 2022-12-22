package com.example.artgroup.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.example.artgroup.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TortureAda extends ArrayAdapter<TortureMode> {
    public ArrayList<TortureMode> MainList;
    public ArrayList<TortureMode> SubjectListTemp;
    public TortureAda.SubjectDataFilter subjectDataFilter;

    public TortureAda(Context context, int id, ArrayList<TortureMode> subjectArrayList) {

        super(context, id, subjectArrayList);

        this.SubjectListTemp = new ArrayList<>();

        this.SubjectListTemp.addAll(subjectArrayList);

        this.MainList = new ArrayList<>();

        this.MainList.addAll(subjectArrayList);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {

            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = vi.inflate(R.layout.ranger, null);
            holder = new ViewHolder();
            holder.user = convertView.findViewById(R.id.target);
            holder.details = convertView.findViewById(R.id.myNamer);
            holder.message = convertView.findViewById(R.id.myMess);
            holder.counter = convertView.findViewById(R.id.myCounter);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        TortureMode subject = SubjectListTemp.get(position);
        String dater = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String ikulu;
        if (subject.getDate().equals(dater)) {
            ikulu = subject.getTiming();
        } else {
            ikulu = subject.getDate() + " " + subject.getTiming();
        }
        holder.user.setText(subject.getTemple());
        holder.details.setText(subject.getName() + " (" + subject.getSen() + "), me");
        holder.message.setText(subject.getMessage() + "\n" + ikulu);
        holder.counter.setText(subject.getCount());
        return convertView;

    }

    public class ViewHolder {
        TextView user, details, message, counter;
    }

    private class SubjectDataFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            charSequence = charSequence.toString().toLowerCase();

            FilterResults filterResults = new FilterResults();

            if (charSequence != null && charSequence.toString().length() > 0) {
                ArrayList<TortureMode> arrayList1 = new ArrayList<>();

                for (int i = 0, l = MainList.size(); i < l; i++) {
                    TortureMode subject = MainList.get(i);

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

            SubjectListTemp = (ArrayList<TortureMode>) filterResults.values;

            notifyDataSetChanged();

            clear();

            for (int i = 0, l = SubjectListTemp.size(); i < l; i++)
                add(SubjectListTemp.get(i));

            notifyDataSetInvalidated();
        }
    }
}
