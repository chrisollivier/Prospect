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
    void createProspect(Prospect prospect) throws IOException {
        String url = "http://86.207.48.28:8080/Prospect-API/backEnd/API/POST/OneProspect.php";
        String data = "{\"nom\":\""+prospect.getNom()+"\",\"prenom\":\""+prospect.getPrenom()+"\",\"siret\":\""+prospect.getSiret()+"\",\"raisonsocial\":\""+prospect.getRaisonSociale()+"\",\"mail\":\""+prospect.getMail()+"\",\"tel\":\""+prospect.getTel()+"\"}";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes("{\"Prospect\":[{\"id\":1,\"nom\":\"yo\",\"prenom\":\"theBrave\",\"siret\":\"14953772159654\",\"score\":4,\"raisonsocial\":\"UwU\",\"mail\":\"gorgeTheBrave69@gmail.com\",\"tel\":\"0436936900\"}]}");
        wr.flush();
        wr.close();
        int responseCode = con.getResponseCode();
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

    public static void PostAllUserApp(String body) throws IOException, NetworkOnMainThreadException{
        // URL url = new URL ();
        // HttpURLConnection con = (HttpURLConnection) url.openConnection();
        // con.setRequestMethod("POST");
        // con.setRequestProperty("Content-Type", "application/json; utf-8");
        // con.setRequestProperty("Accept", "application/json");
        // con.setDoOutput(true);
        // try(OutputStream os = con.getOutputStream()) {
        //     byte[] input = body.getBytes("utf-8");
        //     os.write(input, 0, input.length);
        // }
        // con.connect();

        // URL url = new URL(HTTP_ROUTS_USERAPP+"POST/OneUser.php");
        // HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // conn.setConnectTimeout(5000);
        // conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        // conn.setDoOutput(true);
        // conn.setDoInput(true);
        // conn.setRequestMethod("POST");

        // OutputStream os = conn.getOutputStream();
        // os.write("{\"UserApp\":[{\"id\":1,\"email\":\"UserAdmin\",\"password\":\"UserAdmin\",\"nom\":\"yo\",\"prenom\":\"UserAdmin\"}]}".getBytes("UTF-8"));
        // os.close();
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
