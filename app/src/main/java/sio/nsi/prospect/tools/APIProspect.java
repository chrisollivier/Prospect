package sio.nsi.prospect.tools;

import android.os.NetworkOnMainThreadException;

import org.jetbrains.annotations.NotNull;
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

public class APIProspect {
    private static final String HTTP_ROUTS_PROSPECT = "http://86.207.48.28:8080/Prospect-API/backEnd/API/";

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
    int getJsonArraySize(String data)throws IOException{
        try {
            JSONObject jsonObject = new JSONObject(data);
            return jsonObject.getJSONArray("Prospect").length();
        }catch (JSONException err){
            return 0;
        }
    }

    public static
    int getProscpectId(String data,int i) throws IOException {
        try {
            JSONObject jsonObject = new JSONObject(data);
            return jsonObject.getJSONArray("Prospect").getJSONObject(i).getInt("id");
        }catch (JSONException err){
            return 0;
        }
    }

    public static @NotNull
    String getProscpectNom(String data, int i) throws IOException {
        try {
            JSONObject jsonObject = new JSONObject(data);
            return jsonObject.getJSONArray("Prospect").getJSONObject(i).getString("nom");
        }catch (JSONException err){
            return "";
        }
    }

    public static @NotNull
    String getProscpectPrenom(String data, int i) throws IOException {
        try {
            JSONObject jsonObject = new JSONObject(data);
            return jsonObject.getJSONArray("Prospect").getJSONObject(i).getString("prenom");
        }catch (JSONException err){
            return "";
        }
    }

    public static @NotNull
    String getProscpectSiret(String data, int i) throws IOException {
        try {
            JSONObject jsonObject = new JSONObject(data);
            return jsonObject.getJSONArray("Prospect").getJSONObject(i).getString("siret");
        }catch (JSONException err){
            return "";
        }
    }

    public static @NotNull
    String getProscpectRaisonSocial(String data, int i) throws IOException {
        try {
            JSONObject jsonObject = new JSONObject(data);
            return jsonObject.getJSONArray("Prospect").getJSONObject(i).getString("raisonsocial");
        }catch (JSONException err){
            return "";
        }
    }

    public static
    int getProscpectScore(String data,int i) throws IOException {
        try {
            JSONObject jsonObject = new JSONObject(data);
            return jsonObject.getJSONArray("Prospect").getJSONObject(i).getInt("score");
        }catch (JSONException err){
            return -1;
        }
    }

    public static @NotNull
    String getProscpectMail(String data, int i) throws IOException {
        try {
            JSONObject jsonObject = new JSONObject(data);
            return jsonObject.getJSONArray("Prospect").getJSONObject(i).getString("mail");
        }catch (JSONException err){
            return "";
        }
    }

    public static @NotNull
    String getProscpectTel(String data,int i) throws IOException {
        try {
            JSONObject jsonObject = new JSONObject(data);
            return jsonObject.getJSONArray("Prospect").getJSONObject(i).getString("tel");
        }catch (JSONException err){
            return "";
        }
    }

    public static void createProspect(Prospect prospect) throws IOException {
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
}