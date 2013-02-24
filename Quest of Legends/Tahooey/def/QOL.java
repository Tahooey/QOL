package def;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import def.fil.BlocksDynamics;
import def.fil.ImgDyn;
import def.fil.World;

import wrld.WorldRunner;

public class QOL extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;

	private Image dbImage;
	private Graphics dbg;
	static final int W = Frame.WIDTH, H = Frame.HEIGHT;
	static final Dimension gameDim = new Dimension(W, H);
	
	public boolean blankScreen=false;

	private Thread game;
	private volatile boolean running = false;
	//private long period = 6 * 1000000;

	public QOL() {
		setPreferredSize(gameDim);
		setBackground(Color.WHITE);
		setFocusable(true);
		requestFocus();
		// Handle all key inputs from user
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int KeyCode=e.getKeyCode();
				if(KeyCode==KeyEvent.VK_W){
					Camera.move(Frame.UP);
				}
				if(KeyCode==KeyEvent.VK_S){
					Camera.move(Frame.DOWN);
				}
				if(KeyCode==KeyEvent.VK_A){
					Camera.move(Frame.LEFT);
				}
				if(KeyCode==KeyEvent.VK_D){
					Camera.move(Frame.RIGHT);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				Camera.move(Frame.STILL);
			}

			@Override
			public void keyTyped(KeyEvent e) {

			}
		});

		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				//Mouse.isPressed=false;
				//Mouse.isReleased=true;
			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {
				//Mouse.isPressed=true;
				//Mouse.isReleased=false;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				//Mouse.isPressed=false;
				//Mouse.isReleased=true;
			}
		});

		addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
				/**int x=e.getX();
				int y=e.getY();
				
				Mouse.x=x;
				Mouse.y=y;**/

			}

			@Override
			public void mouseMoved(MouseEvent e) {
				/**int x=e.getX();
				int y=e.getY();
				
				Mouse.x=x;
				Mouse.y=y;**/

			}
		});
	}

	@Override
	public void run() {
		while (running) {
			gameUpdate();
			gameRender();
			paintScreen();
		}
	}
	
	static Thread WorldUpdater = new Thread(){
		public void run(){
			WorldRunner.updateBlocks();
		}
	};
	static Thread CamUpdater = new Thread(){
		public void run(){
			Camera.update();
		}
	};

	private void gameUpdate() {
		if (running && game != null) {
			CamUpdater.run();
			WorldUpdater.run();
		}
	}

	public void draw(Graphics g) {
		g.setColor(Color.blue);
		WorldRunner.drawBlocks(g);
	}

	private void gameRender() {
		if (dbImage == null) {
			dbImage = createImage(W, H);
			if (dbImage == null) {
				System.err.println("dbImage is still null!");
				return;
			} else {
				dbg = dbImage.getGraphics();
			}
		}
		dbg.setColor(Frame.BACKGROUND);
		dbg.fillRect(0, 0, W, H);
		draw(dbg);
	}

	private void paintScreen() {
		Graphics g;
		try {
			g = this.getGraphics();
			if (dbImage != null && g != null) {
				g.drawImage(dbImage, 0, 0, null);
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public void addNotify() {
		super.addNotify();
		BlocksDynamics.boot();
		ImgDyn.loadTerrainSheet();
		World.boot();
		WorldRunner.build();
		startGame();
	}

	private void startGame() {
		if (game == null || !running) {
			game = new Thread(this);
			running = true;
			game.start();
		}
	}

	public void stopGame() {
		if (running) {
			running = false;
		}
	}

	public void log(String i) {
		System.out.println(i);
	}

}
