package hra;

import java.util.ArrayList;

public class GeneratorObrazkov {
    private static ArrayList<String> zoznamObrazkov;

    public static ArrayList generujObrazok(String picture) {
        zoznamObrazkov = new ArrayList<String>();
        for (int i = 0; i < 4; i++) {
            String cestaKObrazku = picture + i + ".png";
            zoznamObrazkov.add(cestaKObrazku);
        }
        return zoznamObrazkov;
    }
}