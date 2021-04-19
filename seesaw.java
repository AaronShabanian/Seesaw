import java.util.concurrent.*;
public class seesaw extends Thread {
    //Two semaphores, one for when someone is going up 
    //and one when someone is going down
    private static Semaphore up;
    private static Semaphore down;
    //Position references of fred and wilma
    public static double fredPos=0;
    public static double wilmaPos=7;
    //Velocity variables when fred is going up vs when wilma is going up
    public static double fredVelocity=1;
    public static double wilmaVelocity=1.5;
    public seesaw()
    {
        // Setting up semaphore on and down off
       up = new Semaphore(1);
       down = new Semaphore(0);
    }
    public static void main(String[] args){
        //starting 2 different threads
        seesaw goingUp =new seesaw(){
            public void run(){
                increasing();
            }
        };
        seesaw goingDown =new seesaw(){
            public void run(){
                decreasing();
            }
        };
        goingUp.start();
        goingDown.start();
        try{
            goingUp.join();
            goingDown.join();
        }
        catch (Exception e){

        }

    }
    public static void increasing(){
        for (int i =0; i<10; i++){
            System.out.println("Cycle: "+ (i+1));
            while (fredPos<7){
                try{
                    // sets semaphore to being used
                    up.acquire();
                }
                catch(Exception e){
                    System.out.println("Error");
                }
                fredPos+=fredVelocity;
                System.out.println("Fred is going up at height: "+fredPos);
                try{
                    Thread.sleep(1000);
                }
                catch(Exception e){
                    System.out.println("Error");
                }
                try{
                    //sets semaphore to being open
                    down.release();
                }
                catch(Exception e){
                    System.out.println("Error");
                }
            }
            while (wilmaPos<7){
                try{
                    // sets semaphore to being used
                    up.acquire();
                }
                catch(Exception e){
                    System.out.println("Error");
                }
                wilmaPos+=wilmaVelocity;
                System.out.println("Wilma is going up at height: "+wilmaPos);
                try{
                    Thread.sleep(1000);
                }
                catch(Exception e){
                    System.out.println("Error");
                }
                try{
                    //sets semaphore to being open
                    down.release();
                }
                catch(Exception e){
                    System.out.println("Error");
                }
            }
            
        }
    } 
    public static void decreasing(){
        for (int i =0; i<10; i++){
            while (wilmaPos>1){
                try{
                    // sets semaphore to being used
                    down.acquire();
                }
                catch(Exception e){
                    System.out.println("Error");
                }
                wilmaPos-=fredVelocity;
                System.out.println("Wilma is going down at height: "+wilmaPos);
                try{
                    Thread.sleep(1000);
                }
                catch(Exception e){
                    System.out.println("Error");
                }
                try{
                    //sets semaphore to being open
                    up.release();
                }
                catch(Exception e){
                    System.out.println("Error");
                }
            }
            while (fredPos>1){
                try{
                    // sets semaphore to being used
                    down.acquire();
                }
                catch(Exception e){
                    System.out.println("Error");
                }
                fredPos-=wilmaVelocity;
                System.out.println("Fred is going down at height: "+fredPos);
                try{
                    Thread.sleep(1000);
                }
                catch(Exception e){
                    System.out.println("Error");
                }
                try{
                    //sets semaphore to being open
                    up.release();
                }
                catch(Exception e){
                    System.out.println("Error");
                }
            }
            
        }
    }
}