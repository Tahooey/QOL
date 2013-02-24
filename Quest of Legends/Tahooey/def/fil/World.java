package def.fil;

import java.io.*;
import java.util.*;

import wrld.*;
import wrld.blcks.*;

public class World {
	
	public static File worldupper;
	public static File worldlower;
	public static FileWriter fw;
	public static Scanner s;
	
	public static void boot(){
		worldupper=getFileInData("worldupper.qol");
		if(!worldupper.exists()){
			try {
				worldupper.createNewFile();
				WriteWorldUpper();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}		
		try {
			ReadWorldUpper();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		worldlower=getFileInData("worldlower.qol");
		if(!worldlower.exists()){
			try {
				worldlower.createNewFile();
				WriteWorldLower();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}		
		try {
			ReadWorldLower();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void WriteWorldLower() throws IOException{
		fw = new FileWriter(worldlower);
		
		fw.write("512 512 \r\n");
			for(int i=0;i<512;i++){
				for(int j=0;j<512;j++){
					fw.write("1 ");
				}
				fw.write("\r\n");
		}
		
		fw.flush();
		fw.close();
	}
	
	public static void ReadWorldLower() throws FileNotFoundException{
		s = new Scanner(worldlower);
		int b=s.nextInt()+s.nextInt();
		for(int i=0;i<WorldRunner.B[0].length;i++){
			for(int j=0;j<WorldRunner.B[0][i].length;j++){
				int k = s.nextInt();
				WorldRunner.B[0][i][j]=new Block(BlocksDynamics.b[k].ID,BlocksDynamics.b[k].isCollidable,BlocksDynamics.b[k].isVisible,BlocksDynamics.b[k].Name);
						//BlocksDynamics.b[s.nextInt()];
			}
		}
	}
	
	public static void WriteWorldUpper() throws IOException{
		fw = new FileWriter(worldupper);
		
		fw.write("512 512 \r\n");
			for(int i=0;i<512;i++){
				for(int j=0;j<512;j++){
					fw.write("0 ");
				}
				fw.write("\r\n");
		}
		
		fw.flush();
		fw.close();
	}
	
	public static void ReadWorldUpper() throws FileNotFoundException{
		s = new Scanner(worldupper);		
		WorldRunner.B=new Block[2][s.nextInt()][s.nextInt()];
		for(int i=0;i<WorldRunner.B[1].length;i++){
			for(int j=0;j<WorldRunner.B[1][i].length;j++){
				int k = s.nextInt();
				WorldRunner.B[1][i][j]=new Block(BlocksDynamics.b[k].ID,BlocksDynamics.b[k].isCollidable,BlocksDynamics.b[k].isVisible,BlocksDynamics.b[k].Name);
						//BlocksDynamics.b[s.nextInt()];
			}
		}
	}
	
	public static File getFileInData(String i){
		return new File(defaultDirectory()+i);
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
