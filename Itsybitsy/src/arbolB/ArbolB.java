package arbolB;

//import java.util.Map;

import java.io.File;

import java.io.FileWriter;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class ArbolB<K, V> implements java.util.Map<K, V>,
                                     java.io.Serializable {

    private Nodo<K, V> mRaiz = null;
    private int mK = 2;
    private int mAltura = 0;
    private int tama = 0;

    java.util.Comparator comparador = new Comparator();

    public void generarGrafoJPG(String pCarpeta, String pNombre) {

        String codigo = this.toDot();

        String nombreTxt = pCarpeta + File.separator + pNombre + ".txt";

        File salida = new File(nombreTxt);

        System.out.println("Localizacion del archivo temporal: ");
        System.out.println(salida.getAbsolutePath());

        try {

            FileWriter fw = new FileWriter(salida);

            fw.write(codigo);

            fw.close();


        } catch (Exception e) {
            e.printStackTrace();
        }


        try {

            String dotPath = "dot";

            String fileInputPath = salida.getAbsolutePath();
            String fileOutputPath =
                salida.getAbsolutePath().replace("txt", "jpg");

            String tParam = "-Tjpg";
            String tOParam = "-o";

            String[] cmd = new String[5];
            cmd[0] = dotPath;
            cmd[1] = tParam;
            cmd[2] = fileInputPath;
            cmd[3] = tOParam;
            cmd[4] = fileOutputPath;

            Runtime rt = Runtime.getRuntime();

            rt.exec(cmd);

            Thread.sleep(600L); //darle un tiempito a dot para generar el jpg

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        }
    }

    public String toDot() {
        StringBuilder b = new StringBuilder();

        b.append("digraph g { \n node [shape=record];\n");

        b.append(mRaiz.toDot());

        b.append("}");

        return b.toString();
    }

    public ArbolB() {
    }

    public ArbolB(int k) {
        this.mK = k;
    }

    public ArbolB(Nodo pRaiz) {
        mK = pRaiz.getK();
        this.mRaiz = pRaiz;
        mAltura = 1;
    }

    public void insert(K key, V obj) {
        tama++;
        if (this.mAltura == 0) {
            this.mRaiz = new Nodo<K, V>(this.mK, key, obj);
            this.mAltura = 1;
            return;
        }

        Split splitted = insert(this.mRaiz, key, obj, 1);

        if (splitted == null) {
        } else {

            Nodo ptr = this.mRaiz;

            this.mRaiz =
                    new Nodo<K, V>(this.mK, (K)splitted.getLlave(), (V)splitted.getDato());
            this.mRaiz.mPunteros[0] = ptr;
            this.mRaiz.mPunteros[1] = splitted.getPuntero();
            this.mAltura++;
        }
    }

    protected Split insert(Nodo node, K key, V obj, int level) {

        Split splitted = null;
        Nodo ptr = null;

        int i = 0;

        //while ((i < node.mB) && (key.mayorQue(node.mLlaves[i])))
        while ((i < node.mB) &&
               (comparador.compare(key, node.mLlaves[i]) > 0)) {
            i++;
        }

        //if ((i < node.mB) && key.igualA(node.mLlaves[i])) {

        if ((i < node.mB) && (comparador.compare(key, node.mLlaves[i]) == 0)) {
            node.mDatos[i] = obj;
            return null;
        }

        if (level < this.mAltura) {

            splitted = insert(node.mPunteros[i], key, obj, level + 1);

            if (splitted == null)
                return null;
            else {
                key = (K)splitted.mLlave;
                obj = (V)splitted.mDato;
                ptr = splitted.mPuntero;
            }
        }

        i = node.mB - 1;
        //while ((i >= 0) && (node.mLlaves[i] == null || key.menorQue(node.mLlaves[i]))) {
        while ((i >= 0) &&
               (node.mLlaves[i] == null || comparador.compare(key, node.mLlaves[i]) <
                0)) {
            node.mLlaves[i + 1] = node.mLlaves[i];
            node.mDatos[i + 1] = node.mDatos[i];
            node.mPunteros[i + 2] = node.mPunteros[i + 1];
            i--;
        }

        node.mLlaves[i + 1] = key;
        node.mDatos[i + 1] = obj;
        if (splitted != null)
            node.mPunteros[i + 2] = splitted.mPuntero;
        node.mB++;

        if (node.mB > 2 * mK) {

            Nodo newnode = new Nodo<K, V>(this.mK);
            newnode.mPunteros[this.mK] = node.mPunteros[node.mB];
            node.mPunteros[node.mB] = null;
            node.mB = this.mK + 1;
            for (i = 0; i < this.mK; i++) {
                newnode.mLlaves[i] = node.mLlaves[i + node.mB];
                node.mLlaves[i + node.mB] = null;
                newnode.mDatos[i] = node.mDatos[i + node.mB];
                node.mDatos[i + node.mB] = null;
                newnode.mPunteros[i] = node.mPunteros[i + node.mB];
                node.mPunteros[i + node.mB] = null;
            }
            node.mB--;

            splitted =
                    new Split(newnode, node.mLlaves[node.mB], node.mDatos[node.mB]);
            node.mLlaves[node.mB] = null;
            node.mDatos[node.mB] = null;
            newnode.mB = this.mK;
            node.mB = this.mK;

            return splitted;
        }

        return null;
    }

    public V search(K key) {
        return search(key, mRaiz);
    }

    public V search(K key, Nodo node) {

        if ((node == null) || (node.mB < 1)) {
            System.err.println("error");
            return null;
        }

        //if (key.menorQue(node.mLlaves[0]))
        if (comparador.compare(key, node.mLlaves[0]) < 0) {
            return search(key, node.mPunteros[0]);
        }

        //if (key.mayorQue(node.mLlaves[node.mB - 1]))
        if (comparador.compare(key, node.mLlaves[node.mB - 1]) > 0) {
            return search(key, node.mPunteros[node.mB]);
        }

        int i = 0;
        //while ((i < node.mB - 1) && (key.mayorQue(node.mLlaves[i])))
        while ((i < node.mB - 1) &&
               (comparador.compare(key, node.mLlaves[i]) > 0)) {
            i++;
        }

        //if (key.igualA(node.mLlaves[i]))
        if (comparador.compare(key, node.mLlaves[i]) == 0)
            return (V)node.mDatos[i];

        return search(key, node.mPunteros[i]);

    }


    public int getAltura() {
        return mAltura;
    }

    //=================================MAP Classes

    public V put(K key, V value) {
        insert(key, value);
        return value;
    }

    public V get(Object key) {
        try {
            return search((K)key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void clear() {
        mRaiz = null;
    }

    public int size() {
        return tama;
    }

    //=================================Unsuported Classes


    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean containsKey(Object key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public V remove(Object key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void putAll(Map<? extends K, ? extends V> m) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Set<K> keySet() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Collection<V> values() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Set<Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
