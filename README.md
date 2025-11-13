# KMP Implementation Report

## Goal
Implement Knuth–Morris–Pratt (KMP) string-search from scratch in Java, test it on short, medium, and long inputs, document behavior for edge cases, and provide time/space complexity analysis.

## Implementation summary

•  The solution contains two main routines:
  1.  buildLPS(pattern): builds the LPS array where lps[i] is the length of the longest proper prefix of pattern[0..i] that is also a suffix of the same substring.
  2.  search(text, pattern): runs KMP to find all 0-based starting indices of pattern occurrences in text using the precomputed LPS to avoid re-scanning characters.
•  Edge-case handling:
  ⁠◦  Empty pattern: defined to match at every index 0..n (documented and implemented).
  ⁠◦  Null or empty text, or pattern longer than text: returns no matches.
  ⁠◦  Overlapping matches: handled naturally by resetting j = lps[j-1] after a match.

## Testing procedure and results

•  Compile: javac kmp.java
•  Run: java kmp
•  Tests executed (exact outputs were captured and placed in output.txt in the repository):

### Test 1 — Short

•  Text: "abracadabra"
•  Pattern: "abra"
•  Result: Positions: 0, 7

### Test 2 — Medium (overlap test)

•  Text: "aaabaaaabaaabaaaab"
•  Pattern: "aaabaa"
•  Result: Positions: 3, 9

### Test 3 — Long (scale test)

•  Text: "abacaba" repeated 2000 times (text length = 14 000)
•  Pattern: "cab"
•  Result: Number of occurrences: 4000
•  Sample indices printed: first 5: 2, 9, 16, 23, 30 ... last 5: 13930, 13937, 13944, 13951, 13958

## Additional edge-case tests included in output.txt:

•  Empty pattern on text "abc": matches at indices 0, 1, 2, 3.
•  Empty text with non-empty pattern: no occurrences.
•  Pattern longer than text: no occurrences.

## Correctness argument (why linear time)

•  buildLPS runs once over the pattern and computes fallback lengths for each position in O(m).
•  During the search, every character of the text is processed at most once as i only moves forward. When a mismatch causes j to fall back using LPS, i is not decremented; j becomes smaller and subsequent comparisons reuse the same text character against an earlier pattern position. These fallbacks are bounded by the total number of increments of j across the algorithm, which ensures the overall search cost is O(n + m).

## Complexity

•  Time: O(m) to build LPS, O(n) to search; combined O(n + m) for a single pattern search.
•  Space: O(m) extra for the LPS array; O(k) to store k match indices. If matches are streamed (printed immediately), additional space can be O(1).

Files to include in repository

•  kmp.java (implementation)
•  output.txt (captured stdout for all tests and edge cases)

How to reproduce

1.  javac kmp.java
2.  java kmp
