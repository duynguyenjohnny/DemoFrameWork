package ultilites;

import java.io.File;

public class FolderFile {

	// E.g: createFolder(parentFolder/childFolder)
	// E.g: createFolder(C:\\parentFolder\\childFolder)
	public static boolean createFolder(String folderPath){
		File file = new File(folderPath);
		if (!file.exists()) {
			if (file.mkdir()) {
				return true;
			} else {
				return false;
			}
		}else{
			System.out.println("Directory was exist !");
			return false;
		}
	}

	// E.g: createMutilFolder(parentFolder/childFolder)
	// E.g: createMutilFolder(C:\\parentFolder\\childFolder)
	public static boolean createMutilFolder(String folderPath){
		File file = new File(folderPath);
		if (!file.exists()) {
			if (file.mkdirs()) {
				return true;
			} else {
				return false;
			}
		}else{
			System.out.println("Directory was exist !");
			return false;
		}
	}
}
