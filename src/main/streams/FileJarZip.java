package streams;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * All the java.io file streams are deprecated.
 * Therefore, we will focus on the new IO file features provided by java.nio
 *
 * This new java.nio provides a bunch of features such as:
 *  - File system support
 *  - Scalability
 *  - Improved exception reporting
 *
 * Plus, it provides the Path and Paths files which give us too, some great features at our disposition.
 */
public class FileJarZip
{

	public static final String READ_RESOURCE_FILENAME = "./resources/readWithNewLineAtEOF.txt";
	public static final String WRITE_RESOURCE_FILENAME = "./resources/write.txt";
	public static final String WRITE_RESOURCE_ZIP_FILENAME = "./resources/zipWrite.zip";

	public FileJarZip(){}

	/**
	 * We will create a new zip file and inside its file system, we will copy the read file but with a new name
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	public void copyInsideZipWithNewName(String newFileName)
	throws URISyntaxException, IOException
	{
		Path zipPath = Paths.get(WRITE_RESOURCE_ZIP_FILENAME);
		try(FileSystem zipFileSystem = createZip(zipPath)){
			Path sourcePath = Paths.get(READ_RESOURCE_FILENAME);
			Path destinationPathInsideZip;
			destinationPathInsideZip = zipFileSystem.getPath("/".concat(newFileName));
			copyFile(sourcePath, destinationPathInsideZip);
		}
	}

	public void writeInsideZip()
	throws URISyntaxException, IOException
	{
		Path zipPath = Paths.get(WRITE_RESOURCE_ZIP_FILENAME);
		try(FileSystem zipFileSystem = createZip(zipPath)){

			// Source and destination have the same name
			Path source = Paths.get(READ_RESOURCE_FILENAME);
			Path destinationInsideZip = zipFileSystem.getPath(READ_RESOURCE_FILENAME);
			String[] allLines = read(source);
			write(destinationInsideZip, allLines);
		}
	}


	protected FileSystem createZip(Path path)
	throws URISyntaxException, IOException
	{
		String zipPath = path.toUri().getPath();
		URI uri = new URI("jar:file", zipPath, null);

		Map<String, String> properties = new HashMap<String,String>();
		properties.put("create", "true");

		return FileSystems.newFileSystem(uri, properties);
	}

	protected void copyFile(Path source, Path destination)
	throws IOException{
		Files.createDirectories(destination.getParent());
		Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
	}

	protected void write(Path destination, String[] allLines)
	throws IOException {
		Files.createDirectories(destination.getParent());
		try(BufferedWriter writer = Files.newBufferedWriter(destination,
															StandardCharsets.UTF_8,
															StandardOpenOption.CREATE,
															StandardOpenOption.APPEND)){
			for(String string: allLines){
				writer.write(string);
				writer.newLine();
			}
		}
	}

	protected String[] read(Path source)
	throws IOException{
		Object[] objectArray = Files.readAllLines(source).toArray();
		return Arrays.copyOf(objectArray, objectArray.length, String[].class);
	}

}
