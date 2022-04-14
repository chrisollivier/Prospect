package sio.nsi.prospect.tools;

import android.os.NetworkOnMainThreadException;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class APIUser {
    private static final String HTTP_ROUTS_USERAPP = "http://86.207.48.28:8080/Prospect-API/backEnd/API/";
    private static final String HTTP_ROUTS_PROSPECT = "http://86.207.48.28:8080/Prospect-API/backEnd/API/";


    public static String getAllUserApp() throws IOException, NetworkOnMainThreadException {
        Log.d("siret", "searching ");
        URL url = new URL(HTTP_ROUTS_USERAPP+"GETAllUser.php");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
            for (String line; (line = reader.readLine()) != null; ) {
                return line;
            }
        }
        return "Error";
    }

    public static int getJsonArraySize(String data)throws IOException{
        try {
            JSONObject jsonObject = new JSONObject(data);
            return jsonObject.getJSONArray("UserApp").length();
        }catch (JSONException err){
            return 0;
        }
    }

    public static String getAppUserMail(String data,int i) throws IOException {
        try {
            JSONObject jsonObject = new JSONObject(data);
            return jsonObject.getJSONArray("UserApp").getJSONObject(i).getString("email");
        }catch (JSONException err){
            return "";
        }
    }

    public static String getAppUserPassword(String data,int i) throws IOException {
        try {
            JSONObject jsonObject = new JSONObject(data);
            return jsonObject.getJSONArray("UserApp").getJSONObject(i).getString("password");
        }catch (JSONException err){
            return "";
        }
    }

    public static String getAppUserNom(String data,int i) throws IOException {
        try {
            JSONObject jsonObject = new JSONObject(data);
            return jsonObject.getJSONArray("UserApp").getJSONObject(i).getString("nom");
        }catch (JSONException err){
            return "";
        }
    }

    public static String getAppUserPrenom(String data,int i) throws IOException {
        try {
            JSONObject jsonObject = new JSONObject(data);
            return jsonObject.getJSONArray("UserApp").getJSONObject(i).getString("prenom");
        }catch (JSONException err){
            return "";
        }
    }

}
