package com.pam.finalproject_2500.DetailActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pam.finalproject_2500.DatabaseHelper;
import com.pam.finalproject_2500.Model.Event;
import com.pam.finalproject_2500.MainActivity;
import com.pam.finalproject_2500.R;

public class DetailNextActivity extends AppCompatActivity {

    TextView txtTitleNext, txtDateNext, txtTimeNext, txtHome, txtAway, txtScorehome, txtScoreaway;
    TextView txtLeague, txtSeason, txtVenue, txtCountry, txtRound, txtStatus;
    ImageView imgThumbNext;
    Button btnFavoriteNext;

    public  static final String ITEM_EXTRA = "item_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_next);

        imgThumbNext = findViewById(R.id.imgThumbNext);
        txtTitleNext = findViewById(R.id.txtTitleNext);
        txtDateNext = findViewById(R.id.txtDateNext);
        txtTimeNext = findViewById(R.id.txtTimeNext);
        txtHome = findViewById(R.id.txtHome);
        txtAway = findViewById(R.id.txtAway);
        txtScorehome = findViewById(R.id.txtHomeScore);
        txtScoreaway = findViewById(R.id.txtAwayScore);
        txtLeague = findViewById(R.id.txtLeague);
        txtSeason = findViewById(R.id.txtSeason);
        txtVenue = findViewById(R.id.txtVenue);
        txtCountry = findViewById(R.id.txtCountry);
        txtRound = findViewById(R.id.txtRound);
        txtStatus = findViewById(R.id.txtStatus);
        btnFavoriteNext = findViewById(R.id.btnFavorite);

        Event event = getIntent().getParcelableExtra(ITEM_EXTRA);

        if(event != null){
            Glide.with(this)
                    .load(event.getStrThumb())
                    .into(imgThumbNext);
            txtTitleNext.setText(event.getStrEvent());
            txtDateNext.setText(event.getDateEvent());
            txtTimeNext.setText(event.getStrTime());
            txtHome.setText(event.getStrHomeTeam());
            txtAway.setText(event.getStrAwayTeam());
            txtLeague.setText(event.getStrLeague());
            txtSeason.setText(event.getStrSeason());
            txtVenue.setText(event.getStrVenue());
            txtCountry.setText(event.getStrCountry());
            txtRound.setText(event.getIntRound());
            txtStatus.setText(event.getStrStatus());
        }

        if(getSupportActionBar() !=null){
            getSupportActionBar().setTitle("Detail " + event.getStrEvent());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        btnFavoriteNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = txtTitleNext.getText().toString();
                String date = txtDateNext.getText().toString();
                String time = txtTimeNext.getText().toString();
                String status = txtStatus.getText().toString();

                DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                Event eve = new Event();
                eve.setStrEvent(title);
                eve.setDateEvent(date);
                eve.setStrTime(time);
                eve.setStrStatus(status);
                eve.setStrThumb(event.getStrThumb());

                db.addFavorite(eve);

                Intent main = new Intent(DetailNextActivity.this, MainActivity.class);
                startActivity(main);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}