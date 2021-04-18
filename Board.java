/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bejeweled.blitz;

import java.awt.Color;
import java.awt.List;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

/**
 *
 * @author Farhan
 */

//1 for circle
//2 for triangle
//3 for square
//4 for diamond
//5 for hexagon
public class Board {
    private Score gameScore;
    private ArrayList<Gem> listOfGem;
    public Board()
    {
        this.gameScore=new Score();
    }
    public void setGameScore(int gameScore)
    {
        this.gameScore.setScoreAchieved(gameScore);
    }
    public int getGameScore()
    {
        return this.gameScore.getScoreAchieved();
    }
    public void swapGem(int[][] coordinates)
    {
        int temp=this.listOfGem.get(coordinates[0][0]*8+coordinates[0][1]).getValue();
        this.listOfGem.get(coordinates[0][0]*8+coordinates[0][1]).setValue(this.listOfGem.get(coordinates[1][0]*8+coordinates[1][1]).getValue());
        this.listOfGem.get(coordinates[1][0]*8+coordinates[1][1]).setValue(temp);
    }
    public ArrayList<Gem> getListOfGems()
    {
        return this.listOfGem;
    }
    public boolean findSpecialGemOccurences()
    {
        if (this.findSpecialGemHorizontal()) {
            return true;
        } else if (this.findSpecialGemVertical()) {
            return true;
        } else {
            return false;
        }
    }
    public void dropSpecialGemCase(int[][] blast_coordinates) {
        int x_coordinate = blast_coordinates[0][0];
        int y_coordinate = blast_coordinates[0][1];
   
        final int min = 1;
        final int max = 5;
        Random rand = new Random();
        for (int k = 0; k < 8; k++) {

            int value = rand.nextInt((max - min) + 1) + min;
            if (value == 1) {
                this.listOfGem.set(8*k+y_coordinate, new CircleGem(value));
            }
            if (value == 2) {
                this.listOfGem.set(8*k+y_coordinate, new TriangleGem(value));
            }
            if (value == 3) {
                this.listOfGem.set(8*k+y_coordinate, new SquareGem(value));
            }
            if (value == 4) {
                this.listOfGem.set(8*k+y_coordinate, new DiamondGem(value));
            }
            if (value == 5) {
                this.listOfGem.set(8*k+y_coordinate, new HexagonGem(value));
            }

        }

    }
    public boolean findSpecialGemHorizontal()
    {
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<5;j++)
            {
                if(this.listOfGem.get(8*i+j).getValue()==this.listOfGem.get(8*i+j+1).getValue() && this.listOfGem.get(8*i+j).getValue()==this.listOfGem.get(8*i+j+2).getValue() && this.listOfGem.get(8*i+j).getValue()==this.listOfGem.get(8*i+j+3).getValue())
                {
                    //first make it special gem
                    this.listOfGem.set(8 * i + j, new LinerGem(6));
                    this.gameScore.setScoreAchieved(this.listOfGem.get(8*i+j).blast(gameScore));
                    return true;
                }
            }
        }
        return false;
    }
    public boolean findSpecialGemVertical()
    {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.listOfGem.get(8 * i + j).getValue() == this.listOfGem.get(8 * i + j + 8).getValue() && this.listOfGem.get(8 * i + j).getValue() == this.listOfGem.get(8 * i + j + 16).getValue() && this.listOfGem.get(8 * i + j).getValue() == this.listOfGem.get(8 * i + j + 24).getValue()) {
                    //first make it special gem
                    this.listOfGem.set(8 * i + j, new LinerGem(6));
                    this.gameScore.setScoreAchieved(this.listOfGem.get(8 * i + j).blast(gameScore));
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean findOccurences()
    {
        if (this.findHorizontal()) {
            return true;
        } else if (this.findVertical()) {
            return true;
        } else {
            return false;
        }
    }
    public boolean findVertical()
    {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.listOfGem.get(8 * i + j).getValue() == this.listOfGem.get(8 * i + j + 8).getValue() && this.listOfGem.get(8 * i + j).getValue() == this.listOfGem.get(8 * i + j + 16).getValue()) {
                    this.gameScore.setScoreAchieved(this.listOfGem.get(8 * i + j).blast(gameScore));
                    this.listOfGem.get(8 * i + j).setValue(0);
                    this.listOfGem.get(8 * i + j + 8).setValue(0);
                    this.listOfGem.get(8 * i + j + 16).setValue(0);
                    return true;
                }
            }
        }
        return false;
    }
    public void dropGemHorizontally(int[][] blast_coordinates) {
        int looper = blast_coordinates[0][0];
        int x_coordinate = blast_coordinates[0][0];
        int y_coordinate = blast_coordinates[0][1];
        while (looper > 0) {
            final int x = x_coordinate;
            final int y = y_coordinate;
            this.listOfGem.set(8 * x + y, this.listOfGem.get(8 * x + y - 8));
            this.listOfGem.set(8 * x + y + 1, this.listOfGem.get(8 * x + y + 1 - 8));
            this.listOfGem.set(8 * x + y + 2, this.listOfGem.get(8 * x + y + 2 - 8));

            --looper;
            --x_coordinate;

        }
        final int min = 1;
        final int max = 5;
        Random rand = new Random();
        for (int k = 0; k < 3; k++) {

            int value = rand.nextInt((max - min) + 1) + min;
            if (value == 1) {
                this.listOfGem.set(y_coordinate+k, new CircleGem(value));
            }
            if (value == 2) {
                this.listOfGem.set(y_coordinate+k, new TriangleGem(value));
            }
            if (value == 3) {
                this.listOfGem.set(y_coordinate+k, new SquareGem(value));
            }
            if (value == 4) {
                this.listOfGem.set(y_coordinate+k, new DiamondGem(value));
            }
            if (value == 5) {
                this.listOfGem.set(y_coordinate+k, new HexagonGem(value));
            }

        }

    }
    public void dropGemVertically(int[][] blast_coordinates) {
        int looper = blast_coordinates[2][0];
        int x_coordinate = blast_coordinates[2][0];
        int y_coordinate = blast_coordinates[2][1];
        while (looper > 2) {
            final int x = x_coordinate;
            final int y = y_coordinate;
            this.listOfGem.set(8 * x + y, this.listOfGem.get(8 * x + y - 24));

            --looper;
            --x_coordinate;

        }
        final int min = 1;
        final int max = 5;
        Random rand = new Random();
        for (int k = 0; k < 3; k++) {

            int value = rand.nextInt((max - min) + 1) + min;
            if (value == 1) {
                this.listOfGem.set(8*k+y_coordinate, new CircleGem(value));
            }
            if (value == 2) {
                this.listOfGem.set(8*k+y_coordinate, new TriangleGem(value));
            }
            if (value == 3) {
                this.listOfGem.set(8*k+y_coordinate, new SquareGem(value));
            }
            if (value == 4) {
                this.listOfGem.set(8*k+y_coordinate, new DiamondGem(value));
            }
            if (value == 5) {
                this.listOfGem.set(8*k+y_coordinate, new HexagonGem(value));
            }

        }

    }
    public boolean findHorizontal()
    {
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<6;j++)
            {
                if(this.listOfGem.get(8*i+j).getValue()==this.listOfGem.get(8*i+j+1).getValue() && this.listOfGem.get(8*i+j).getValue()==this.listOfGem.get(8*i+j+2).getValue())
                {
                    this.gameScore.setScoreAchieved(this.listOfGem.get(8*i+j).blast(gameScore));
                    this.listOfGem.get(8*i+j).setValue(0);
                    this.listOfGem.get(8*i+j+1).setValue(0);
                    this.listOfGem.get(8*i+j+2).setValue(0);
                    return true;
                }
            }
        }
        return false;
    }
    public void updateBoard() {
        final int min = 1;
        final int max = 5;
        Random rand = new Random();
        for (int k = 0; k < 64; k++) {
            if (this.listOfGem.get(k).getValue() == 0) {
                
                int value = rand.nextInt((max - min) + 1) + min;
                if (value == 1) {
                    this.listOfGem.set(k, new CircleGem(value));
                }
                if (value == 2) {
                    this.listOfGem.set(k, new TriangleGem(value));
                }
                if (value == 3) {
                    this.listOfGem.set(k, new SquareGem(value));
                }
                if (value == 4) {
                    this.listOfGem.set(k, new DiamondGem(value));
                }
                if (value == 5) {
                    this.listOfGem.set(k, new HexagonGem(value));
                }
            }
        }
    }
    public ArrayList<Gem> createBoard()
    {
        this.listOfGem=new ArrayList<Gem>();
        final int min=1;
        final int max=5;
        Random rand=new Random();
        for(int i=0;i<64;i++)
        {
            int value=rand.nextInt((max-min)+1)+min;
            if(value==1)
                listOfGem.add(new CircleGem(value));
            if(value==2)
                listOfGem.add(new TriangleGem(value));
            if(value==3)
                listOfGem.add(new SquareGem(value));
            if(value==4)
                listOfGem.add(new DiamondGem(value));
            if(value==5)
                listOfGem.add(new HexagonGem(value));
           
        }
        return this.listOfGem;
    }
}
