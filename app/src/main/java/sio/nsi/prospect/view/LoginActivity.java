package sio.nsi.prospect.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import sio.nsi.prospect.R;
import sio.nsi.prospect.model.User;
import sio.nsi.prospect.tools.APIProspect;
import sio.nsi.prospect.tools.DataBaseHelper;

import java.io.IOException;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    private DataBaseHelper dataBase;
    private Button Btnlogin;
    private EditText InputLogin;
    private EditText InputPassword;
    private APIProspect APIProspect;

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
            String JsonUser = APIProspect.getAllUserApp();
            System.out.println(JsonUser);
            Log.v("Json",JsonUser);
            Log.v("database",""+ dataBase.readNumberUserFromMail("UserAdmin"));
            for (int i = 0; i < APIProspect.getJsonArraySize(JsonUser); i++) {
                if (dataBase.readNumberUserFromMail(APIProspect.getAppUserMail(JsonUser,i)) == 0){
                    dataBase.addNewUser(new User(
                            APIProspect.getAppUserMail(JsonUser,i),
                            APIProspect.getAppUserPassword(JsonUser,i),
                            APIProspect.getAppUserNom(JsonUser,i),
                            APIProspect.getAppUserPrenom(JsonUser,i)
                    ));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //your codes here
    }
    public View.OnClickListener eventBtnlogin = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            User user = new User(InputLogin.getText().toString(),InputPassword.getText().toString());
            ArrayList<User> allUser = dataBase.readUser(user);

            Log.d("Comparaison", "-" + allUser.get(0).getPassword() + "- vs -" + user.getPassword() + "-");


            if ( user.getPassword() != null && allUser.get(0).getPassword() != null && user.getPassword().equals(allUser.get(0).getPassword())){
                Log.d("connexion", "Connexion effectuée : " + allUser.get(0).getPassword());
                Intent connexion = new Intent(LoginActivity.this, AccueilActivity.class);
                startActivity(connexion);
            }else{
                InputPassword.setError("Password and username didn't match");
                Log.d("Error","connection failed");
            }
        }
    };
}