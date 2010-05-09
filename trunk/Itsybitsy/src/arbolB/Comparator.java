package arbolB;

/**
 *
 * @author tian
 */
class Comparator<T> implements java.util.Comparator<T> {
	public int compare (T o1, T o2) {
		return ((Comparable<T>)o1).compareTo(o2) ;
	}
}

class ComparadorMix<T> {
	Comparator<T> c ;
	public ComparadorMix() {
		c = null ;
	}

	public Comparator<T> getComparador() {
		if (c==null)
			c = new Comparator<T>() ;
		return c ;
	}

	public void setComparador(Comparator<T> c) {
		if (c==null)
			c = new Comparator<T>() ;
		this.c = c ;
	}

}