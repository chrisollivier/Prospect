package sio.nsi.prospect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import sio.nsi.prospect.R;

public class LoginActivity extends AppCompatActivity {
    public Button Btnlogin = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);
        
        Btnlogin = (Button) findViewById(R.id.BtnLogin);
        Btnlogin.setOnClickListener(eventBtnlogin);
    }
    public View.OnClickListener eventBtnlogin = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent connexion = new Intent(LoginActivity.this, sio.nsi.prospect.AccueilActivity.class);
            startActivity(connexion);
        }
    };


}
