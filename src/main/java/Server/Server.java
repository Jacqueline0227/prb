/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try {
            int port = 8088;

            //clave del servidor
            int b = 3;
            //cliente p , g y clave
            double clientP, clientG, clientA, B, Bdash;
            String Bstr;
            //Estableciendo la conexion
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Esperando al cliente en el puerto " + serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();
            System.out.println("Conectando a " + server.getRemoteSocketAddress());

            //clave privada del servidor
            System.out.println("Del servidor : Clave Privada =" + b);

            //acepta los datos del cliente
            DataInputStream in = new DataInputStream(server.getInputStream());

            clientP = Integer.parseInt(in.readUTF()); //recibe p 
            System.out.println("Del Cliente : P = " + clientP);

            clientG = Integer.parseInt(in.readUTF()); //cliente g
            System.out.println("Del Cliente : G = " + clientG);
            
            clientA=Double.parseDouble(in.readUTF());//recibe el dato a que esta procesando
            System.out.println("Del Cliente : A = " + clientA);
            
            B=((Math.pow(clientG, b)) % clientP); // calculo el dato de B
            Bstr=Double.toString(B);
            
            //enviamos el dato de b al cliente
            OutputStream outToclient=server.getOutputStream();
            DataOutputStream out=new DataOutputStream(outToclient);
            
            out.writeUTF(Bstr); //enviamos b
             
            Bdash=((Math.pow(clientA, b)) % clientP); // calculamos la clave secreta
            
            System.out.println("Clave secreta para realizar el cifrado simetrico = " + Bdash);
            server.close();

        } catch (Exception e) {
            System.out.println("Se agoto el tiempo de espera del zòcalo");
        }
    }

}
