package def.fil;

import java.io.*;
import java.util.*;

import wrld.blcks.*;

public class BlocksDynamics {
	
	public static File blockDyn;
	public static FileWriter fw;
	public static Scanner s;
	
	public static Block[] b;
	
	public static void boot(){
		blockDyn=getFileInData("blocksDyn.qol");
		if(!blockDyn.exists()){
			try {
				blockDyn.createNewFile();
				write(blockDyn,"0");
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(!blockDyn.exists()){
				System.out.println("AN ERROR HAS OCCURED IN FILE WRITING");
			}
		}
		try {
			readFiles();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static File getFileInData(String i){
		return new File(defaultDirectory()+i);
	}
	
	public static void write(File f,String i) throws IOException{
		fw = new FileWriter(f);
		
		fw.write(i);
		
		fw.flush();
		fw.close();
	}
	
	public static void readFiles() throws FileNotFoundException{
		s=new Scanner(blockDyn);
		b = new Block[s.nextInt()];
		int ID=0;
		boolean coll=false;
		boolean vis=false;
		for(int i=0;i<b.length;i++){
			ID=s.nextInt();
			if(s.next().equals("true")){
				coll=true;
			}else{
				coll=false;
			}
			if(s.next().equals("true")){
				vis=true;
			}else{
				vis=false;
			}
			b[i]=new Block(ID,coll,vis,s.next());
		}
	}
	
	public static String defaultDirectory(){
		String os = System.getProperty("os.name").toUpperCase();
		String folder = "";
		
		String SEP = System.getProperty("file.separator");

		if(os.contains("MAC")){
			folder = System.getProperty("user.home") + SEP + "Library" + SEP + "Application Support" + SEP + "Tahooey"+SEP+"QOL"+SEP;
		}
		else if(os.contains("WIN")){
			folder = System.getProperty("user.home") + SEP + "AppData" + SEP + "Roaming" + SEP + "Tahooey"+SEP+"QOL"+SEP;
		}
		else {
			folder = System.getProperty("user.home") + SEP + "Tahooey"+SEP+"QOL"+SEP;
		}
		File f = new File(folder);
		if(!f.exists()) f.mkdir();
		return folder;
	}

}
