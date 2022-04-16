package sio.nsi.prospect.view;

import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.os.StrictMode;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import org.w3c.dom.Text;
import sio.nsi.prospect.R;
import sio.nsi.prospect.tools.APISiret;

public class AddProspectActivity extends AppCompatActivity {
    private TextView SiretOutput;
    private TextView RSOutput;
    private EditText InputRS;
    private Button BtnSiret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addprospect_activity);
        BtnSiret = (Button) findViewById(R.id.BtnSiret);
        InputRS = (EditText) findViewById(R.id.inputRS);
        SiretOutput = (TextView) findViewById(R.id.siretOutput);

        BtnSiret.setOnClickListener(eventBtnsiret);

        // Permet de désactiver les restrictions de networkonmain
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }

    public View.OnClickListener eventBtnsiret = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d("button", "Siret search button clicked");
            try {
                Log.d("siret", APISiret.getDataFromText(InputRS.getText().toString()));
                SiretOutput.setText(APISiret.getSiretFromText(InputRS.getText().toString()));
                RSOutput.setText(APISiret.getRSFromText(InputRS.getText().toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
}
