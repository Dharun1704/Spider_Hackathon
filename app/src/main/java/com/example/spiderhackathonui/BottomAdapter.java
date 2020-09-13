package com.example.spiderhackathonui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BottomAdapter extends RecyclerView.Adapter<BottomAdapter.ViewHolder> {

    Context context;

    public BottomAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.viewpager_layout_2, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == 0) {
            holder.news1.setText("Unity IPO aims to fuel growth across gaming and beyond");
            holder.news2.setText("Welcome to the next generation of gaming");
            holder.news3.setText("Xbox Game Pass Ultimate: Microsoft Takes Aim at Sony With Cloud Gaming Service");
            holder.news4.setVisibility(View.GONE);

            holder.img1.setImageResource(R.drawable.download);
            holder.img2.setImageResource(R.drawable.download_1);
            holder.img3.setImageResource(R.drawable.download_2);
            holder.img4.setVisibility(View.GONE);
            holder.imgBig.setImageResource(R.drawable.download_scbig);

            holder.time1.setText("3k views \u2022 5 days");
            holder.time2.setText("1.5k views \u2022 3 days");
            holder.time3.setText("1.1k views \u2022 2 days");
            holder.time4.setVisibility(View.GONE);
        }

        if (position == 1) {
            holder.news1.setText("Top 10 tips on how to study smarter, not longer");
            holder.news2.setText("Dark matter clumps in galaxy clusters bend light surprisingly well");
            holder.news3.setText("Healthier screen time is one challenge of distance learning");
            holder.news4.setVisibility(View.VISIBLE);
            holder.news4.setText("This moth may outsmart smog by learning to like pollution-altered aromas");

            holder.img1.setImageResource(R.drawable.download);
            holder.img2.setImageResource(R.drawable.download_1);
            holder.img3.setImageResource(R.drawable.download_2);
            holder.img4.setVisibility(View.VISIBLE);
            holder.img4.setImageResource(R.drawable.download_3);
            holder.imgBig.setImageResource(R.drawable.download_scbig);

            holder.time1.setText("3k views \u2022 5 days");
            holder.time2.setText("1.5k views \u2022 3 days");
            holder.time3.setText("1.1k views \u2022 2 days");
            holder.time4.setVisibility(View.VISIBLE);
            holder.time4.setText("1.1k views \u2022 4 days");
        }

        if (position == 2) {
            holder.news1.setText("Unity IPO aims to fuel growth across gaming and beyond");
            holder.news2.setText("Welcome to the next generation of gaming");
            holder.news3.setText("Xbox Game Pass Ultimate: Microsoft Takes Aim at Sony With Cloud Gaming Service");
            holder.news4.setVisibility(View.VISIBLE);
            holder.news4.setText("TSouth Korea's gaming 'Big 3' zero in on smartphone users");

            holder.img1.setImageResource(R.drawable.download_gm);
            holder.img2.setImageResource(R.drawable.download_gm2);
            holder.img3.setImageResource(R.drawable.download_gm3);
            holder.img4.setVisibility(View.VISIBLE);
            holder.img4.setImageResource(R.drawable.download_gm4);
            holder.imgBig.setImageResource(R.drawable.download_gmbig);

            holder.time1.setText("3k views \u2022 5 days");
            holder.time2.setText("1.5k views \u2022 3 days");
            holder.time3.setText("1.1k views \u2022 2 days");
            holder.time4.setVisibility(View.VISIBLE);
            holder.time4.setText("1.1k views \u2022 4 days");
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView news1, news2, news3, news4;
        ImageView img1, img2, img3, img4, imgBig;
        TextView time1, time2, time3,time4;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            news1 = itemView.findViewById(R.id.scHead1);
            news2 = itemView.findViewById(R.id.scHead2);
            news3 = itemView.findViewById(R.id.scHead3);
            news4 = itemView.findViewById(R.id.scHead4);

            img1 = itemView.findViewById(R.id.image1);
            img2 = itemView.findViewById(R.id.image2);
            img3 = itemView.findViewById(R.id.image3);
            img4 = itemView.findViewById(R.id.image4);
            imgBig = itemView.findViewById(R.id.imageBig);

            time1 = itemView.findViewById(R.id.textTime1);
            time2 = itemView.findViewById(R.id.textTime2);
            time3 = itemView.findViewById(R.id.textTime3);
            time4 = itemView.findViewById(R.id.textTime4);
        }
    }
}
