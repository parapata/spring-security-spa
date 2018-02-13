package com.example.util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class StringUtil {

    /** space. */
    private static final char SPACE = '\u0020';

    /** &nbsp;. */
    private static final char NBPS = '\u00A0';

    /** nspace. */
    private static final char N_SPACE = '\u3000';

    private StringUtil() {
        throw new AssertionError();
    }

    public static boolean isEmpty(final String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isNotEmpty(final String str) {
        return str != null && str.isEmpty();
    }

    public static boolean equals(final String str1, final String str2) {
        return str1 == null ? str2 == null : str1.equals(str2);
    }

    public static boolean equalsIgnoreCase(final String str1, final String str2) {
        return str1 == null ? str2 == null : str1.equalsIgnoreCase(str2);
    }

    public static boolean isNumber(final String str) {
        if (isEmpty(str)) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (c < '0' || '9' < c) {
                return false;
            }
        }
        return true;
    }

    public static String ltrim(final String str) {
        if (isEmpty(str)) {
            return str;
        }
        int pos = 0;
        for (char c : str.toCharArray()) {
            if (c > SPACE && c != NBPS && c != N_SPACE) {
                break;
            }
            ++pos;
        }
        return str.substring(pos);
    }

    public static String rtrim(final String str) {
        if (isEmpty(str)) {
            return str;
        }
        int pos = str.length() - 1;
        char[] chs = str.toCharArray();

        while (0 < pos) {
            if (chs[pos] > SPACE && chs[pos] != NBPS && chs[pos] != N_SPACE) {
                break;
            }
            --pos;
        }
        return str.substring(0, pos + 1);
    }

    public static String trim(final String str) {
        if (isEmpty(str)) {
            return str;
        }
        return rtrim(ltrim(str));
    }

    public static String replaceFirst(final String str,
            final String regex, final String replacement) {
        if (isEmpty(str) || regex == null || replacement == null) {
            return str;
        }
        return str.replaceFirst(regex, replacement);
    }

    public static String replaceAll(final String str,
            final String regex, final String replacement) {
        if (isEmpty(str) || regex == null || replacement == null) {
            return str;
        }
        return str.replaceAll(regex, replacement);
    }

    public static String[] split(final String str, final String delim) {
        if (isEmpty(str)) {
            return new String[0];
        }
        List<String> list = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(str, delim);
        while (tokenizer.hasMoreElements()) {
            list.add((String) tokenizer.nextElement());
        }
        return list.toArray(new String[list.size()]);
    }

    public static String nvl2(final String str, String expr1, String expr2) {
        return isEmpty(str) ? expr1 : expr2;
    }

    public static String camelize(final String str) {
        if (StringUtil.isEmpty(str)) {
            return str;
        }
        String[] array = split(str.toLowerCase(), "_");
        if (array.length == 1) {
            return capitalize(str);
        }
        StringBuffer sb = new StringBuffer();
        for (String item : array) {
            if (sb.length() == 0) {
                sb.append(item);
            } else {
                sb.append(capitalize(item));
            }
        }
        return sb.toString();
    }

    public static String capitalize(final String str) {
        if (StringUtil.isEmpty(str)) {
            return str;
        }
        char chars[] = str.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        return new String(chars);
    }

    public static String snakelize(final String str) {
        if (StringUtil.isEmpty(str)) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                sb.append("_");
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
