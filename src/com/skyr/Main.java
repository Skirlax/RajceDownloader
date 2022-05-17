package com.skyr;

public class Main {

    public static void main(String[] args) {
        SavePhotos photos = new SavePhotos();
        HandleInput handleInput = new HandleInput();

        String[] credentials = handleInput.ReadInput();
        String expression_or_url = handleInput.MakeExpression(credentials);
        if (expression_or_url.equals("")) {
            photos.send_payload("", credentials[0], false, credentials[credentials.length - 1]);
        } else {

            photos.send_payload(expression_or_url, credentials[0], true, credentials[credentials.length - 1]);
        }
    }


}
