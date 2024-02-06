//Student name: Hengjing ZHang
//Student number: 300288003
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;

public class PriorityQueue1 implements PriorityQueueIF<LabelledPoint> {
    private LabelledPoint[] priorityQueue;
    private int capacity;
    private int size;

    public PriorityQueue1(int capacity) {
        priorityQueue = new LabelledPoint[capacity];
        this.capacity = capacity;
    }
    public PriorityQueue1(ArrayList<LabelledPoint> labelledPoints, int capacity) {
        this.capacity = capacity;
        priorityQueue = new LabelledPoint[capacity];
        for (LabelledPoint point : labelledPoints) {
            offer(point);
        }
    }

    @Override
    public boolean offer(LabelledPoint labelledPoint) {
        if (size < capacity) {
            int index = 0;
            while (index < size) {
                if (priorityQueue[index].getKey() >= labelledPoint.getKey()) {
                    break;
                }
                index ++;
            }
            for (int i = size; i > index; i --) {
                priorityQueue[i] = priorityQueue[i - 1];
            }
            priorityQueue[index] = labelledPoint;
            size ++;
            return true;
        }
        return false;
    }

    @Override
    public LabelledPoint poll() {
        if (size == 0) {
            return null;
        }
        size --;
        LabelledPoint tmp = priorityQueue[size];
        priorityQueue[size] = null;
        return tmp;
    }

    @Override
    public LabelledPoint peek() {
        if (size == 0) {
            return  null;
        }
        return priorityQueue[size - 1];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }
}
