import java.util.Scanner;

public class Hanoi{

  public static class Tower {

    Tower() {}
    // constructor
    Tower(int tower[]){
      this.tower = tower;
    }

    public void setTower(int tower[]) {
      this.tower = tower;
    }

    public int pop() {
      int l = end_value( tower );
      int v = tower[l];
      tower[l] = 0;
      return v;
    }

    public void transferTo(Tower dest) {

      dest.tower[ end_of_array(dest.tower) ] = pop();

    }

    public int tower[];
  }

  public static Tower tower_a;
  public static Tower tower_b;
  public static Tower tower_c;

    public static int disk_A[];
    public static int disk_B[];
    public static int disk_C[];
	public static int count = 0;

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

    // 처음으로 0이 나오는 위치. 비어있는 위치
    static int end_of_array(int[] array){
        for(int i = 0; i < array.length; i++){
            if(array[i] == 0)
                return i;
        }
        return 0;
    }
    // disk가 있는 마지막 위치
    static int end_value(int[] array){
      int l = end_of_array(array);

      if( l!=0 ){
        return l-1;
      }
      return 0;
    }

    // 디스크의 상태 A, B, C, 깊이 n 을 모두 C로 옮기고, 과정을 출력하고, 횟수를 세는 함수.
    static void hanoi(int n, int[] A, int[] B, int[] C){
        /*      Implement your code Here      */

        if ( n == 1 ) {
          int ic = end_of_array(C);
          int ia = end_value(A);

          C[ic] = A[ia];
          A[ia] = 0;

          visualization(A,B,C);

        }
        else {

          hanoi(n-1, A,C,B);

          int ic = end_of_array(C);
          int ia = end_value(A);

          C[ic] = A[ia];
          A[ia] = 0;

          hanoi(n-1, B,C,A);

          // MoveTower(disk - 1, source, spare, dest)
          // move disk from source to dest
          // MoveTower(disk - 1, spare, dest, source)

        }

        visualization(A,B,C);


    }

    static boolean hanoi(int n, Tower A, Tower B, Tower C) {

      if ( n == 1 ) {
        count++;
        A.transferTo(C);
        return true;
      } else {
        if ( hanoi(n-1,A,C,B) )
          visualization(tower_a.tower,tower_b.tower,tower_c.tower);

        count++;
        A.transferTo(C);

        visualization(tower_a.tower,tower_b.tower,tower_c.tower);

        if ( hanoi(n-1,B,A,C) )
          visualization(tower_a.tower,tower_b.tower,tower_c.tower);
      }

      return false;

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

        tower_a = new Tower(disk_A);
        tower_b = new Tower(disk_B);
        tower_c = new Tower(disk_C);

        visualization(disk_A, disk_B, disk_C);

        if ( hanoi(level, tower_a,tower_b,tower_c) )
          visualization(tower_a.tower,tower_b.tower,tower_c.tower);

		System.out.println("The number of moving operation : " + count);

    }
}
