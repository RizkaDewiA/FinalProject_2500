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

public class DetailLastActivity extends AppCompatActivity {

    TextView txtTitleLast, txtDateLast, txtTimeLast, txtHome, txtAway, txtScorehome, txtScoreaway;
    TextView txtLeague, txtSeason, txtVenue, txtCountry, txtRound, txtStatus;
    ImageView imgThumbLast;
    Button btnFavoriteLast;

    public static final String ITEM_EXTRA = "item_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_last);

        imgThumbLast = findViewById(R.id.imgThumbLast);
        txtTitleLast = findViewById(R.id.txtTitleLast);
        txtDateLast = findViewById(R.id.txtDateLast);
        txtTimeLast = findViewById(R.id.txtTimeLast);
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
        btnFavoriteLast = findViewById(R.id.btnFavorite);
        btnFavoriteLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnFavoriteLast.setBackgroundColor(getResources().getColor(R.color.red));
            }
        });


        Event event = getIntent().getParcelableExtra(ITEM_EXTRA);

        if(event != null){
            Glide.with(this)
                    .load(event.getStrThumb())
                    .into(imgThumbLast);
            txtTitleLast.setText(event.getStrEvent());
            txtDateLast.setText(event.getDateEvent());
            txtTimeLast.setText(event.getStrTime());
            txtHome.setText(event.getStrHomeTeam());
            txtAway.setText(event.getStrAwayTeam());
            txtScorehome.setText(event.getIntHomeScore());
            txtScoreaway.setText(event.getIntAwayScore());
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

        btnFavoriteLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = txtTitleLast.getText().toString();
                String date = txtDateLast.getText().toString();
                String time = txtTimeLast.getText().toString();
                String status = txtStatus.getText().toString();

                DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                Event eve = new Event();
                eve.setStrEvent(title);
                eve.setDateEvent(date);
                eve.setStrTime(time);
                eve.setStrStatus(status);
                eve.setStrThumb(event.getStrThumb());

                db.addFavorite(eve);

                Intent main = new Intent(DetailLastActivity.this, MainActivity.class);
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