package sio.nsi.prospect.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import sio.nsi.prospect.R;
import sio.nsi.prospect.tools.DataBaseHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AddProspectActivity extends AppCompatActivity {
    private static EditText InputSiret;
    private Button BtnSiret;
    private static HttpURLConnection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addprospect_activity);
        BtnSiret = (Button) findViewById(R.id.BtnSiret);
        InputSiret = (EditText) findViewById(R.id.inputSiret);


        BtnSiret.setOnClickListener(eventBtnsiret);
    }

    public View.OnClickListener eventBtnsiret = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d("button", "Siret search button clicked");
            try {
                Log.d("siret", getDataFromSiret(InputSiret.getText().toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    public static String getDataFromSiret(String search) throws IOException {
        if (search.length() == 14) {
            URL url = new URL("https://entreprise.data.gouv.fr/api/sirene/v1/siret/" + search);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            if (connection.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
            } else {
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String output;
                StringBuilder sb = new StringBuilder();
                while ((output = br.readLine()) != null) {
                    sb.append(output);
                }
                connection.disconnect();
                return sb.toString();
            }
        } else {
            InputSiret.setError("Longueur du siret incorrecte");
            return "";
        }
    }
}
