package sio.nsi.prospect.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import sio.nsi.prospect.R;
import sio.nsi.prospect.model.User;
import sio.nsi.prospect.tools.API;
import sio.nsi.prospect.tools.DataBaseHelper;
import java.io.IOException;
import java.util.ArrayList;


public class SignInActivity extends AppCompatActivity {
    private DataBaseHelper dataBase;
    private EditText InputLogin;
    private EditText InputPassword;
    private EditText InputLName;
    private EditText InputFName;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBase = new DataBaseHelper(this);
        setContentView(R.layout.signinactivity);
        InputLogin = (EditText) findViewById(R.id.inputLogin);
        InputPassword = (EditText) findViewById(R.id.inputPassword);
        InputLName = (EditText) findViewById(R.id.inputLName);
        InputFName = (EditText) findViewById(R.id.inputFName);
        Button btnCreate = findViewById(R.id.BtnCreate);
        btnCreate.setOnClickListener(eventBtnCreated);
        Button btnBackLogin = (Button) findViewById(R.id.BtnBackLogin);
        btnBackLogin.setOnClickListener(eventBtnBackLogin);
    }

    public View.OnClickListener eventBtnCreated = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!SignInActivity.isEmpty(InputFName)&&!SignInActivity.isEmpty(InputLName)&&!SignInActivity.isEmpty(InputLogin)&&!SignInActivity.isEmpty(InputPassword)) {
                dataBase.addNewUser(new User(
                        InputLogin.getText().toString(),
                        InputPassword.getText().toString(),
                        InputFName.getText().toString(),
                        InputLName.getText().toString()
                ));
                try {
                    JSONObject jsonBody = new JSONObject();
                    jsonBody.put("UserApp", new JSONArray());
                    JSONObject UserJson = new JSONObject();
                    for (User user : dataBase.readAllUser()) {
                        UserJson.put("id", user.getId());
                        UserJson.put("email", user.getEmail());
                        UserJson.put("password", user.getPassword());
                        UserJson.put("nom", user.getNom());
                        UserJson.put("prenom", user.getPrenom());
                        jsonBody.accumulate("UserApp", UserJson);
                    }
                    Log.d("json for POST", jsonBody.toString());
                    API.postUser(jsonBody.toString());
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }else {
                Toast.makeText(SignInActivity.this,"Des informations sont incompletes ou manquantes",Toast.LENGTH_LONG).show();
            }
        }
    };

    public View.OnClickListener eventBtnBackLogin = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent connexion = new Intent(SignInActivity.this, LoginActivity.class);
            startActivity(connexion);
        }
    };

    private static boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }
}
