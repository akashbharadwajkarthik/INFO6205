package edu.neu.coe.info6205.union_find;

public class UF_Client_Driver {
    private static int pairs = 0;
    public static int count(int n){ // return number of connections for n-sites
        UF_HWQUPC uf_hwqupc = new UF_HWQUPC(n);
        int connections = 0;
        while(uf_hwqupc.components()>1){
            int site1 = (int)(n*Math.random()); // generating a pseudorandom integer from [0,n-1] with uniform distribution
            int site2 = (int)(n*Math.random()); // generating a pseudorandom integer from [0,n-1] with uniform distribution
            if(!uf_hwqupc.isConnected(site1,site2)){// if sites not already connected
                uf_hwqupc.union(site1,site2);// connect them
                connections++; // increment connections since a new connection was made
            }
            pairs++;
        }
        return connections;
    }

    public static void main(String args[]){
        int runs = 15;
        for(int sites= 100;sites <= 2000000; sites+=sites){
            double avgConnections = 0.0;
            double avgPairs = 0.0;
            for (int trial = 1; trial <= runs;trial++){
                avgConnections += (count(sites) - avgConnections)/trial;
                avgPairs += (pairs - avgPairs)/trial;
                pairs = 0;
            }
            System.out.println("Number of objects (n): " + sites + ", Number of connections = " + avgConnections + ", Number of pairs (m) :" + String.format("%.2f",avgPairs));
        }
    }
}
