
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author EGYPT_LAPTOP
 */
public class parser {
    
    String[] Arguments={};// Will be filled by arguments extracted by parse method   
	    String Cmd=" "; // Will be filled by the command extracted by parse method
	    
	    
	    public String parse(String input)
	{ 
	    String f="";
	    boolean ff=true;
	    String []in= input.split(" ");
	    String [] commands={"args","cd","rm","mv","ls","date","cat","more","pwd"};
	    String key = in[0];  
	    String arg[]=new String [in.length-1];
	          
	        int result =Arrays.binarySearch(commands,key);  
	        if (result == -1)  
	            f="This command is not allowed! you cand use 'help' command to know all commands ";  
	        else  
	        {
	            if(in.length>1)
	            {
	                for(int i=0;i<in.length-1;i++){
	                    arg[i]=in[i+1];
	                }
                        if(in[0].equalsIgnoreCase("mkdir")||in[0].equalsIgnoreCase("rmdir")||in[0].equalsIgnoreCase("cp"))
                        {
                            Cmd=in[0];
	                    Arguments=new String[arg.length];
	                    for (int i = 0; i < arg.length; i++)     
	                            Arguments[i] = arg[i];                             
	                f="";
                        }
                        else{
	                for(int i=0;i<arg.length;i++)
	                {
	                    if(arg[i].contains(".txt"))
	                    {ff=checkshortpath(arg[i]);
	                    if(ff==false)
	                        f="Not exist file";
	                    }
	                    
	                    else
	                    {
	                        ff=checklongpath(arg[i]);
	                        if(ff==false)
	                        f="Not valid path, please check that you entered a valid path and without spaces";
	                    }
	                    
	                }
	                if(in[0].equalsIgnoreCase("args"))
	                {
	                  Cmd=in[0];
	                  f="";
	                  Arguments=new String[arg.length];
	                    for (int i = 0; i < arg.length; i++)     
	                            Arguments[i] = arg[i];        
	                
	                
	                }
	               else if(f.equalsIgnoreCase(""))
	                {
	                    Cmd=in[0];
	                    Arguments=new String[arg.length];
	                    for (int i = 0; i < arg.length; i++)     
	                            Arguments[i] = arg[i];                             
	                f="";}}
	            }
	            
	            else
	            { Cmd=in[0];
	             f="";}       
	        }
	    
	  
	        return f;
	}
	    public boolean checklongpath(String p)
	    {boolean f=false;
	       File file = new File(p);
	       
	       if((p.charAt(0)=='C' || p.charAt(0)=='D' || p.charAt(0)=='E')&& p.charAt(1)==':'&& p.charAt(2)==('\\')&&file.isDirectory())
	        {
	            f=true;
	        }
	        return f; 
	    }
	    
	    public boolean checkshortpath(String p)
	    
	    {  
	       String d=System.getProperty("user.dir");
	           boolean check = new File(d, p).exists();
	       
	        return check; 
	    }
	    public String getCmd(){
	        String cmd=Cmd;
	        return cmd;
	    }
	    public String[] getArguments(){
	        String args[]=Arguments;
	        return args;
	    };
	    public void displayy()
	    {
	        System.out.println(Cmd+"  ");
	        for(int i=0;i<Arguments.length;i++)
	            System.out.println(Arguments[i]+"  ");
	    }
	    public void display() throws Exception {
	       
	        terminal t=new terminal();
	        if(Cmd.equalsIgnoreCase("cat"))
	    {
	      if(Arguments.length==0)
	      System.out.println("Error too few parameters please enter a parameter after cat");
	      
	    
	      else if(Arguments.length>=1)
	      {
	         
	            ArrayList<String>arr=new ArrayList<>();
	            arr.addAll(t.cat(Arguments));
	            t.Print(arr);
	             
	      
	      }
	     
	          
	    }else if(Cmd.equalsIgnoreCase("pwd"))
	    {
	    	if(Arguments.length>0)
	    		System.out.println("You cannot add a parameter in pwd");
	    	else
	    	{
	    		 ArrayList<String>arr=new ArrayList<>();
		            arr.addAll(t.pwd());
		            t.Print(arr);
	    	}
	    	
	    }else if(Cmd.equalsIgnoreCase("clear"))
	    {
	    	t.clear();
	    }
	    else if(Cmd.equalsIgnoreCase("rm"))
	    {
	        if(Arguments.length==0) //example: rm
	          System.out.println("Error too few parameters please enter a parameter after rm");
	        else if(Arguments.length==1)
	        {
	            
	            t.rm(Arguments[0]);
	        }
	        else
	          { 
	               System.out.println("ERROR you cant enter a parameter with rm");
	          }      
	    }
	    else if(Cmd.equalsIgnoreCase("mv"))
	    {
	        if(Arguments.length==0) //example: mv
	          System.out.println("Error too few parameters please enter 2 parameters after mv");
	        else if(Arguments.length==1) //example: mv file.txt
	            System.out.println("Error too few parameters please enter the destination path ");
	        else if(Arguments.length==2) //example: mv file.txt home.txt
	          { 
	           t.mv(Arguments[0], Arguments[1]);
	          }
	        else
	            System.out.println("ERROR you cant enter more than source and destination paths ");
	    }
	    else if(Cmd.equalsIgnoreCase("ls"))
	    {
	      if(Arguments.length==0) //example ls
	      { 
	    	 ArrayList<String>arr=new ArrayList<>(); 
	    	 arr.addAll(t.ls());
	         t.Print(arr);
	         
	      }
	      else
	       System.out.println("ERROR you cant enter a parameter with ls");

	    }
	    else if( Cmd.equalsIgnoreCase("date"))
	        t.date();
	    else if( Cmd.equalsIgnoreCase("args"))
	    {
	    if(Arguments.length==0)//args
	            System.out.println("too few parameters");
	    else if(Arguments.length==1)//args cp
	        t.args(Arguments[0]);
	    else
	            System.out.println("cant enter more than 1 parameter");
	    
	    
	    }
	    else if(Cmd.equalsIgnoreCase("cd"))
	    {
	      if(Arguments.length==0) //example cd 
	      {
	         t.cd("C:\\Users\\Hp\\eclipse-workspaceJava\\AssignmentOs1");
	      }
	      else if(Arguments.length==1) //example cd C:/
	      {
	         
	            t.cd(Arguments[0]);
	      }
	      else
	            System.out.println("ERROR you cant enter more than 1 parameter or you enterd a path with spaces");
	       
	    }
	    else if(Cmd.equalsIgnoreCase("more"))
	    {
	    	if(Arguments.length==0)
	    		System.out.println("Too few Arguments for more funtion");
	    	else
	    		t.more(Arguments);
	    }
	    else if(Cmd.equalsIgnoreCase("mkdir"))
	    {
	    	if(Arguments.length == 1) //example mkdir os.txt
	    		t.mkdir(Arguments[0]);
	    	else if(Arguments.length==0)  //example mkdir
	    		System.out.println("Too few arguments,mkdir command should have one argument ");
	    	else    //example mkdir os.txt assignment.txt
	    		System.out.println("Too many arguments,mkdir command should take one argument only");
	    }
	    else if(Cmd.equalsIgnoreCase("rmdir"))
	    {
	    	if (Arguments.length == 1) //example rmdir os.txt
	            t.rmdir(Arguments[0]);
	        else if(Arguments.length==0)  //example rmdir
	            System.out.println("Too few arguments,rmdir command should have one argument");
	        else  //example rmdir os.txt assignment.txt
	            System.out.println("Too many arguments,rmdir command should take one argument only");
	    }
	    else if(Cmd.equalsIgnoreCase("help"))
	    {
	    	t.Help();
	    }
	    else if(Cmd.equalsIgnoreCase("cp"))
	    {
	        if(Arguments.length == 2) //example cp os.txt Documents
	            t.cp(Arguments[0],Arguments[1]);
	        else if (Arguments.length == 0 || Arguments.length == 1) //example cp || cp os.txt
	            System.out.println("Too few arguments,cp command should have two arguments");
	        else   //example cp os.txt assignment.txt Documents
	            System.out.println("Too many arguments,cp command should take two arguments only");
	    }
	    else
	        System.out.println("command "+Cmd+" not exist, you can use help function to know our commands");
	 

	    }
}
