package np.com.sourcecodegen;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class GetSysInfo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
				
		String nameOS = "os.name";  
				  String versionOS = "os.version";  
				  String architectureOS = "os.arch";
				  System.out.println("\n  The information about OS");
				  System.out.println("\nName of the OS: " + 
				System.getProperty(nameOS));
				  System.out.println("Version of the OS: " + 
				System.getProperty(versionOS));
				  System.out.println("Architecture of THe OS: " + 
				System.getProperty(architectureOS));
				  getInfo();
	}
	

	 static void getInfo() {
		    System.out.println("Available processors (cores): " + 
			        Runtime.getRuntime().availableProcessors());

			    /* Total amount of free memory available to the JVM */
			    System.out.println("Free memory (Megabytes): " + 
			        (Runtime.getRuntime().freeMemory()/(1024*1024)));

			    /* This will return Long.MAX_VALUE if there is no preset limit */
			    long maxMemory = Runtime.getRuntime().maxMemory();
			    /* Maximum amount of memory the JVM will attempt to use */
			    System.out.println("Maximum memory (Megabytes): " + 
			        (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory/(1024*1024)));
/*
			     Total memory currently available to the JVM 
			    System.out.println("Total memory available to JVM (Megabytes): " + 
			        Runtime.getRuntime().totalMemory()/(1024*1024));
			    	try {
						Runtime.getRuntime().exec("who");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println("Error no user name");
						e.printStackTrace();
					}
			    	*/
			    	/* Get a list of all filesystem roots on this system */
			    File[] roots = File.listRoots();

			    /* For each filesystem root, print some info */
			    for (File root : roots) {
			      System.out.println("File system root: " + root.getAbsolutePath());
			      System.out.println("Total space (Megabytes): " + root.getTotalSpace()/(1024*1024));
			      System.out.println("Free space (Megabytes): " + root.getFreeSpace()/(1024*1024));
			      System.out.println("Usable space (Megabytes): " + root.getUsableSpace()/(1024*1024));
			      
	    }
}
}
