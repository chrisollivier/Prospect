package sio.nsi.prospect.tools;

import android.accounts.NetworkErrorException;
import android.os.NetworkOnMainThreadException;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

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
        return "couldn't find data";
    }

    public static String getDataFromSiret(String search) throws IOException {
        URL url = new URL(UrlAPI_Siret+search);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
            for (String line; (line = reader.readLine()) != null; ) {
                return line;
            }
        }
        return "Error";
    }

    public static String getSiretFromData(String data) throws IOException {
        try {
            JSONObject jsonObject = new JSONObject(data);
            return jsonObject.getJSONArray("etablissement").getJSONObject(0).getString("siret");
        }catch (JSONException err){
            return "couldn't find siret";
        }
    }

    public static String getSiretFromText(String search) throws IOException, NetworkOnMainThreadException {
        Log.d("siret", "searching : " + search);
        URL url = new URL(UrlAPI_RaisonSociale+search);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
            for (String line; (line = reader.readLine()) != null; ) {
                try {
                    JSONObject jsonObject = new JSONObject(line);
                    return jsonObject.getJSONArray("etablissement").getJSONObject(0).getString("siret");
                }catch (JSONException err){
                    return "couldn't find siret";
                }
            }
        }
        return "couldn't find data";
    }

    public static String getRSFromText(String search) throws IOException {
        String data = getDataFromSiret(search);
        try {
            JSONObject jsonObject = new JSONObject(data);
            return jsonObject.getJSONArray("etablissement").getJSONObject(0).getString("l1_normalisee");
        }catch (JSONException err){
            return "couldn't find raison sociale";
        }
    }
}
