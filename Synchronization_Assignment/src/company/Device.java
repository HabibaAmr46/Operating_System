package company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Device extends Thread {
    String  myName;
    String type;
    static  Router re;
    public Device( String Name,String type,Router r)
    {

        this.myName = Name;
        this.type = type;
        re=r;
    }
    void connect() {

        re.occupy(myName,type);
    }
    void logout()
    {
        re.relase();
    }
    void perform(){

        try {
         System.out.println("Connection "+(re.getConNo()+1)+": "+myName+" performs online activity");
        	try {
        		//write output to the file Assignment2 using BufferWriter
        		 String text = "Connection "+(re.getConNo()+1)+": "+myName+" performs online activity";
        	    BufferedWriter writer = new BufferedWriter( new FileWriter("Assignment2.txt", true)); 
        	    writer.write(text);
        	    writer.newLine();
        	    writer.close();
        	     
        	    } catch (IOException e) {
        	      System.out.println("An error occurred.");
        	      e.printStackTrace();
        	    }
        	
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        logout();
    }
    @Override
    public void run() {

        // run by thread A
        connect();
        perform();


    }
}
