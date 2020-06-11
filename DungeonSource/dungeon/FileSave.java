package dungeon;

import java.io.*;
import java.nio.file.*;

public class FileSave implements Memento {

	private String fileName;
	
	public FileSave(String fileName)
	{
		this.fileName = fileName;
	}
	
	@Override
	public byte[] getSavedState() {
		
		try {
			byte[] returnBytes = Files.readAllBytes(Paths.get(fileName));
			return returnBytes;
		}
		catch(FileNotFoundException ex)
		{
			System.out.println(ex.getMessage());
		}
		catch(IOException ex)
		{
			System.out.println(ex.getMessage());
		}
		
		return null;
		
	}

	@Override
	public void setState(byte[] stateToSave) {
		
		try {
			FileOutputStream fos = new FileOutputStream(fileName);
			fos.write(stateToSave);
			fos.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
		catch(IOException ex) {
			System.out.println(ex.getMessage());
		}
		
	}

}