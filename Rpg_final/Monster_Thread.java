import java.util.*;
import java.io.*;

public class Monster_Thread {
    Game_Logic currentGL;

    public Monster_Thread(Game_Logic gl) {
        currentGL = gl;
    }

    public void startMonsterThread() {
        Thread one = new Thread() {
            public void run() {
                try {
                    while (true) {
                        //System.out.println("Quelque chose approche");
                        populateGame();
                        Thread.sleep(1000000); //rappelle la fonction 
                        //System.out.println("Quelque chose approche de nouveau");
                    }
                } catch (InterruptedException v) {
                    System.out.println(v);
                }
            }
        };
        one.start();
    }

    public void populateGame() {
        int roomMobCount = 0;
        List<String> lines = new ArrayList<>();
        try {
            lines = currentGL.readLines("TextFiles/MonstreLoc.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < lines.size(); i++) {
            String[] words = lines.get(i).split(" ");       // separation par l'espace
            if(words[0].equals("Name:")) {              // 1er mot = nom:
                for (int y = 0; y < Game_Objects.salle.size(); y++) {   
                    if(Game_Objects.salle.get(y).number == Integer.parseInt(words[2])) { // si le nom du monstre ce trouve bien dans la bonne salle 
                        for(int z = 0; z < Game_Objects.salle.get(y).npc.size(); z++) {
                            if(Game_Objects.salle.get(y).npc.get(z).id.equalsIgnoreCase(words[1])) {  // ignore le premier mot ("name")
                                //roomMobCount++; // implementation
                            }
                        }
                    }
                }
                if ( roomMobCount == 0) {
                    for( int y = 0; y < Game_Objects.salle.size(); y++) {
                        if (Game_Objects.salle.get(y).number == Integer.parseInt(words[2])) {
                            try {
                                Game_Objects.salle.get(y).npc.add((NPC) Class.forName(words[1]).newInstance());
                            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
            roomMobCount = 0;
        }
    }
}