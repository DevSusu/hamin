public class BST {

  public static class Node {

    Node parent;
    Node lchild;
    Node rchild;
    int value;

    Node(int value) {
      this.value = value;
    }

    // insert as leaf
    // Node(Node parent) {
    //
    // }

    void insert(int value) {

      // compare with value
      if ( this.value > value ) {

        if ( this.lchild != null ) { // null 이지 않으면
          this.lchild.insert(value);
        }
        else {
          Node tmp = new Node(value);
          tmp.parent = this;
          this.lchild = tmp;
        }

      }
      else {

        if ( this.rchild != null ) { // null 이지 않으면
          this.rchild.insert(value);
        }
        else {
          Node tmp = new Node(value);
          tmp.parent = this;
          this.rchild = tmp;
        }

      }

      // push to either left or right

      // if left or right doesn't exist,
      // then create one.

    }

    void traverse_front() {

      // visit left child

      // print value

      // visit right child

    }

  }

  public static class Tree {

    Tree(Node root) {
      this.root = root;
    }

    Node root;

    void insert(int value) {

      root.insert(value);
      // child????

    }

    // 작은값부터 뽑아내서 출력하는 함수. 전위순회
    void traverse_front() {

    }

  }

  public static void main(String[] args) {

    // node to be root
    Node node1 = new Node(50);

    // node1을 루트로 가지는 트리를 만들겠다.
    Tree tree = new Tree( node1 );

    tree.insert(30);
    tree.insert(70);

    System.out.println(tree.root.value); // 50
    System.out.println(tree.root.lchild.value); // 30
    System.out.println(tree.root.rchild.value); // 70

    // Node node2 = new Node(30);
    // Node node3 = new Node(70);
    //
    // tree.insert(node2); tree.insert(node3);

  }
}
