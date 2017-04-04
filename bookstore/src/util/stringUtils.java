package util;

/**
 * Created by jia on 3/23/17.
 */
public class stringUtils {
    //is the string empty
    public static boolean isEmpty(String str) {
        return null == str || str.equals("") || str.matches("\\s*");
    }

    public static String defaultValue(String content, String defaultValue) {
        if (isEmpty(content)) {
            return defaultValue;
        }
        return content;
    }

    public static String columnToProperty(String column) {
        //if the field is empty
        if (isEmpty(column))
            return "";
        //get the length
        Byte length = (byte) column.length();

        StringBuilder sb = new StringBuilder(length);
        int i = 0;
        for (int j = 0; j < length; j++) {
            if (column.charAt(j) == '_') {
                while (column.charAt(j + 1) == '_') {
                    j += 1;
                }
                sb.append(("" + column.charAt(++j)).toUpperCase());

            } else {
                sb.append(column.charAt(j));
            }
        }
        return sb.toString();
    }

    public static String upperCaseFirstCharacter(String str){
        StringBuilder sb = new StringBuilder();
        char[] arr = str.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if(i==0) sb.append((arr[i] + "").toUpperCase());
            else sb.append((arr[i]+""));
        }
        return sb.toString();
    }
}

