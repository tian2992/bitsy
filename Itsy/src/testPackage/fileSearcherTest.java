package testPackage;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;

import FullTextSearch.*;

import ItsyL.Item;

import java.util.List;

import org.junit.Test;

public class fileSearcherTest {
    public fileSearcherTest() {
    }
    
    public void main(){
        
    }

    @Before
    public void setUp() throws Exception {
        FileIndexer fi = FileIndexer.getInstance();
        fi.init("/home/tian/sketches");
    }

    @Test
    public void testFileSearch(){
        FileIndexer fi = FileIndexer.getInstance();
        
        List<Item> listo = fi.buscarQuery("Astaroth");
        
        assertNotSame(0,listo.size());
        
        for (Item i: listo){
            System.out.println(i.getRuta());
        }
        
        
    }

    @After
    public void tearDown() throws Exception {
    }
}
