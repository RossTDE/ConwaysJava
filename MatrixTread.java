public class MatrixTread extends Matrix implements Runnable {

    protected int start_coord[];
    protected int end_coord[];

    public MatrixTread(boolean[][] mtr, int coord1[], int coord2[]) {
        super(mtr, coord1, coord2);

        start_coord = coord1;
        end_coord = coord2;
    }
    
    @Override
    public void run() {
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

        this.matrix = new_matrix;
    }
}
