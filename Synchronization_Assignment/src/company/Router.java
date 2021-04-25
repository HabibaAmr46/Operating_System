package company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class Router {
   private int inptr=0;
   private int out=0;
   int conSize;
   String[] names;//array to store the names of devices to control occupy and release
   int conNo;//to determine which connection is running currently
   
   semaphore spaces;// to determine remaining spaces in array names
   semaphore elements;// to determine elements stores in array names
   semaphore s;//to make sure that no one access the variables stored in each function
   
 
   Router(int n)
   {
	   conSize=n;
	   spaces=new semaphore(conSize);
	   elements=new semaphore(0);
	   s=new semaphore(1);
	   names=new String[conSize];
   }
  
  public int getConNo() {
	return conNo;
}
    public void occupy(String name,String type)
    {
    	spaces.P(name,type);
    	s.P();
    	names[inptr]=name;
    	System.out.println("Connection "+(inptr+1)+" :"+names[inptr]+ " Occupied");
    	try {
    		//write output to the file Assignment2 using BufferWriter
    		String text ="Connection "+(inptr+1)+" :"+names[inptr]+ " Occupied";
    	    BufferedWriter writer = new BufferedWriter( new FileWriter("Assignment2.txt", true)); 
    	    writer.write(text);
    	    writer.newLine();
    	    writer.close();
  	      //System.out.println("Successfully wrote to the file.");
  	    } catch (IOException e) {
  	      System.out.println("An error occurred.");
  	      e.printStackTrace();
  	    }
    	
    	conNo=inptr;
    	inptr=(inptr+1)%conSize;
    	s.V();
    	elements.V();	
    	
    }
    public void relase()
    {
       elements.P();
       s.P();
       String name=names[out];
       System.out.println("Connection "+(out+1)+" :"+name+ " logged out");
       try {
    	 //write output to the file Assignment2 using BufferWriter
    	   String text ="Connection "+(out+1)+" :"+name+ " logged out";
   	    BufferedWriter writer = new BufferedWriter( new FileWriter("Assignment2.txt", true)); 
   	    writer.write(text);
   	    writer.newLine();
   	    writer.close();
   	      
   	    } catch (IOException e) {
   	      System.out.println("An error occurred.");
   	      e.printStackTrace();
   	    }
     
       out=(out+1)%conSize;
       s.V();
       spaces.V();
    }

}
