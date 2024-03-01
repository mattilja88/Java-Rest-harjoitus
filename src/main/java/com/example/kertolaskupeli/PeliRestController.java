package com.example.kertolaskupeli;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;


@RestController
public class PeliRestController {
    
    private List<Laskut> laskut;

    @GetMapping("/")
    public String Etusivu(){
        Random random = new Random();

        laskut = new ArrayList<>();

        for (int i = 0; i < 10; i++){
            int random_x = random.nextInt(10);
            int random_y = random.nextInt(10);
            laskut.add(new Laskut(random_x, random_y));
        }

        String sivu = "Terveituloa yksinkertaiseen kertolaskupeliin!<br><br>"
                    + "Katso tästä laskut ja vastaa niihin PostManin post-komennoilla osoitteelle localhost:8080/vastaukset.<br>"
                    + "Mene Valitse Body-->x-www-form-urlencodes--> Key esim. Vastaus1, Vastaus2 jne.. ja Valueen vastaus.<br><br>"
                    + "Oikeat vastaukset löydät osoitteesta localhost:8080/tulokset<br><br>"
                    + "Päivittämällä sivun saat uudet kertolaskut<br><br>";

        for (int i = 1; i <= 10; i++){
            String teksti = laskut.get(i-1).getLasku();
            sivu += "Lasku "+ i + ": &nbsp;&nbsp;&nbsp;" + teksti + "<br><br>";
        }

        return sivu;
    }

    @GetMapping("/tulokset")
    public String Tulokset(){

        String sivu = "Oikeat vastaukset:<br><br>";

        for (int i =1; i <= 10; i++){
            String teksti = laskut.get(i-1).getOikeatLaskus();
            sivu += "Lasku "+ i + ": &nbsp;&nbsp;&nbsp;" + teksti + "<br><br>";
        }

        return sivu;
    }

    @PostMapping("/vastaukset")
    public String vastaus(@RequestBody String vastaus){

        List<String> tulos = new ArrayList<>();
        List<String> vastaukset = new ArrayList<>();
        List<String> apu = new ArrayList<>();

        String[] jaettu = vastaus.split("&");
        for (String i : jaettu){
            apu.add(i);
        }

        for (String i : apu){
            String[] x = i.split("=");
            for ( String a : x){
                String numerot = "0123456789";
                if (numerot.contains(Character.toString(a.charAt(0)))){
                    vastaukset.add(a);
                }
            }
        }

        if (vastaukset.size() < laskut.size()) {
            while (vastaukset.size() <= laskut.size()){
                vastaukset.add("XX");
            }
        }

        for (int i = 0; i < laskut.size(); i++){
            if (vastaukset.get(i).equals(laskut.get(i).getVastaus())){
                tulos.add("Lasku "+(i+1)+" Oikein!");
            } else {
                tulos.add("Lasku "+(i+1)+" Väärin!");
            }
        }
        
        System.out.println(vastaukset);

        return tulos.toString();
    }

}
