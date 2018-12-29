package shudu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * @author Sun
 *我们的原理就是从第0行0列开始，依次往里面填入1-9之间的数字，然后判断填入的这个数字是否能放进去（该行该列和它所在的小九宫格是否有重复数字）。
 *如果能放进去，那么就继续用1-9去试该行的下一列。一直到该行的最后一列，然后换行继续重复上面的步骤（也就是执行backTrace方法）。
 *一直执行到最后一个空格，也就是i=8,j=8的时候，且最后这个空格所放的值也完全符合规则，
 *那么此时就算完成，不用再继续调用backTrace方法了，输出正确解即可。
 */
public class Sudoku extends JFrame{
	private static final long serialVersionUID = 1L;
	private int[][] matrix;
	private int[][] compareMatrix;
    private static JButton jb = new JButton("solve");
    private static JButton[][] t;
    private static JButton[][] a;
    JPanel ProblemPanel = new JPanel();
	JPanel SolvePanel = new JPanel();
    public Sudoku(int[][] matrix,int[][] compareMatrix) {
        this.matrix = matrix;
        this.compareMatrix = compareMatrix;
    }
    public Sudoku(){
    	ProblemPanel.setLayout(new FlowLayout());
    	SolvePanel.setLayout(new FlowLayout());
    	SolvePanel.setPreferredSize(new Dimension(420, 390));
    	ProblemPanel.setPreferredSize(new Dimension(420, 390));
    	SolvePanel.setBackground(Color.green);
        t = new JButton[9][9];
        a = new JButton[9][9];
    	for(int i=0;i<9;i++){
    		for(int j=0;j<9;j++){
    			t[i][j] = new JButton();
    			a[i][j] = new JButton();
     		}
    	}
    	initialnize();
    	jb.setBackground(Color.red);
    	ProblemPanel.add(jb);
    	for(int i=0;i<9;i++){
    		for(int j=0;j<9;j++){
    		  	ProblemPanel.add(t[i][j],FlowLayout.LEFT);
    		  	SolvePanel.add(a[i][j],FlowLayout.LEFT);
     		}
    	}
    	this.add(ProblemPanel,BorderLayout.WEST);
    	this.add(SolvePanel,BorderLayout.EAST);
    }
  
	public static void main(String[] args) {
    	Sudoku sudokuGui = new Sudoku();
    	sudokuGui.setTitle("数独游戏");
    	sudokuGui.setSize(840,400);
    	sudokuGui.setLocationRelativeTo(null);
    	sudokuGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	sudokuGui.setVisible(true);
        int[][] sudoku1 = {
                {8, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 3, 6, 0, 0, 0, 0, 0},
                {0, 7, 0, 0, 9, 0, 2, 0, 0},
                {0, 5, 0, 0, 0, 7, 0, 0, 0},
                {0, 0, 0, 0, 4, 5, 7, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 3, 0},
                {0, 0, 1, 0, 0, 0, 0, 6, 8},
                {0, 0, 8, 5, 0, 0, 0, 1, 0},
                {0, 9, 0, 0, 0, 0, 4, 0, 0}};
        int[][] sudoku2 = {
                {8, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 3, 6, 0, 0, 0, 0, 0},
                {0, 7, 0, 0, 9, 0, 2, 0, 0},
                {0, 5, 0, 0, 0, 7, 0, 0, 0},
                {0, 0, 0, 0, 4, 5, 7, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 3, 0},
                {0, 0, 1, 0, 0, 0, 0, 6, 8},
                {0, 0, 8, 5, 0, 0, 0, 1, 0},
                {0, 9, 0, 0, 0, 0, 4, 0, 0}};
        Sudoku s = new Sudoku(sudoku1,sudoku2);
        jb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 s.backTrace(0, 0);
			}
		});
    }
    /**
     * 数独算法
     *
     * @param i 行号
     * @param j 列号
     */
    private void backTrace(int i, int j) {
        if (i == 8 && j == 9) {
            //已经成功了，打印数组即可
            System.out.println("获取正确解");
            printArray();
            for(int m=0;i<9;i++){
        		for(int n=0;j<9;j++){
        			SolvePanel.add(t[m][n],FlowLayout.LEFT);
         		}
        	}
            return;
        }
        //已经到了列末尾了，还没到行尾，就换行
        if (j == 9) {
            i++;
            j = 0;
        }
        //如果i行j列是空格，那么才进入给空格填值的逻辑
        if (matrix[i][j] == 0) {
            for (int k = 1; k <= 9; k++) {
                //判断给i行j列放1-9中的任意一个数是否能满足规则
                if (check(i, j, k)) {
                    //将该值赋给该空格，然后进入下一个空格
                    matrix[i][j] = k;
                    backTrace(i, j + 1);
                    //初始化该空格
                    matrix[i][j] = 0;
                }
            }
        } else {
            //如果该位置已经有值了，就进入下一个空格进行计算
            backTrace(i, j + 1);
        }
    }
 
    /**
     * 判断给某行某列赋值是否符合规则
     *
     * @param row    被赋值的行号
     * @param line   被赋值的列号
     * @param number 赋的值
     * @return
     */
    private boolean check(int row, int line, int number) {
        //判断该行该列是否有重复数字
        for (int i = 0; i < 9; i++) {
            if (matrix[row][i] == number || matrix[i][line] == number) {
                return false;
            }
        }
        //判断小九宫格是否有重复
        int tempRow = row / 3;
        int tempLine = line / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrix[tempRow * 3 + i][tempLine * 3 + j] == number) {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * 打印矩阵
     */
    public void printArray() {
        for (int i = 0; i <9; i++) {
            for (int j = 0; j <9; j++) {
                System.out.print(matrix[i][j] + " ");
                if(compareMatrix[8-i][8-j] != 0){
                	 a[i][j].setText(matrix[8-i][8-j] + "");
                }
                else{
                	 a[i][j].setText(matrix[8-i][8-j] + "");
                	 a[i][j].setBackground(Color.BLUE);
                }
            }
            System.out.println();
        }
        System.out.println(); 
    }
    
    private void initialnize() {
		t[0][0].setText("0");
		t[0][1].setText("0");
		t[0][2].setText("4");
		t[0][3].setText("0");
		t[0][4].setText("0");
		t[0][5].setText("0");
		t[0][6].setText("0");
		t[0][7].setText("9");
		t[0][8].setText("0");
		
		t[1][0].setText("0");
		t[1][1].setText("1");
		t[1][2].setText("0");
		t[1][3].setText("0");
		t[1][4].setText("0");
		t[1][5].setText("5");
		t[1][6].setText("8");
		t[1][7].setText("0");
		t[1][8].setText("0");
		
		
		t[2][0].setText("8");
		t[2][1].setText("6");
		t[2][2].setText("0");
		t[2][3].setText("0");
		t[2][4].setText("0");
		t[2][5].setText("0");
		t[2][6].setText("1");
		t[2][7].setText("0");
		t[2][8].setText("0");
		
		t[3][0].setText("0");
		t[3][1].setText("3");
		t[3][2].setText("0");
		t[3][3].setText("0");
		t[3][4].setText("0");
		t[3][5].setText("1");
		t[3][6].setText("0");
		t[3][7].setText("0");
		t[3][8].setText("0");
		
		t[4][0].setText("0");
		t[4][1].setText("0");
		t[4][2].setText("7");
		t[4][3].setText("5");
		t[4][4].setText("4");
		t[4][5].setText("0");
		t[4][6].setText("0");
		t[4][7].setText("0");
		t[4][8].setText("0");
		
		t[5][0].setText("0");
		t[5][1].setText("0");
		t[5][2].setText("0");
		t[5][3].setText("7");
		t[5][4].setText("0");
		t[5][5].setText("0");
		t[5][6].setText("0");
		t[5][7].setText("5");
		t[5][8].setText("0");
		
		t[6][0].setText("0");
		t[6][1].setText("0");
		t[6][2].setText("2");
		t[6][3].setText("0");
		t[6][4].setText("9");
		t[6][5].setText("0");
		t[6][6].setText("0");
		t[6][7].setText("7");
		t[6][8].setText("0");
		
		t[7][0].setText("0");
		t[7][1].setText("0");
		t[7][2].setText("0");
		t[7][3].setText("0");
		t[7][4].setText("0");
		t[7][5].setText("6");
		t[7][6].setText("3");
		t[7][7].setText("0");
		t[7][8].setText("0");
		
		t[8][0].setText("0");
		t[8][1].setText("0");
		t[8][2].setText("0");
		t[8][3].setText("0");
		t[8][4].setText("0");
		t[8][5].setText("0");
		t[8][6].setText("0");
		t[8][7].setText("0");
		t[8][8].setText("8");
		
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				a[i][j].setText("0");
			}
		}
	}
}