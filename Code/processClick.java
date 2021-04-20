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
                    //The following code will be executed once the delay is reached
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
					}
					else {
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