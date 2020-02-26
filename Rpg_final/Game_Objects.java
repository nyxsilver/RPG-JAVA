import java.util.*;

public class Game_Objects {
    static PC pc = new PC();
    static List<NPC> npc= new ArrayList<NPC>();
    static ArrayList<Salle> salle = new ArrayList<Salle>();
    static List<Object> NPCDataBase = new ArrayList<Object>();
    static List<Item> ItemDataBase = new ArrayList<Item>();
    static Combat Combat = new Combat();
    static RNG rng = new RNG();
    

    public static void initNPCArray() {
        NPCDataBase.add(new NPC());
        NPCDataBase.add(new Cactuar());
        NPCDataBase.add(new Behemoth());
        NPCDataBase.add(new Duck());
        NPCDataBase.add(new Nidhogg());
        NPCDataBase.add(new Byakko());
    }

    public static void ItemDataBase() {
        ItemDataBase.add(new Item());
        ItemDataBase.add(new EpeeEnBois());
        ItemDataBase.add(new FendoirTitania());
        ItemDataBase.add(new BraceletDeForce());
    }
}