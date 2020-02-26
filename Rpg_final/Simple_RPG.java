public class Simple_RPG {
	static Game_Logic gl = new Game_Logic();

	public static void main(String[] args) {
		Game_Objects.initNPCArray();
		Game_Objects.ItemDataBase();
		MenuStart menuStart = new MenuStart();
		Monster_Thread mt = new Monster_Thread(gl);
		mt.startMonsterThread();

		System.out.println("Bienvenue dans le monde d'Eorzea");
		menuStart.Menu();
		if (menuStart.getChoix() == 1) {
			System.out.println("link start");
			menuStart.Class();
			if(menuStart.getChoix2() == 1) {
				System.out.println("Les Dieux vous on accorder une faveur, ils vous donne 100hp et 55 de dexterite pour commencer!");
			}
			else if (menuStart.getChoix2() == 2){
				System.out.println("Les Dieux vous on accorder une faveur, ils vous donne 80hp et 70 de dexterite pour commencer!");
			}
			else if (menuStart.getChoix2() == 3) {
				System.out.println("Les Dieux vous on accorder une faveur, ils vous donne 70hp et 75 de dexterite pour commencer!");

			}
			System.out.println("L'appel du Cristal.... Hydaelyn,... une planete irisee de bleus et de verts, dotee d'une nature foisonnante et génereuse...");
			System.out.println("A l'ouest d'une immense masse continentale surnommee la Trigee se trouve une terre que l'on dit aimee des Dieux et forgee par les heros.");
			System.out.println(" Son nom... Eorzea.");
			System.out.println("C'est la que votre aventure commence.");
			System.out.println("Repondant à l'appel du Cristal-mere, la source de toute vie, vous vous lancez dans une quete pour sauver le monde des tenebres");
			while(true) {
				game_loop();
			}
		}
		System.out.println("a bientot");
	}
	public static void game_loop()
	{
		gl.waitforCommand();
	}

}