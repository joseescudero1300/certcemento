package servicios;

import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

public class DireccionIP {
    public String getIpAddr(HttpServletRequest request) {
          final String[] HEADER_LIST = {
             "X-Forwarded-For",
             "Proxy-Client-IP",
             "WL-Proxy-Client-IP",
             "HTTP_X_FORWARDED_FOR",
             "HTTP_X_FORWARDED",
             "HTTP_X_CLUSTER_CLIENT_IP",
             "HTTP_CLIENT_IP",
             "HTTP_FORWARDED_FOR",
             "HTTP_FORWARDED",
             "HTTP_VIA",
             "REMOTE_ADDR"
         };
         for (String header : HEADER_LIST) {
             String ip = request.getHeader(header);
             if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip) && !isPrivateAdress(ip)) {
                 return ip;
             }
         }
         return request.getRemoteAddr();
     }

     public boolean isPrivateAdress(String host) {
         try {
             return java.net.InetAddress.getByName(host).isSiteLocalAddress();
         } catch (UnknownHostException e) {
             return true;
         }
     }

}
