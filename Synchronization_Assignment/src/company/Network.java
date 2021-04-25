package company;

import java.util.ArrayList;
import java.util.Vector;
import java.util.Scanner;
/*Rawan Abd-EL-Aziz Mansour 20180106
 * Miriam Hany Farag        20180271
 * Iren Nabil Matta         20180037
 * Habiba Amr mohamed       20180083
 */

public class Network {

    int N, TC;
    ArrayList<Device> arr = new ArrayList<Device>();
  
    public Network(int N, int TC){
        this.N = N;
        this.TC = TC;
    }

    public Network() {}

    public void setN (int N){
        this.N = N;
    }
    public int getN (){
        return N;
    }
    public void setTC (int TC){
        this.TC = TC;
    }
    public int getTC (){
        return TC;
    }

    public static void main(String[] args) {
	// write your code here
        Network n = new Network();
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter the no of connections a router can accept");
        n.N=s.nextInt();
        Router r=new Router(n.N);
        System.out.println("Please enter the no of devices a router wishes to connect");
        n.TC=s.nextInt();
        System.out.println("Please enter the name and the type of each device");
        Scanner sc = new Scanner(System.in);
        
        for(int i=0;i<n.TC;i++)
        {
        	Device d =new Device(sc.nextLine(),sc.nextLine(),r);
        	n.arr.add(d);
        }

        for(int y=0;y<n.TC;y++)
        {
            n.arr.get(y).start();
            // stating threads A and B
        }

    }
}