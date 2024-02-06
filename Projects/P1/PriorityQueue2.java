//Student name: Hengjing ZHang
//Student number: 300288003
import java.util.ArrayList;
import java.util.Set;
import java.util.WeakHashMap;

public class PriorityQueue2 implements PriorityQueueIF<LabelledPoint>{
    private LabelledPoint[] priorityQueue;
    private int capacity;
    private int size;
    public PriorityQueue2(int capacity) {
        priorityQueue = new LabelledPoint[capacity];
        this.capacity = capacity;
    }

    public PriorityQueue2(ArrayList<LabelledPoint> labelledPoints, int capacity){
        priorityQueue = new LabelledPoint[capacity];
        this.capacity = capacity;
        for (LabelledPoint point : labelledPoints) {
            offer(point);
        }
    }

    @Override
    public boolean offer(LabelledPoint labelledPoint) {
        if (size < capacity) {
            priorityQueue[size] = labelledPoint;
            int i = size;
            while (i > 0) {
                int parent = (i - 1) / 2;
                if (labelledPoint.getKey() > priorityQueue[parent].getKey()) {
                    LabelledPoint tmp = priorityQueue[parent];
                    priorityQueue[parent] = labelledPoint;
                    priorityQueue[i] = tmp;
                    i = parent;
                } else {
                    break;
                }
            }
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
        LabelledPoint result = priorityQueue[0];
        size --;
        priorityQueue[0] = priorityQueue[size];
        priorityQueue[size] = null;

        int i = 0;
        while ((2*i + 1) < size) {
            LabelledPoint left = priorityQueue[2*i + 1];
            int maxIndex = 2 * i + 1;
            if ((2*i + 2) < size) {
                LabelledPoint right = priorityQueue[2*i + 2];
                if (right.getKey() > left.getKey()) {
                    maxIndex = 2*i + 2;
                }
            }
            if (priorityQueue[i].getKey() < priorityQueue[maxIndex].getKey()) {
                LabelledPoint tmp = priorityQueue[i];
                priorityQueue[i] = priorityQueue[maxIndex];
                priorityQueue[maxIndex] = tmp;
                i = maxIndex;
            } else {
                break;
            }
        }
        return  result;
    }



    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public LabelledPoint peek() {
        if (isEmpty()) {
            return null;
        }
        return priorityQueue[0];
    }
}
