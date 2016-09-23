package main.java.com.mindscapehq.android.raygun4android.network.http;

import main.java.com.mindscapehq.android.raygun4android.network.RaygunNetworkUtils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

public class RaygunHttpsUrlStreamHandler extends URLStreamHandler
{
  private static final int PORT = 443;
  private static final String PROTOCOL = "https";
  private URLStreamHandler originalHandler;

  public RaygunHttpsUrlStreamHandler(URLStreamHandler handler)
  {
    originalHandler = handler;
  }

  protected URLConnection openConnection(URL url)
      throws IOException
  {
    try
    {
      Method method = RaygunNetworkUtils.findMethod(originalHandler.getClass(), "openConnection", new Class<?>[]{ URL.class });
      method.setAccessible(true);

      URLConnection urlConnection = (URLConnection) method.invoke(originalHandler, new Object[]{ url } );

      if (urlConnection == null)
      {
        throw new IOException();
      }

      return new RaygunHttpsUrlConnection(urlConnection);
    }
    catch(NoSuchMethodException e) {
    }
    catch(IllegalAccessException e) {
    }
    catch(Throwable e) {
    }

    throw new IOException();
  }

  protected URLConnection openConnection(URL url, Proxy proxy)
      throws IOException
  {
    try
    {
      Method method = RaygunNetworkUtils.findMethod(originalHandler.getClass(), "openConnection", new Class<?>[]{ URL.class, Proxy.class });
      method.setAccessible(true);

      URLConnection urlConnection = (URLConnection) method.invoke(originalHandler, new Object[]{ url, proxy } );

      if (urlConnection == null)
      {
        throw new IOException();
      }

      return new RaygunHttpsUrlConnection(urlConnection);
    }
    catch(NoSuchMethodException e) {
    }
    catch(IllegalAccessException e) {
    }
    catch(Throwable e) {
    }

    throw new IOException();
  }

  public int getDefaultPort()
  {
    return PORT;
  }

  public String getProtocol()
  {
    return PROTOCOL;
  }
}