package bo.gob.aduana.system;

import java.io.IOException;

import java.lang.reflect.Field;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Util {

    public static void main(String[] args) {
        System.out.println("Input - "+1393572325000L);
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            System.out.println(TimeZone.getTimeZone("UTC"));
            Date date= new Date();  
            String dateString = formatter.format(date);

            System.out.println("Converted UTC TIME (using Format method) : "+dateString);

            Date date2 =null;
            try {
                date2 = formatter.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            System.out.println("Parsed Date Object (using Parse method) : "+date2);
    }
    
    public static float convertir (String num) {
        return Float.parseFloat(num);
        //return Float.parseFloat(num.replace(".", "").replace(",", "."));    
    }
    
   
    public static void var_dump(Object o) {
        System.out.println("INICIO ----------------------->");
        if (o == null) {
            System.out.println("El objeto es null");
        }
        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                System.out.println(field.getName() + " - " + field.get(o));
            }catch (java.lang.IllegalAccessException e) {
                System.out.println(e);
            }
        }
        System.out.println("FIN ----------------------->");
    }

    public static String capitalizeString(String string) {
        char[] chars = string.toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i] == '.' ||
                       chars[i] == '\'') { // You can add other chars here
                found = false;
            }
        }
        return String.valueOf(chars);
    }
    
    public static String getObjeto(String path) {
        char[] chars = path.toCharArray();
        String objeto = "";
        for (int i = 0; i < chars.length; i++) {
            int num = (int)chars[i];
            if (num >= 65 && num <= 90) {
                return objeto;
            }
            objeto += chars[i];
        }
        return objeto;
    }
    
    public static void isAjax(HttpServletRequest request, HttpServletResponse response) {
        if (!"XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            Util.redirect(request, response, "/index.do");
        }
    }
    
    public static void redirect(HttpServletRequest request, HttpServletResponse response, String url) {
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (ServletException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static int getIdTramite(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("id") != null && Integer.parseInt(request.getParameter("id")) > 0) {
            return Integer.parseInt(request.getParameter("id"));
        } else {
            if (request.getSession().getAttribute("ID") != null) {
                return Integer.parseInt(request.getSession().getAttribute("ID").toString());
            }
        }
        Util.redirect(request, response, "/dashboard.do");
        return -1;
    }
    
    public static boolean test (String value, String pattern) {
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern);
        java.util.regex.Matcher m = p.matcher(value);
        return m.matches();
    }
       
    public static String join(String elements[], String separator) {
        String text = "";
        for (String el : elements) {
            text += el + separator;
        }
        return text.replaceAll("\\" + separator + "+$","");
    }
    
    public static void noCache(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.
    }
    
}
