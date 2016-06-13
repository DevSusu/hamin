import java.util.*;
import java.util.Map.Entry;
import java.io.*;

class HW6_TrieToFindWords {

    ////////////////////////////////////////////////////////////////////////////////////////////
    //----------------------------------- class WordHash -------------------------------------//
    ////////////////////////////////////////////////////////////////////////////////////////////
    public static class WordHash{
        private HashMap <String, HashMap<Integer, Float>> wordSet; // Float, because this value also can be a TFIDF value.

        WordHash(){
            wordSet = new HashMap <String, HashMap<Integer, Float>>();
        }

        public void addWord(String word){ // will used at the method "insertWord".
            if(!wordSet.containsKey(word)){
                wordSet.put(word, new HashMap<Integer, Float>());
            }
        }

        public void insertWord(String word, int docuNum, float freq){
            addWord(word);
            HashMap <Integer, Float> freqPerDocu = (HashMap<Integer, Float>)wordSet.get(word);
            // the value of "wordSet" become "freqPerDocu".

            // XXX //

            // 해당 document 에서 나타난 적이 없으면 새로 빈도수 1로 만들어주고,
            // 있을 경우 횟수를 한 번 증가시켜준다.
            if(!freqPerDocu.containsKey(docuNum)) {
              freqPerDocu.put(docuNum,freq); // freq = 1
            }
            else {
              freqPerDocu.put(docuNum,freqPerDocu.get(docuNum)+freq);
            }

            // XXX //

        }

        public void printWordSet(){
            Set<Entry<String, HashMap<Integer, Float>>> set = wordSet.entrySet();
            Iterator<Entry<String, HashMap<Integer, Float>>> it = set.iterator(); // iterator of outside HashMap.

            while(it.hasNext()){
                Map.Entry<String, HashMap<Integer, Float>> e = (Map.Entry<String, HashMap<Integer, Float>>)it.next();
                Set<Entry<Integer, Float>> subSet = ((HashMap<Integer, Float>)e.getValue()).entrySet();
                Iterator<Entry<Integer, Float>> subIt = subSet.iterator(); // iterator of inside HashMap.

                System.out.println(" * " + e.getKey() + "[" + subSet.size() + "]");

                while (subIt.hasNext()){
                    Map.Entry<Integer, Float> subE = (Map.Entry<Integer, Float>)subIt.next();
                    int docuNum = (int)subE.getKey();
                    float freq = (float)subE.getValue();
                    System.out.println(docuNum + " " + freq);
                }
                System.out.println();

            }
        }

        public void filterWord(){ // Use TFIDF value.
        // Change freq value to TFIDF value.

        // XXX //
        // 이때까지 나온 모든 단어들을 Loop
        for ( String i : wordSet.keySet() ) {
          HashMap <Integer, Float> freqPerDocu = (HashMap<Integer, Float>)wordSet.get(i);

          // 단어 출현 빈도를 문서 갯수만큼 나눠줌
          for ( Integer j : freqPerDocu.keySet() )
            freqPerDocu.put(j, freqPerDocu.get(j) / freqPerDocu.size() );

        }
        // XXX //

        }

    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    //----------------------------------- class TrieNode -------------------------------------//
    ////////////////////////////////////////////////////////////////////////////////////////////
    public static class TrieNode {
        private boolean isWord; // isEndOfWord
        private char data;
        private TrieNode[] childs;
        private InfoNode infoNode;

        TrieNode(){
            isWord = false;
            childs = new TrieNode[26];
        }

        TrieNode(char _data, boolean isWordNew){
            isWord = isWordNew;
            data = _data;
            childs = new TrieNode[26];
        }

        boolean hasLetter(char letter) {
          return hasLetter((int)(letter-'A'));
        }
        boolean hasLetter(int index) {
          return null != childs[ index ];
        }

        void insert(char letter, TrieNode node) {
          childs[(int)(letter-'A')] = node;
        }

        TrieNode get(char letter) {
          return get((int)(letter-'A'));
        }

        TrieNode get(int index) {
          if( hasLetter(index) )
            return childs[index];
          else
            return null;
        }

        void setInfoNode(String word, int docuNum, float tfidf){
            if( null == infoNode) {
              infoNode = new InfoNode(word,docuNum,tfidf);
            }
            else {
              infoNode.setInfoNode( new InfoNode(word,docuNum,tfidf) );
            }
        }
        void printInfoNode() {
          if( isWord == false || infoNode == null ){
            return;
          }
          infoNode.printInfo();
        }
    }

    public static class InfoNode { // become a leaf node.
        private String word;
        private int docuNum;
        private float tfidf;
        private InfoNode infoChild; // because, multiple documents can cover same topic.

        InfoNode(){
            word = null;
        }

        InfoNode(String _word, int _docuNum, float _tfidf){
          word = _word;
          docuNum = _docuNum;
          tfidf = _tfidf;
        }

        void setInfoNode(InfoNode infoChild) {
          if( null == this.infoChild )
            this.infoChild = infoChild;
          else
            this.infoChild.setInfoNode(infoChild);
        }

        void printInfo() {
          System.out.println(word + "\t\t" + docuNum + " " + tfidf);
          if( null != infoChild )
            infoChild.printInfo();
        }
    }

    public static class Trie {
        private TrieNode root;

        Trie(){
            root = new TrieNode();
        }

        public void insertion(String word, int docuNum, float tfidf){ // Insert a word into a Trie.
          // XXX //
          char[] letters = word.toCharArray();
          TrieNode stat = root;
          int i;
          for( i=0; i< word.length(); i++ ) {
            if( stat.hasLetter( letters[i] ) ) {
              stat = stat.get( letters[i] );
            }
            else {
              TrieNode tmp = new TrieNode( letters[i] , (i==(word.length()-1)) );
              stat.insert( letters[i] , tmp );
              stat = tmp;
            }
          }
          stat.setInfoNode(word, docuNum, tfidf);
          // XXX //
        }

        public boolean find(String word){ // If there is the word, return true. Else, return false.
          // XXX //
          char[] letters = word.toCharArray();
          TrieNode stat = root;
          int i;
          for( i=0; i< word.length(); i++ ) {
            if( stat.hasLetter( letters[i] ) ) {
              stat = stat.get( letters[i] );
            }
            else return false;
          }
          return true;
          // XXX //
        }

        public void search(String word){ // If there is the word, print it's info.
          char[] letters = word.toCharArray();
          TrieNode stat = root;
          int i;
          for( i=0; i< word.length(); i++ ) {
            if( stat.hasLetter( letters[i] ) ) {
              stat = stat.get( letters[i] );
            }
            else return ;
          }
          stat.printInfoNode();
        // ADD CODE //
        }


        private void print_slave(TrieNode node){
          int i;
          for ( i=0;i<26;i++ ) {
            TrieNode tmp = node.get(i);
            if( null != tmp )
              print_slave(tmp);
          }
          node.printInfoNode();
        }

        public void printAll(){
          print_slave(root);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////// Main Function ///////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) throws IOException{
        WordHash WH = new WordHash();
        int howManyDocu = 10;

        for(int i = 1; i <= howManyDocu; i++){
            int docuNum = i;
            String ch = String.valueOf(docuNum);
            // String filename = "/home/jypark/com/docu/docu" + ch + ".txt"; 다시 이걸로 바꿔줘야 putty 에서 돌아갑니다.
            String filename = "/Users/susu/Workspace/Java/hamin/hw6/docu/docu" + ch + ".txt";
            System.out.println(filename);

            BufferedReader br = new BufferedReader(new FileReader(filename));
            while(true){
                String line = br.readLine();
                if (line == null) break;
                System.out.println(line);

                StringTokenizer tk = new StringTokenizer(line, ". "); // split line with "." or " " or ". "
                String token;
                while(tk.hasMoreTokens()){
                    // token = tk.nextToken().toUpperCase().replaceAll("\\^([A-Za-z]+)([,'])([A-Za-z]?+)",""); // convert every character to capital letter.
                    token = tk.nextToken().toUpperCase().replaceAll("[,'\"0-9+]",""); // convert every character to capital letter.
                    WH.insertWord(token, docuNum, 1);
                }
            }
            br.close();
            System.out.println("----------------------------------------------");
        }

        WH.filterWord();
        WH.printWordSet();

        System.out.println("----------------------------------------------");

        Trie T = new Trie();
        Set<Entry<String, HashMap<Integer, Float>>> set = WH.wordSet.entrySet();
        Iterator<Entry<String, HashMap<Integer, Float>>> it = set.iterator();

        while(it.hasNext()){
            Map.Entry<String, HashMap<Integer, Float>> e = (Map.Entry<String, HashMap<Integer, Float>>)it.next();
            Set<Entry<Integer, Float>> subSet = ((HashMap<Integer, Float>)e.getValue()).entrySet();
            Iterator<Entry<Integer, Float>> subIt = subSet.iterator();

            while(subIt.hasNext()){
                Map.Entry<Integer, Float> subE = (Map.Entry<Integer, Float>)subIt.next();
                int docuNum = (int)subE.getKey();
                float freq = (float)subE.getValue();

                // System.out.println(e.getKey() + "\t\t" + docuNum + " " + freq);
                T.insertion(e.getKey(), docuNum, freq);
                System.out.printf("%-16s", e.getKey());
                System.out.println(docuNum + " " + freq);
            }
        }

        System.out.println("----------------------------------------------");
        System.out.println(T.find("BANANA"));
        System.out.println(T.find("APPLE"));
        System.out.println(T.find("CODING"));
        T.search("BANANA");
        T.search("LEMON");
        System.out.println("----------------------------------------------");

        T.printAll();
        System.out.println("----------------------------------------------");

    }
    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////

}
