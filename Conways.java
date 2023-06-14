import java.io.IOException;
//import java.lang.Thread;

public class Conways {


    public static void main(String[] args) {
        
        Matrix game = new Matrix(179, 3, 3);

        //int count = 0;

        for(int count = 0; ; count++) { 

            // - - - - - - - - LIVE GAME - - - - - - 
            
            
            /*try { //pause between frames
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } 

            

            System.out.print("\033[H\033[2J");   // cleaning previous frame
            System.out.flush();  

            System.out.println(game); // printing new frame
             
            */
            // - - - - - - - - Simulation - - - - - -
            long startTime = System.nanoTime();
            game.RunCycle(); // refreshing
            long endTime   = System.nanoTime();
            long totalTime = endTime - startTime;

            if(count%100 == 0) {
                System.out.println(count + " (" + game.getRow() + " x " + game.getCol() + ") " + (totalTime/1000000));
            }

           // System.out.print("\033[H\033[2J");   // cleaning previous frame
           // System.out.flush();

            
        }

        
        //System.out.println(game);
    }
}