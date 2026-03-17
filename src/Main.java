import java.util.ArrayList;


public class Main {
    public static final String PLAYER_FILE = "data/players.csv";
    private static final int PLAYER_INDEX = 0;
    public static void main(String[] args) {
        ArrayList<String> players = FIleIO.readFileData(PLAYER_FILE);

        //Assume the only player we are changing in=s the first player in the array
        Player p = new Player(players.get(PLAYER_INDEX));

        System.out.println(p.CSVStr());


        System.out.println(p);
        p.setLastPlayed();
        players.set(PLAYER_INDEX, p.CSVStr());
        System.out.println("Updated");
        System.out.println(p);
        FIleIO.writeFileData(PLAYER_FILE, players);

    }
}
