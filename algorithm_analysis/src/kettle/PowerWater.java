package kettle;
import java.util.HashMap;
import java.util.LinkedList;
public class PowerWater {
    public static void main(String[] args) {
        LinkedList<State> states = new LinkedList<State>();     //��������������̱�������ʽڵ���б�(FIFO)
        HashMap<Integer, State> history = new HashMap<Integer, State>(); //history�б���ÿ���³��ֵ�״̬
        State state = new State(8, 0, 0);       //��ʼ��״̬Ϊ8,0,0
        states.addLast(state);//����״̬�ӽ�ȥ
        history.put(state.getNum(), state);//��ϣ�洢
        boolean hasFind = false;//
        while (states.size() > 0||!hasFind) {
            State tmp = states.removeFirst();   //FIFO�Ƚ��ȳ�
            for (int i = 0; i < 3; i++)   //pour water from i to j
            {
                for (int j = 0; j < 3; j++) {       //ÿ���ڵ㶼��6��״̬(���������е�״̬)
                    if (i != j) {
                        if (tmp.canPour(i, j)) {
                            State newState = new State(tmp.num);
                            //��ˮ
                            newState.pour(i, j);
                            newState.preState = tmp;
                            if (!history.containsKey(newState.getNum())) {      //�������״̬û�г��ֹ���������״̬����history��
                                history.put(newState.getNum(), newState);
                                states.addLast(newState);
                            }
                            //�ҵ�����4��״̬��ӡ
                            if (newState.num[0] == 4 && newState.num[1] == 4) {     //�������������ӡ������ˮ����
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