import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {
 
     
         public static void main(String[] args) {
                 // TODO Auto-generated method stub
                 for(int i = 0; i < 10000; i++){
                         new Thread(new HeartBeat()).start();
                 }
         }
 }
 
class HeartBeat implements Runnable{
         
        private static final int SLEEP = 9000;
         private static final String HOST = "192.168.0.104";
         private static final int PORT =5000;
         private static final String  HEARTBEAT = "heartBeat";
         
        /**
          * @see java.lang.Runnable#run()
          */
         @Override
         public void run() {
                 // TODO Auto-generated method stub
                 Socket socket = null;
                 PrintWriter printWriter = null;
                 BufferedReader bufferedReader = null;
                 try {
                         socket = new Socket(HOST,PORT);
                         printWriter = new PrintWriter(socket.getOutputStream());
                         bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                         while(true){
                                 printWriter.println(HEARTBEAT);
                                 printWriter.flush();
                                 Thread.sleep(SLEEP);
                         }
                 } catch (UnknownHostException e) {
                         // TODO Auto-generated catch block
                         e.printStackTrace();
                 } catch (IOException e) {
                         // TODO Auto-generated catch block
                         e.printStackTrace();
                 } catch (InterruptedException e) {
                         // TODO Auto-generated catch block
                         e.printStackTrace();
                 }finally{
                         try {
                                 if(printWriter != null){
                                         printWriter.close();
                                 }
                                 
                                if(bufferedReader != null){
                                         bufferedReader.close();
                                 }
                                 if(socket != null){
                                         socket.close();
                                 }
                         } catch (IOException e) {
                                 e.printStackTrace();
                         }
                 }
         }
 }
 
