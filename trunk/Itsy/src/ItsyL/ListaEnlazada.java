package ItsyL;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Es la implementación de la lista enlazada acorde a java.util.List, Ya funciona :'D
 * @param <E> la clase de la lista
 * @author tian
 */
public class ListaEnlazada<E> implements java.util.List<E>, java.io.Serializable {

    /**
     * Constructor default, inicializa todo
     */
    public ListaEnlazada(){
        ini = fin = null;
    }

    static public boolean FuncioListon = false;

    private Contenedor ini, fin;

    int tama;

    /**
     * Añade el elemento a la lista
     * @param e
     * @return true siempre :D
     */
    public boolean add(E e) {
        tama++;
        if (ini==null){
            ini = new Contenedor(e);
            fin = ini;
            return true;
        }
        else { //solo lo añade al final...
            Contenedor conti = new Contenedor(e);
            conti.setAnt(fin);
            fin.setSig(conti);
            fin = conti;
            return true;
        }
    }

    /**
     * Retorna el tamaño de la lista, instantaneo
     * @return
     */
    public int size() {
      return tama;
      /*
        int contador = 0;
        Contenedor temp  = ini;
        while (temp!=null){
            contador++;
            temp = temp.getSig();
        }
        return contador;
       *
       */
    }

    /**
     * Si los punteros estan vacios, esta vacia
     * @return true si vacia, false si no vacia esta
     */
    public boolean isEmpty() {
        if (ini == null && fin==null)
            return true;
        else
            return false;
    }

    /**
     * Verifica que exista ya
     * @param o
     * @return true si lo contiene, false si no lo contiene
     */
    public boolean contains(Object o) {
        Contenedor tescato;
        boolean ordenanza = true;
        if (o.toString().compareToIgnoreCase("s")>0) //intentando optmizar un poco
        ordenanza = false;

        if (ordenanza)
            tescato = ini;
        else
            tescato = fin;

        while (tescato != null){
            if (tescato.payload.equals(o)){
                return true;
            }
            if (ordenanza)
                tescato = tescato.getSig();
            else
                tescato = tescato.getAnt();
        }

        return false; //no se encontro
    }

    /**
     * Permite acceder al iterador
     * @return
     */
    public Iterator<E> iterator() {
        return new Yterbio(this);
    }

    /**
     *
     * @return
     */
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param <T>
     * @param a
     * @return
     */
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Borra el elemento de la lista
     * @param o
     * @return true si funciona y esta, falso si no lo esta
     */
    public boolean remove(Object o) {
        Contenedor temp = ini;
        while (temp!=null){
            if (temp.getPayload().equals(o)){ //lo hemos encontrado
                tama--;
                if (ini.equals(temp)&&fin.equals(temp)){ //era unico
                    ini = null;
                    fin = null;
                    temp = null;
                    return true;
                }

                if (temp.isAnt()) //asegurar la seguridad
                    temp.getAnt().setSig(temp.getSig());
                else { // o sea es el inicial
                    ini = temp.getSig();
                }
                if (temp.isSig())
                    temp.getSig().setAnt(temp.getAnt());
                else { //O sea es el final...
                    fin = temp.getAnt();
                }



                temp = null;
                return true;
            }
            temp = temp.getSig();
        }
        return false;
    }

    /**
     *
     * @param c
     * @return
     */
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Añade todos los elementos de una coleccion a otra, en el caso de ListaEnlazada en tiempo O(n)=1, en otra lista, es lineal
     * @param c
     * @return
     */
    public boolean addAll(Collection<? extends E> c) { //deberia funcionar...
        if (c == null)
            return true;
        if (c.isEmpty())
            return true;
        
        tama += c.size();

        if (c.getClass().equals(this.getClass())){
           ListaEnlazada q = (ListaEnlazada)c;
           if (this.isEmpty()){ //si la a la que queremos añadir es nula
               setIni(q.getIni());
               setFin(q.getFin());
               return true;
           }
           getFin().setSig(q.getIni());
           setFin(q.getFin());
           return true;
        }
        else {
            for (E ellie: c){
                add(ellie);
            }
            return true;
        }
    }

    /**
     * Permite reajustar el inicio
     * @param c
     * @return
     */
    boolean setIni(Contenedor c){
        ini = c; //Estupido Typo
        return true;
    }
    /**
     * Permite reajustar el final
     * @param c
     * @return
     */
    boolean setFin(Contenedor c){
        fin = c;
        return true;
    }

    Contenedor getIni(){
        return ini;
    }

    Contenedor getFin(){
        return fin;
    }

    /**
     * Solo la añade al final
     * @param index
     * @param c
     * @return
     */
    public boolean addAll(int index, Collection<? extends E> c) {
        return addAll(c);

    }

    /**
     *
     * @param c
     * @return
     */
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param c
     * @return
     */
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Limpia la Lista
     */
    public void clear() {
        setIni(null);
        setFin(null);
        tama=0;
    }

    /**
     * Terriblemente ineficiente, pero funciona
     * @param index
     * @return null si el indice se pasa, si no el elemento
     */
    public E get(int index) {
        if (index > this.size()|| index < 0)
            return null;
        Contenedor temp = getIni();
        int i = 0;
        while (i!=index){
          temp = temp.getSig();
          i++;
        }
        return temp.getPayload();
    }

    /**
     *
     * @param index
     * @param element
     * @return
     */
    public E set(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Placeholder shuco
     * @param index
     * @param element
     */
    public void add(int index, E element) {
        add(element);
    }

    /**
     *
     * @param index
     * @return
     */
    public E remove(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param o
     * @return
     */
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param o
     * @return
     */
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Utiliza Yterbio para la interfaz iteradora
     * @return
     */
    public ListIterator<E> listIterator() {
        return new Yterbio(this);
    }

    /**
     *
     * @param index
     * @return
     */
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param fromIndex
     * @param toIndex
     * @return
     */
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //===================CLASES INTERNAS===================

    /**
     * Yterbio es una cruda implementacion de un iterador
     *
     * @implements java.util.ListIterator
     */
    class Yterbio implements java.util.ListIterator<E>, java.io.Serializable {

        public Yterbio(ListaEnlazada<E> listolon){
            l = listolon;
            inicio = l.ini;
            finito = l.fin;

        }

        ListaEnlazada<E> l;

        Contenedor inicio;
        Contenedor finito;
        Contenedor tempi;

        public boolean hasNext() {
            if (ini==null || fin == null)
                return false;

            if (tempi==null){
                return true;
            }
            if (tempi==fin){
                return false;
            }
            return tempi.isSig();
        }

        public E next() {
            if (tempi == null){
                tempi = ini;
                return tempi.getPayload();
            }
            else {
                tempi = tempi.getSig();
                return tempi.getPayload();
            }
        }

        public void remove() {
            Contenedor prev = tempi.getAnt();
            Contenedor sigur = tempi.getSig();

            if (prev!=null)
                prev.setSig(sigur);

            if (sigur!=null)
                sigur.setAnt(prev);

            if (prev==null) //si el que eliminamos era el inicial
                tempi=ini;
        }

        public boolean hasPrevious() {
            if (ini==null || fin == null)
                return false;
            if (tempi==null){
                return true;
            }
            if (tempi==ini){
                return false;
            }
            return tempi.isAnt();
        }

        public E previous() {
            if (tempi == null){
                tempi = fin;
                return tempi.getPayload();
            }
            else {
                tempi = tempi.getAnt();
                return tempi.getPayload();
            }
        }

        public int nextIndex() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public int previousIndex() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void set(E e) { //Deberian funcionar
            tempi.setPayload(e);
        }

        public void add(E e) { //supuestamente deberia funcionar
            l.add(e);
        }
    }


    class Contenedor implements java.io.Serializable{
        Contenedor sig; //Debieron Haber sido ZIG para gran justicia
        Contenedor ant;

        E payload;

        public Contenedor(){}

        public Contenedor(E pay){
            payload = pay;
        }

        public String toString(){
            return payload.toString();
        }

        public boolean isAnt(){
            if (getAnt()==null)
                return false;
            return true;
        }

        public boolean isSig(){
            if (getSig()==null)
                return false;
            return true;
        }

        public Contenedor getAnt() {
            return ant;
        }

        public void setAnt(Contenedor ant) {
            this.ant = ant;
        }

        public E getPayload() {
            return payload;
        }

        public void setPayload(E payload) {
            this.payload = payload;
        }

        public Contenedor getSig() {
            return sig;
        }

        public void setSig(Contenedor sig) {
            this.sig = sig;
        }

    }

}
