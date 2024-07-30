package HorseRaceSimulator;

public class RaceTestPart1 {
	    public static void main(String[] args) {
	        
	        Horse horse1 = new Horse('♘', "PIPPI LONGSTOCKING", 0.7);
	        Horse horse2 = new Horse('♞', "KOKOMO", 0.6);
	        Horse horse3 = new Horse('❌', "EL JEFE", 0.4);

	        Race race = new Race(10); // Race length of 10 meters

	        race.addHorse(horse1, 1);
	        race.addHorse(horse2, 2);
	        race.addHorse(horse3, 3);

	        race.startRace();
	    }
}
