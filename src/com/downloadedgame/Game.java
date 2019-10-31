package com.downloadedgame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.Random;

public class Game extends Canvas implements Runnable
{
	private static final long serialVersionUID = 4924646733948937610L;

	public static boolean inGame = true;
	private boolean running = false;
	private Thread thread;
	public static int WIDTH=1032, HEIGHT=563;
	private BufferedImage level=null;
	public boolean levelArr[][]=new boolean[64][64];
 
	private Handler handler;
	private KeyInput keyInput;
	private MouseInput minput;
	private Camera camera;
	
	Random rand = new Random();
	
	private void init()
	{

		camera=new Camera(0,0);
		handler = new Handler();
		minput=new MouseInput(handler,camera);
		keyInput=new KeyInput(handler);		
		this.addKeyListener(keyInput);
		this.addMouseListener(minput);
		
		BufferedImageLoader loader = new BufferedImageLoader();
		level=loader.loadImage("/res/level.png");	
		LoadLevel(level);

		for(int i=0;i<4;i++)
		handler.addObject(new BasicEnemy(300,300,50,50, handler, ID.BasicEnemy));

		handler.addObject(new HealthBar(camera.getX(), camera.getY(),300, handler, ID.HealthBar, camera));

	}
	


	public synchronized void start(){
		if(running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	public void run()
	{
		init();
		this.requestFocus();
		System.out.println("Thread has begun.");
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick(); 
				updates++;
				delta--;
			}
			render();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	} 

	private void tick()
	{
		for(int i =0;i<handler.object.size();i++) {
			if (handler.object.get(i).getId()==ID.Player) {
				camera.tick(handler.object.get(i));
			}}
		handler.tick();
		
	}
	
	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		/////////////////////////////////////////
		 
		if (inGame) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g2d.translate(-camera.getX(), -camera.getY());
		handler.render(g);
		g2d.translate(camera.getX(), camera.getY());
		}
		else 
			gameOver(g);
		////////////////////////////////////////
		
		g.dispose();
		bs.show();
	}
	
	public static float clamp(float var, float min, double max) {
		if(var>=max)
		return var=(float) max;
		else if (var<=min)
		return var=min;
		else return var;
	}
	
    private static void gameOver(Graphics g) {

        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 50);

		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
        g.setColor(Color.green);
        g.setFont(small);
        g.drawString(msg, 200,200);
    }
    private static void Uwon (Graphics g) {

        String msg = "You win";
        Font small = new Font("Helvetica", Font.BOLD, 75);

		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
        g.setColor(Color.green);
        g.setFont(small);
        g.drawString(msg, 200,200);
    }
    private void LoadLevel(BufferedImage image) {
    	int w = image.getWidth();
    	int h = image.getHeight();
  
    	
    	for (int xx=0; xx<w;xx++) {
    		for (int yy=0;yy<h;yy++) {
    			int pixel = image.getRGB(xx, yy);
    			int red = (pixel >> 16) & 0xff;
    			int green = (pixel >> 8) & 0xff;
    			int blue = (pixel) & 0xff;
    		
    			if(blue == 255)
    				handler.addObject(new Player(xx*32, yy*32,Player.width,Player.height, handler, ID.Player,keyInput));
    			if(red == 255) {
    				handler.addObject(new Block(xx*32, yy*32,32,32, ID.Block));
    				levelArr[xx][yy]=true;
    			}
    		}
    	}
    	
    }
        
	public static void main(String args[])
	{
		new Window(WIDTH, HEIGHT, "Vano`s gaming", new Game());
	}
}
