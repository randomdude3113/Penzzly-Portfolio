package net.tree.framework.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import net.tree.framework.encryption.AesEncryption;

public class SocketNetworkClient
{
  private String pass_key = "8tWKXaK8Ubj6DY8ypju3x8VxaYtEQhvM4EyRskTLCrCTZpvz";
  AesEncryption aes = new AesEncryption();
  private BufferedReader input;
  private PrintWriter output;
  private Socket socket;
  
  public void connectToServer(String serverAddress, int port)
    throws IOException
  {
    this.socket = new Socket(serverAddress, port);
    this.input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
    this.output = new PrintWriter(this.socket.getOutputStream(), true);
  }
  
  public void closeConnection()
    throws IOException
  {
    this.socket.close();
  }
  
  public void sendData(String data)
  {
    data = this.aes.encryptData(data, this.pass_key);
    this.output.println(data);
  }
  
  public String receiveData()
    throws IOException
  {
    String data = this.input.readLine();
    data = this.aes.decryptData(data, this.pass_key);
    return data;
  }
}
