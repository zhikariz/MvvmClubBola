package id.legible.codelabs.helmi.osg3clubbola.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import id.legible.codelabs.helmi.osg3clubbola.model.TeamDetail;

@Database(entities = {TeamDetail.class}, version = 1)
public abstract class TeamDataBase extends RoomDatabase {

    private static TeamDataBase INSTANCE;

    public abstract TeamDao teamDao();
    private static final Object sLock = new Object();

    public static TeamDataBase getInstance(Context context) {
        synchronized (sLock) {
            if(INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        TeamDataBase.class, "Team.db")
                        .build();
            }
            return INSTANCE;
        }
    }
}
