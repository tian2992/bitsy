package FullTextSearch;

import java.io.*;

import org.apache.lucene.document.*;


public class FileDocument {
    
    /**
     * Genera documentos en base a un Archivo
     * @param f
     * @return
     * @throws FileNotFoundException
     */
    public static Document Document(File f) throws java.io.FileNotFoundException {
        Document doc = new Document();
        
        doc.add(new Field("path", f.getAbsolutePath(), Field.Store.YES, //absolute en vez de solo path
                          Field.Index.NOT_ANALYZED));           //Indexa pero no analiza
        doc.add(new Field("modified",
                          DateTools.timeToString(f.lastModified(), DateTools.Resolution.MINUTE),
                          Field.Store.YES, Field.Index.NOT_ANALYZED));
        doc.add(new Field("contents", new FileReader(f)));
        
        doc.add(new Field("folder", f.isDirectory()+"", Field.Store.YES, Field.Index.NOT_ANALYZED)); 
        //feo para almacenar si es folder
        return doc;
    }

}
