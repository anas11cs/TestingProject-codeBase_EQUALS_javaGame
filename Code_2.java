    public void swapGem(int[][] coordinates)
    {
        int temp=this.listOfGem.get(coordinates[0][0]*8+coordinates[0][1]).getValue();
        this.listOfGem.get(coordinates[0][0]*8+coordinates[0][1]).setValue(this.listOfGem.get(coordinates[1][0]*8+coordinates[1][1]).getValue());
        this.listOfGem.get(coordinates[1][0]*8+coordinates[1][1]).setValue(temp);
    }
	// =====================================_
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
	// ==========================
	