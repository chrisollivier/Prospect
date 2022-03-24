package sio.nsi.prospect.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import sio.nsi.prospect.R;

public class LoginActivity extends AppCompatActivity {
    private Button Btnlogin;
    private EditText InputLogin;
    private EditText InputPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);
        InputLogin = (EditText) findViewById(R.id.inputLogin);
        InputPassword = (EditText) findViewById(R.id.inputLogin);
        Btnlogin = (Button) findViewById(R.id.BtnLogin);
        Btnlogin.setOnClickListener(eventBtnlogin);
    }
    public View.OnClickListener eventBtnlogin = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent connexion = new Intent(LoginActivity.this, AccueilActivity.class);
            startActivity(connexion);
        }
    };


}
