package kettle;
import java.util.HashMap;
import java.util.LinkedList;
public class PowerWater {
    public static void main(String[] args) {
        LinkedList<State> states = new LinkedList<State>();     //广度优先搜索过程保存待访问节点的列表(FIFO)
        HashMap<Integer, State> history = new HashMap<Integer, State>(); //history中保存每种新出现的状态
        State state = new State(8, 0, 0);       //初始化状态为8,0,0
        states.addLast(state);//初试状态加进去
        history.put(state.getNum(), state);//哈希存储
        boolean hasFind = false;//
        while (states.size() > 0||!hasFind) {
            State tmp = states.removeFirst();   //FIFO先进先出
            for (int i = 0; i < 3; i++)   //pour water from i to j
            {
                for (int j = 0; j < 3; j++) {       //每个节点都有6种状态(包括不可行的状态)
                    if (i != j) {
                        if (tmp.canPour(i, j)) {
                            State newState = new State(tmp.num);
                            //倒水
                            newState.pour(i, j);
                            newState.preState = tmp;
                            if (!history.containsKey(newState.getNum())) {      //如果这种状态没有出现过，则将这种状态加入history中
                                history.put(newState.getNum(), newState);
                                states.addLast(newState);
                            }
                            //找到存在4的状态打印
                            if (newState.num[0] == 4 && newState.num[1] == 4) {     //如果达成条件则打印整个倒水过程
                                while (newState.preState!=null)
                                {
                                    System.out.println(newState.toString());
                                    newState = newState.preState;
                                }
//                                System.out.println(newState.toString());
                                hasFind = true;
                                break;
                            }
                        }
                    }
                }
                if (hasFind)
                    break;
            }
        }
        if (!hasFind)
            System.out.print("No such solution!");
    }
}