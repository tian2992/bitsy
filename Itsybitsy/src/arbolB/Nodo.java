package arbolB;

import java.lang.reflect.Array;

public class Nodo<K,V> {

    int mK;
    int mB;
    K mLlaves[];
    V mDatos[];
    Nodo<K,V> mPunteros[];

    private static int numeroDeNodo = 1;

    public String getDotName() {
        return "Nodo" + this.hashCode();
    }

    public String toDot(  )  {
            
            StringBuilder b = new StringBuilder();
            
            b.append( getDotName() );
            b.append("[label=\"<P0>");
            for( int i = 0; i < mB; i++ ) {
                b.append( "|" +  mLlaves[i].toString() );
                b.append( "|<P" + (i+1) + ">" );                
            }
            
            b.append("\"];\n");
            
            for( int i = 0; i <= mB ; i++ ) {
                if( mPunteros[i] != null )   {
                    b.append( mPunteros[i].toDot() );
                    b.append( getDotName() + ":P" + i + " -> " + mPunteros[i].getDotName() + ";\n" );
                }
            }
            
            return b.toString();
            
        }


    public Nodo(int pK) {
        this.mK = pK;
        mB = 0;

        //System.out.println(mLlaves);

        //mLlaves = (K[]) Array.newInstance(mLlaves.getClass(), (2 * pK + 1));
        //mDatos  = (V[]) Array.newInstance(mDatos.getClass(),  (2 * pK + 1));


        //Muy FEO!
        mLlaves = (K[]) new Object[(2 * pK + 1)];
        mDatos  = (V[]) new Object[(2 * pK + 1)];

        //mLlaves = new K[2 * pK + 1];
        //mDatos = new V[2 * pK + 1];
        mPunteros = new Nodo[(2 * pK) + 2];
    }

    public Nodo (int pK, K pLlave, V pDato) {
        this(pK);
        mB = 1;
        mLlaves[0] = (K)pLlave;
        mDatos[0] = (V)pDato;
    }

    public void setK(int mK) {
        this.mK = mK;
    }

    public int getK() {
        return mK;
    }
}
