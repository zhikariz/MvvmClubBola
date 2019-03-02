package id.legible.codelabs.helmi.osg3clubbola;

import android.content.Context;

import id.legible.codelabs.helmi.osg3clubbola.data.TeamRepository;
import id.legible.codelabs.helmi.osg3clubbola.data.local.TeamLocalDataSource;
import id.legible.codelabs.helmi.osg3clubbola.data.remote.TeamRemoteDataSource;

public class Injection {
    public static TeamRepository provideTeamRepository(Context context) {
        return new TeamRepository(new TeamRemoteDataSource(), new TeamLocalDataSource(context));
    }
}
