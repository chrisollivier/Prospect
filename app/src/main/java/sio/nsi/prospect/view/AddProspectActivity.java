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
    private EditText InputRaisonSocial;
    private EditText InputPrenom;
    private EditText InputNom;
    private EditText InputTel;
    private EditText InputMail;
    private RatingBar InputStarRating;

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

    public View.OnClickListener backbutton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(AddProspectActivity.this, AccueilActivity.class);
            startActivity(intent);
        }
    };
    public View.OnClickListener logout = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(AddProspectActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    };

    public View.OnClickListener eventBtnSubmit = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String siret = "";
            try {
                siret = API.getSiretFromRS(InputRaisonSocial.getText().toString());
            }catch (Exception e){

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
                JSONObject jsonBody = new JSONObject();
                jsonBody.put("Prospect", new JSONArray());
                JSONObject ProspectJson = new JSONObject();
                for (Prospect prospect : dataBase.readAllProspect()) {
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
                Log.v("Post Json",jsonBody.toString());
                Log.v("button", "Siret search button clicked");
                Log.v("Post status", "" + API.postProspect(jsonBody.toString()));
            } catch (Exception e) {
                Log.v("error", e.getMessage());
            }
            Intent connexion = new Intent(AddProspectActivity.this, AccueilActivity.class);
            startActivity(connexion);
        }
    };
}
