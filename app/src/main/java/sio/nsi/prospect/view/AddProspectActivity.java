package sio.nsi.prospect.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import sio.nsi.prospect.R;
import sio.nsi.prospect.model.Prospect;
import sio.nsi.prospect.model.User;
import sio.nsi.prospect.tools.API;
import sio.nsi.prospect.tools.DataBaseHelper;

import java.io.IOException;
import java.util.ArrayList;

public class AddProspectActivity extends AppCompatActivity {
    private DataBaseHelper dataBase;
    private EditText InputRaisonSocial, InputPrenom, InputNom, InputTel, InputMail;
    private RatingBar InputStarRating;
    private String siret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBase = new DataBaseHelper(this);
        setContentView(R.layout.addprospectactivity);
        InputRaisonSocial = findViewById(R.id.inputRaisonSocial);
        InputNom = findViewById(R.id.inputPrenom);
        InputPrenom = findViewById(R.id.inputNom);
        InputTel = findViewById(R.id.inputTel);
        InputMail = findViewById(R.id.inputMail);
        InputStarRating = findViewById(R.id.StarRating);

        Button btnSubmit = findViewById(R.id.button_createProspect);
        btnSubmit.setOnClickListener(eventBtnSubmit);

        ImageView back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(backbutton);

        ImageView btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(logout);
    }

    public View.OnClickListener backbutton = v -> {
        Intent intent = new Intent(AddProspectActivity.this, AccueilActivity.class);
        startActivity(intent);
    };
    public View.OnClickListener logout = v -> {
        Intent intent = new Intent(AddProspectActivity.this, LoginActivity.class);
        startActivity(intent);
    };

    public View.OnClickListener eventBtnSubmit = v -> {
        try {
            siret = API.getSiretFromRS(InputRaisonSocial.getText().toString());
        } catch (Exception ignored) {
        }
        dataBase.addNewProspect(new Prospect(
                InputNom.getText().toString(),
                InputPrenom.getText().toString(),
                siret,
                InputRaisonSocial.getText().toString(),
                (int) InputStarRating.getRating(),
                InputMail.getText().toString(),
                InputTel.getText().toString()));
        try {
            JSONObject jsonProspect = new JSONObject();
            jsonProspect.put("Prospect", new JSONArray());
            ArrayList<Prospect> listOfAllProspect = dataBase.readAllProspect();
            for (int i = 0; i < dataBase.readAllProspect().size(); i++) {
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
        Intent connexion = new Intent(AddProspectActivity.this, AccueilActivity.class);
        startActivity(connexion);
    };
}
