import java.util.ArrayList;
import java.util.List;

public class kmp {
    public static int[] buildLPS(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int len = 0;
        lps[0] = 0;

        int i = 1;
        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
    public static List<Integer> search(String text, String pattern) {
        List<Integer> result = new ArrayList<>();
        if (pattern == null || pattern.length() == 0) {
            for (int i = 0; i <= text.length(); i++) result.add(i);
            return result;
        }
        if (text == null || text.length() == 0 || pattern.length() > text.length()) return result;

        int[] lps = buildLPS(pattern);
        int n = text.length(), m = pattern.length();
        int i = 0, j = 0;

        while (i < n) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++; j++;
                if (j == m) {
                    result.add(i - j);
                    j = lps[j - 1];
                }
            } else {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return result;
    }

    private static String positionsToString(List<Integer> pos) {
        if (pos.isEmpty()) return "No occurrences";
        StringBuilder sb = new StringBuilder();
        for (int p : pos) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(p);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("KMP algorithm tests\n");

        String text1 = "abracadabra";
        String pattern1 = "abra";
        List<Integer> res1 = search(text1, pattern1);
        System.out.println("Test 1 - Short:");
        System.out.println("Text:    " + text1);
        System.out.println("Pattern: " + pattern1);
        System.out.println("Positions: " + positionsToString(res1));
        System.out.println();

        String text2 = "aaabaaaabaaabaaaab";
        String pattern2 = "aaabaa";
        List<Integer> res2 = search(text2, pattern2);
        System.out.println("Test 2 - Medium:");
        System.out.println("Text:    " + text2);
        System.out.println("Pattern: " + pattern2);
        System.out.println("Positions: " + positionsToString(res2));
        System.out.println();

        StringBuilder sb = new StringBuilder();
        for (int rep = 0; rep < 2000; rep++) {
            sb.append("abacaba");
        }
        String text3 = sb.toString();
        String pattern3 = "cab";
        List<Integer> res3 = search(text3, pattern3);
        System.out.println("Test 3 - Long:");
        System.out.println("Text length: " + text3.length());
        System.out.println("Pattern: " + pattern3);
        System.out.println("Number of occurrences: " + res3.size());
        for (int k = 0; k < Math.min(5, res3.size()); k++) {
            System.out.print(res3.get(k) + (k < Math.min(5, res3.size()) - 1 ? ", " : ""));
        }
        if (res3.size() > 10) System.out.print(", ..., ");
        int start = Math.max(5, res3.size() - 5);
        for (int k = start; k < res3.size(); k++) {
            if (k > start) System.out.print(", ");
            System.out.print(res3.get(k));
        }
        System.out.println("\n");
    }
} 
