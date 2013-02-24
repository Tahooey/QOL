package def.fil;

import java.awt.image.*;
import java.io.*;
import java.net.*;

import javax.imageio.ImageIO;

import wrld.blcks.Block;

public class ImgDyn {
	
	public static BufferedImage terrainSheet;
	public static BufferedImage[] blocks;
	
	public static void loadTerrainSheet(){
		try {
			terrainSheet = loadFromDirectory("images/terrain.png");
			loadBlocks();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadBlocks(){
		blocks = new BufferedImage[BlocksDynamics.b.length];
		for(int i=0;i<blocks.length;i++){
			blocks[i]=getSubImage(terrainSheet,i*Block.w,0,Block.w,Block.h);
		}
	}
	
	public static BufferedImage loadFromDirectory(String i) throws IOException{
		File f = new File(defaultDirectory()+i);
		
		return ImageIO.read(f);
	}
	
	public static BufferedImage getSubImage(BufferedImage i,int x,int y,int w,int h){
		return i.getSubimage(x, y, w, h);
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
