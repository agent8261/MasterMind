package us.mastermind;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

public class NetTest
{
  private static boolean lOCAL_SERVER = false;
  private static final int BUFFER_SIZE = 10240;
  private static final Charset BYTE_ENCODING = Charset.forName("UTF-8");
  
  static final String LOCAL_URL = "http://localhost:8888";
  static final String REMOTE_URL = "http://www.sceadumind.us";
  
  static final String GREETING_PATH = "/backend/greet";
  
  public static void DoTest()
  {
    //doDebugMode();
    ByteArrayOutputStream bArr = new ByteArrayOutputStream();
    
    try
    {
      doUnsecuredGreeting(bArr);
      byte [] bytes = bArr.toByteArray();
      
      String s = new String(bytes, BYTE_ENCODING);
      System.out.println(s);
      System.out.println("Done");
    }
    catch( IOException e )
    {
      e.printStackTrace();
    }
    
  }
  
  // ---------------------------------------------------------------------------
  public static void doUnsecuredGreeting(ByteArrayOutputStream bArr)
      throws IOException
  {
    URL serverUrl = new URL(getGreetingUrl());
    HttpURLConnection conn = null;
    conn = (HttpURLConnection) serverUrl.openConnection();
    conn.setDoInput(true);
    conn.setRequestMethod("GET");
    
    byte[] data = new byte[BUFFER_SIZE];
    InputStream in = conn.getInputStream();
    int length = 0;
    while( (length = in.read(data, 0, BUFFER_SIZE)) > 0 )
    { bArr.write(data, 0, length); }
    
    int code = conn.getResponseCode(); 
    if( (code / 100) != 2 ) // Status codes not 2XX are bad
      throw new IOException();
  }
  
  // ---------------------------------------------------------------------------
  public static void doDebugMode()
  {
    lOCAL_SERVER = true;
  }
  
  // ---------------------------------------------------------------------------
  public static String getGreetingUrl()
  {
    return makeUrlFromBase(GREETING_PATH);
  }
  
  // ---------------------------------------------------------------------------
  private static String makeUrlFromBase(String path)
  {
    if( lOCAL_SERVER )
      return LOCAL_URL + path;
    else
      return REMOTE_URL + path;
  }
}
