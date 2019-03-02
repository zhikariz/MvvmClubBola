package id.legible.codelabs.helmi.osg3clubbola.data;

import id.legible.codelabs.helmi.osg3clubbola.model.Team;

public interface TeamDataSource {
    void getListTeams(GetTeamsCallback callback);

    interface GetTeamsCallback {
        void onTeamLoaded(Team data);
        void onDataNotAvailable(String errorMessage);
    }
}
