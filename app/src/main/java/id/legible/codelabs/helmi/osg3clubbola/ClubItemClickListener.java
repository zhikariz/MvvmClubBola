package id.legible.codelabs.helmi.osg3clubbola;

import android.widget.ImageView;

import id.legible.codelabs.helmi.osg3clubbola.model.TeamDetail;

public interface ClubItemClickListener {
    void onClubItemClick(TeamDetail clubItem, ImageView shareImageView);
}
