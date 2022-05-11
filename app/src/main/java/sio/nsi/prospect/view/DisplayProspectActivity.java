package sio.nsi.prospect.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import sio.nsi.prospect.R;
import sio.nsi.prospect.model.Prospect;

public class DisplayProspectActivity extends AppCompatActivity {
    private Prospect prospect;
    EditText TextNom, TextPrenom, TextRS, TextMail, TextTel;
    RatingBar StareRatting;

    protected void onCreate(@Nullable Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.displayprospectactivity);
        Bundle bundle = getIntent().getExtras();
        prospect = new Prospect(bundle.getString("ProspectNom"),
                bundle.getString("ProspectPrenom"),
                bundle.getString("ProspectSiret"),
                bundle.getString("ProspectRS"),
                bundle.getInt("ProspectScore"),
                bundle.getString("ProspectMail"),
                bundle.getString("ProspectTel")
        );

        TextNom = findViewById(R.id.textNom);
        TextNom.setText(prospect.getNom());
        TextNom.setEnabled(false);
        TextPrenom = findViewById(R.id.textPrenom);
        TextPrenom.setText(prospect.getPrenom());
        TextPrenom.setEnabled(false);
        TextRS = findViewById(R.id.textRaisonSocial);
        TextRS.setText(prospect.getRaisonSociale());
        TextRS.setEnabled(false);
        TextMail = findViewById(R.id.textMail);
        TextMail.setText(prospect.getMail());
        TextMail.setEnabled(false);
        TextTel = findViewById(R.id.textTel);
        TextTel.setText(prospect.getTel());
        TextTel.setEnabled(false);
        StareRatting = findViewById(R.id.ShowStarRating);
        StareRatting.setRating((float) prospect.getScore());
        StareRatting.setEnabled(false);

        ImageView back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(backbutton);

        ImageView btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(logout);


    }

    public View.OnClickListener backbutton = v -> {
        Intent intent = new Intent(DisplayProspectActivity.this, AccueilActivity.class);
        startActivity(intent);
    };

    public View.OnClickListener logout = v -> {
        Intent intent = new Intent(DisplayProspectActivity.this, LoginActivity.class);
        startActivity(intent);
    };
}
