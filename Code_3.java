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