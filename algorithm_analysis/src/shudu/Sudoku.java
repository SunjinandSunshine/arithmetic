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
 *���ǵ�ԭ����Ǵӵ�0��0�п�ʼ����������������1-9֮������֣�Ȼ���ж��������������Ƿ��ܷŽ�ȥ�����и��к������ڵ�С�Ź����Ƿ����ظ����֣���
 *����ܷŽ�ȥ����ô�ͼ�����1-9ȥ�Ը��е���һ�С�һֱ�����е����һ�У�Ȼ���м����ظ�����Ĳ��裨Ҳ����ִ��backTrace��������
 *һֱִ�е����һ���ո�Ҳ����i=8,j=8��ʱ�����������ո����ŵ�ֵҲ��ȫ���Ϲ���
 *��ô��ʱ������ɣ������ټ�������backTrace�����ˣ������ȷ�⼴�ɡ�
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
    	sudokuGui.setTitle("������Ϸ");
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
     * �����㷨
     *
     * @param i �к�
     * @param j �к�
     */
    private void backTrace(int i, int j) {
        if (i == 8 && j == 9) {
            //�Ѿ��ɹ��ˣ���ӡ���鼴��
            System.out.println("��ȡ��ȷ��");
            printArray();
            for(int m=0;i<9;i++){
        		for(int n=0;j<9;j++){
        			SolvePanel.add(t[m][n],FlowLayout.LEFT);
         		}
        	}
            return;
        }
        //�Ѿ�������ĩβ�ˣ���û����β���ͻ���
        if (j == 9) {
            i++;
            j = 0;
        }
        //���i��j���ǿո���ô�Ž�����ո���ֵ���߼�
        if (matrix[i][j] == 0) {
            for (int k = 1; k <= 9; k++) {
                //�жϸ�i��j�з�1-9�е�����һ�����Ƿ����������
                if (check(i, j, k)) {
                    //����ֵ�����ÿո�Ȼ�������һ���ո�
                    matrix[i][j] = k;
                    backTrace(i, j + 1);
                    //��ʼ���ÿո�
                    matrix[i][j] = 0;
                }
            }
        } else {
            //�����λ���Ѿ���ֵ�ˣ��ͽ�����һ���ո���м���
            backTrace(i, j + 1);
        }
    }
 
    /**
     * �жϸ�ĳ��ĳ�и�ֵ�Ƿ���Ϲ���
     *
     * @param row    ����ֵ���к�
     * @param line   ����ֵ���к�
     * @param number ����ֵ
     * @return
     */
    private boolean check(int row, int line, int number) {
        //�жϸ��и����Ƿ����ظ�����
        for (int i = 0; i < 9; i++) {
            if (matrix[row][i] == number || matrix[i][line] == number) {
                return false;
            }
        }
        //�ж�С�Ź����Ƿ����ظ�
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
     * ��ӡ����
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