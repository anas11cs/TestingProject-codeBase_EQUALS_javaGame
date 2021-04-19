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