public class Matrix {

    protected boolean[][] matrix;
    protected int row;
    protected int col;

    public Matrix(int comb, int rw, int cl) { //initial constructor
        this.row = rw+2;
        this.col = cl+2;

        matrix = new boolean[row][col];

        for(int i = 1; i < row-1; i++) {
            for(int j = 1; j < col-1; j++) {
                if(comb > 0 && (comb%2) == 1) { //terns number to a binary number for initial setup
                    matrix[i][j] = true;
                } else {
                    matrix[i][j] = false;
                }
                comb /= 2;
            }
        }
    }

    public Matrix(boolean[][] mtr, int coord1[], int coord2[]) {
        this.row = coord2[0] - coord1[0] + 1;
        this.col = coord2[1] - coord1[1] + 1;

        this.matrix = new boolean[row][col];

        this.Clear();

        for(int i = 0; i < (coord2[0]-coord1[0]+3); i++) {
            for(int j = 0; j < (coord2[1]-coord1[1]+3); j++) {
                if(mtr[i][j]) { this.matrix[i + coord1[0] - 1][j + coord1[1] - 1] = true; }
            }
        }
    }

        public void Update(boolean[][] new_matrix) {

        int rw = this.row, cl = this.col; // saving old length to refill after extending
        boolean[] grid_extend = {false, false, false, false};

        for(int i = 0; i < this.row; i++) { // detects which side should be exteded
            for(int j = 0; j < this.col; j++) {
                if(new_matrix[i][j]) {

                    if(i == 0) { // top side
                        grid_extend[0] = true; 
                    } else if(i == row-1) { // bottom side
                        grid_extend[2] = true; 
                    } 
                         
                    if (j == 0) { // right side
                        grid_extend[1] = true; 
                    } else if(j == col-1) { // left side
                        grid_extend[3] = true;
                    }
                }
            }
        }

        int[] coord = {0, 0}; //coordinates of insertion of old matrix

        if(grid_extend[0]) { //extending any of 4 sides
            this.row++;
            coord[0]++; //shifts down
        } if(grid_extend[2]) {
            this.row++;
        } if(grid_extend[1]) {
            this.col++;
            coord[1]++; //shifts right
        } if(grid_extend[3]) {
            this.col++;
        }

        this.matrix = new boolean[row][col]; //setting new sized matrix

        this.Clear();

        for(int i = 0; i < rw; i++) {  //fills the grid with values from the new grid
            for(int j = 0; j < cl; j++) {
                if(new_matrix[i][j]) {
                    this.matrix[i+coord[0]][j+coord[1]] = true;
                }
            }
        }
    }

    public void RunCycle() {

        int count;
        boolean[][] new_matrix = new boolean[row][col];

        for(int i = 0; i < row; i++) { //counts all nighbours of each cell and creates new life matrix
            for(int j = 0; j < col; j++) {

                count = CountNeighbours(i, j); 

                if(this.matrix[i][j]) { // if this cell is alive

                    if(count == 2 || count == 3) { // if live cell has 2 or 3 neighbours
                        new_matrix[i][j] = true;
                    } else {
                        new_matrix[i][j] = false;
                    }
                } else {
                    if(count == 3) { // comes live if has 3 live neighbours
                        new_matrix[i][j] = true;
                    } else { 
                        new_matrix[i][j] = false;
                    }    
                }
            }
        }

        this.Update(new_matrix);
    }

    public int CountNeighbours(int rw, int cl) {

        int alive = 0; //live neighbours

        for(int i = rw-1; i < rw+2; i++) { //searching all 9 cells
            for(int j = cl-1; j < cl+2; j++) {
                if(i > -1 && i < row && j > -1 && j < col) { //searching for borders
                    if(this.matrix[i][j]) {
                        alive++;
                    }
                }
            }
        }

        if(this.matrix[rw][cl]) { alive--; } //deleting main cell from the count
            
        return alive;
    }

    public int getRow() { return this.row; }

    public int getCol() { return this.col; }

    public boolean getCell(int rw, int cl) { return this.matrix[rw][cl]; }  // gets parameter of 1 cell

    public void Clear() { //method to set whole matrix to false ("dead")

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {

                matrix[i][j] = false; //turning whole matrix off
            }
        }
    }

    public String toString() {
        
        String str = "";

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(matrix[i][j]) {
                    str += "[]";
                } else {
                    str += "  ";
                }
            } str += '\n';
        }

        return str;
    }
    
}
