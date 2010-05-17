package FullTextSearch;

import java.io.File;
import java.io.IOException;

import java.util.*;
import ItsyL.*;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Collector;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Scorer;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class FileIndexer {
    
    String dirAIndexar;
    File INDEX_DIR;
    IndexReader reader;
    Analyzer analyzer;
    
    boolean inicializado = false;
    
    private FileIndexer(){
        
    }
    
    public boolean init(String dir){
        if (inicializado) {
            return inicializado;
        } else {
            dirAIndexar = dir;
            INDEX_DIR = new File("index");

            crearIndice(new File(dirAIndexar));
            //para este paso ya indexamos todos los archivos

            crearReader(INDEX_DIR);
            
            analyzer = new StandardAnalyzer(Version.LUCENE_CURRENT);
            
            inicializado = true;
            return true;
        }
    }
    
    
    private int crearReader(File INDEX_DIR){
        try{
            reader = IndexReader.open(FSDirectory.open(INDEX_DIR));
            return 1;
        }
        catch (Exception e){
            return -1;
        }
    }
    
    
    /**
     * Crea el indice, retorna 1 si lo creo, -1 error, 2 si ya existia 
     * @param docDir
     * @return
     */
    private int crearIndice(File docDir){
        if (INDEX_DIR.exists()) {
            return 2;
        } else {
            if (!docDir.exists() || !docDir.canRead()) {
                System.err.println("El directorio'" +docDir.getAbsolutePath()+ "' no se puede leer, ¿que paso?");
                return -1;
            }

            Date start = new Date();
            try {
                IndexWriter writer = //Creamos el generador de indices
                    new IndexWriter(FSDirectory.open(INDEX_DIR),
                                    new StandardAnalyzer(Version.LUCENE_CURRENT),
                                    true, IndexWriter.MaxFieldLength.LIMITED);
                System.out.println("Indexando a '" + INDEX_DIR + "");
                indexar(writer, docDir); //el procedimiento que indexa en si...
                System.out.println("Optimizando indices...");
                writer.optimize();
                writer.close();

                Date end = new Date();
                System.out.println(end.getTime() - start.getTime() +
                                   " tardo la indexacion");

            } catch (IOException e) {
                e.printStackTrace();
            }
            return 1;
        }
    }


    private void indexar(IndexWriter writer, File file) throws IOException {
        if (file.canRead()) {
            if (file.isDirectory()) {
                String[] files = file.list();
                if (files != null) {
                    for (int i = 0; i < files.length; i++) {
                        indexar(writer, new File(file, files[i])); //indexamos cada archivo del folder
                    }
                }
            } else {
                System.out.println("adding " + file);
                try {
                    writer.addDocument(FileDocument.Document(file));
                }
                catch (Exception e) { 
                    e.printStackTrace();
                }                
                //algo malo puede pasar, simplemente pasemole encima
            }
        }
    }
    
    public List<Item> buscarQuery(String s){
        List<Item> resultados = new ListaEnlazada<Item>();
        QueryParser parser = new QueryParser(Version.LUCENE_CURRENT, "contents", analyzer);
        Searcher searcher = new IndexSearcher(reader);


        try {
            CollectorList col = new CollectorList();
            Query query = parser.parse(s);
            searcher.search(query, col);
            
            for (Integer i:col.getLis()){
                Document doc = searcher.doc(i);
                Item it = new Item();
                it.setRuta(doc.get("path"));
                resultados.add(it);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return resultados;
    }

    //estilo singleton
    public static FileIndexer getInstance() {
        return FileIndexer.FileScannerHolder.INSTANCE;
    }


    private static class FileScannerHolder {
        private static final FileIndexer INSTANCE = new FileIndexer();
    }

    class CollectorList extends Collector {
        private Scorer scorer;
        private int docBase;
        
        ListaEnlazada<Integer> lis = new ListaEnlazada<Integer>();
        
        public List<Integer> getLis(){
            return lis;
        }
        
        @Override
        public void collect(int doc) throws IOException {
            lis.add(doc);
        }

        @Override
        public boolean acceptsDocsOutOfOrder() {
            return true;
        }

        @Override
        public void setNextReader(IndexReader reader,
                                  int docBase) throws IOException {
            this.docBase = docBase;
        }

        @Override
        public void setScorer(Scorer scorer) throws IOException {
            this.scorer = scorer;
        }

    }
    
    
}
