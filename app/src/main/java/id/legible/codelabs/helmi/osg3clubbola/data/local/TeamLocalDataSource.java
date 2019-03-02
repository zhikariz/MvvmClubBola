package id.legible.codelabs.helmi.osg3clubbola.data.local;

import android.content.Context;

import java.util.List;

import id.legible.codelabs.helmi.osg3clubbola.data.TeamDataSource;
import id.legible.codelabs.helmi.osg3clubbola.model.Team;
import id.legible.codelabs.helmi.osg3clubbola.model.TeamDetail;

public class TeamLocalDataSource implements TeamDataSource {

    private Context context;
    private TeamDao teamDao;

    public TeamLocalDataSource(Context context) {
        this.context = context;
        teamDao = TeamDataBase.getInstance(context).teamDao();
    }

    @Override
    public void getListTeams(final GetTeamsCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<TeamDetail> team = teamDao.getTeams();
                if (team.isEmpty()) {
                    callback.onDataNotAvailable("Data di Database SQLite Kosong");
                } else {
                    Team teams = new Team(team);
                    callback.onTeamLoaded(teams);
                }
            }
        };
        new Thread(runnable).start();
    }

    public void saveDataTeam(final List<TeamDetail> teamDetail) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                teamDao.insertTeam(teamDetail);
            }
        };
        new Thread(runnable).start();
    }
}
