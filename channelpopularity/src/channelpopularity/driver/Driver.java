package channelpopularity.driver;

import channelpopularity.util.FileProcessor;
import channelpopularity.util.Results;

import java.io.IOException;
import java.nio.file.InvalidPathException;

import channelpopularity.helper.HelperClass;

/**
 * @author John Doe
 *
 */
public class Driver {
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 2;

	public static void main(String[] args) throws InvalidPathException,SecurityException,IOException {

		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if ((args.length != 2) || (args[0].equals("${input}")) || (args[1].equals("${output}"))) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			System.exit(0);
		}
		System.out.println("Hello World! Lets get started with the assignment");
		
		FileProcessor fp = null;
		try 
		{
			fp = new FileProcessor(args[0]);
		
			Results res = new Results(args[1]);
			HelperClass hp = new HelperClass();
			hp.InputParser(fp,res);
			res.writeToFile();
			res.writeToStdout();
		} 
		catch (InvalidPathException | SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		finally
		{
			fp.close();
		}
	}
}
