package com.colorify.colorify;

import com.platform.core.utility.Logger;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import static org.junit.jupiter.api.Assertions.*;

class ColorifyApplicationTest {
    @Test
    public void testApplicationHello() {
        String host = "localhost";
        int port = 8080;

        String targetURL = "http://" + host + ":" + port + "/hello";

        StringBuilder stringBuilder = new StringBuilder();
        try {
            URL url = new URL(targetURL);
            URLConnection uc = url.openConnection();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            uc.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null)
                stringBuilder.append(inputLine);
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Logger.test(stringBuilder.toString());
    }

}