package sio.nsi.prospect.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.os.StrictMode;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import org.w3c.dom.Text;
import sio.nsi.prospect.R;
import sio.nsi.prospect.model.Prospect;
import sio.nsi.prospect.tools.APIProspect;
import sio.nsi.prospect.tools.APISiret;
import sio.nsi.prospect.tools.DataBaseHelper;

import java.io.IOException;

public class AddProspectActivity extends AppCompatActivity {
    private TextView SiretOutput;
    private DataBaseHelper dataBase;
    private TextView RSOutput;
    private EditText InputRS;
    private Button BtnSiret;
<<<<<<< Updated upstream
    private EditText inputFName;
    private EditText inputLName;
    private EditText inputTel;
    private EditText inputMail;
    private EditText inputNotes;
    private Button BtnSubmit;
=======
    private Button back_button;
    private Button btnLogout;
>>>>>>> Stashed changes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBase = new DataBaseHelper(this);
        setContentView(R.layout.addprospect_activity);
        BtnSiret = (Button) findViewById(R.id.BtnSiret);
        BtnSubmit = (Button) findViewById(R.id.button_createProspect);
        InputRS = (EditText) findViewById(R.id.inputRS);
        SiretOutput = (TextView) findViewById(R.id.siretOutput);
        inputFName = (EditText) findViewById(R.id.inputFName);
        inputLName = (EditText) findViewById(R.id.inputLName);
        inputTel = (EditText) findViewById(R.id.inputTel);
        inputMail = (EditText) findViewById(R.id.inputMail);
        inputNotes = (EditText) findViewById(R.id.inputNotes);
        BtnSubmit.setOnClickListener(eventBtnSubmit);
        BtnSiret.setOnClickListener(eventBtnsiret);
        ImageView back_button =findViewById(R.id.back_button);
        back_button.setOnClickListener(backbutton);

        ImageView btnLogout =findViewById(R.id.btnLogout);
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

    public View.OnClickListener eventBtnsiret = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d("button", "Siret search button clicked");
            try {
                SiretOutput.setText(APISiret.getSiretFromText(InputRS.getText().toString()));
                RSOutput.setText(APISiret.getRSFromText(InputRS.getText().toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    public View.OnClickListener eventBtnSubmit = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Prospect prospect = new Prospect(inputFName.getText().toString(),inputLName.getText().toString(),SiretOutput.getText().toString(),InputRS.getText().toString(),Integer.parseInt(inputNotes.getText().toString()) ,inputMail.getText().toString(),inputTel.getText().toString());
            dataBase.addNewProspect(prospect);
            try {
                APIProspect.createProspect(prospect);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Intent connexion = new Intent(AddProspectActivity.this, AccueilActivity.class);
            startActivity(connexion);
        }
    };
}
