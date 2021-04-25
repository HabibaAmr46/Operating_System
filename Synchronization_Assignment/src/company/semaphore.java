package company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class semaphore {
    protected int value = 0 ;

    protected semaphore() {
        value = 0 ;
    }

    protected semaphore(int initial) {
        value = initial ;
    }
    
    public synchronized void P() {

        value-- ;
        if (value < 0) {
            try {
            	
                wait();}

            catch(  InterruptedException e ) { }
        }
       

    }

    public synchronized void P(String name,String type) {

        value-- ;
        if (value < 0) {
            try {
            	System.out.println("("+name+")"+"("+type+")"+"arrived and waiting");
            	try {
            		//write output to the file Assignment2 using BufferWriter
            		 String text ="("+name+")"+"("+type+")"+"arrived and waiting";
         	   	    BufferedWriter writer = new BufferedWriter( new FileWriter("Assignment2.txt", true)); 
         	   	    writer.write(text);
         	   	    writer.newLine();
         	   	    writer.close();
             	    } catch (IOException e) {
             	      System.out.println("An error occurred.");
             	      e.printStackTrace();
             	    }
            	
                wait();}

            catch(  InterruptedException e ) { }
        }else
        {
        System.out.println("("+name+")"+"("+type+")"+"arrived");
        	try {
        		//write output to the file Assignment2 using BufferWriter
        		 String text ="("+name+")"+"("+type+")"+"arrived";
        	   	    BufferedWriter writer = new BufferedWriter( new FileWriter("Assignment2.txt", true)); 
        	   	    writer.write(text);
        	   	    writer.newLine();
        	   	    writer.close();
       	    } catch (IOException e) {
       	      System.out.println("An error occurred.");
       	      e.printStackTrace();
       	    }
        	
        }
       

    }

    public synchronized void V() {
        value++ ;
        if (value <= 0)
            notify() ;
    }
}
