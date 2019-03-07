package id.legible.codelabs.helmi.osg3clubbola.viewmodel;

import android.content.Context;

import id.legible.codelabs.helmi.osg3clubbola.data.TeamDataSource;
import id.legible.codelabs.helmi.osg3clubbola.data.TeamRepository;
import id.legible.codelabs.helmi.osg3clubbola.model.Team;

public class TeamViewModel {

    private TeamRepository teamRepository;
    private TeamNavigator mNavigator;

    public TeamViewModel(TeamRepository teamRepository, Context context) {
        this.teamRepository = teamRepository;
    }

    public void setNavigator(TeamNavigator navigator) {
        mNavigator = navigator;
    }

    public void getListTeam() {
        teamRepository.getListTeams(new TeamDataSource.GetTeamsCallback() {
            @Override
            public void onTeamLoaded(Team data) {
                mNavigator.loadListTeam(data.getTeams());
            }

            @Override
            public void onDataNotAvailable(String errorMessage) {
                mNavigator.errorLoadListTeam(errorMessage);
            }
        });
    }
}
