package id.legible.codelabs.helmi.osg3clubbola.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.legible.codelabs.helmi.osg3clubbola.Injection;
import id.legible.codelabs.helmi.osg3clubbola.R;
import id.legible.codelabs.helmi.osg3clubbola.TeamNavigator;
import id.legible.codelabs.helmi.osg3clubbola.adapter.TeamBolaAdapter;
import id.legible.codelabs.helmi.osg3clubbola.model.TeamDetail;
import id.legible.codelabs.helmi.osg3clubbola.viewmodel.TeamViewModel;

public class MainActivity extends AppCompatActivity implements TeamNavigator {

    @BindView(R.id.recycler_team_bola)
    RecyclerView recTeam;

    private RecyclerView.LayoutManager mManager;

    private TeamViewModel mTeamViewModel;

    private TeamBolaAdapter adapter;
    private List<TeamDetail> dataListTeamBola;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mTeamViewModel = new TeamViewModel(Injection.provideTeamRepository(this), getApplicationContext());
        dataListTeamBola = new ArrayList<>();
        mTeamViewModel.setNavigator(this);
        initAdapter();
        mTeamViewModel.getListTeam();
    }

    private void initAdapter() {
        mManager = new LinearLayoutManager(getApplicationContext());
        recTeam.setLayoutManager(mManager);
        adapter = new TeamBolaAdapter(getApplicationContext(), dataListTeamBola);
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
}
