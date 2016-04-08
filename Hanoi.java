import java.util.Scanner;

public class Hanoi{
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
            Thread.sleep(1000);
        }catch(InterruptedException e){}

    }
    static int end_of_array(int[] array){
        for(int i = 0; i < array.length; i++){
            if(array[i] == 0)
                return i;
        }
        return 0;
    }
    static void hanoi(int n, int[] A, int[] B, int[] C){
        /*      Implement your code Here      */


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
