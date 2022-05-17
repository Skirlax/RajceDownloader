package com.skyr;

import org.jsoup.Jsoup;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class SavePhotos {
    private int photoNumber = 1;

    SavePhotos (){





    }

    public void send_payload(String PayloadExpression, String url,boolean areCredentials, String outputFolder) {


        try {

            URL obj_url = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj_url.openConnection();
            connection.setReadTimeout(6000);
            connection.addRequestProperty("User-Agent","Mozilla");
            connection.addRequestProperty("Accept-Language","en-US,en;q=0.5");

            connection.setDoOutput(true);
            if (areCredentials) {
                OutputStreamWriter w = new OutputStreamWriter(connection.getOutputStream());
                w.write(PayloadExpression);
                w.close();
            }

            BufferedReader r = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = r.readLine()) != null) {
                response.append(line);
            }
            r.close();
            connection.disconnect();


            LocateInHTML(response.toString(), outputFolder);




        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    private void SaveFullImage(URL url, String outputFolder) {
        try {
            BufferedImage image = ImageIO.read(url);
            ImageIO.write(image, "jpg", new File(outputFolder+"/image" + photoNumber + ".jpg"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        photoNumber++;
    }


    private void LocateInHTML(String response, String outputFolder) {
        try {
            var doc = Jsoup.parse(response.toString());
//            System.out.println(response.toString());
            // find divs by id: thumbs-container
            var divs = doc.getElementById("thumbs-container");
            assert divs != null;
            var divs_in = divs.getElementsByClass("thumb-img-wrapper");
            for (var div : divs_in) {
                var img = div.getElementsByTag("img");
                var src = img.attr("abs:src");
                String FullURL_str = src.replace("thumb", "images");
                URL FullURL = new URL(FullURL_str);
                SaveFullImage(FullURL, outputFolder);
            }
        }
        catch (IOException ignored){

            }


    }


    }


