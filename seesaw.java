public class seesaw extends Thread {
    public static void main(String[] args){
        seesaw fred =new seesaw(){
            public void run(){
                fredSee();
            }
        };
        seesaw wilma =new seesaw(){
            public void run(){
                wilmaSaw();
            }
        };
    }
    public static void fredSee(){

    } 
    public static void wilmaSaw(){

    }
}