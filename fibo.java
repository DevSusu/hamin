public class fibo {

  static int[] fibo_array = new int[1000];

  // fibo(n) 은 n 번째 피보나치 수
  static int fibo(int n) {

    System.out.print(n);
    System.out.print(" ");
    // 종결 조건
    if (fibo_array[n] != 0) {
      return fibo_array[n];
    }
    else {
      // 반복 조건
      // f는 n-1번째 피보나치 수 더하기 n-2번째 피보나치 수
      fibo_array[n] = fibo(n-1) + fibo(n-2);
      return fibo_array[n];
    }

  }

  public static void main(String[] args) {

    fibo_array[1] = 1;
    fibo_array[2] = 1;


    // System.out.println(fibo(1));
    // System.out.println(fibo(2));
    // System.out.println(fibo(3));
    // System.out.println(fibo(4));
    System.out.println(fibo(5));
    // System.out.println(fibo(6));
    // System.out.println(fibo(7));


  }
}
