package id.legible.codelabs.helmi.osg3clubbola.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import id.legible.codelabs.helmi.osg3clubbola.ClubItemClickListener;
import id.legible.codelabs.helmi.osg3clubbola.databinding.ActivityMainBinding;
import id.legible.codelabs.helmi.osg3clubbola.viewmodel.Injection;
import id.legible.codelabs.helmi.osg3clubbola.R;
import id.legible.codelabs.helmi.osg3clubbola.viewmodel.TeamNavigator;
import id.legible.codelabs.helmi.osg3clubbola.adapter.TeamBolaAdapter;
import id.legible.codelabs.helmi.osg3clubbola.model.TeamDetail;
import id.legible.codelabs.helmi.osg3clubbola.viewmodel.TeamViewModel;

public class MainActivity extends AppCompatActivity implements TeamNavigator, ClubItemClickListener {

    public static final String KEY_TEAM_DETAIL = "team_detail";
    public static final String KEY_TEAM_DETAIL_TRANSITION_NAME = "strTeamBadge";

    private ActivityMainBinding binding;

    private RecyclerView recTeam;

    private TeamViewModel mTeamViewModel;

    private TeamBolaAdapter adapter;
    private List<TeamDetail> dataListTeamBola;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mTeamViewModel = new TeamViewModel(Injection.provideTeamRepository(this), getApplicationContext());
        dataListTeamBola = new ArrayList<>();
        mTeamViewModel.setNavigator(this);
        mTeamViewModel.getListTeam();

        binding.setVm(mTeamViewModel);
        initAdapter();
    }

    private void initAdapter() {
        adapter = new TeamBolaAdapter(this,dataListTeamBola);
        recTeam = binding.recyclerTeamBola;
        recTeam.setLayoutManager(new LinearLayoutManager(this));
        recTeam.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recTeam.setAdapter(adapter);
    }

    @Override
    public void loadListTeam(List<TeamDetail> listTeam) {
        dataListTeamBola.addAll(listTeam);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void errorLoadListTeam(String message) {
        Log.e("ERROR",message);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClubItemClick(TeamDetail clubItem, ImageView shareImageView) {
        Intent intent = new Intent(this, DetailClubActivity.class);
        intent.putExtra(KEY_TEAM_DETAIL, clubItem);
        intent.putExtra(KEY_TEAM_DETAIL_TRANSITION_NAME, ViewCompat.getTransitionName(shareImageView));
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,shareImageView,
                ViewCompat.getTransitionName(shareImageView));
        startActivity(intent, options.toBundle());
    }
}
