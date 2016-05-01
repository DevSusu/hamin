import java.io.*;
import java.util.*;

public class binarySearchTree{

  public static class node{
    private int value;
    private node left;
    private node right;

    public node(int value){
      this.value = value;
      left = null;
      right = null;
    }

    public int getValue(){
      return this.value;
    }

    public node getRight(){
      return this.right;
    }

    public node getLeft(){
      return this.left;
    }

    public void setRight(node n){
      this.right = n;
    }

    public void setLeft(node n){
      this.left = n;
    }

  }


  public static class BST{
    private node root;

    public BST(){
      this.root = null;
    }

    public node getRoot(){
      return this.root;
    }

    public void insert(int value){

      /* implement your own code */
      node tmp = new node(value);
      node status = this.root;

      if( status == null )
      {
          this.root = tmp;
          return ;
      }

      while(true) {

      }

    }

    public boolean find(int value){

      /* implement your own code */

    }

    public void traverseAllNode(node n){

      /* implement your own code */

    }


  }

  public static boolean BinarySearch(int array[], int key){
    int low = 0;
    int high = array.length - 1;
    while(low<=high){
      int mid = (low+high)/2;
      if(array[mid] == key){
        return true;
      }
      else if(array[mid] < key)
      low = mid+1;
      else
      high = mid-1;
    }
    return false;
  }
  public static boolean LinearSearch(int array[], int key){
    for(int i = 0; i < array.length; i++){
      if(array[i] == key)
      return true;
    }
    return false;
  }

  public static void main(String [] args) throws IOException{
    BST b = new BST();

    /////////////////////////////////////////
    /////                               /////
    /////            MAIN CODE          /////
    /////                               /////
    /////                               /////
    /////   This code will be           /////
    /////   used for grading.           /////
    /////   You have to activate        /////
    /////   before submit your code.    /////
    /////                               /////
    /////////////////////////////////////////


    ///// Deactivete from here to use SIMPLE TEST CODE  /////
    /*
    BufferedReader br = new BufferedReader(new FileReader("sample_input.txt"));
    int sorted_array[] = new int[100000];

    String data;
    int count = 0;
    int findTest0 = -1;
    int findTest1 = -1;
    int findTest2 = -1;
    int findTest3 = 1620880;

    while(true){
    data = br.readLine();
    if(data == null)
    break;
    b.insert(Integer.parseInt(data));
    sorted_array[count] = Integer.parseInt(data);
    if(count == 102)        findTest0 = Integer.parseInt(data);
    else if(count == 3021)  findTest1 = Integer.parseInt(data);
    else if(count == 9876)  findTest2 = Integer.parseInt(data);
    ++count;
  }
  br.close();
  /////       sort array      /////
  Arrays.sort(sorted_array);

  /////  Test find method /////
  if(b.find(findTest0))
  System.out.println("Test input 0 : " + findTest0 + " is found!");
  else
  System.out.println("Test input 0 : " + findTest0 + " is not found!");
  if(b.find(findTest1))
  System.out.println("Test input 1 : " + findTest1 + " is found!");
  else
  System.out.println("Test input 1 : " + findTest1 + " is not found!");
  if(b.find(findTest2))
  System.out.println("Test input 2 : " + findTest2 + " is found!");
  else
  System.out.println("Test input 2 : " + findTest2 + " is not found!");
  if(b.find(findTest3))
  System.out.println("Test input 3 : " + findTest3 + " is found!");
  else
  System.out.println("Test input 3 : " + findTest3 + " is not found!");
  /////  Test find method /////

  b.traverseAllNode(b.getRoot());

  System.out.println();

  /////////////////////////////////////////
  /////                               /////
  /////       SORTING TEST CODE       /////
  /////                               /////
  /////////////////////////////////////////


  /////   Deactivete from here when you submit your code      /////
  /////   We will compare the running time of each search     /////
  /////           1. linear search(with sorted_array)         /////
  /////           2. binary search(with sorted_array)         /////
  /////           3. binary search tree                       /////
  /////   When you write report,                              /////

  double start = System.currentTimeMillis();
  for(int i = 0; i < 100000; i++)
  LinearSearch(sorted_array, sorted_array[i]);
  double duration = System.currentTimeMillis()-start;
  System.out.println("Linear Search: " +duration);

  start = System.currentTimeMillis();
  for(int i = 0; i < 100000; i++)
  BinarySearch(sorted_array, sorted_array[i]);
  duration = System.currentTimeMillis()-start;
  System.out.println("Binary Search: " +duration);

  start = System.currentTimeMillis();
  for(int i = 0; i < 100000; i++)
  b.find(sorted_array[i]);
  duration = System.currentTimeMillis()-start;
  System.out.println("BST Search: " +duration);

  */
  /////   Deactivate to here to use SIMPLE TEST CODE  /////


  /////////////////////////////////////////
  /////                               /////
  /////       SIMPLE TEST CODE        /////
  /////                               /////
  /////                               /////
  /////   Deactivate above code       /////
  /////   and activate below code     /////
  /////   for simple test.            /////
  /////   You have to deactivate      /////
  /////   before submit your code.    /////
  /////                               /////
  /////////////////////////////////////////

  /////   Activate from here to use SIMPLE TEST CODE  /////

  b.insert(10);
  b.insert(5);
  b.insert(8);
  b.insert(20);
  b.insert(15);
  b.insert(1);
  b.insert(30);
  b.insert(4);
  b.insert(11);
  b.insert(17);
  b.insert(3);


  /////  Test find method /////

  /////       Can find    /////
  if(b.find(10))  System.out.println("find 10");
  else            System.out.println("cannot find 10");
  if(b.find(5))  System.out.println("find 5");
  else            System.out.println("cannot find 5");
  if(b.find(8))  System.out.println("find 8");
  else            System.out.println("cannot find 8");
  if(b.find(20))  System.out.println("find 20");
  else            System.out.println("cannot find 20");
  if(b.find(15))  System.out.println("find 15");
  else            System.out.println("cannot find 15");
  if(b.find(1))  System.out.println("find 1");
  else            System.out.println("cannot find 1");
  if(b.find(30))  System.out.println("find 30");
  else            System.out.println("cannot find 30");
  if(b.find(4))  System.out.println("find 4");
  else            System.out.println("cannot find 4");
  if(b.find(11))  System.out.println("find 11");
  else            System.out.println("cannot find 11");
  if(b.find(17))  System.out.println("find 17");
  else            System.out.println("cannot find 17");
  if(b.find(3))  System.out.println("find 3");
  else            System.out.println("cannot find 3");

  /////   Cannot find     /////
  if(b.find(11664))  System.out.println("find 11664");
  else            System.out.println("cannot find 11664");

  /////   Traverse test   /////
  b.traverseAllNode(b.getRoot());

  System.out.println();
  /////   Activate to here to use SIMPLE TEST CODE    /////
}

}
