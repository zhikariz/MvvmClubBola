package id.legible.codelabs.helmi.osg3clubbola.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import id.legible.codelabs.helmi.osg3clubbola.ClubItemClickListener;
import id.legible.codelabs.helmi.osg3clubbola.R;
import id.legible.codelabs.helmi.osg3clubbola.databinding.ItemRowBinding;
import id.legible.codelabs.helmi.osg3clubbola.model.TeamDetail;

public class TeamBolaAdapter extends RecyclerView.Adapter<TeamBolaAdapter.TeamBolaHolder> {

    private ClubItemClickListener clubItemClickListener;
    private List<TeamDetail> listKubBola;
    private LayoutInflater layoutInflater;

    public TeamBolaAdapter(ClubItemClickListener clubItemClickListener, List<TeamDetail> listKlubBola) {
        this.listKubBola = listKlubBola;
        this.clubItemClickListener = clubItemClickListener;
    }

    @NonNull
    @Override
    public TeamBolaHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(layoutInflater == null) {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        ItemRowBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_row, viewGroup, false);
        return new TeamBolaHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final TeamBolaHolder klubBolaHolder, final int i) {
        klubBolaHolder.binding.setTeamDetailVM(listKubBola.get(i));

        ViewCompat.setTransitionName(klubBolaHolder.binding.itemClubImg, listKubBola.get(i).getTeamLogo());
        klubBolaHolder.binding.cardView.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {
               clubItemClickListener.onClubItemClick(listKubBola.get(i), klubBolaHolder.binding.itemClubImg);
           }
        });
    }

    @Override
    public int getItemCount() {
        return listKubBola.size();
    }

    class TeamBolaHolder extends RecyclerView.ViewHolder {

        private final ItemRowBinding binding;

        TeamBolaHolder(@NonNull ItemRowBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.binding = itemRowBinding;
        }
    }
}
