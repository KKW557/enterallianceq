package icu.kevin557.eq.utils;

/**
 * @author 557
 */
public class MathUtils {

    public static boolean isInteger(String s)
    {
        try {
            Integer.parseInt(s);
            return true;
        }
        catch(NumberFormatException e)
        {
            return false;
        }
    }

    public static boolean isLong(String s)
    {
        try {
            Long.parseLong(s);
            return true;
        }
        catch(NumberFormatException e)
        {
            return false;
        }
    }

    public static boolean isDouble(String s)
    {
        try
        {
            Double.parseDouble(s);
            return true;
        }
        catch(NumberFormatException e)
        {
            return false;
        }
    }
}
