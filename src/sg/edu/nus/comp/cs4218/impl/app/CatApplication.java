package sg.edu.nus.comp.cs4218.impl.app;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import sg.edu.nus.comp.cs4218.Application;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.CatException;

/**
 * The cat command concatenates the content of given files and prints on the
 * standard output.
 * 
 * <p>
 * <b>Command format:</b> <code>cat [FILE]...</code>
 * <dl>
 * <dt>FILE</dt>
 * <dd>the name of the file(s). If no files are specified, use stdin.</dd>
 * </dl>
 * </p>
 */
public class CatApplication implements Application {

	/**
	 * Runs the cat application with the specified arguments.
	 * 
	 * @param args
	 *            Array of arguments for the application. Each array element is
	 *            the path to a file. If no files are specified stdin is used.
	 * @param stdin
	 *            An InputStream. The input for the command is read from this
	 *            InputStream if no files are specified.
	 * @param stdout
	 *            An OutputStream. The output of the command is written to this
	 *            OutputStream.
	 * 
	 * @throws CatException
	 *             If the file(s) specified do not exist or are unreadable.
	 */
	@Override
	public void run(String[] args, InputStream stdin, OutputStream stdout)
			throws CatException {

		if(stdout == null){
			catWithNoOutputStream();
		}
		
		if (args == null || args.length == 0) {
			catWithNoArguments(stdin, stdout);
		} else {

			catWithArguments(args, stdout);
		}
	}

	/**
	 * @param args
	 * @param stdout
	 * @throws CatException
	 */
	private void catWithArguments(String[] args, OutputStream stdout) throws CatException {
		int numOfFiles = args.length;

		if (numOfFiles > 0) {
			Path filePath;
			Path[] filePathArray = new Path[numOfFiles];
			Path currentDir = Paths.get(Environment.currentDirectory);
			boolean isFileReadable = false;

			for (int i = 0; i < numOfFiles; i++) {
				filePath = currentDir.resolve(args[i]);
				isFileReadable = checkIfFileIsReadable(filePath);
				if (isFileReadable) {
					filePathArray[i] = filePath;
				}
			}

			// file could be read. perform cat command
			if (filePathArray.length != 0) {
				for (int j = 0; j < filePathArray.length; j++) {
					try {
						byte[] byteFileArray = Files
								.readAllBytes(filePathArray[j]);
						stdout.write(byteFileArray);
						stdout.write("\n".getBytes());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
			}
		}
	}

	/**
	 * @throws CatException
	 */
	private void catWithNoOutputStream() throws CatException {
		throw new CatException("OutputStream not provided");
	}

	private void catWithNoArguments(InputStream stdin, OutputStream stdout) throws CatException {
		if (stdin == null) {
			throw new CatException("InputStream not provided");
		}
		try {
			int intCount;
			while ((intCount = stdin.read()) != -1) {
				stdout.write(intCount);
			}
			stdout.write("\n".getBytes());
		} catch (Exception exIO) {
			exIO.printStackTrace();
		}
	}

	/**
	 * Checks if a file is readable.
	 * 
	 * @param filePath
	 *            The path to the file
	 * @return True if the file is readable.
	 * @throws CatException
	 *             If the file is not readable
	 */
	
	boolean checkIfFileIsReadable(Path filePath) throws CatException {
		
		if (Files.isDirectory(filePath)) {
			throw new CatException("This is a directory");
		}
		if (Files.exists(filePath) && Files.isReadable(filePath)) {
			return true;
		} else {
			throw new CatException("Could not read file");
		}
	}
}
