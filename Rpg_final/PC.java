import java.util.*;

public class PC {
    //joueur
    String name;
    int hp;
    int dexterite;
    int etage = 0;
  public  ArrayList<Item> item = new ArrayList<Item>(); //item standard
    public  ArrayList<Item> itemUsee = new ArrayList<Item>(); // item utilisé

    public void look() {
        System.out.println("hp:" + hp);
        System.out.println("dexterite:" + dexterite);
    }

    public void removeItem(String x) {
        for(int i = 0; i < itemUsee.size(); i++) { 
            if(itemUsee.get(i).id.equalsIgnoreCase(x)){    //verif l'id = item taper
                System.out.println("tu enleves " + itemUsee.get(i).id);
                item.add(itemUsee.get(i));  // ajout item inventaire
                itemUsee.remove(i);     // retirer
            }
        }
    }

    public void vu(){
        for(int i = 0; i < itemUsee.size(); i++) { //boucle dans item utilisé
            System.out.println(itemUsee.get(i).name + ":" + itemUsee.get(i).slot); //enumeration de tous item
        }
    }

    public void porter(String[] x) {
        if (itemUsee.size() == 0) {
            for (int i = 0; i < item.size(); i++) { //liste d'item a mettre dans l'inventaire
                if (x[1].equalsIgnoreCase(item.get(i).id) && item.get(i).isportable){ // si l'item est egale a l'item avec l'id si il est portable
                    itemUsee.add(item.get(i)); // ajout item porter
                    System.out.println("tu portes " + item.get(i).name);
                    item.remove(i); // retire l'item de l'inventaire
                    break;
                }
            }
        }
        else {
            boolean isportable = false;
            for (int i = 0; i < itemUsee.size(); i++) {
                for(int y = 0; y < item.size(); y++) {
                    if(x[1].equalsIgnoreCase(item.get(y).id) && item.get(y).isportable){ //si l'item est egale a l'id de ce qu'on a taper et si il est portable
                        if(item.get(y).slot.equals(itemUsee.get(i).slot)) { // si l'article peux etre mis au slot prevu
                            System.out.println("tu as deja quelque chose porte à l'endroit");
                            isportable = true;
                        }
                    }
                }
                if(isportable) { //si tu peux le porter ou non
                    itemUsee.add(item.get(i)); // apres les verif lajout de litem au hero 
                    System.out.println("tu portes" + item.get(i).name);
                    item.remove(i); // et on le retire du sac
                    break;
                }
            }
        }
    }
}