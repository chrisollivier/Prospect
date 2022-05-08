package sio.nsi.prospect.tools;

import android.os.NetworkOnMainThreadException;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import sio.nsi.prospect.model.Prospect;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class API {
    private static final String HTTP_ROUTS_PROSPECT = "http://86.207.48.28:8080/Prospect-API/backEnd/API/";
    private static final String HTTP_ROUTS_RAISONSOCIALE = "https://entreprise.data.gouv.fr/api/sirene/v1/full_text/";
    private static final String HTTP_ROUTS_USERAPP = "http://86.207.48.28:8080/Prospect-API/backEnd/API/";

    //API Prospect

    public static
    String getAllProspect() throws IOException, NetworkOnMainThreadException {
        URL url = new URL(HTTP_ROUTS_PROSPECT+"GET/AllProspect.php");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8))) {
            for (String line; (line = reader.readLine()) != null; ) {
                return line;
            }
        }
        return "Error";
    }

    public static
    int postProspect(String jsonBody) throws IOException {
        URL obj = new URL(HTTP_ROUTS_PROSPECT + "POST/OneProspect.php");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(jsonBody);
        wr.flush();
        wr.close();
        return con.getResponseCode();
    }

    //API USER

    public static
    String getAllUserApp() throws IOException, NetworkOnMainThreadException {
        URL url = new URL(HTTP_ROUTS_USERAPP+"GET/AllUser.php");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
            for (String line; (line = reader.readLine()) != null; ) {
                return line;
            }
        }
        return "Error";
    }

    public static
    int postUser(String jsonBody) throws IOException {
        URL obj = new URL(HTTP_ROUTS_PROSPECT + "POST/OneUser.php");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(jsonBody);
        wr.flush();
        wr.close();
        return con.getResponseCode();
    }

    //API SIRET

    public static String getDataFromText(String search) throws IOException, NetworkOnMainThreadException {
        Log.d("siret", "searching : " + search);
        URL url = new URL(HTTP_ROUTS_RAISONSOCIALE+search);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
            for (String line; (line = reader.readLine()) != null; ) {
                return line;
            }
        }
        return "couldn't find data";
    }

    public static String getSiretFromText(String search) throws IOException, NetworkOnMainThreadException {
        String data = getDataFromText(search);
        try {
            JSONObject jsonObject = new JSONObject(data);
            return jsonObject.getJSONArray("etablissement").getJSONObject(0).getString("siret");
        } catch (JSONException err) {
            return "couldn't find siret";
        }
    }

}
