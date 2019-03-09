package id.legible.codelabs.helmi.osg3clubbola.view;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import id.legible.codelabs.helmi.osg3clubbola.R;
import id.legible.codelabs.helmi.osg3clubbola.model.TeamDetail;

public class DetailClubActivity extends AppCompatActivity {

    private TeamDetail teamDetail;
    private Bundle extras;
    private ImageView clubLogo;
    private TextView clubName;
    private TextView clubDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_club);
        initView();

        extras = getIntent().getExtras();
        teamDetail = (TeamDetail) extras.getSerializable(MainActivity.KEY_TEAM_DETAIL);

        setClub();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail Club");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setClub() {
        String imageUrl = teamDetail.getTeamLogo();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            String imageTransitionName = extras.getString(MainActivity.KEY_TEAM_DETAIL_TRANSITION_NAME);
            clubLogo.setTransitionName(imageTransitionName);
        }

        Picasso.get()
                .load(imageUrl)
                .noFade()
                .into(clubLogo, new Callback() {
                    @Override
                    public void onSuccess() {
                        supportStartPostponedEnterTransition();
                    }

                    @Override
                    public void onError(Exception e) {
                        supportStartPostponedEnterTransition();
                    }
                });

        clubName.setText(teamDetail.getTeamName());
        clubDescription.setText(teamDetail.getTeamDescription());

    }

    private void initView() {
        clubLogo = findViewById(R.id.club_logo);
        clubName = findViewById(R.id.club_name);
        clubDescription = findViewById(R.id.club_description);
    }
}
