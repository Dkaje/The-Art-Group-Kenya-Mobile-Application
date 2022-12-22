package com.example.artgroup.models;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artgroup.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FallacyWork extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<GreatMes> list;
    private Context ctx;

    public FallacyWork(Context context, List<GreatMes> list) {
        this.list = list;
        ctx = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder viewHolder;
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.why_miss, viewGroup, false);
        viewHolder = new OriginalViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder hotel, int i) {
        if (hotel instanceof OriginalViewHolder) {
            final OriginalViewHolder viewHolder = (OriginalViewHolder) hotel;
            final GreatMes get = list.get(i);
            String dater = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
            String kuimbie;
            if (get.getDate().equals(dater)) {
                kuimbie = get.getTiming();
            } else {
                kuimbie = get.getDate() + " " + get.getTiming();
            }
            if (get.getMeme().equals("K")) {
                viewHolder.sUser.setText(get.getTemple());
                viewHolder.sDat.setText(kuimbie);
                viewHolder.sName.setText(get.getName() + ", " + get.getSen());
                viewHolder.sMess.setText(get.getMessage());
                viewHolder.ss.setVisibility(View.VISIBLE);
            } else {
                viewHolder.rUser.setText(get.getTemple2());
                viewHolder.rDat.setText(kuimbie);
                viewHolder.rName.setText(get.getRec() + ", me");
                viewHolder.rMess.setText(get.getMessage());
                viewHolder.rr.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        public TextView sUser, sName, sDat, sMess, rUser, rName, rDat, rMess;
        public RelativeLayout ss, rr;

        public OriginalViewHolder(@NonNull View view) {
            super(view);
            sUser = view.findViewById(R.id.sendF);
            sName = view.findViewById(R.id.sendName);
            sDat = view.findViewById(R.id.sendDate);
            sMess = view.findViewById(R.id.sendMess);
            rUser = view.findViewById(R.id.recF);
            rName = view.findViewById(R.id.recName);
            rDat = view.findViewById(R.id.recDate);
            rMess = view.findViewById(R.id.recMess);
            ss = view.findViewById(R.id.send);
            rr = view.findViewById(R.id.rec);
        }
    }
}

