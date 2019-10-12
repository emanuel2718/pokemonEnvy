package Main;


public class Launch {

    public static void main(String[] args) {
        Handler handler = new Handler();
        GameSetUp game = new GameSetUp("pokemonEnvy",handler);
        handler.setGame(game);
        game.start();
    }
}
