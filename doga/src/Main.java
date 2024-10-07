import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // 2. feladat
        List<Bejegyzes> bejegyzesek = new ArrayList<>();
        bejegyzesek.add(new Bejegyzes("Szerzo1", "Első bejegyzés"));
        bejegyzesek.add(new Bejegyzes("Szerzo2", "Második bejegyzés"));

        int bejegyzesszam;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Add meg a bejegyzések számát: ");
        bejegyzesszam = Integer.parseInt(scanner.nextLine());
        if (bejegyzesszam <= 0) {
            throw new NumberFormatException();
        }

        for (int i = 0; i < bejegyzesszam; i++) {
            System.out.print("Add meg a szerző nevét: ");
            String szerzo = scanner.nextLine();
            System.out.print("Add meg a bejegyzés tartalmát: ");
            String tartalom = scanner.nextLine();
            bejegyzesek.add(new Bejegyzes(szerzo, tartalom));
        }

        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Diák\\IdeaProjects\\doga\\src\\bejegyzesek.csv"))) {
            String sor;
            while ((sor = br.readLine()) != null) {
                String[] adatok = sor.split(";");
                bejegyzesek.add(new Bejegyzes(adatok[0], adatok[1]));
            }
        } catch (IOException e) {
            System.out.println("Hiba a fájl beolvasásakor");
        }

        Random random = new Random();
        int likeokSzama = bejegyzesek.size() * 20;
        for (int i = 0; i < likeokSzama; i++) {
            int randomIndex = random.nextInt(bejegyzesek.size());
            bejegyzesek.get(randomIndex).like();
        }

        System.out.println("Adja meg mire szeretne modositani a masodik szoveget:");
        String modosit = scanner.nextLine();
        bejegyzesek.get(1).setTartalom(modosit);


        for (Bejegyzes bejegyzes : bejegyzesek) {
            System.out.println(bejegyzes.getSzerzo());
            System.out.println(bejegyzes.getTartalom());
        }

        // 3. feladat
        int legtobblike = 0;
        int hely = 0;
        for (int j = 0; j < bejegyzesek.size(); j++) {
            if (bejegyzesek.get(j).getLikeok() > legtobblike ) {
                legtobblike = bejegyzesek.get(j).getLikeok();
                hely = j;
            }
        }
        System.out.println("A Legtobb Like:");
        System.out.println(bejegyzesek.get(hely).getSzerzo());
        System.out.println(bejegyzesek.get(hely).getTartalom());

        int sok = 0;
        for (int i = 0; i < bejegyzesek.size(); i++) {
            if (bejegyzesek.get(i).getLikeok() > 34 ) {
                sok += 1;
            }
        }
        System.out.println("Tobb mint 35 like:");
        System.out.println(sok);


        int kevesebb = 0;
        for (int i = 0; i < bejegyzesek.size(); i++) {
            if (bejegyzesek.get(i).getLikeok() < 15 ) {
                kevesebb++;
            }
        }
        System.out.println("Kevesebb minnt 15 like:");
        System.out.println(kevesebb);
    }
}