package sio.nsi.prospect.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import sio.nsi.prospect.R;
import sio.nsi.prospect.model.User;
import sio.nsi.prospect.tools.DataBaseHelper;

import java.util.ArrayList;
import java.util.Locale;

public class LoginActivity extends AppCompatActivity {
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
    }
    public View.OnClickListener eventBtnlogin = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


           // User tempUser = new User("admin@gmail.com","yo","AdminUser","AdminUser");
           // dataBase.addNewUser(tempUser);


            User user = new User(InputLogin.getText().toString(),InputPassword.getText().toString());
            ArrayList<User> allUser = dataBase.readUser(user);


            Log.d("Comparaison", "-" + allUser.get(0).getPassword() + "- vs -" + user.getPassword() + "-");



            if (user.getPassword().equals(allUser.get(0).getPassword())){
                Log.d("connexion", "Connexion effectuée : " + allUser.get(0).getPassword());
                Intent connexion = new Intent(LoginActivity.this, AccueilActivity.class);
                startActivity(connexion);
            }else{
                Log.d("Error","connection failed");
            }
        }
    };
}