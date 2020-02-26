public class NPC {
    //ennemis
    String name;
    String id = "NPC";
    String desc;
    int hp;
    int dexterite;
    int etage;
    
    public void look() {
        System.out.println(name);
        System.out.println("dexterite:" + dexterite);
        System.out.println("hp:" + hp);
    }
}