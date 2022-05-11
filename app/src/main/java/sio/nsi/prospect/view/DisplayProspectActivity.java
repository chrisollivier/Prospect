package sio.nsi.prospect.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONObject;
import sio.nsi.prospect.R;
import sio.nsi.prospect.model.Prospect;
import sio.nsi.prospect.tools.API;
import sio.nsi.prospect.tools.DataBaseHelper;

public class DisplayProspectActivity extends AppCompatActivity {
    private Prospect prospect;
    private DataBaseHelper dataBase;
    EditText TextNom, TextPrenom, TextRS, TextMail, TextTel;
    RatingBar StareRatting;

    protected void onCreate(@Nullable Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        dataBase = new DataBaseHelper(this);
        setContentView(R.layout.displayprospectactivity);
        Bundle bundle = getIntent().getExtras();
        prospect = new Prospect(
                bundle.getInt("ProspectId"),
                bundle.getString("ProspectNom"),
                bundle.getString("ProspectPrenom"),
                bundle.getString("ProspectSiret"),
                bundle.getString("ProspectRS"),
                bundle.getInt("ProspectScore"),
                bundle.getString("ProspectMail"),
                bundle.getString("ProspectTel")
        );
        Log.v("prospect", prospect.toString());

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

        Button btn_Modifier = findViewById(R.id.Button_Modifier);
        btn_Modifier.setOnClickListener(btnModifier);

        Button btn_Enregistre = findViewById(R.id.Button_Enregistrer);
        btn_Enregistre.setOnClickListener(btnEnregistre);
        btn_Enregistre.setEnabled(false);

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

    public View.OnClickListener btnModifier = v -> {
        Button btn_Enregistre = findViewById(R.id.Button_Enregistrer);
        btn_Enregistre.setEnabled(true);
        TextNom.setEnabled(true);
        TextPrenom.setEnabled(true);
        TextRS.setEnabled(true);
        TextTel.setEnabled(true);
        TextMail.setEnabled(true);
        StareRatting.setEnabled(true);
    };

    public View.OnClickListener btnEnregistre = v -> {
        try {
            String siret = API.getSiretFromRS(TextRS.getText().toString());
            prospect.setNom(TextNom.getText().toString());
            prospect.setPrenom(TextPrenom.getText().toString());
            prospect.setRaisonSociale(TextRS.getText().toString());
            prospect.setSiret(siret);
            prospect.setScore((int) StareRatting.getRating());
            prospect.setMail(TextMail.getText().toString());
            prospect.setTel(TextTel.getText().toString());
            dataBase.updateProspectFromProspect(prospect);
        } catch (Exception ignored) {}
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("Prospect", new JSONArray());
            JSONObject ProspectJson = new JSONObject();
            for (Prospect prospect : dataBase.readAllProspect()) {
                Log.v("prospect", prospect.toString());
                ProspectJson.put("id", prospect.getId());
                ProspectJson.put("nom", prospect.getNom());
                ProspectJson.put("prenom", prospect.getPrenom());
                ProspectJson.put("siret", prospect.getSiret());
                ProspectJson.put("score", prospect.getScore());
                ProspectJson.put("raisonsocial", prospect.getRaisonSociale());
                ProspectJson.put("mail", prospect.getMail());
                ProspectJson.put("tel", prospect.getTel());
                jsonBody.accumulate("Prospect", ProspectJson);
            }
            Log.v("Post Json", jsonBody.toString());
            Log.v("Post status", "" + API.postProspect(jsonBody.toString()));
        } catch (Exception e) {}
        Intent connexion = new Intent(DisplayProspectActivity.this, AccueilActivity.class);
        startActivity(connexion);
    };

}
