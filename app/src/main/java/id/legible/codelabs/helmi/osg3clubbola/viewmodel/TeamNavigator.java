package id.legible.codelabs.helmi.osg3clubbola.viewmodel;

import java.util.List;

import id.legible.codelabs.helmi.osg3clubbola.model.TeamDetail;

public interface TeamNavigator {
    void loadListTeam(List<TeamDetail> listTeam);
    void errorLoadListTeam(String message);
}
