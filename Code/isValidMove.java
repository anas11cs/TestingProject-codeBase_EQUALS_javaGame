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