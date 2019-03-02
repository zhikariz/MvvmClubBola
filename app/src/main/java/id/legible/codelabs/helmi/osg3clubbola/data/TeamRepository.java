package id.legible.codelabs.helmi.osg3clubbola.data;

import android.support.annotation.Nullable;

import id.legible.codelabs.helmi.osg3clubbola.data.local.TeamLocalDataSource;
import id.legible.codelabs.helmi.osg3clubbola.data.remote.TeamRemoteDataSource;
import id.legible.codelabs.helmi.osg3clubbola.model.Team;

public class TeamRepository implements TeamDataSource {
    private TeamRemoteDataSource teamRemoteDataSource;
    private TeamLocalDataSource teamLocalDataSource;

    public TeamRepository(TeamRemoteDataSource teamRemoteDataSource, TeamLocalDataSource teamLocalDataSource) {
        this.teamRemoteDataSource = teamRemoteDataSource;
        this.teamLocalDataSource = teamLocalDataSource;
    }

    private void getTeamsFromRemoteDataSource(@Nullable final GetTeamsCallback callback) {
        teamRemoteDataSource.getListTeams(new GetTeamsCallback() {
            @Override
            public void onTeamLoaded(Team data) {
                // insert to Database
                teamLocalDataSource.saveDataTeam(data.getTeams());
                callback.onTeamLoaded(data);
            }

            @Override
            public void onDataNotAvailable(String errorMessage) {
                callback.onDataNotAvailable(errorMessage);
            }
        });
    }

    @Override
    public void getListTeams(final GetTeamsCallback callback) {
        teamLocalDataSource.getListTeams(new GetTeamsCallback() {
            @Override
            public void onTeamLoaded(Team data) {
                callback.onTeamLoaded(data);
            }

            @Override
            public void onDataNotAvailable(String errorMessage) {
                getTeamsFromRemoteDataSource(callback);

            }
        });
    }
}
