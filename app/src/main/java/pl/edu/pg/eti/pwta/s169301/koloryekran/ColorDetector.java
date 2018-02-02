package pl.edu.pg.eti.pwta.s169301.koloryekran;


import android.graphics.Bitmap;
import android.graphics.Color;

import java.util.ArrayList;

public class ColorDetector {

    private int bialy, czarny, czerwony, zielony, zolty,rozowy,pomaranczowy,niebieski= 0;

    ArrayList<Kolorowo> kolory = new ArrayList<>();


    public String detectColor(Bitmap bmp, int startX, int endX, int startY, int endY) {

        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                int rgb;
                try {
                    rgb = bmp.getPixel(x, y);
                } catch (IllegalArgumentException ex) {
                    return "";
                }

                float hsb[] = new float[3];
                int r = Color.red(rgb);
                int b = Color.blue(rgb);
                int g = Color.green(rgb);

                Color.RGBToHSV(r, g, b, hsb);

                if (hsb[1] < 0.2 && hsb[2] > 0.82)
                    bialy += 1;
                else if (hsb[2] < 0.2 && hsb[1] < 0.2 && hsb[0] < 0.2)
                    czarny +=1;
                else {
                    float deg = hsb[0];
                    if (deg >= 0 && deg < 15)
                        czerwony+=1;
                    else if (deg >= 15 && deg < 40)
                        pomaranczowy +=1;
                    else if (deg >= 40 && deg < 80)
                        zolty +=1;
                    else if (deg >= 80 && deg < 180)
                        zielony+=1;
                    else if (deg >= 180 && deg < 270)
                        niebieski+=1;
                    else if (deg >= 270 && deg < 320)
                        rozowy+=1;
                    else
                        czerwony+=1;
                }

            }
        }

        return getMaxColor();

    }

    private void addToList(){

        kolory.add(new Kolorowo("Biały",bialy));
        kolory.add(new Kolorowo("Czarny",czarny));
        kolory.add(new Kolorowo("Żółty",zolty));
        kolory.add(new Kolorowo("Pomarańczowy",pomaranczowy));
        kolory.add(new Kolorowo("Czerwony",czerwony));
        kolory.add(new Kolorowo("Różowy",rozowy));
        kolory.add(new Kolorowo("Niebieski",niebieski));
        kolory.add(new Kolorowo("Zielony",zielony));

    }

    private String getMaxColor() {


        addToList();
        int max = 0;
        String colorBest =" ";
        for(int i =0; i< kolory.size(); i++){
            Kolorowo aktualny = kolory.get(i);
            if(aktualny.getIlosc() > max){
                max = aktualny.getIlosc();
                colorBest = aktualny.getNazwa();
            }

        }
        return colorBest;
    }

 private static class Kolorowo {
        private String Nazwa;
        private int Ilosc;

        public Kolorowo(String n, int i){
            this.Nazwa = n;
            this.Ilosc= i;
        }

        public int getIlosc(){
            return this.Ilosc;
        }

        public String getNazwa(){
            return  this.Nazwa;
        }

 }


}

