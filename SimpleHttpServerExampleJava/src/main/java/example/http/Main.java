package example.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        //"Tworzymy Socket na naszym komputerze ktory bedzie nasluchiwal na porcie 8080"
        ServerSocket socket = new ServerSocket(8080);
        Socket client = socket.accept();

        //czekamy na wiadomosc od klienta
        InputStream inputStream = client.getInputStream();
        //wysylamy wiadomosc do klienta
        OutputStream outputStream = client.getOutputStream();


        System.out.println(getRequestFromClient(inputStream));

    }

    public static StringBuilder getRequestFromClient(InputStream inputStream) throws IOException {
        int _byte;
        int CR = 0x0D; // 13 (carriage return)
        int LF = 0x0A; // 10 (new line)
        StringBuilder stringBuilder = new StringBuilder();

        while ((_byte=inputStream.read())>=0){

            if(_byte!=CR) {
                stringBuilder.append((char) _byte);
                continue;
            }

            //_byte == CR
            stringBuilder.append((char) _byte);
            _byte = inputStream.read();

            if(_byte != LF){
                stringBuilder.append((char) _byte);
                continue;
            }

            //_byte == LF
            stringBuilder.append((char) _byte);
            _byte = inputStream.read();

            if(_byte!=CR) {
                stringBuilder.append((char) _byte);
                continue;
            }

            //_byte == CR ( oznaka ze zaczela sie pusta linia)
            return stringBuilder;
        }

        return stringBuilder;
    }

}