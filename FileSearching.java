import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileSearching {

	static String searchWord = "";
	static int countOfFiles = 0;
	static int countOfFolders = 0;

	public static void getFiles(String directory) throws FileNotFoundException, IOException{
		File[] filesInDirectory = new File(directory).listFiles();
		if(filesInDirectory != null) {
			for(File f : filesInDirectory){
				String fPath = f.getAbsolutePath();
				if(f.getName().equalsIgnoreCase(searchWord)) {
					countOfFolders++;
					addFoldersToFile(fPath);
				}
				if(!(f.isDirectory()) && fPath.contains(".") && 
						searchWord.equalsIgnoreCase(fPath.substring(
								fPath.lastIndexOf(".")-searchWord.length(),
								fPath.lastIndexOf(".")))) {
					countOfFiles++;
					addFilesToFile(fPath);
				}
				getFiles(f.getAbsolutePath());
			}
		}

	}
	public static void addFilesToFile(String s) throws FileNotFoundException, IOException {
		try(BufferedWriter brOut = new BufferedWriter
				(new FileWriter("F:\\Files\\FileOf("+searchWord+").txt",true))){
			brOut.append(s+"\n");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void addFoldersToFile(String s) throws FileNotFoundException, IOException {
		try(BufferedWriter brOut = new BufferedWriter
				(new FileWriter("F:\\Files\\FolderOf("+searchWord+").txt",true))){
			brOut.append(s+"\n");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {

		Scanner input = new Scanner(System.in);
		System.out.print("Enter the file/folder to be found:");
		searchWord = input.nextLine();
		File[] allFiles = File.listRoots();
		for(File f: allFiles) {
			getFiles(f.getPath());
		}

		System.out.println("Count");
		if(countOfFiles!=0) {
			System.out.println("Files:"+countOfFiles);
			System.out.println("Files paths are stored in this file: "
					+ "\"F:\\Files\\FileOf("+searchWord+").txt\"");
		}else {
			System.out.println("No Files Found");
		}
		if(countOfFolders!=0) {
			System.out.println("Folders:"+countOfFolders);
			System.out.println("Folders paths are stored in this file: "
					+ "\"F:\\Files\\FileOf("+searchWord+").txt\"");
		}else {
			System.out.println("No Folders Found");
		}
		input.close();

	}

}

