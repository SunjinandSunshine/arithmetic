package kettle;
class State {
    int[] num = new int[3];        //ÿ��������ˮ������   
    int[] capicity = {8, 5, 3};   //ÿ�����ӵ��������
    State preState;          //  preState�б����ŵ�ǰ״̬��ǰһ��״̬���������ҵ����յ�ˮ����ʱ����ͨ�����Ϸ���preState��������ĵ�ˮ����
    public State(int x1, int x2, int x3) {
        this.num[0] = x1;
        this.num[1] = x2;
        this.num[2] = x3;
    }
    public State(int[] x) {
        this.num[0] = x[0];
        this.num[1] = x[1];
        this.num[2] = x[2];
    }
    // �жϴ�from��to�ĵ�ˮ�Ƿ����
    public boolean canPour(int from, int to) {
    	//���ˮ��û��ˮ
        if (num[from] == 0)
            return false;
        //Ŀ�걭�Ѿ�����
        if (num[to] == capicity[to])
            return false;
        else
            return true;
    }
    //��ˮ��from����to��
    public void pour(int from, int to) {
        if (num[from] + num[to] > capicity[to]) {
            num[from] -= (capicity[to] - num[to]);
            num[to] = capicity[to];
        } else {
            num[to] += num[from];
            num[from] = 0;
        }
    }
    //��ÿ��״̬�ӱ�׼�� ��ϣ����
    public int getNum() {
        return 100 * num[0] + 10 * num[1] + 1*num[2];
    }
    //��ӡ��ǰˮ��״̬
    public String toString()
    {
        return "cup1: "+num[0]+" cup2: "+num[1]+" cup3: "+num[2];
    }
}