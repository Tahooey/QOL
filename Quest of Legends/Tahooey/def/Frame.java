package def;

import java.awt.*;

import javax.swing.*;

import wrld.WorldRunner;
import def.fil.BlocksDynamics;
import def.fil.World;

public class Frame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH=512,HEIGHT=512;
	public static final int SCALE=4;
	public static final int SPEED=1*SCALE;
	public static final String NAME="QOL";
	public static final Color BACKGROUND=Color.BLACK;
	
	public static QOL QOL = new QOL();
	
	public static final int UP=0,DOWN=1,LEFT=2,RIGHT=3,STILL=4;
	
	public Frame(){
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setTitle(NAME);
		setBackground(BACKGROUND);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		add(QOL);
		
		
		
		setVisible(true);
		
	}

}
