package np.com.sourcecodegen;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		/*
		String mydata = "some string with 'the data i want' inside";
		Pattern pattern = Pattern.compile("'(.*?)'");
		Matcher matcher = pattern.matcher(mydata);
		if (matcher.find())
		{
		    System.out.println(matcher.group(0));
		}
		*/
		/*
		ButtonRelease event, serial 29, synthetic NO, window 0x1600001,
	    root 0x98, subw 0x0, time 8782107, (318,362), root:(1005,384),
	    state 0x100, button 1, same_screen YES

	ButtonPress
	*/
		String pat = "\\\\ButtonPress\\\\W+(?:\\\\w+\\\\W+){1,6}?ButtonRelease\\\\b.";
		String pat2 = "(?=Button).*(?= )";
		String finalPat = "(?=Button).*(?= )";
		Pattern pattern = Pattern.compile(finalPat);
		Pattern pattern2 = Pattern.compile("(?<=Button)(.*)(?= )");
		
		//Build command 
        List<String> commands = new ArrayList<String>();
        commands.add("xev");
        //Add arguments
//        commands.add("/home/sndp/a");
        System.out.println(commands);

        //Run macro on target
        ProcessBuilder pb = new ProcessBuilder(commands);
        pb.directory(new File("/home/"));
        pb.redirectErrorStream(true);
        Process process = pb.start();

        //Read output
        StringBuilder out = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = null, previous = null;
        while ((line = br.readLine()) != null)
		
        	if (!line.equals(previous)) {
                previous = line;
                out.append(line);
                Matcher matcher = pattern.matcher(line);
                Matcher matcher2 = pattern2.matcher(line);
                if (matcher.find())
        		{
        		    System.out.println(matcher.group(0));
        		}
        		else {
        			
//                                System.out.println(line);
        		}
        		}
        	
        //Check result
        if (process.waitFor() == 0) {
            System.out.println("Terminated!!");
            System.exit(0);
        }

        //Abnormal termination: Log command parameters and output and throw ExecutionException
        System.err.println(commands);
        System.err.println(out.toString());
        System.exit(1);
    }

	}
