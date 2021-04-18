/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bejeweled.blitz;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.List;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author Farhan
 */
public class GamePlayFrame extends javax.swing.JFrame {
    
    TimerPanel timer= new TimerPanel();
    private Time myTime=new Time();
    private Timer uiTimer;
    private Timer uiScore;
    Board gemBoard;
    private int counter = 0;
    private int[][] coordinates = new int[2][2];
    private int[][] blast_coordinates = new int[3][2];
    private JButton[][] tiles;
    ArrayList<Gem> listOfGems;
    public static int levelType;
//    private final ImageIcon hexagon = new ImageIcon(this.getClass().getResource("/hexagon.png").getImage());
    private Icon hexagon = new ImageIcon("D:\\FAST\\Semester 5\\Object Oriented Analysis and Design\\Project\\Bonus Part\\hexagon.PNG");
    private Icon diamond = new ImageIcon("D:\\FAST\\Semester 5\\Object Oriented Analysis and Design\\Project\\Bonus Part\\diamond.PNG");
    private Icon circle = new ImageIcon("D:\\FAST\\Semester 5\\Object Oriented Analysis and Design\\Project\\Bonus Part\\circle.PNG");
    private Icon triangle = new ImageIcon("D:\\FAST\\Semester 5\\Object Oriented Analysis and Design\\Project\\Bonus Part\\triangle.PNG");
    private Icon square = new ImageIcon("D:\\FAST\\Semester 5\\Object Oriented Analysis and Design\\Project\\Bonus Part\\square.PNG");
    private Icon liner = new ImageIcon("D:\\FAST\\Semester 5\\Object Oriented Analysis and Design\\Project\\Bonus Part\\liner.PNG");

    /**
     * Creates new form BoardFrame
     */
    public GamePlayFrame() {
//        initComponents();
        jLabel1 = new javax.swing.JLabel();
        
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1307, 700));
        setSize(new java.awt.Dimension(0, 0));

        jLabel1.setIcon(new javax.swing.ImageIcon("D:\\FAST\\Semester 5\\Object Oriented Analysis and Design\\Project\\Bonus Part\\background images\\gameframe.jpg")); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, java.awt.BorderLayout.CENTER);

        JPanel board = new JPanel();
        
        
        board.setLayout(new GridLayout(8, 8));
        
        ButtonHandler handler = new ButtonHandler();
        tiles = new JButton[8][8];
        gemBoard = new Board();
        listOfGems = gemBoard.createBoard();
        while(this.gemBoard.findOccurences())
        {
            this.gemBoard.updateBoard();
        }
        this.listOfGems=gemBoard.getListOfGems();
        for (int x = 0; x < 8; x++) {

            for (int y = 0; y < 8; y++) {

                tiles[x][y] = new JButton();

                //tiles[x][y].setActionCommand(x + " " + y);
                //tiles[x][y].setIcon(diamond);
                if (listOfGems.get(8 * x + y).getValue() == 1) {
                    tiles[x][y].setIcon(circle);
                }
                if (listOfGems.get(8 * x + y).getValue() == 2) {
                    tiles[x][y].setIcon(triangle);
                }
                if (listOfGems.get(8 * x + y).getValue() == 3) {
                    tiles[x][y].setIcon(square);
                }
                if (listOfGems.get(8 * x + y).getValue() == 4) {
                    tiles[x][y].setIcon(diamond);
                }
                if (listOfGems.get(8 * x + y).getValue() == 5) {
                    tiles[x][y].setIcon(hexagon);
                }
                tiles[x][y].addActionListener(handler);
                tiles[x][y].setBackground(Color.black);
                board.add(tiles[x][y]);
            }
        }
        board.setBackground(Color.BLACK);
        board.setPreferredSize(new Dimension(650, 0));
        
        add(board, BorderLayout.EAST);
//        timer.setBackground(Color.YELLOW);
        //timer.setSize(40, 40);
//        JButton btn=new JButton("Hello");
//        btn.setPreferredSize(new Dimension(40, 40));
//        btn.setBounds(0, 0, 120, 50);
//       timer.add(btn);

//        JButton btn1=new JButton("world");
//        btn1.setPreferredSize(new Dimension(40, 40));
//        timer.add(btn1);
        timer.setPreferredSize(new Dimension(300, 300));
        add(timer, BorderLayout.WEST);
        this.showTimer();
        this.showScore();
        GamePlayFrame.playSound("D:\\FAST\\Semester 5\\Object Oriented Analysis and Design\\Project\\Bonus Part\\sounds\\gameplay.wav");
    }
    public static void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
    public void loadLevel() {
        if (GamePlayFrame.levelType == 1) {
            this.myTime.setMin(2);
            this.myTime.setSec(60);
        } else if (GamePlayFrame.levelType == 2) {
            this.myTime.setMin(1);
            this.myTime.setSec(60);
        } else {
            this.myTime.setMin(0);
            this.myTime.setSec(60);
        }
    }
    public void showTimer() {
        
        this.loadLevel();
        //timer.setSecLabel(myTime.getSec());
        timer.setMinLabel(myTime.getMin());
        this.uiTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                myTime.setSec((myTime.getSec() - 1));
                timer.setSecLabel(myTime.getSec());
                LoginFrame.playSound("D:\\FAST\\Semester 5\\Object Oriented Analysis and Design\\Project\\Bonus Part\\sounds\\clock.wav");
                if (myTime.getSec() == 0) {
                    if (myTime.getMin() == 0) {
                        uiTimer.stop();
                        uiScore.stop();
                        GameOverFrame gameOver=new GameOverFrame();
                        gameOver.setVisible(true);
                        dispose();
                    } else {
                        myTime.setMin((myTime.getMin() - 1));
                        timer.setMinLabel(myTime.getMin());
                        myTime.setSec(59);
                        timer.setSecLabel(myTime.getSec());
                    }
                }
            }
        });
        this.uiTimer.start();
    }
    
    public void showScore() {
        this.gemBoard.setGameScore(0);
        //timer.setCurrenntScoreLabel(this.gemBoard.getGameScore());
        this.uiScore = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(gemBoard.getGameScore());
                timer.setCurrenntScoreLabel(gemBoard.getGameScore());
                if (gemBoard.getGameScore() > 100) {
                    uiScore.stop();
                    uiTimer.stop();
                    GameOverFrame gameOver=new GameOverFrame();
                    gameOver.setVisible(true);
                    dispose();
                }
            }
        });
        this.uiScore.start();
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1307, 700));
        setSize(new java.awt.Dimension(0, 0));
        getContentPane().setLayout(null);

        jLabel1.setIcon(new javax.swing.ImageIcon("D:\\FAST\\Semester 5\\Object Oriented Analysis and Design\\Project\\Bonus Part\\background images\\menu.jpg")); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1290, 660);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GamePlayFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GamePlayFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GamePlayFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GamePlayFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GamePlayFrame().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

    //coordinates[0][0] contains source i
    //coordinates[0][1] contains source j
    //coordinates[1][0] contains destination i
    //coordinates[1][1] contains destination j
    private boolean isValidMove() {
        if ((this.coordinates[0][0] - 1) >= 0) {
            if ((this.coordinates[0][0] - 1) == this.coordinates[1][0] && this.coordinates[0][1] == this.coordinates[1][1]) {
                return true;
            }
        }
        if ((this.coordinates[0][1] - 1) >= 0) {
            if (this.coordinates[0][0] == this.coordinates[1][0] && (this.coordinates[0][1] - 1) == this.coordinates[1][1]) {
                return true;
            }
        }
        if ((this.coordinates[0][1] + 1) < 8) {
            if (this.coordinates[0][0] == this.coordinates[1][0] && (this.coordinates[0][1] + 1) == this.coordinates[1][1]) {
                return true;
            }
        }
        if ((this.coordinates[0][0] + 1) < 8) {
            if ((this.coordinates[0][0] + 1) == this.coordinates[1][0] && this.coordinates[0][1] == this.coordinates[1][1]) {
                return true;
            }
        }
        return false;
    }
    public void createGUI() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (listOfGems.get(8 * x + y).getValue() == 1) {
                    tiles[x][y].setIcon(circle);
                }
                if (listOfGems.get(8 * x + y).getValue() == 2) {
                    tiles[x][y].setIcon(triangle);
                }
                if (listOfGems.get(8 * x + y).getValue() == 3) {
                    tiles[x][y].setIcon(square);
                }
                if (listOfGems.get(8 * x + y).getValue() == 4) {
                    tiles[x][y].setIcon(diamond);
                }
                if (listOfGems.get(8 * x + y).getValue() == 5) {
                    tiles[x][y].setIcon(hexagon);
                }
            }
        }
    }

    
    
    public void dropGemsHorizontally() {
        int looper = this.blast_coordinates[0][0];
        int x_coordinate = this.blast_coordinates[0][0];
        int y_coordinate = this.blast_coordinates[0][1];
        while (looper > 0) {
            final int x = x_coordinate;
            final int y = y_coordinate;
            int delay = 500;//specify the delay for the timer
            
            Timer timer1 = new Timer(delay, e -> {
                //The following code will be executed once the delay is reached
                this.tiles[x][y].setBackground(Color.BLACK);
                this.tiles[x][y + 1].setBackground(Color.BLACK);
                this.tiles[x][y + 2].setBackground(Color.BLACK);
                
                this.tiles[x][y].setIcon(this.tiles[x - 1][y].getIcon());
                this.tiles[x][y + 1].setIcon(this.tiles[x - 1][y + 1].getIcon());
                this.tiles[x][y + 2].setIcon(this.tiles[x - 1][y + 2].getIcon());
                
                this.createGUI();
            });
            timer1.setRepeats(false);//make sure the timer only runs once
            timer1.start();
            
            --looper;
            --x_coordinate;

        }
        //  upper row
        this.gemBoard.dropGemHorizontally(blast_coordinates);
        this.listOfGems = this.gemBoard.getListOfGems();
        int delay = 500;//specify the delay for the timer
        Timer timer1 = new Timer(delay, e -> {
            //The following code will be executed once the delay is reached

            //this.tiles[0][y_coordinate].setIcon(null);
            //this.tiles[0][y_coordinate + 1].setIcon(null);
            //this.tiles[0][y_coordinate + 2].setIcon(null);
            
            this.tiles[0][y_coordinate].setBackground(Color.BLACK);
            this.tiles[0][y_coordinate + 1].setBackground(Color.BLACK);
            this.tiles[0][y_coordinate + 2].setBackground(Color.BLACK);
            this.createGUI();
        });
        timer1.setRepeats(false);//make sure the timer only runs once
        timer1.start();

        //delay = 500;//specify the delay for the timer
        //Timer timer2 = new Timer(delay, e -> {
            //The following code will be executed once the delay is reached
            for (int k = 0; k < 3; k++) {
                if (this.listOfGems.get(y_coordinate + k).getValue() == 1) {
                    this.tiles[0][y_coordinate + k].setIcon(circle);
                }
                if (this.listOfGems.get(y_coordinate + k).getValue() == 2) {
                    this.tiles[0][y_coordinate + k].setIcon(triangle);
                }
                if (this.listOfGems.get(y_coordinate + k).getValue() == 3) {
                    this.tiles[0][y_coordinate + k].setIcon(square);
                }
                if (this.listOfGems.get(y_coordinate + k).getValue() == 4) {
                    this.tiles[0][y_coordinate + k].setIcon(diamond);
                }
                if (this.listOfGems.get(y_coordinate + k).getValue() == 5) {
                    this.tiles[0][y_coordinate + k].setIcon(hexagon);
                }
            }

        //});
        //timer2.setRepeats(false);//make sure the timer only runs once
        //timer2.start();
        
    }
    
    public void dropGemsVertically() {
        int looper = this.blast_coordinates[2][0];
        int x_coordinate = this.blast_coordinates[2][0];
        int y_coordinate = this.blast_coordinates[2][1];
        while (looper > 2) {
            final int x = x_coordinate;
            final int y = y_coordinate;
            int delay = 500;//specify the delay for the timer
            
            Timer timer1 = new Timer(delay, e -> {
                //The following code will be executed once the delay is reached
                this.tiles[x][y].setBackground(Color.BLACK);
                
                this.tiles[x][y].setIcon(this.tiles[x - 3][y].getIcon());
                this.createGUI();
            });
            timer1.setRepeats(false);//make sure the timer only runs once
            timer1.start();
 
            --looper;
            --x_coordinate;

        }
        //  upper row
        this.gemBoard.dropGemVertically(blast_coordinates);
        this.listOfGems = this.gemBoard.getListOfGems();
        int delay = 500;//specify the delay for the timer
        Timer timer1 = new Timer(delay, e -> {
            //The following code will be executed once the delay is reached

            //this.tiles[0][y_coordinate].setIcon(null);
            //this.tiles[1][y_coordinate].setIcon(null);
            //this.tiles[2][y_coordinate].setIcon(null);
            
            this.tiles[0][y_coordinate].setBackground(Color.BLACK);
            this.tiles[1][y_coordinate].setBackground(Color.BLACK);
            this.tiles[2][y_coordinate].setBackground(Color.BLACK);
            this.createGUI();
        });
        timer1.setRepeats(false);//make sure the timer only runs once
        timer1.start();

        //delay = 500;//specify the delay for the timer
        //Timer timer2 = new Timer(delay, e -> {
            //The following code will be executed once the delay is reached
            for (int k = 0; k < 3; k++) {
                if (this.listOfGems.get(8*k+y_coordinate).getValue() == 1) {
                    this.tiles[k][y_coordinate].setIcon(circle);
                }
                if (this.listOfGems.get(8*k+y_coordinate).getValue() == 2) {
                    this.tiles[k][y_coordinate].setIcon(triangle);
                }
                if (this.listOfGems.get(8*k+y_coordinate).getValue() == 3) {
                    this.tiles[k][y_coordinate].setIcon(square);
                }
                if (this.listOfGems.get(8*k+y_coordinate).getValue() == 4) {
                    this.tiles[k][y_coordinate].setIcon(diamond);
                }
                if (this.listOfGems.get(8*k+y_coordinate).getValue() == 5) {
                    this.tiles[k][y_coordinate].setIcon(hexagon);
                }
            }

        //});
        //timer2.setRepeats(false);//make sure the timer only runs once
       // timer2.start();
        
    }
    public void blastUISpecialGem() {
        this.listOfGems = this.gemBoard.getListOfGems();
        int index = 0;
        for (int k = 0; k < 64; k++) {
            if (this.listOfGems.get(k).getValue() == 6) {
                int x_coordinate = k / 8;
                int y_coordinate = k % 8;
                this.blast_coordinates[index][0] = x_coordinate;
                this.blast_coordinates[index][1] = y_coordinate;
                ++index;
                this.tiles[x_coordinate][y_coordinate].setIcon(liner);

            }
        }
        
        this.dropSpecialGemsCase();
    }
    public void dropSpecialGemsCase() {
        int x_coordinate = this.blast_coordinates[0][0];
        int y_coordinate = this.blast_coordinates[0][1];
        final int x = x_coordinate;
        final int y = y_coordinate;
        int delay = 1000;//specify the delay for the timer

        Timer timer1 = new Timer(delay, e -> {
            //The following code will be executed once the delay is reached
            this.tiles[0][y].setIcon(null);
            this.tiles[0][y].setBackground(Color.yellow);

            this.tiles[1][y].setIcon(null);
            this.tiles[1][y].setBackground(Color.yellow);

            this.tiles[2][y].setIcon(null);
            this.tiles[2][y].setBackground(Color.yellow);

            this.tiles[3][y].setIcon(null);
            this.tiles[3][y].setBackground(Color.yellow);

            this.tiles[4][y].setIcon(null);
            this.tiles[4][y].setBackground(Color.yellow);

            this.tiles[5][y].setIcon(null);
            this.tiles[5][y].setBackground(Color.yellow);

            this.tiles[6][y].setIcon(null);
            this.tiles[6][y].setBackground(Color.yellow);

            this.tiles[7][y].setIcon(null);
            this.tiles[7][y].setBackground(Color.yellow);
            LoginFrame.playSound("D:\\FAST\\Semester 5\\Object Oriented Analysis and Design\\Project\\Bonus Part\\sounds\\gernade.wav");
            //this.createGUI();
        });
        timer1.setRepeats(false);//make sure the timer only runs once
        timer1.start();

        //  upper row
        this.gemBoard.dropSpecialGemCase(blast_coordinates);
        this.listOfGems = this.gemBoard.getListOfGems();
        delay = 2000;//specify the delay for the timer
        Timer timer2 = new Timer(delay, e -> {
            //The following code will be executed once the delay is reached

            //this.tiles[0][y_coordinate].setIcon(null);
            //this.tiles[1][y_coordinate].setIcon(null);
            //this.tiles[2][y_coordinate].setIcon(null);
            this.tiles[0][y_coordinate].setBackground(Color.BLACK);
            this.tiles[1][y_coordinate].setBackground(Color.BLACK);
            this.tiles[2][y_coordinate].setBackground(Color.BLACK);
            this.tiles[3][y_coordinate].setBackground(Color.BLACK);
            this.tiles[4][y_coordinate].setBackground(Color.BLACK);
            this.tiles[5][y_coordinate].setBackground(Color.BLACK);
            this.tiles[6][y_coordinate].setBackground(Color.BLACK);
            this.tiles[7][y_coordinate].setBackground(Color.BLACK);
            this.createGUI();
        });
        timer2.setRepeats(false);//make sure the timer only runs once
        timer2.start();

        //delay = 500;//specify the delay for the timer
        //Timer timer2 = new Timer(delay, e -> {
        //The following code will be executed once the delay is reached

        //});
        //timer2.setRepeats(false);//make sure the timer only runs once
        // timer2.start();
    }

    public void blastUIGem() {
        this.listOfGems = this.gemBoard.getListOfGems();
        int index = 0;
        for (int k = 0; k < 64; k++) {
            if (this.listOfGems.get(k).getValue() == 0) {
                int x_coordinate = k / 8;
                int y_coordinate = k % 8;
                this.blast_coordinates[index][0] = x_coordinate;
                this.blast_coordinates[index][1] = y_coordinate;
                ++index;
                this.tiles[x_coordinate][y_coordinate].setIcon(null);
                this.tiles[x_coordinate][y_coordinate].setBackground(Color.yellow);

            }
        }
        LoginFrame.playSound("D:\\FAST\\Semester 5\\Object Oriented Analysis and Design\\Project\\Bonus Part\\sounds\\gernade.wav");
        if (this.blast_coordinates[0][0] == this.blast_coordinates[1][0]) {
            this.dropGemsHorizontally();

        } else {
            this.dropGemsVertically();
        }
    }
    
    
    private void processClick(int i, int j) {
        ++this.counter;
        if (this.counter == 1) {
            this.coordinates[0][0] = i;
            this.coordinates[0][1] = j;
        }
        if (this.counter == 2) {
            this.coordinates[1][0] = i;
            this.coordinates[1][1] = j;
            if (this.isValidMove()) {
                Icon temp = this.tiles[(this.coordinates[0][0])][(this.coordinates[0][1])].getIcon();
                this.tiles[(this.coordinates[0][0])][(this.coordinates[0][1])].setIcon(null);
                
                this.tiles[(this.coordinates[0][0])][(this.coordinates[0][1])].setIcon(this.tiles[(this.coordinates[1][0])][(this.coordinates[1][1])].getIcon());
                
                this.tiles[(this.coordinates[1][0])][(this.coordinates[1][1])].setIcon(temp);
                this.gemBoard.swapGem(coordinates);
                int delay = 1000;//specify the delay for the timer
                
                Timer timer = new Timer(delay, e -> {
                    //The following code will be executed once the delay is reached\
                    if(this.gemBoard.findSpecialGemOccurences())
                    {
                        this.blastUISpecialGem();
                        Timer nestedTimer = new Timer(delay, e1 -> {
                            if (this.gemBoard.findOccurences()) {
                                do {
                                    this.blastUIGem();
                                } while (this.gemBoard.findOccurences());
                            }
                        });
                        nestedTimer.setRepeats(false);//make sure the timer only runs once
                        nestedTimer.start();
                    }
                    else if (this.gemBoard.findOccurences()) {
                        do    
                        {
                            this.blastUIGem();
                        }while(this.gemBoard.findOccurences());
                        
                } else {
                    final Icon temp2 = this.tiles[(this.coordinates[0][0])][(this.coordinates[0][1])].getIcon();
                    this.tiles[(this.coordinates[0][0])][(this.coordinates[0][1])].setIcon(null);
                    
                    this.tiles[(this.coordinates[0][0])][(this.coordinates[0][1])].setIcon(this.tiles[(this.coordinates[1][0])][(this.coordinates[1][1])].getIcon());
                    
                    this.tiles[(this.coordinates[1][0])][(this.coordinates[1][1])].setIcon(temp2);
                    this.gemBoard.swapGem(coordinates);
                    LoginFrame.playSound("D:\\FAST\\Semester 5\\Object Oriented Analysis and Design\\Project\\Bonus Part\\sounds\\no.wav");
                }
                });
                timer.setRepeats(false);//make sure the timer only runs once
                timer.start();
                
            }
            int delay = 500;//specify the delay for the timer
            Timer timer = new Timer(delay, e -> {
                this.tiles[(this.coordinates[0][0])][(this.coordinates[0][1])].setBackground(Color.BLACK);
                this.tiles[(this.coordinates[1][0])][(this.coordinates[1][1])].setBackground(Color.BLACK);
            });
            timer.setRepeats(false);//make sure the timer only runs once
            timer.start();
            
            this.counter = 0;
        }
    }

    private class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            LoginFrame.playSound("D:\\FAST\\Semester 5\\Object Oriented Analysis and Design\\Project\\Bonus Part\\sounds\\click.wav");
            Object source = ae.getSource();
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (source == tiles[i][j]) {
                        tiles[i][j].setBackground(Color.yellow);
                        processClick(i, j);
                        return;
                    }
                }
            }
        }

    }

}
