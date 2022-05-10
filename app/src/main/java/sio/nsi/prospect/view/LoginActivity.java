package sio.nsi.prospect.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
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

public class LoginActivity extends AppCompatActivity {
    private DataBaseHelper dataBase;
    private EditText InputLogin, InputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);
        dataBase = new DataBaseHelper(this);
        InputLogin = (EditText) findViewById(R.id.inputLogin);
        InputPassword = (EditText) findViewById(R.id.inputPassword);
        Button btnLogin = (Button) findViewById(R.id.BtnLogin);
        btnLogin.setOnClickListener(eventBtnLogin);
        Button btnSignIn = findViewById(R.id.BtnSignIn);
        btnSignIn.setOnClickListener(eventBtnSignIn);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Log.v("API Status","" + getAllDataFromAPI());
    }

    public View.OnClickListener eventBtnLogin = v -> {

            User user = new User(InputLogin.getText().toString(), InputPassword.getText().toString());
            ArrayList<User> allUser = dataBase.readUserFormUser(user);
            try {
                if (user.getPassword() != null && allUser.get(0).getPassword() != null && user.getPassword().equals(allUser.get(0).getPassword())) {
                    Log.v("connexion", "Connexion effectuée : " + allUser.get(0).getPassword());
                    Intent connexion = new Intent(LoginActivity.this, AccueilActivity.class);
                    startActivity(connexion);
                } else {
                    InputPassword.setError("Password and username didn't match");
                    Log.v("Error", "connection failed");
                }
            } catch (Exception e) {
                Log.v("Error", "connection failed" + e);
                InputPassword.setError("Password and username didn't match");
            }
    };

    public View.OnClickListener eventBtnSignIn = v -> {
        Intent connexion = new Intent(LoginActivity.this, SignInActivity.class);
        startActivity(connexion);
    };

    private boolean getAllDataFromAPI(){
        try {
            String JsonUser = API.getAllUserApp();
            for (int i = 0; i < User.getJsonArraySize(JsonUser); i++) {
                if (dataBase.readNumberUserFromMail(User.getAppUserMail(JsonUser, i)) == 0) {
                    dataBase.addNewUser(new User(
                            User.getAppUserMail(JsonUser, i),
                            User.getAppUserPassword(JsonUser, i),
                            User.getAppUserNom(JsonUser, i),
                            User.getAppUserPrenom(JsonUser, i)
                    ));
                }
            }
            String JsonProspect = API.getAllProspect();
            for (int i = 0; i < Prospect.getJsonArraySize(JsonProspect); i++) {
                if (dataBase.readNumberProspectFromNomPrenomSiret(Prospect.getProscpectNom(JsonProspect, i), Prospect.getProscpectPrenom(JsonProspect, i), Prospect.getProscpectSiret(JsonProspect, i)) == 0) {
                    dataBase.addNewProspect(new Prospect(
                            Prospect.getProscpectNom(JsonProspect, i),
                            Prospect.getProscpectPrenom(JsonProspect, i),
                            Prospect.getProscpectSiret(JsonProspect, i),
                            Prospect.getProscpectRaisonSocial(JsonProspect, i),
                            Prospect.getProscpectScore(JsonProspect, i),
                            Prospect.getProscpectMail(JsonProspect, i),
                            Prospect.getProscpectTel(JsonProspect, i)
                    ));
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}