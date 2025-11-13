# KMP Implementation

•  Goal
Implement KMP string search from scratch in Java and test on short, medium, and long strings.
•  Implementation notes
The algorithm builds an LPS array (longest proper prefix that is also suffix) in linear time relative to the pattern length. The search routine walks text and pattern using the LPS fallback to avoid re-scanning characters in text.
•  Tests
  1.  Short: text = "abracadabra", pattern = "abra" -> found at indices 0 and 7.
  2.  Medium: text = "aaabaaaabaaabaaaab", pattern = "aaabaa" -> found at indices 3 and 9 (demonstrates overlap handling and correct fallback).
  3.  Long: repeated "abacaba" 2000 times (text length 14000), pattern = "cab" -> many occurrences; program prints count and sample indices to validate linear behavior.
•  Complexity analysis
  ⁠◦  Preprocessing (LPS): O(m) time, O(m) extra space for the lps array.
  ⁠◦  Search: O(n) time in worst case; combined O(n + m).
  ⁠◦  Space: O(m) additional for LPS plus O(k) for storing k match indices. If only reporting matches on the fly, space can be O(1) beyond lps.
•  Correctness and edge cases
  ⁠◦  Empty pattern: treated as matching at every position (0..n).
  ⁠◦  Pattern longer than text: returns no occurrences.
  ⁠◦  Works with overlapping matches due to proper LPS fallback.
•  How to run
  1.  javac src/KMP.java
  2.  java -cp src KMP
•  Notes for repository
  ⁠◦  Add source file under src/.
  ⁠◦  Add samples and REPORT.md.
  ⁠◦  Add a short README with the run commands above.
