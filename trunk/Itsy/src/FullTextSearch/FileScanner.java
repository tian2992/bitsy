package FullTextSearch;

import java.io.File;

import java.util.logging.Logger;

public class FileScanner {
    public FileScanner() {
        super();
    }
    
    static final File INDEX_DIR = new File("index");
    
    public static FileScanner getInstance() {
        return FileScannerHolder.INSTANCE;
    }
    
    private static class FileScannerHolder {
        private static final FileScanner INSTANCE = new FileScanner();
    }
    
}
