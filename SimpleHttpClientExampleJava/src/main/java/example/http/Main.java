package example.http;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {

        //wchodzimy w role klienta, wiec nawiazujemy handshaka i tworzymy polaczenie z podanym URL
        URL obj = new URL("https://discord.com/api/webhooks/1108811425553600593/WVNI2SCM9oORhVU5FaiAOozpNTQkahsetmwOreJDsO7-81W-2AraP7vbdNxoyeGT8Wx7");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //tworzymy teraz zapytanie HTTP
        //Do prawidlowego przetworzenia potrzebujemy wybrac Metode oraz w jakim formacie
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type","application/json");
        con.setDoOutput(true);

        OutputStream outputStream = con.getOutputStream();
        //tutaj musimy zamienic nasza odpowiedz na ciag bajtow, pamietajac ze ma byc to w formacie json

        //mozemy uzyc ObjectMappera, jest to bibloteka do zamiany obiektow,stringa na jsona i tez jest opcja na ciag bajtow
        ObjectMapper objectMapper = new ObjectMapper();
        JsonObjectExample exampleObject = new JsonObjectExample("Pawel","Krysko");

        outputStream.write(objectMapper.writeValueAsBytes(exampleObject));
        outputStream.flush();
        outputStream.close();

    }
}