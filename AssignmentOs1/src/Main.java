
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author EGYPT_LAPTOP
 *
 */

public class Main {

    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) throws Exception {
	       /* 
	        Habiba Amr Mohammed:20180083
	       	Rawan Abdelaziz mansour:20180106
			Miriam hany Farag:20180271
			Nourhan Amgad morsey:20180319
	        */
		 
	 while(true){
	          parser p=new parser();
	          parser p2=new parser();
	        Scanner sc=new Scanner(System.in);  
	       System.out.print(System.getProperty("user.dir")+" >~$ ");
	      String input = sc.nextLine();
	      String flage="";
	      String flagee="";
	      if(input.equalsIgnoreCase("exit"))
	          break;
	      else if(input.contains("|"))
	      {
	         
	          
	          String s1="";
	          String s2="";
	          int j=0;
	          for(int i=0;i<input.length();i++)
	          {
	              if(input.charAt(i)=='|')
	              {j=i;
	                  break;
	            }
	                  
	          }
	          s1=input.substring(0, j-1);
	          s2=input.substring(j+2, input.length());
	         
	          
	         flage= p.parse(s1);
	         flagee= p2.parse(s2);
	         if(flage!=""&&flagee!="")
	            System.out.println(flage+"\n"+flagee);
	       
	         else if(flagee!=""&&flage.equalsIgnoreCase(""))
	         { p.display();
	            System.out.println(flagee);
	         }
	         else if(flage!=""&&flagee.equalsIgnoreCase(""))
	         {  p2.display();
	         System.out.println(flage);
	         }
	         else{
	           p.display();
	           p2.display();
	         }
	            
	   
	      }
	      else if(input.contains(">")||input.contains(">>"))
	      {
	    	  terminal t=new terminal();
	    	  String s1="";
	    	  String s2="";
	    	  int j=0;
	    	  boolean App = false;
	    	 
	    	 
	          for(int i=0;i<input.length();i++)
	          {
	        	   if(input.charAt(i)=='>' && input.charAt(i+1)=='>')
	              {
	            	  j=i;
	            	  App=true;
	            	  break;
	              }
	        	   else if(input.charAt(i)=='>')
	              {	j=i;
	              App=false;
	                  break;
	              }
	             
	                  
	          }
	          if(App==true)
	          {
	        	  s1=input.substring(0, j-1);
		          s2=input.substring(j+2, input.length());
	          }
	          else {
	          s1=input.substring(0, j-1);
	          s2=input.substring(j+1, input.length());
	          }
	      
	          String[] Operator=s1.split(" ");
	          ArrayList<String> filecontents=new ArrayList<>();
	          String Flage=p.parse(s1);
	          if((Flage=="")) {
		    	  if(Operator[0].equalsIgnoreCase("cat"))
		    	  {
		    		  String[] paths = Arrays.copyOfRange(Operator, 1, Operator.length);
		    		  if(paths.length==0)
		    			  System.out.println("You have to add arguments after cat command");
		    		  else {
		    		  filecontents.addAll(t.cat(paths));
		    		  t.redirect(filecontents,App,s2);  
		    		  }
		    	  }
		    	  else if((Operator[0].equalsIgnoreCase("ls")))
		    			  {
		    		  filecontents.addAll(t.ls());
		    		  t.redirect(filecontents,App,s2);      
		    			  }
		    	  else if ((Operator[0].equalsIgnoreCase("pwd")))
		    	  {
		    		  filecontents.addAll(t.pwd());
		    		  t.redirect(filecontents,App,s2);      
		    		  
		    	  }
		    	  else
		    	  {
		    		  System.out.println("Invalid command with the operator");
		    	  }
	          }
	      else
	      {
	    	  System.out.println(Flage);
	      }
	   }
	      
	     else
	      {flage= p.parse(input);
	       if(flage!="")
	             System.out.println(flage);
	       else
	          p.display(); 
	      }
	        
	    }
	     
	    }  
    }
    

