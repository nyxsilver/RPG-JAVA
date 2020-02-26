import java.util.*;

public class Salle {
    int number = 0;
    String name = null;
    List<String> desc = new ArrayList<String>(); // description de la salle
    List<String> exits = new ArrayList<String>(); // les sorties
    List<NPC> npc = new ArrayList<NPC>(); // les monstres
    ArrayList<Item> item = new ArrayList<Item>(); // les items

    public Salle(int x) {
        number = x;
    }

    public Salle(int number, String name, List<String> desc, List<String> exits, List<NPC> npc, ArrayList<Item> item) {
        this.number = number;
        this.name = name;
        this.desc = desc;
        this.exits = exits;
        this.npc = npc;
        this.item = item;
    }
}