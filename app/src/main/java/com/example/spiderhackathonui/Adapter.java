package com.example.spiderhackathonui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    ArrayList<String> heading;

    public Adapter(Context context, ArrayList<String> heading) {
        this.context = context;
        this.heading = heading;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.viewpager_layout_1, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = heading.get(position);
        holder.headingView.setText(name);
    }

    @Override
    public int getItemCount() {
        return heading.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView headingView;
        RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            headingView = itemView.findViewById(R.id.detail_hd);
            relativeLayout = itemView.findViewById(R.id.pagerElementLayout);
        }
    }
}
