package testPackage;

import ItsyL.NetworkController;

import java.net.Inet4Address;
import java.net.InetAddress;

public class NetSocketTest {
    public NetSocketTest() {
        super();
    }
    
    public static void main(String args[]) throws Exception{
        NetworkController n = NetworkController.getInstance();
        System.out.println(InetAddress.getLocalHost().getCanonicalHostName());
        n.init("localhost",6148);
        //el objeto NetWorkControllerThread hace el trabajo
    }
}
