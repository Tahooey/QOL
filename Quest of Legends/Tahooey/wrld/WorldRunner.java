package wrld;

import java.awt.*;

import def.*;
import def.fil.ImgDyn;

import wrld.blcks.*;

public class WorldRunner {
	
	static int smallestOnscreenwd = 0, smallestOnscreenhd = 0;
	static int largestOnscreenwd = 0, largestOnscreenhd = 0;

	static int smallestOnscreenwu = 0, smallestOnscreenhu = 0;
	static int largestOnscreenwu = 0, largestOnscreenhu = 0;
	
	public static Block[][][] B;
	
	public static void build(){
		for(int k=0;k<2;k++){
		for(int i=0;i<B[k].length;i++){
			for(int j=0;j<B[k][i].length;j++){
				B[k][i][j].x=j;
				B[k][i][j].y=i;
				
				B[k][i][j].IMG=ImgDyn.blocks[B[k][i][j].ID];
				
				B[k][i][j].boot();
			}
		}
		}
	}
	
	public static void updateBlocks(){
		int finw=0,finh=0;
		
		smallestOnscreenwd=Camera.x/(Block.w*def.Frame.SCALE)*-1;
		largestOnscreenwd=smallestOnscreenwd+(def.Frame.WIDTH/(Block.h*def.Frame.SCALE))+1;
		smallestOnscreenhd=Camera.y/(Block.w*def.Frame.SCALE)*-1;
		largestOnscreenhd=smallestOnscreenhd+(def.Frame.HEIGHT/(Block.h*def.Frame.SCALE))+1;
		
		for(int h=smallestOnscreenhd;h<largestOnscreenhd;h++){
			for(int w=smallestOnscreenwd;w<largestOnscreenwd;w++){
				finw=w;
				finh=h;
				if(h<=-1){
					finh=0;
				}
				if(w<=-1){
					finw=0;
				}
				if(h>=B[0].length){
					finh=B[0].length-1;
				}
				if(w>=B[0][0].length){
					finw=B[0][0].length-1;
				}
				B[0][finh][finw].update();
				B[1][finh][finw].update();
			}
		}
	}
	
	public static void drawBlocks(Graphics g){
		int finw=0,finh=0;
		
		smallestOnscreenwd=Camera.x/(Block.w*def.Frame.SCALE)*-1;
		largestOnscreenwd=smallestOnscreenwd+(def.Frame.WIDTH/(Block.h*def.Frame.SCALE))+1;
		smallestOnscreenhd=Camera.y/(Block.w*def.Frame.SCALE)*-1;
		largestOnscreenhd=smallestOnscreenhd+(def.Frame.HEIGHT/(Block.h*def.Frame.SCALE))+1;
		
		for(int h=smallestOnscreenhd;h<largestOnscreenhd;h++){
			for(int w=smallestOnscreenwd;w<largestOnscreenwd;w++){
				finw=w;
				finh=h;
				if(h<=-1){
					finh=0;
				}
				if(w<=-1){
					finw=0;
				}
				if(h>=B[0].length){
					finh=B[0].length-1;
				}
				if(w>=B[0][0].length){
					finw=B[0][0].length-1;
				}
				B[0][finh][finw].draw(g);
				B[1][finh][finw].draw(g);
			}
		}
	}

}
