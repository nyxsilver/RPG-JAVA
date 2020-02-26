public class Combat {

    public void attack(String[] x) {
        for( int i = 0; i < Game_Objects.salle.size(); i++) {
            if( Game_Objects.salle.get(i).number == Game_Objects.pc.etage) {
                for (int y = 0; y < Game_Objects.salle.get(i).npc.size(); y++) {
                    if (Game_Objects.salle.get(i).npc.get(y).id.equalsIgnoreCase(x[1])) {
                        int npc_hit = Game_Objects.rng.returnRandom(100);
                        npc_hit = npc_hit + (Game_Objects.salle.get(i).npc.get(y).dexterite / 2);
                        if(npc_hit > 50) {
                            int npc_damage = Game_Objects.rng.returnRandom(10);
                            Game_Objects.pc.hp = Game_Objects.pc.hp - npc_damage;
                            System.out.println(Game_Objects.salle.get(i).npc.get(y).name + " te tapes pour " + npc_damage);
                        }   
                        else {
                            System.out.println(Game_Objects.salle.get(i).npc.get(y).name + " te loupes");
                        }
                        int pc_hit = Game_Objects.rng.returnRandom(100);
                        pc_hit = npc_hit + (Game_Objects.salle.get(i).npc.get(y).dexterite / 2);
                        if (pc_hit > 50) {
                            int pc_damage = Game_Objects.rng.returnRandom(10);
                            Game_Objects.salle.get(i).npc.get(y).hp = Game_Objects.salle.get(i).npc.get(y).hp - pc_damage;
                            System.out.println("tu tapes pour " + pc_damage);
                            if (Game_Objects.salle.get(i).npc.get(y).hp <= 0) {
                                npc_death(i,y);
                            }
                        }
                        else {
                            System.out.println("LOUPE!!!!");
                        }
                    }  
                }
            }
        }
    }

    public void npc_death(int i, int y) {
        System.out.println(Game_Objects.salle.get(i).npc.get(y).name + " est mort");
        Game_Objects.salle.get(i).npc.remove(y);
    }
}