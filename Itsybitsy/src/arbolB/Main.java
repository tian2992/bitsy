package arbolB;

public class Main {
  
    public static void main(String[] args) {


          System.out.println("prueba");

    ArbolB<String  , String> tree = new ArbolB(2);
    ArbolB<Integer , Integer> treeS = new ArbolB(2);

    int[] values = {100, 101, 40, 30, 25, 26, 15, 99, 205, 360};

    String[] valores = {"hola", "mundo", "rudy", "rosa", "algo", "nada"};

    for (int i = 0; i < valores.length; i++) {
      //System.out.print(" " + values[i]);
      tree.insert(valores[i], "Dato " + i);
      treeS.insert(values[i], i);
    }

    System.out.println(tree.search("mundo"));


    }
}
