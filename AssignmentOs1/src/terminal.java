
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author EGYPT_LAPTOP
 */
public class terminal {
  String CurrentDirectory=System.getProperty("user.dir");
	
	 public void cd(String path)
	    {
	        if(path.contains(".txt"))
                {
                     System.out.println(" IT is a file, please enter full path");
                }
                else{
                System.setProperty("user.dir", path);
	          System.out.println("current dir = " + System.getProperty("user.dir"));
                          }
	    
	    }
	 	public ArrayList<String> pwd()
		{
			String directory=System.getProperty("user.dir");
			ArrayList<String> FileContent=new ArrayList<String>();
			FileContent.add(directory);
			return FileContent;
		}
	 public void redirect(ArrayList<String> fileContent,boolean App,String path)
		{
			FileWriter fw;
			File file=new File(CurrentDirectory+"/"+path);
			try { 
			
			if(file.isDirectory())
			{
				System.out.println(file.getName()+": Is a directory");
				return;
			}
			
			if(App==true)
				fw = new FileWriter(file,true);
			else
				fw=new FileWriter(file);
			
			for(int i=0;i<fileContent.size();i++)
			{
				fw.write(fileContent.get(i));
				fw.write("\n");
				fw.flush();
			}
			fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}
	 	public void Print(ArrayList<String> fileContent)
		{
				for(int i=0;i<fileContent.size();i++)
				{
					System.out.println(fileContent.get(i));
				}
			
		}
	 	public ArrayList<String> ls()
	    {
	        String director=System.getProperty("user.dir");
	        File folder = new File(director);
	       String [] files = folder.list();
	       ArrayList<String> fileC=new ArrayList<String>();
	        fileC.addAll(Arrays.asList(files));
	        
	       return fileC; 
	    }
	      
	     public void mkdir(String dirName) throws IOException
	     {
	    	 Path newPath = Paths.get(dirName);
	         if (!Files.exists(newPath)) {
	             Files.createDirectory(newPath);
	             System.out.println("The directory is created");
	         } else {
	             System.out.println("This directory already exists");
	         }
	     }
	     
	     public void rmdir(String dirName) throws IOException
	     {
	         Path newPath = Paths.get(dirName);
	         if(!Files.isExecutable(newPath))
	             System.out.println("This directory does not exist");
	         else
	         {
	             Files.deleteIfExists(newPath);
	             System.out.println("The directory has been removed");
	         }
	     }
	     
	     public void fileToFile(File firstFile, File secondFile) throws IOException 
	     {
	         Scanner toRead = new Scanner(firstFile);
	         String data = new String();
	         Vector<String> lines = new Vector<String>();
	         while(toRead.hasNextLine()){
	             data = toRead.nextLine();
	             lines.add(data);
	         }
	         FileWriter copyToFile = new FileWriter(secondFile);
	         for(int i=0;i<lines.size();i++)
	         {
	             copyToFile.write(lines.get(i)+"\n");
	         }
	         copyToFile.close();
	     }
	     public void cp(String sourcePath, String destinationPath) throws IOException 
	     {
	         File firstFile = new File(CurrentDirectory+"/"+sourcePath);
	         if(firstFile.isFile())
	         {
	             File secondFile = new File(destinationPath);
	             if(secondFile.isFile()) {
	                 fileToFile(firstFile,secondFile);
	                 System.out.println("The file has been copied to the another file");
	             }
	             else if(secondFile.isDirectory()) {
	                 //System.out.println("Folder's name: " + secondFile.getName());
	                  File newFile = new File(destinationPath,firstFile.getName());
	                  if(newFile.createNewFile()) {
	                      fileToFile(firstFile,newFile);
	                      System.out.println("The file has been copied to the directory");
	                  }
	                  else
	                      System.out.println("The file already exists");
	             }
	             else
	                 System.out.println("Not a file nor a folder,so we can't copy to it ");
	         }
	         else
	             System.out.println("Error,cp don't copy anything except files");
	     }
	 
	     public void date()
	     {
	    	 Date date = new Date() ;
	    	 System.out.println("Current system's date and time : "+date);
	     }
	     public void clear()
	    {
	     for(int i=0;i<100;i++)
	         System.out.println();
	    }
	      public void args(String cmd)
	    {
	      if(cmd.equalsIgnoreCase("cp")) 
	            System.out.println("arg1: SourcePath, arg2: DestinationPath");
	      
	      else if(cmd.equalsIgnoreCase("cd"))
	      {   System.out.println("cd can take no arguments");
	          System.out.println("arg1: the directory to be navigated to");       
	      }
	      else if(cmd.equalsIgnoreCase("ls"))
	      {
	          System.out.println("ls can take no arguments");
	          System.out.println("arg1: ls -l  turns on long listing format");
	      }
	      else if(cmd.equalsIgnoreCase("cat"))
	      {
	          System.out.println("arg1: fileName");
	          System.out.println("arg1 arg2 argN: it takes unlimited number of fileNames to be concatenated and displayed");   
	      }
	      else if(cmd.equalsIgnoreCase("more"))
	              System.out.println("arg1: fileName");
	      
	      else if (cmd.equalsIgnoreCase("mkdir"))   
	              System.out.println("arg1: directoryName to be created");
	      
	      else if (cmd.equalsIgnoreCase("rmdir")) 
	          System.out.println("arg1: directoryName to be removed");
	      
	      else if (cmd.equalsIgnoreCase("mv"))
	        System.out.println("arg1: sourcePath, arg2: DestinationPath");
	      
	       else if (cmd.equalsIgnoreCase("rm"))
	       { System.out.println("arg1: fileName to be removed");
	           System.out.println("arg1 arg2 argN: it takes unlimited number of fileNames to be removed");      
	       }
	       
	      else if(cmd.equalsIgnoreCase("clear"))
	            System.out.println("Takes no arguments");
	      
	      else if(cmd.equalsIgnoreCase("pwd"))
	            System.out.println("Takes no arguments");
	      
	       else if(cmd.equalsIgnoreCase("help"))
	            System.out.println("Takes no arguments");
	       
	      else if(cmd.equalsIgnoreCase("date"))
	            System.out.println("Takes no arguments");
	      else
	            System.out.println("Enter a valid command name");
	    }
	     
	   public void rm(String path)
	    {
	        
	    	 File file=new File(CurrentDirectory+"/"+path);
	 			if(file.isDirectory())
	 			{
	 				System.out.println("Cannot remove "+file.getName()+" :Is a directory");
	 				return;
	 			}
	 			else
	 				file.delete();
	 		
	 	
	    }
	     public void mv(String sourcePath, String destinationPath)
	    {
	        
	    	 try {
	 		  	File Soufile=new File(CurrentDirectory+"/"+sourcePath);
	 		    File Destfile=new File(destinationPath);
	 		    
	 		    
	 		    if(Soufile.isDirectory())
	 		    {
	 		    	System.out.println("Cannot move a directory with mv only");
	 		    	return;
	 		    }
	 		    if(Destfile.isFile()) {
	 		    	 System.out.println("Cannot remove to a file");
	 		    	 return;
	 		     }
	 		     if(!Destfile.exists())
	 		     {
	 		    	 Soufile.renameTo(Destfile);
	 		    	 return;
	 		     }
	 		     
	 		     	FileReader fr = new FileReader(CurrentDirectory+"/"+sourcePath);
	 				BufferedReader br = new BufferedReader(fr);
	 				FileWriter fw=new FileWriter(Destfile+"/"+Soufile.getName());
	 				String s;
	 				while ((s = br.readLine()) != null) { // read a line
	 					fw.write(s);
	 					// write to output file
	 					fw.flush();
	 					fw.write("\n");
	 				}
	 				fr.close();
	 				br.close();
	 				fw.close();
	 				Soufile.delete();

	    	
	 				
	 		}catch(Exception e)
	 		{
	 			e.printStackTrace();
	 		}
	    }
	     public void more(String paths[]) throws Exception{
	    	 for(int i=0;i<paths.length;i++) {
					File file=new File(paths[i]);	
						//check if the file is a directory
					if(file.isDirectory()) {
							System.out.println("***"+file.getName()+": directory***");
							continue;
						}
					System.out.println("File:"+file.getName());
					File myObj = new File(CurrentDirectory+"/"+paths[i]);
					 Scanner input = new Scanner(System.in);
				      Scanner myReader = new Scanner(myObj);
				      String enterKey="";
						int check=0;
				      while (myReader.hasNextLine()&&enterKey.equals("")) {
				        String data = myReader.nextLine();
				        System.out.println(data);
				        check++;
					  	if(check%9==0)
					  	{
						  System.out.println("--More--"+"("+check+")%");
						  System.out.println("Press Enter to continue viewing the file contents");
						  enterKey=input.nextLine();
					  	}
				      }
				      myReader.close();
	    	 }
	     }
	     public void Help()
	 	{
	 		System.out.println("args: list all command arguments");
	 		System.out.println("date:Display current date and time");
	 		System.out.println("Exit:stop all");
	 		System.out.println("clear:Clears the console");
	 		System.out.println("cd:Change the current working directory");
	 		System.out.println("ls:Lists the files and folders in the directory you specify");
	 		System.out.println("cat:Lists contents of files to the terminal window");
	 		System.out.println("mkdir:Allows you to create new directories in the filesystem");
	 		System.out.println("rmdir:Deletes a directory");
	 		System.out.println("rm:Deletes a file");
	 		System.out.println("more:Display files content and scroll down the output in one direction only");
	 		System.out.println("less:Display files content and scroll the output in two directions");
	 		System.out.println("pwd:Print the path of the working directory");
	 		System.out.println("mv:Move files");
	 		System.out.println("cp:Copy files");	
	 	}
	    
	      	public ArrayList<String> cat(String[] paths)
	    		{
	      		
	    			ArrayList<String>FileContent=new ArrayList<String>();
	    			try{
	    				for(int i=0;i<paths.length;i++) {
	    				
	    					File file=new File(paths[i]);	
	    						//check if the file is a directory
	    						if(file.isDirectory()) {
	    							System.out.println(file.getName()+" :Is a directory");
	    							continue;
	    						}
	    					
	    				  // Open the file that is the first 
	    				  // command line parameter
	    				  FileInputStream fstream = new FileInputStream(CurrentDirectory+"/"+paths[i]);
	    				 
	    				  // Get the object of DataInputStream
	    				  DataInputStream in = new DataInputStream(fstream);
	    				  BufferedReader br = new BufferedReader(new InputStreamReader(in));
	    				  String strLine;
	    				  //Read File Line By Line
	    				  while ((strLine = br.readLine()) != null)   {
	    				  // Print the content on the console
	    				  FileContent.add(strLine);
	    				  }
	    				
	    				  //Close the input stream
	    				  in.close();
	    				
	    				    }
	    			}catch (Exception e){//Catch exception if any
	    				  System.err.println("Error: " + e.getMessage());
	    				  }
	    			return FileContent;
	    			}
}
