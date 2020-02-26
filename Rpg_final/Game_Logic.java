import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Game_Logic {

	public Game_Logic() {
		Game_Objects.salle.add(new Salle(0));
		List<String> roomInfo = new ArrayList<>();

		try {
			roomInfo = readLines("TextFiles/RoomDescriptions.txt"); //lire les description dans le fichier txt
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < roomInfo.size(); i++) {
			String[] firstWord = roomInfo.get(i).split(" "); // premier mot
			String[] everyThingElse = roomInfo.get(i).split(":"); // le reste

			if (firstWord[0].equals("NomSalle:")) {
				int currentRoomSize = Game_Objects.salle.size();
				Game_Objects.salle.add(new Salle(currentRoomSize));
				Game_Objects.salle.get(Game_Objects.salle.size() - 1).name = everyThingElse[1];
				Game_Objects.salle.get(Game_Objects.salle.size() - 1).number = (currentRoomSize);
				
				int roomcount = 0;
				for (int z = 0; z < roomInfo.size(); z++) {
					String[] nextFirstWord = roomInfo.get(z).split(" ");
					if(nextFirstWord[0].equals("NomSalle:")){
						roomcount++;
					}
					if (roomcount == currentRoomSize) {
						if(nextFirstWord[0].equals("Desc:")) {
							String[] nextEverythingElse = roomInfo.get(z).split(":");
							Game_Objects.salle.get(Game_Objects.salle.size() - 1).desc.add(nextEverythingElse[1]);
						}
					}
				}
				roomcount = 0;
				for( int z = 0; z < roomInfo.size(); z++) {
					String[] nextFirstWord = roomInfo.get(z).split(" ");
					if(nextFirstWord[0].equals("NomSalle:")) {
						roomcount++;
					}
					if(roomcount == currentRoomSize) {
						if(nextFirstWord[0].equals("Exit:")){
							String[] nextEverythingElse = roomInfo.get(z).split(":");
							Game_Objects.salle.get(Game_Objects.salle.size() - 1).exits.add(nextEverythingElse[1]);
						}
					}
				}
			}
		}
	}

						//*** prendre fichier et le lire ***//
	public List<String> readLines(String filename) throws IOException { //gestion des exceptions
		FileReader fileReader = new FileReader(filename);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		List<String> lines = new ArrayList<String>();
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			lines.add(line);
		}
		bufferedReader.close(); //sort le tableau de toute les lignes
		return lines;
	}

	public void waitforCommand() {
		if(Game_Objects.pc.etage == 0)
		createCharacter();

		System.out.println("Que faire?");
		Scanner sc1 = new Scanner(System.in);
		String com = sc1.nextLine();
		String[] words = com.split(" ");

		processCommand(words);
		}

	public void processCommand(String[] x) {
		if(x[0].equals("observe")) {
			look(x);
		}
		if (x[0].equals("invoque")) {
			summon(x);
		}
		if(x[0].equals("creer")) {
			createItem(x);
		}
		if (x[0].equals("prendre")) {
			get(x);
		}
		if (x[0].equals("porter")) {
			Game_Objects.pc.porter(x);
		}
		if (x[0].equals("vu")){
			Game_Objects.pc.vu();
		}
		if (x[0].equals("supprime")) {
			Game_Objects.pc.removeItem(x[1]);
		}

		if (x[0].equals("deplace")) {
			move(x);
		}
	}

	public void look(String[] x) { // fonction regarder tout
		if (x.length == 1) { //1 mot
			for (int i = 0; i < Game_Objects.salle.size(); i++){  //pieces
				if (Game_Objects.salle.get(i).number == Game_Objects.pc.etage) { // meme numero que la salle PCN
					System.out.println(Game_Objects.salle.get(i).name);// imprimer le nom
					for(int y = 0 ; y < Game_Objects.salle.get(i).desc.size(); y++) { //boucle des descriptions
						System.out.println(Game_Objects.salle.get(i).desc.get(y));
					}
					System.out.println("Exits");
					for(int y = 0; y < Game_Objects.salle.get(i).exits.size(); y++) { //boucle des sorties
						String exitNameFull = Game_Objects.salle.get(i).exits.get(y); //tableau pour le noms de sorties
						String[] exitName = exitNameFull.split(" ");
						System.out.println(exitName[0]);
					}
					for (int y = 0; y < Game_Objects.salle.get(i).npc.size(); y++) { 	//monstre
						System.out.println(Game_Objects.salle.get(i).npc.get(y).desc);
					}
					for (int y = 0; y < Game_Objects.salle.get(i).item.size(); y++) { //item
						System.out.println(Game_Objects.salle.get(i).item.get(y).desc);
					}
				}
			}
		}
		if(x.length == 2) {
			if(x[1].equals("self")) {
				Game_Objects.pc.look();
				System.out.println("Sur vous, vous avez : ");
				for (int i = 0; i < Game_Objects.pc.item.size(); i++) {
					System.out.println(Game_Objects.pc.item.get(i).name);
				}
			}
			for(int y = 0; y < Game_Objects.salle.size(); y++){
				if (Game_Objects.salle.get(y).number == Game_Objects.pc.etage) {
					for(int i = 0; i < Game_Objects.salle.get(y).npc.size(); i++) {
						if(x[1].equalsIgnoreCase(Game_Objects.salle.get(y).npc.get(i).id)) {
							Game_Objects.salle.get(y).npc.get(i).look();
						}
					}
				}
			}
		}
	}

	public void summon(String[] x) { // invoquer des monstres
		if (x.length == 1) {
			System.out.println("Invoquer quoi?");
		}
		if (x.length == 2) {
			for(int i = 0; i < Game_Objects.NPCDataBase.size(); i++) {
				NPC localNPC = (NPC)Game_Objects.NPCDataBase.get(i);
				if (localNPC.id.equalsIgnoreCase(x[1])) {
					for (int y = 0; y < Game_Objects.salle.size(); y++) {
						if (Game_Objects.salle.get(y).number == Game_Objects.pc.etage) {
							try {
								Game_Objects.salle.get(y).npc.add((NPC) Class.forName(localNPC.id).newInstance()); //deux méthodes forName/newInstance, pour récupérer une instance de Class via nom d'une classe
								System.out.println("tu as invoque " + Game_Objects.salle.get(y).npc.get(Game_Objects.salle.get(y).npc.size() - 1).name);
								Game_Objects.salle.get(y).npc.get(Game_Objects.salle.get(y).npc.size() - 1);
							} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}

	public void createCharacter() { // creation du personnage
		System.out.println("Bienvenue en jeu. Quel est votre nom?");
		Scanner sc2 = new Scanner(System.in);
		Game_Objects.pc.name = sc2.next();
		System.out.println("Bienveue a toi " + Game_Objects.pc.name + "!");
		Game_Objects.pc.hp = 100;
		Game_Objects.pc.dexterite = 75;
		Game_Objects.pc.etage = 1;
	}

	public void createItem(String[] x) { //creation Item
		if (x.length == 1) {
			System.out.println("oula! Que veux tu creer?");
		}
		if(x.length == 2) {
			for(int i = 0; i < Game_Objects.ItemDataBase.size(); i++) { //tableau de base de donnee d'item
				Item localItem = (Item)Game_Objects.ItemDataBase.get(i);  
				if(localItem.id.equalsIgnoreCase(x[1])) {
					for(int y = 0; y < Game_Objects.salle.size(); y++) {
						if(Game_Objects.salle.get(y).number == Game_Objects.pc.etage) { // voir si l'item est dans la meme piece
							try {
								Game_Objects.salle.get(y).item.add((Item) Class.forName(localItem.id).newInstance()); //un element id en tant que nouvelle classe
							} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
								e.printStackTrace();
							}
							System.out.println("tu as cree " + Game_Objects.salle.get(y).item.get(Game_Objects.salle.get(y).item.size() - 1).name); //la piece quand laquelle il ce trouve/la liste des item
							//le dernier et imprimer le nom
						}
					}
				}
			}
		}
	}

	public void get(String[] x) { // recuperer les items creer
		if(x.length == 1) {
			System.out.println("Obtenir quoi?");
		}
		if (x.length == 2) {
			for (int i = 0; i < Game_Objects.ItemDataBase.size(); i++) {
				for (int y = 0; y < Game_Objects.salle.size(); y++) {
					if (Game_Objects.salle.get(y).number == Game_Objects.pc.etage) {
						for(int z = 0; z < Game_Objects.salle.get(y).item.size(); z++) {
							if (x[1].equalsIgnoreCase(Game_Objects.salle.get(z).item.get(z).id)) {
								Item localItem = Game_Objects.salle.get(y).item.get(z);
								Game_Objects.pc.item.add(localItem);
								System.out.println("tu prends " + localItem.name);
								Game_Objects.salle.get(y).item.remove(z);
								break;
							}
						}
					}
				}
			}
		}
	}

	public void move(String[] x) {
		if(x.length == 1) {
			System.out.println("Ou aller?");
		}
		if (x.length == 2) {
			for (int i = 0; i < Game_Objects.salle.size(); i++) {
				if (Game_Objects.salle.get(i).number == Game_Objects.pc.etage) {
					for (int y = 0; y < Game_Objects.salle.get(i).exits.size(); y++) {
						
						String exitRequiesred = Game_Objects.salle.get(i).exits.get(y);
						String[] exitArray = exitRequiesred.split(" ");
						if (x[1].equalsIgnoreCase(exitArray[1])) {
							Game_Objects.pc.etage = Integer.parseInt(exitArray[2]);
							System.out.println("tu pars en " + exitArray[1]);
							String[] maupos = new String[1];
							maupos[0] = "rien";
							look(maupos);

						}
					}
				}
			}
		}
	}
}