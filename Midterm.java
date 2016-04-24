public class Midterm {

  public static void change(int var) {
    System.out.println(System.identityHashCode(var));
    var = 100;
  }
  public static void change(int[] var) {
    System.out.println(System.identityHashCode(var));
    var[0] = 200;
  }
  public static void main(String[] args) {

    boolean a = true;
    if( a ) {
      System.out.println("True");
    } else {
      System.out.println("False");
    }

    System.out.println(a ? "True" : "False");

    int b = 10;
    while(b > 0) {
      int c = 10;
      b--;

    }
    // System.out.println(c); // error

    b = 10;

    switch(b) {
      case 10 : // if(b==10)
        System.out.println(10);
        break;
      case 5 : // else if(b==5)
        System.out.println(5);
        break;
      case 1 :
        System.out.println(1);
        break;
    }

    int var = 10;
    System.out.println(System.identityHashCode(var));
    change(var);
    System.out.println(var);

    int[] arr = {0,0,0};
    System.out.println(System.identityHashCode(arr));
    change(arr);
    System.out.println(arr[0]);

    // final float PI = 3.141592;
    // System.out.println(fi);

    Stat s1 = new Stat();
    Stat s2 = new Stat();
    Stat s3 = new Stat();

    s1.a = 1234;
    s1.b = 4321;
    System.out.println(s2.a);
    System.out.println(s2.b);

  }

  public static class Stat {
    Stat() {

    }
    public static int a = 0;
    public int b = 0;
  }
}
