package com.skyr;

import java.util.Scanner;

public class HandleInput {
    public String MakeExpression(String[] credentials) {
        try {
            String username = credentials[1];
            String password = credentials[2];
            return "login=" + username + "&code=" + password;
        }
        catch (Exception e){
            return "";

        }



    }
    public String[] ReadInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Url alba: ");
        String url = scanner.nextLine();
        System.out.print("Do jaké složky chcete obrázky uložit? (Bez '/' na konci!): ");
        String outputFolder = scanner.nextLine();
        System.out.print("Je album soukromé? (y/n): ");
        String private_album = scanner.nextLine();
        if (private_album.equals("y")) {
            System.out.print("Jméno: ");
            String username = scanner.nextLine();
            System.out.print("Kód: ");
            String password = scanner.nextLine();
            return new String[]{url, username, password, outputFolder};
        }
        else {
            return new String[]{url, outputFolder};
        }

    }
}
