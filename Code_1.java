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
		// ==========================
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
		// ============================
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
	// ====================================
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
// =================
