package sio.nsi.prospect.tools;

import android.accounts.NetworkErrorException;
import android.os.NetworkOnMainThreadException;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class APISiret {

    private static final String UrlAPI_Siret = "https://entreprise.data.gouv.fr/api/sirene/v1/siret/";
    private static final String UrlAPI_RaisonSociale = "https://entreprise.data.gouv.fr/api/sirene/v1/full_text/";

    public String getUrlAPI_Siret() {
        return UrlAPI_Siret;
    }

    public String getUrlAPI_RaisonSociale() {
        return UrlAPI_RaisonSociale;
    }

    public static String getDataFromText(String search) throws IOException, NetworkOnMainThreadException {
        Log.d("siret", "searching : " + search);
        URL url = new URL(UrlAPI_RaisonSociale+search);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
            for (String line; (line = reader.readLine()) != null; ) {
                return line;
            }
        }
        return "Error";
    }

    public String getDataFromSiret(String search) throws IOException {
        URL url = new URL(UrlAPI_Siret+search);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
            for (String line; (line = reader.readLine()) != null; ) {
                return line;
            }
        }
        return "Error";
    }
}
