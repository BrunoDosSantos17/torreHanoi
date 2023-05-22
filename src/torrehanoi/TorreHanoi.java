package torrehanoi;

import game.game;

public class TorreHanoi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        try {
            
            if ((args[0]).trim().equalsIgnoreCase("contigua")) {
                game Game = new game(true);
                Game.start();
            }
            if ((args[0]).trim().equalsIgnoreCase("dinamica")) {
                game Game = new game(true);
                Game.start();
            }


        } catch (Exception e) {
            System.out.println("OPS!!! Você digitou algo errado o comando certo é\n"
                    + "java -jar torreHanoi.jar contigua\n ou"
                    + "java -jar torreHanoi.jar dinamica");
        }

    }

}