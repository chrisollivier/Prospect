package sio.nsi.prospect.view;

import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.util.Log;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import sio.nsi.prospect.R;
import sio.nsi.prospect.tools.APISiret;

public class AddProspectActivity extends AppCompatActivity {
    private EditText InputRS;
    private Button BtnSiret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addprospect_activity);
        BtnSiret = (Button) findViewById(R.id.BtnSiret);
        InputRS = (EditText) findViewById(R.id.inputRS);

        BtnSiret.setOnClickListener(eventBtnsiret);
    }

    public View.OnClickListener eventBtnsiret = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d("button", "Siret search button clicked");
            try {
                Log.d("siret", APISiret.getDataFromText(InputRS.getText().toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

}
