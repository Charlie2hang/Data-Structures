/**
 * Array Heap implementation of a priority queue
 * @author Lachlan Plant
 */
public class HeapPriorityQueue<K extends Comparable,V> implements PriorityQueue<K ,V> {
    
    private Entry[] storage; //The Heap itself in array form
    private int tail;        //Index of last element in the heap
    
    /**
    * Default constructor
    */
    public HeapPriorityQueue(){
    }
    
    /**
    * HeapPriorityQueue constructor with max storage of size elements
    */
    public HeapPriorityQueue(int size){
        storage=new Entry[size+1];
        tail=0;
    }
    
    /****************************************************
     * 
     *             Priority Queue Methods
     * 
     ****************************************************/
    
    /**
    * Returns the number of items in the priority queue.
    * O(1)
    * @return number of items
    */
    public int size(){
        return tail;
    }

    /**
    * Tests whether the priority queue is empty.
    * O(1)
    * @return true if the priority queue is empty, false otherwise
    */
    public boolean isEmpty(){
        return size()==0;
    }
    
    /**
    * Inserts a key-value pair and returns the entry created.
    * O(log(n))
    * @param key     the key of the new entry
    * @param value   the associated value of the new entry
    * @return the entry storing the new key-value pair
    * @throws IllegalArgumentException if the heap is full
    */
    public Entry<K,V> insert(K key, V value) throws IllegalArgumentException{
        if (tail==storage.length-1){
            throw new IllegalArgumentException();
        }
        Entry<K,V> elem= new Entry<>(key,value);
        storage[++tail]=elem;
        upHeap(tail);
        return elem;
    }
    
    /**
    * Returns (but does not remove) an entry with minimal key.
    * O(1)
    * @return entry having a minimal key (or null if empty)
    */
    public Entry<K,V> min(){
        return storage[1];
    } 
    
    /**
    * Removes and returns an entry with minimal key.
    * O(log(n))
    * @return the removed entry (or null if empty)
    */ 
    public Entry<K,V> removeMin(){
        if(isEmpty()){
            return null;
        }
        Entry removeElem=storage[1];
        storage[1]=storage[tail];
        storage[tail]=null;
        tail--;
        if (!isEmpty()){
            downHeap(1);
        }

        return removeElem;
    }  
    
    
    /****************************************************
     * 
     *           Methods for Heap Operations
     * 
     ****************************************************/
    
    /**
    * Algorithm to place element after insertion at the tail.
    * O(log(n))
    */
    private void upHeap(int location){
        if(location<1 || location>tail){
            throw new IllegalArgumentException();
        }
        if (tail==1){
            return;
        }
        if (storage[location].getKey().compareTo(storage[location/2].getKey())>-1){}
        else if (storage[location].getKey().compareTo(storage[location/2].getKey())==-1){
            swap(location,location/2);
            if (location/2!=1) {
                upHeap(location / 2);
            }else{};
        }

    }
    
    /**
    * Algorithm to place element after removal of root and tail element placed at root.
    * O(log(n))
    */
    private void downHeap(int location){
        if(location<1 || location>tail){
            throw new IllegalArgumentException();
        }
        Entry larger=storage[location];
        int toSwap=location;
        if (2*location>=storage.length||2*location+1>=storage.length){
            return;
        }
        if (storage[2*location+1]==null && storage[2*location]==null){
            return;
        } else if (storage[2*location+1]==null){
            larger=storage[2*location];
            toSwap=2*location;
        }else if (storage[2*location]==null){
            larger=storage[2*location+1];
            toSwap=2*location+1;
        } else if (storage[2*location+1]!=null && storage[2*location]!=null){
            if (storage[2*location].getKey().compareTo(storage[2*location+1].getKey())==-1){
                larger=storage[2*location];
                toSwap=2*location;
            }else{
                larger=storage[2*location+1];
                toSwap=2*location+1;
            }
        }
        if (storage[location].getKey().compareTo(larger.getKey())>-1){
            swap(location,toSwap);
            downHeap(toSwap);
        }else{
            return;
        }


    }
    
    /**
    * Find parent of a given location,
    * Parent of the root is the root
    * O(1)
    */
    private int parent(int location){
        return location/2;
    }
    
   
    /**
    * Inplace swap of 2 elements, assumes locations are in array
    * O(1)
    */
    private void swap(int location1, int location2){
        Entry temp=storage[location1];
        storage[location1]=storage[location2];
        storage[location2]=temp;

    }
    
}
