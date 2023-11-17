/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        try {
            String pstr, gstr , Astr;
            String serverName="localhost";
            int port=8088;
            
            //declare p , g y la clave cliente
            
            int p=23;
            int g=9;
            int a=4;
            double Adash, serverB;
            
            //se establece la conexion
            
            System.out.println("Connecting to " +serverName + "on port"
            +port);
            Socket client=new Socket(serverName, port);
            System.out.println("Just connected to"
            +client.getRemoteSocketAddress());
            
            //se envia los datos
            OutputStream outToServer=client.getOutputStream();
            DataOutputStream out=new DataOutputStream(outToServer);
            
            pstr=Integer.toString(p);
            out.writeUTF(pstr); //envia p
            
            gstr=Integer.toString(g);
            out.writeUTF(gstr);//envia g
            
            double A=((Math.pow(g, a)) %p);// calculo de A
            Astr=Double.toString(A);
            out.writeUTF(Astr); //envia A
            
            //clave privada del cliente
            System.out.println("Desde el cliente : Clave privada = " +a);
            
            //acepta los datos
            
            DataInputStream in=new DataInputStream(client.getInputStream());
            
            serverB=Double.parseDouble(in.readUTF());
            System.out.println("Desde el servidor : Clave publica = " + serverB);
            
            Adash=((Math.pow(serverB, a))% p ) ;//calculo de adash
            System.out.println("Clave secreta para realizar el cifrado simetrico " + Adash);
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
            
            
        }
    }
    
}
