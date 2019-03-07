package id.legible.codelabs.helmi.osg3clubbola.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

import id.legible.codelabs.helmi.osg3clubbola.BR;

@Entity(tableName = "team")
public class TeamDetail extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    public int mId;

    @ColumnInfo(name = "team_name")
    @SerializedName("strTeam")
    public String teamName;

    @ColumnInfo(name = "team_logo")
    @SerializedName("strTeamBadge")
    public String teamLogo;

    public TeamDetail(int mId, String teamName, String teamLogo) {
        this.mId = mId;
        this.teamName = teamName;
        this.teamLogo = teamLogo;
    }
    @Bindable
    public int getmId() {
        return mId;
    }
    @Bindable
    public String getTeamName() {
        return teamName;
    }
    @Bindable
    public String getTeamLogo() {
        return teamLogo;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
        notifyPropertyChanged(BR.teamName);
    }

    public void setTeamLogo(String teamLogo) {
        this.teamLogo = teamLogo;
    }

    @BindingAdapter({"teamLogo"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.get().load(imageUrl).into(view);
    }
}