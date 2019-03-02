package id.legible.codelabs.helmi.osg3clubbola.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.legible.codelabs.helmi.osg3clubbola.R;
import id.legible.codelabs.helmi.osg3clubbola.model.TeamDetail;

public class TeamBolaAdapter extends RecyclerView.Adapter<TeamBolaAdapter.MyHolder> {

    private Context context;
    private List<TeamDetail> teamDetails;

    public TeamBolaAdapter(Context context, List<TeamDetail> teamDetails) {
        this.context = context;
        this.teamDetails = teamDetails;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        MyHolder holder = new MyHolder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Glide.with(context).load(teamDetails.get(position).teamLogo).into(holder.ivLogo);
        holder.tvClub.setText(teamDetails.get(position).teamName);
    }

    @Override
    public int getItemCount() {
        return teamDetails.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_logo)
        ImageView ivLogo;
        @BindView(R.id.tv_club)
        TextView tvClub;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
