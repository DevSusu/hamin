

public class N3 {

  static int f(int n) {

  }

  public static void main(String[] args) {

    // step 1
    // nnn(1); // nnn(1) 을 하면 뭔지 모르겠지만 항상 1 을 출력한다.
    // System.out.println("");
    //
    // // step 2
    // nnn(2);
    // System.out.println("");
    // // nnn(3);
    //
    // nnn(4);
    // System.out.println("");
    // nnn(8);
    // System.out.println("");
    // nnn(5);
    // System.out.println("");
    //
    // max(5,10);
    // saveMax(5,10);
  }

  // 3n+1 의 과정을 배열로 주는 함수
  // static int[] nnn(int n ) {
  //  ...
  // }

  // 3n+1 의 과정을 출력해주는 함수
  static void nnn( int n ) {

    System.out.print(n);
    System.out.print(" ");

    if( n == 1 ) {
      return ; // break
    }
    else if ( n%2 == 0 ) {
      nnn(n/2);
    }
    else {
      nnn(3*n+1);
    }

  }
}
