package sio.nsi.prospect.tools;

import android.os.NetworkOnMainThreadException;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class APIUser {
    private static final String HTTP_ROUTS_USERAPP = "http://86.207.48.28:8080/Prospect-API/backEnd/API/";

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

    public static
    int getJsonArraySize(String data)throws IOException{
        try {
            JSONObject jsonObject = new JSONObject(data);
            return jsonObject.getJSONArray("UserApp").length();
        }catch (JSONException err){
            return 0;
        }
    }

    public static @NotNull
    String getAppUserMail(String data, int i) throws IOException {
        try {
            JSONObject jsonObject = new JSONObject(data);
            return jsonObject.getJSONArray("UserApp").getJSONObject(i).getString("email");
        }catch (JSONException err){
            return "";
        }
    }

    public static @NotNull
    String getAppUserPassword(String data, int i) throws IOException {
        try {
            JSONObject jsonObject = new JSONObject(data);
            return jsonObject.getJSONArray("UserApp").getJSONObject(i).getString("password");
        }catch (JSONException err){
            return "";
        }
    }

    public static @NotNull
    String getAppUserNom(String data, int i) throws IOException {
        try {
            JSONObject jsonObject = new JSONObject(data);
            return jsonObject.getJSONArray("UserApp").getJSONObject(i).getString("nom");
        }catch (JSONException err){
            return "";
        }
    }

    public static @NotNull
    String getAppUserPrenom(String data, int i) throws IOException {
        try {
            JSONObject jsonObject = new JSONObject(data);
            return jsonObject.getJSONArray("UserApp").getJSONObject(i).getString("prenom");
        }catch (JSONException err){
            return "";
        }
    }

}
