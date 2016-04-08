import java.util.Scanner;

public class susuHanoi{
    public static int disk_A[];
    public static int disk_B[];
    public static int disk_C[];
	public static int count = 0;

  public static int from = 0;
  public static int moveto = 2; // default move to c

    static void print_disk(int size){
        for(int i = 0; i < 5-size; i++)
            System.out.print(" ");
        for(int i = 0; i < size; i++){
            if(i == size/2 && size%2 == 0)
                System.out.print("||--");
            else if(i == size/2 && size%2 == 1)
                System.out.print("-||-");
            else
                System.out.print("--");
        }
        for(int i = 0; i < 5-size; i++)
            System.out.print(" ");
        System.out.print("\t");
    }
    static void visualization(int[] A, int[] B, int[] C){
        for(int i = 4; i >= 0; i--){
            print_disk(A[i]);
            print_disk(B[i]);
            print_disk(C[i]);
            System.out.println("");
        }
        System.out.println("============\t============\t============\n");

        try{
            Thread.sleep(100);
        }catch(InterruptedException e){}

    }
    static int end_of_array(int[] array){
        for(int i = 0; i < array.length; i++){
            if(array[i] == 0)
                return i;
        }
        return 0;
    }
    static int end_value(int[] array){
      int l = end_of_array(array);

      if( l!=0 ){
        return array[l-1];
      }
      return 0;
    }
    static void hanoi(int n, int[] A, int[] B, int[] C){
        /*      Implement your code Here      */
        count++;
        /*
        * a_n = 2*a_n-1 + 1
        * 1. move n-1 disks to B
        * 2. move 1 last disk to C
        * 3. move n-1 disks to C
        */
        int va = end_value(A), la = end_of_array(A);
        int vb = end_value(B), lb = end_of_array(B);
        int vc = end_value(C), lc = end_of_array(C);

        if ( n!= 1 ) {
          moveto = 3 - moveto;
          hanoi(n-1,A,B,C);
        } else {
          if ( from == 0 ) {

            if(moveto == 2) {
              C[lc] = va;
              A[la-1] = 0;
            }
            else if(moveto == 1){
              B[lb] = va;
              A[la-1] = 0;
            }

          } else if ( from == 1 ) {

            if(moveto == 0) {
              A[la] = vb;
              B[lb-1] = 0;
            }
            else if(moveto == 2){
              C[lc] = vb;
              B[lb-1] = 0;
            }

          } else {

            if(moveto == 0) {
              A[la] = vc;
              C[lc-1] = 0;
            }
            else if(moveto == 1){
              B[lb] = vc;
              C[lc-1] = 0;
            }

          }
          visualization(A,B,C);
          return ;
        }

        moveto = 3 - moveto;
        hanoi(n-1,A,B,C);

        if(moveto == 2) {
          C[lc] = A[la];
          A[la] = 0;
        }
        else if(moveto == 1){
          B[lb] = A[la];
          A[la] = 0;
        }
        visualization(A,B,C);


    }
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        disk_A = new int[6];
        disk_B = new int[6];
        disk_C = new int[6];

        System.out.print("Number of disk: ");
        int level = scan.nextInt();

        for(int i = 0; i < 5; i++){
            if(i < level)
                disk_A[i] = level - i;
            else
                disk_A[i] = 0;
            disk_B[i] = 0;
            disk_C[i] = 0;
        }

        visualization(disk_A, disk_B, disk_C);
        hanoi(level, disk_A, disk_B, disk_C);
		System.out.println("The number of moving operation : " + count);

    }
}
