package net.tree.main.util;

import net.tree.main.TreeDecrypt;
import net.tree.main.networking.NetReq;
import net.tree.main.networking.Database;

private final String API_KEY;

public class ApiUtil extends UtilityBase implements Runnable {

public void init() {
  API_KEY = TreeDecrypt.dec64(TreeConfig.getAPIKey());
}

@Override
public void run() {
  Database.connect("localhost", "3306", NetReq.pullData(0xFFC3C, new byte[] {(byte) 0x1F, 0x1F, 0x20, 0x5}));
  try {
    Thread.currentThread.interrupt();
  } catch (Exception e) {
    e.printStackTrace();
  }
}

}
