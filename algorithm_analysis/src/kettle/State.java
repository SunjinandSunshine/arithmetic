package kettle;
class State {
    int[] num = new int[3];        //每个杯子中水的数量   
    int[] capicity = {8, 5, 3};   //每个杯子的最大容量
    State preState;          //  preState中保存着当前状态的前一个状态，这样当找到最终倒水方案时可以通过不断访问preState获得完整的倒水过程
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
    // 判断从from到to的倒水是否可行
    public boolean canPour(int from, int to) {
    	//如果水杯没有水
        if (num[from] == 0)
            return false;
        //目标杯已经满了
        if (num[to] == capicity[to])
            return false;
        else
            return true;
    }
    //将水从from倒入to中
    public void pour(int from, int to) {
        if (num[from] + num[to] > capicity[to]) {
            num[from] -= (capicity[to] - num[to]);
            num[to] = capicity[to];
        } else {
            num[to] += num[from];
            num[from] = 0;
        }
    }
    //给每个状态加标准符 哈希查找
    public int getNum() {
        return 100 * num[0] + 10 * num[1] + 1*num[2];
    }
    //打印当前水杯状态
    public String toString()
    {
        return "cup1: "+num[0]+" cup2: "+num[1]+" cup3: "+num[2];
    }
}