package id.legible.codelabs.helmi.osg3clubbola.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import id.legible.codelabs.helmi.osg3clubbola.model.TeamDetail;

@Dao
public interface TeamDao {
    @Query("SELECT * FROM team")
    List<TeamDetail> getTeams();

    @Insert
    void insertTeam(List<TeamDetail> team);
}
