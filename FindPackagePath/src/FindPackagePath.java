import java.io.File;
import java.io.IOException;


public class FindPackagePath {
	
	FindPackagePath(File folder) {
		listDirectoryEntry(folder);
	}

	public static void main(String[] args) {
		File path = new File("PATH_TO_FOLDER");
		new FindPackagePath(path);
	}

	private void listDirectoryEntry(File folder) {
		boolean found = false;
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory() && !fileEntry.getName().equals("CVS")) {
				found = true;
				listDirectoryEntry(fileEntry);
			}
			if (found) {
				try {
					String path = fileEntry.getCanonicalPath();
					int srcIndex = path.indexOf("src");
					String newpath = path.substring(srcIndex + 4).replace("\\", ".");
					System.out.println(newpath);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				found = false;
			}
		}

	}
}
