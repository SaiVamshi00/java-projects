import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class SnakeGame extends JPanel implements ActionListener,KeyListener{
    private class Tile{
        int x;
        int y;
        Tile(int x,int y){
            this.x=x;
            this.y=y;
        }
    }
     int boardHeight;
     int boardWidth;
     int titleSize = 25;
     
     //Snake
     Tile snakeHead;
     ArrayList<Tile>snakeBody;
     //Food
     Tile food;
     Random random;

     //game logic
     Timer gameLoop;
     int velocityX;
     int velocityY;
     boolean gameOver = false;

     SnakeGame(int boardWidth,int boardHeight){
        this.boardWidth=boardWidth;
        this.boardHeight=boardHeight;
        setPreferredSize(new Dimension(this.boardWidth,this.boardHeight));
        setBackground(Color.black);
        addKeyListener(this);
        setFocusable(true);

        snakeHead = new Tile(5, 5);
        snakeBody = new ArrayList<Tile>();

        food = new Tile(10,10);
        random = new Random();
        placeFood();
        
        velocityX=0;
        velocityY=1;

        gameLoop = new Timer(100,this);
        gameLoop.start();
     }

     public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
     }

     public void draw(Graphics g){
          //Grid

        //   for(int i=0;i< boardWidth/titleSize;i++){
        //     g.drawLine(i*titleSize,0,i*titleSize,boardHeight);
        //     g.drawLine(0, i*titleSize, boardWidth, i*titleSize);
        //   }
        // Food
        g.setColor(Color.red);
        g.fillRect(food.x*titleSize,food.y*titleSize, titleSize, titleSize);


        // Snake
        g.setColor(Color.green);
        g.fillRect(snakeHead.x*titleSize, snakeHead.y*titleSize, titleSize, titleSize);

        //snake Body
        for(int i=0;i<snakeBody.size();i++){
            Tile snakePart = snakeBody.get(i);
            g.fillRect(snakePart.x*titleSize, snakePart.y*titleSize, titleSize, titleSize);
        }

        //Score
        g.setFont(new Font("Arial",Font.PLAIN,16));
        if(gameOver){
            g.setColor(Color.red);
            g.drawString("Game Over: "+String.valueOf(snakeBody.size()), titleSize-16,titleSize);
        }
        else{
            g.drawString("Score: "+String.valueOf(snakeBody.size()), titleSize-16, titleSize);
        }
     }

     public void placeFood(){
        food.x = random.nextInt(boardWidth/titleSize);
        food.y = random.nextInt(boardHeight/titleSize);
     }
     public boolean collision(Tile t1,Tile t2){
        return t1.x==t2.x && t1.y==t2.y;
     }
public void move(){
    if(collision(snakeHead, food)){
        snakeBody.add(new Tile(food.x,food.y));
        placeFood();
    }
// snake Body
for(int i=snakeBody.size()-1;i>=0;i--){
    Tile snakePart = snakeBody.get(i);
    if(i==0){
       snakePart.x = snakeHead.x;
       snakePart.y=snakeHead.y;
    }
    else{
        Tile preSnakePart = snakeBody.get(i-1);
        snakePart.x = preSnakePart.x;
        snakePart.y = preSnakePart.y;
    }
}

    //snake Head
    snakeHead.x+=velocityX;
    snakeHead.y+=velocityY;

    //game over conditions
    for(int i=0;i<snakeBody.size();i++){
        Tile snakePart = snakeBody.get(i);
        if(collision(snakeHead, snakePart)){
            gameOver=true;
        }
    }
    if(snakeHead.x*titleSize<0||snakeHead.x * titleSize >boardWidth||snakeHead.y*titleSize<0||snakeHead.y*titleSize>boardHeight){
     gameOver=true;
    }
}
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if(gameOver){
            gameLoop.stop();
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_UP && velocityY!=1){
            velocityX=0;
            velocityY=-1;
        }
        else if(e.getKeyCode()==KeyEvent.VK_DOWN && velocityY!=-1){
            velocityX=0;
            velocityY=1;
        }
        else if(e.getKeyCode()==KeyEvent.VK_LEFT && velocityX!=1){
            velocityX=-1;
            velocityY=0;
        }
        else if(e.getKeyCode()==KeyEvent.VK_RIGHT && velocityX!=-1){
            velocityX=1;
            velocityY=0;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }
   
    
}
