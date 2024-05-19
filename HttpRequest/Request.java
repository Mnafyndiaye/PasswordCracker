package HttpRequest;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Request {
       //la méthode qui va permettre d'envoyer les requêtes
    public static int requete(String password) throws Exception{

        String loginUrl = "http://localhost/FormulairePhp/formulaire.php";

        //fixer le login
        String login = "nafy";

        String postData = "login=" + login + "&password=" + password;
        byte[] postDataBytes = postData.getBytes(StandardCharsets.UTF_8);

        try {

            URL url = new URL(loginUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);

            int responseCode = conn.getResponseCode();

            
            if (responseCode == HttpURLConnection.HTTP_OK) {

                Scanner responseScanner = new Scanner(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();

                while (responseScanner.hasNextLine()) {

                    response.append(responseScanner.nextLine());

                }
                
                String message = response.toString();
                if (message.trim().equalsIgnoreCase("ConnexionReussie")) {

                    System.out.println(message);
                    responseScanner.close();
                    conn.disconnect();
                    return 5;
                } 
                
            }

            conn.disconnect();
            return 1;

        }catch (Exception e) {

            throw e;
            
        }
    }
}
