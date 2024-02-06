// ==========================================================================
// $Id: HeapSortInPlace.java,v 1.1 2006/11/05 03:27:51 jlang Exp $
// CSI2110 Lab code; Heap sort in place
// ==========================================================================
// (C)opyright:
//
//   SITE, University of Ottawa
//   800 King Edward Ave.
//   Ottawa, On., K1N 6N5
//   Canada. 
//   http://www.site.uottawa.ca
// 
// Creator: unknown (Lab source without reference), adapted by J.Lang
// Email:   jlang@site.uottawa.ca
// ==========================================================================
// $Log: HeapSortInPlace.java,v $
// Revision 1.1  2006/11/05 03:27:51  jlang
// Added lab8 on sorting.
//
// Revision 2.1 2022/11/13 lmoura
// Changed the implementation of heapsort to rely on full downheap operations,
// matching the concept of downheap seen in class
//
// ==========================================================================
/**
 * Implements an in-place array-based heap sort.
 */
public class HeapSortInPlace<T extends Comparable<T>> {
  protected T[] d_seq;

  /**
   * Construct a HeapSort function object
   * _seq will be sorted on construction
   */
  public HeapSortInPlace( T[] _seq ) {
	d_seq = _seq;
    int size = d_seq.length;
    int start=0;
    int end= size-1;
    heapify(start,end);
//    print(d_seq);
    while (end>0){
      swap(start,end);
      end-=1;
      downheap(start,end);
    }
    /**/
//    System.out.println("Heapsort has dummy body.\n"
//    		+ "Please implement:\n"+"1) heapify (bottom up max-heap construction);\n"
//    		+ "2) Repeat several delete max, placing the largest in the sorted part\n"
//    		+ "Both parts must call downheap.");
//    /**/
//    // **** Heapify to build a maxheap: bottom-up heap construction ******
//    // Include next a sequence of downheap to trasnform array in heap
//    // Remove the next line, it is just an example,
//    // since Heapify will have a loop with several calls to downheap
//    /**/
//    downheap((size-2)/2, size-1); // first downheap needed to heapify (remove this line)
//    /**/
//
//    /**/
//    // iteratively loop, delete max and fill in large elements from
//    // the end
//    /**/

  }

  /**
   * downheap(start, end) fixes the maxheap property for subtree rooted at start
   * Preconditions: 
   * d_seq[0..end] represents a complete binary tree
   * subtree rooted at position start may violate maxheap property
   * subtrees rooted at the children of start do not violate the maxheap property
   * Postcondition:
   * subtree rooted at start does no violate maxheap property
   **/
  protected void downheap(int start, int end) {
	  /**/
//	  System.out.println("Downheap not implemented:\n"
//
//	  		+ "This method must do sucessive swaps between node and largest of two kids "
//	  		+ "until no swap is needed or the node has no kids");
	  /**/
    int current=start;
    while(2*current+1<=end){
      if (2*current+2>end){
        if (d_seq[current].compareTo(d_seq[2*current+1])==-1){
          swap(current,2*current+1);
          current=2*current+1;
        }else{
          break;
        }
      }else{
        if (d_seq[2*current+1].compareTo(d_seq[2*current+2])==1){
          if(d_seq[current].compareTo(d_seq[2*current+1])==-1) {
            swap(current, 2 * current + 1);
            current=2*current+1;
          }else{
            break;
          }
        }else{
          if(d_seq[current].compareTo(d_seq[2*current+2])==-1) {
            swap(current, 2 * current + 2);
            current=2*current+2;
          }else {
            break;
          }
        }
      }

    }


	  
  }
    protected void heapify (int start, int end){
      //build the maxheap.
      int current= end;
      while(current>=0){
//            print(d_seq);
          downheap(current,end);
//        print(d_seq);
          current--;
        }
      print(d_seq);
      }

    protected void swap (int lo1, int lo2){
      T temp=d_seq[lo1];
      d_seq[lo1]=d_seq[lo2];
      d_seq[lo2]=temp;
    }
    public static void main(String[] args){
      Integer[] testarray={ 5,7,8,10,13,15,16,20,22,25};
      HeapSortInPlace<Integer> test= new HeapSortInPlace(testarray);
      for (Integer i:testarray){
//        System.out.print(" ");
        System.out.print(i+" ");
      }
    }
    public void print(T[] array){
      for(T i:array){
        System.out.print(i + " ");
      }
      System.out.println();

    }
}

//57  ,43,  66,  4,  100,  84,  66,  45,  36,  16,  11,  28,  13,  9 , 33 , 7 , 64,  84 , 64,  64,79 , 32,  36,  23 , 35 , 33 , 39 , 92 , 73,  61