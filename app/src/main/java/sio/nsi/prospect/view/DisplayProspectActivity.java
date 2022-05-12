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

import java.util.ArrayList;

public class DisplayProspectActivity extends AppCompatActivity {
    private Prospect prospectapp;
    private DataBaseHelper dataBase;
    EditText TextNom, TextPrenom, TextRS, TextMail, TextTel;
    RatingBar StareRatting;

    protected void onCreate(@Nullable Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        dataBase = new DataBaseHelper(this);
        setContentView(R.layout.displayprospectactivity);
        Bundle bundle = getIntent().getExtras();
        prospectapp = new Prospect(
                bundle.getInt("ProspectId"),
                bundle.getString("ProspectNom"),
                bundle.getString("ProspectPrenom"),
                bundle.getString("ProspectSiret"),
                bundle.getString("ProspectRS"),
                bundle.getInt("ProspectScore"),
                bundle.getString("ProspectMail"),
                bundle.getString("ProspectTel")
        );

        TextNom = findViewById(R.id.textNom);
        TextNom.setText(prospectapp.getNom());
        TextNom.setEnabled(false);
        TextPrenom = findViewById(R.id.textPrenom);
        TextPrenom.setText(prospectapp.getPrenom());
        TextPrenom.setEnabled(false);
        TextRS = findViewById(R.id.textRaisonSocial);
        TextRS.setText(prospectapp.getRaisonSociale());
        TextRS.setEnabled(false);
        TextMail = findViewById(R.id.textMail);
        TextMail.setText(prospectapp.getMail());
        TextMail.setEnabled(false);
        TextTel = findViewById(R.id.textTel);
        TextTel.setText(prospectapp.getTel());
        TextTel.setEnabled(false);
        StareRatting = findViewById(R.id.ShowStarRating);
        StareRatting.setRating((float) prospectapp.getScore());
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
            prospectapp.setNom(TextNom.getText().toString());
            prospectapp.setPrenom(TextPrenom.getText().toString());
            prospectapp.setRaisonSociale(TextRS.getText().toString());
            prospectapp.setSiret(siret);
            prospectapp.setScore((int) StareRatting.getRating());
            prospectapp.setMail(TextMail.getText().toString());
            prospectapp.setTel(TextTel.getText().toString());
            dataBase.updateProspectFromProspect(prospectapp);
        } catch (Exception ignored) {}
        try {
            JSONObject jsonProspect = new JSONObject();
            jsonProspect.put("Prospect", new JSONArray());
            ArrayList<Prospect> listOfAllProspect = dataBase.readAllProspect();
            for (int i = 0; i < dataBase.readAllProspect().size() ; i++) {
                JSONObject ProspectJson = new JSONObject();
                ProspectJson.put("id", listOfAllProspect.get(i).getId());
                ProspectJson.put("nom", listOfAllProspect.get(i).getNom());
                ProspectJson.put("prenom", listOfAllProspect.get(i).getPrenom());
                ProspectJson.put("siret", listOfAllProspect.get(i).getSiret());
                ProspectJson.put("score", listOfAllProspect.get(i).getScore());
                ProspectJson.put("raisonsocial", listOfAllProspect.get(i).getRaisonSociale());
                ProspectJson.put("mail", listOfAllProspect.get(i).getMail());
                ProspectJson.put("tel", listOfAllProspect.get(i).getTel());
                jsonProspect.accumulate("Prospect", ProspectJson);
            }
            Log.v("Post status", "" + API.postProspect(jsonProspect.toString()));
        } catch (Exception e) {
            Log.v("error", e.getMessage());
        }

        Intent connexion = new Intent(DisplayProspectActivity.this, AccueilActivity.class);
        startActivity(connexion);
    };

}