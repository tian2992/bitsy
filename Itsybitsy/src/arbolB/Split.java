package arbolB;

class Split<K,V> {
    Nodo mPuntero;
    K mLlave;
    V mDato;

    public Split(Nodo pPuntero, K pLlave, V pDato) {
        this.mPuntero = pPuntero;
        this.mLlave = pLlave;
        this.mDato = pDato;
    }

    public void setPuntero(Nodo mPuntero) {
        this.mPuntero = mPuntero;
    }

    public Nodo getPuntero() {
        return mPuntero;
    }

    public void setLlave(K mLlave) {
        this.mLlave = mLlave;
    }

    public K getLlave() {
        return mLlave;
    }

    public void setDato(V mDato) {
        this.mDato = mDato;
    }

    public V getDato() {
        return mDato;
    }
}
