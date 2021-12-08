package campominado;

public class Temporizador implements Runnable{  // Temporizador
    protected static boolean exit = false;
    protected static int seconds;
    
    public static void run(int stop) {
        while (!exit) {
            seconds++;
            if(seconds==stop)
                System.exit(stop);
            try {
                Thread.sleep(1000);
                
            } catch (Exception e) {
            	System.out.println("Houve um erro");
            } 
            System.out.println(seconds);
        }
    }
    public static void stop(){
        exit = true;
    }
 /*   public static void rodar() {
    	seconds = 0;
    	
    } */
    @Override
    public void run() {
        // TODO Auto-generated method stub
        
    }
}