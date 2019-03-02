package id.legible.codelabs.helmi.osg3clubbola.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Team {

    @SerializedName("teams")
    List<TeamDetail> teams;

    public Team(List<TeamDetail> teams){
        this.teams = teams;
    }

    public List<TeamDetail> getTeams(){
        return teams;
    }
}
