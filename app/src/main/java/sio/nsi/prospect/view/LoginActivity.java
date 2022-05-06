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

public class LoginActivity extends AppCompatActivity{
    private DataBaseHelper dataBase;
    private Button Btnlogin;
    private EditText InputLogin;
    private EditText InputPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBase = new DataBaseHelper(this);
        setContentView(R.layout.loginactivity);
        InputLogin = (EditText) findViewById(R.id.inputLogin);
        InputPassword = (EditText) findViewById(R.id.inputPassword);
        Btnlogin = (Button) findViewById(R.id.BtnLogin);
        Btnlogin.setOnClickListener(eventBtnlogin);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("UserApp",new JSONArray());
            JSONObject UserJson = new JSONObject();
            ArrayList<User> userToConvert = dataBase.readAllUser();
            for (int i = 0; i < dataBase.readAllUser().size(); i++) {
                UserJson.put("id",userToConvert.get(i).getId());
                UserJson.put("email",userToConvert.get(i).getEmail());
                UserJson.put("password",userToConvert.get(i).getPassword());
                UserJson.put("nom","yo");
                UserJson.put("prenom",userToConvert.get(i).getPrenom());
                jsonBody.accumulate("UserApp",UserJson);
            }
            Log.d("json for POST",jsonBody.toString());
            API.PostAllUserApp(jsonBody.toString());
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }


    }

    public View.OnClickListener eventBtnlogin = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            User user = new User(InputLogin.getText().toString(), InputPassword.getText().toString());
            ArrayList<User> allUser = dataBase.readUserFormUser(user);

            if (user.getPassword() != null && allUser.get(0).getPassword() != null && user.getPassword().equals(allUser.get(0).getPassword())) {
                Log.d("connexion", "Connexion effectuée : " + allUser.get(0).getPassword());
                Intent connexion = new Intent(LoginActivity.this, AccueilActivity.class);
                startActivity(connexion);
            } else {
                InputPassword.setError("Password and username didn't match");
                Log.d("Error", "connection failed");
            }
        }
    };

    //@Override
    //protected void onStop() {
    //    super.onStop();
    //
//
    //}
}