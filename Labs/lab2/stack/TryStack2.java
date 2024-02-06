

public class TryStack2 {

    // Change this file as much or as little as you need to.

    /**
     * Main driver method that will reverse an array.
     *
     * @param args
     */
    protected  Integer[] array;
    protected  Stack<Integer> stack;
    public TryStack2(int n,LinkedStack<Integer>stack){
        setArray(n);
        this.stack=stack;

    }
    public void setArray(int n){
        array=new Integer[n];
        for (int i=0;i<n;i++){
            array[i]=i*2;
        }
    }
    protected void printArray(){
        System.out.println();
        for (int elems : array) {
            System.out.print(elems + "\t");
        }
        System.out.println();
    }
    protected void reverseArray(){
        for (int element : array){
            stack.push(element);
        }
        System.out.println("\nInspecting stack (top..bottom): " + this.stack);// testing
        int i=0;
        while(!stack.isEmpty()){
            array[i]= stack.pop();
            i++;
        }
    }
    protected void runSimulation2() {
        printArray();
        reverseArray();
        printArray();
    }

    public static void main (String[] args) {
        int n = args.length > 0 ? Integer.valueOf(args[0]) : 50;
        TryStack2 tryStack = new TryStack2(n, new LinkedStack<>());
        tryStack.runSimulation2();

    }
}
