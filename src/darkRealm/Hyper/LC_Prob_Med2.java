package darkRealm.Hyper;

import java.util.*;

/**
 * Created by Jayam on 2/4/2017.
 */
public class LC_Prob_Med2 {

  /*  [Prob 396] Rotate Function
   * Given an array of integers A and let n to be its length. Assume Bk to be an array obtained by rotating the array
   * A k positions clock-wise, we define a "rotation function" F on A as follow:
   * F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1].
   * Calculate the maximum value of F(0), F(1), ..., F(n-1).
   * Note:
   * n is guaranteed to be less than 105.
   * Example:
   * A = [4, 3, 2, 6]
   * F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
   * F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
   * F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
   * F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
   * So the maximum value of F(0), F(1), F(2), F(3) is F(3) = 26.
  * */
  public static int rotate(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    int sum = 0, prevRotationSum = 0;
    for (int i = 0; i < arr.length; i++) {
      sum += arr[i];
      prevRotationSum += i * arr[i];
    }
    int max = prevRotationSum;
    for (int i = arr.length - 1; i > 0; i--) {
      prevRotationSum += sum - arr.length * arr[i]; //sum - no of passes into arr[i]
      max = Math.max(prevRotationSum, max);
    }
    return max;
  }

  /*  [Prob 200] Number of Islands
   *   Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water
   *   and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid
   *   are all surrounded by water.
   *   Example 1:
   *   11110
   *   11010
   *   11000
   *   00000
   *   Answer: 1
   *
   *   Example 2:
   *   11000
   *   11000
   *   00100
   *   00011
   *   Answer: 3
   * */

  public static int numberOfIslands(int[][] matrix) {
    if (matrix == null || matrix.length == 0) {
      return 0;
    }
    boolean[][] visited = new boolean[matrix.length][matrix[0].length];
    int islands = 0;
    _ROW = matrix.length;
    _COL = matrix[0].length;
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        if (matrix[i][j] == 1 && !visited[i][j]) {
          DFS(matrix, i, j, visited);
          ++islands;
        }
      }
    }
    return islands;
  }

  static int _ROW, _COL;

  private static boolean isSafe(int[][] matrix, int row, int col, boolean[][] visited) {
    boolean isSafe = (row > -1 && row < _ROW) && (col > -1 && col < _COL) && matrix[row][col] == 1 && !visited[row][col];
    return isSafe;
  }

  private static void DFS(int[][] matrix, int row, int col, boolean[][] visited) {
    // neighbours : top left, top, top right, right, bottom right, bottom, bottom left, left
    int[] rowNeighbours = new int[]{-1, -1, -1, 0, 1, 1, 1, 0};
    int[] colNeighbours = new int[]{-1, 0, 1, 1, 1, 0, -1, -1};
    visited[row][col] = true;
    int eRow, eCol;

    for (int i = 0; i < rowNeighbours.length; i++) {
      eRow = row + rowNeighbours[i];
      eCol = col + colNeighbours[i];
      if (isSafe(matrix, eRow, eCol, visited)) {
        DFS(matrix, row + rowNeighbours[i], col + colNeighbours[i], visited);
      }
    }
  }

  /*  [Prob 127] Word Ladder
  *   Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation
  *   sequence from beginWord to endWord, such that:
  *   Only one letter can be changed at a time.
  *   Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
  *   For example,
  *   Given:
  *   beginWord = "hit"
  *   endWord = "cog"
  *   wordList = ["hot","dot","dog","lot","log","cog"]
  *   As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
  *   return its length 5.
  *
  *   A) Would use Bidirectinal BFS while keeping the nodes of the most recent levels from both the ends will terminate
  *   when we find any one word os found in most recent dist from other side.
  *   Complexity N/2 * maxBreadth(the nodes on the dist) wordLength * 26
  * */
  public static int wordLadder(String start, String end, List<String> dictionary) {
    if (!dictionary.contains(end)) return 0;
    Set<String> words = new HashSet<>(dictionary);
    Set<String> startSet = new HashSet<>(), endSet = new HashSet<>(), next = null;
    int pathLen = 1;
    // add start & end words to their sets
    startSet.add(start);
    endSet.add(end);
    words.remove(start);
    words.remove(end);
    while (!startSet.isEmpty()) {
      next = new HashSet<>();
      for (String word : startSet) {
        char[] wordArr = word.toCharArray();
        for (int i = 0; i < wordArr.length; i++) {
          char old = wordArr[i];
          for (char c = 'a'; c <= 'z'; c++) {
            wordArr[i] = c;
            String formed = new String(wordArr);
            if (endSet.contains(formed))
              return pathLen + 1;
            if (words.contains(formed)) {
              next.add(formed);
              words.remove(formed);
            }
          }
          wordArr[i] = old;
        }
      }
      startSet = next.size() < endSet.size() ? next : endSet;
      endSet = startSet.size() < endSet.size() ? endSet : next;
      pathLen++;
    }
    return 0;
  }


  /* [Prob 126] WordLadderII
  * */
  static class WNode { // a helper node for Djikstrars
    String word;
    int dist;
    WNode prev;

    public WNode(String word, int dist, WNode prev) {
      this.word = word;
      this.dist = dist;
      this.prev = prev;
    }
  }

  public static List<List<String>> wordLadderII(String start, String end, List<String> wordList) {
    Set<String> dict = new HashSet<>(wordList);
    List<List<String>> paths = new ArrayList<>();
    if (!dict.contains(end)) return paths; // if end not in dict then return

    LinkedList<WNode> queue = new LinkedList<>();
    queue.add(new WNode(start, 1, null));// start que with start word & dist as 1

    HashSet<String> visited = new HashSet<>();
    HashSet<String> unvisited = new HashSet<>();
    unvisited.addAll(dict);
    int preDist = 0;

    while (!queue.isEmpty()) {
      WNode trav = queue.remove();
      String word = trav.word;
      int currDist = trav.dist;

      // type this after you have typed the word forming logic
      if (word.equals(end)) { // we ahve found
        ArrayList<String> list = new ArrayList<String>();
        list.add(trav.word);
        while (trav.prev != null) {
          list.add(0, trav.prev.word);
          trav = trav.prev;
        }
        paths.add(list);
        continue;
      }

      if (preDist < currDist) { // means we have reached here with the min dist thus there is no point in processsing these words again
        unvisited.removeAll(visited);
      }
      preDist = currDist;

      // new word forming logic
      char[] arr = word.toCharArray();
      for (int i = 0; i < arr.length; i++) {
        for (char c = 'a'; c <= 'z'; c++) {
          char temp = arr[i];
          if (arr[i] != c) {
            arr[i] = c;
          }
          String formed = new String(arr);
          if (unvisited.contains(formed)) {
            queue.add(new WNode(formed, trav.dist + 1, trav));
            visited.add(formed);
          }
          arr[i] = temp;
        }
      }
    }
    return paths;
  }



  /*  [Prob 5] Longest Palindromic Substring
  *   Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
  *   Example:
  *   Input: "babad"
  *   Output: "bab"
  *   Note: "aba" is also a valid answer.
  *   Example:
  *   Input: "cbbd"
  *   Output: "bb"
  * */
  static int maxLen = Integer.MIN_VALUE;
  static int maxStart;

  public static String longestPalindrome(String str) {
    if (str == null || str.length() < 2) {
      return str;
    }
    for (int i = 0; i < str.length(); i++) {
      expandPalindrome(str, i, i);  // odd len palindrome
      expandPalindrome(str, i, i + 1);  // even len palindrome
    }
    return str.substring(maxStart, maxStart + maxLen);
  }

  private static void expandPalindrome(String str, int left, int right) {
    while ((left >= 0 && right < str.length()) && (str.charAt(left) == str.charAt(right))) {
      left--;
      right++;
    }
    int hereLen = right - left - 1;
    if (hereLen > maxLen) {
      maxLen = hereLen;
      maxStart = left + 1;
    }
  }

  /* 1 : Two sum
  * */

  public static int[] twoSum(int[] nums, int sum) {
    if (nums == null || nums.length == 0) {
      return new int[]{};
    }
//    HashMap<Integer, Integer> nos = new HashMap<>();
    Set<Integer> nos = new HashSet<>();
    int[] res = new int[2];
    // while putting in hash map/hash set(if ids are not required) check if the diff is already present in the hashset/hashmap
    for (int i = 0; i < nums.length; i++) {
      int find = sum - nums[i];
      if (nos.contains(find)) {
        res[1] = i;
        res[0] = find;
        return res;
      }
      nos.add(nums[i]);
    }
    return res;
  }

  /*  [Prob 459] Repeated SubString
  * Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies
  * of the substring together. You may assume the given string consists of lowercase English letters only and its length
  * will not exceed 10000.
  * Example 1:
  * Input: "abab"
  * Output: True
  * Explanation: It's the substring "ab" twice.
  * Example 2:
  * Input: "aba"
  * Output: False
  * Example 3:
  * Input: "abcabcabcabc"
  * Output: True
  * Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
  * */

  public static boolean repeatedSubStringPattern(String str) {
    if (str == null || str.length() < 2) {
      return true;
    }
    StringBuilder pattern = new StringBuilder();
    int len = str.length();
    char c = '\u0000';
    for (int i = 0; i < len / 2; i++) {
      c = str.charAt(i);
      pattern.append(c);
      if (len % pattern.length() == 0) {
        int times = len / pattern.length();
        StringBuilder match = new StringBuilder();
        while (times != 0) {
          match.append(pattern);
          times--;
        }
        if (match.toString().equals(str)) {
          return true;
        }
      }
    }
    return false;
  }



  /* [Prob 139] Word Break
  * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be
  * segmented into a space-separated sequence of one or more dictionary words. You may assume the dictionary does not
  * contain duplicate words.
  * For example, given
  * s = "leetcode",
  * dict = ["leet", "code"].
  * Return true because "leetcode" can be segmented as "leet code".
  * A) we slide a window and try to see if the current chars in a window for a string that is in dictionary.
  * We also keep this intermediate result saved in a boolean array to propogate the result forward when we increase the
  * sliding window size. if (Iam) "I" and "am" can be formed using dictionary while breaking the string we satore the
  * result that string of length 3 can be formed using dictionary, similarly if the result for the length of the string
  * is also true mean the whole string can be formed using the dictionary while doing some partitions at places.
  * */
  public static boolean wordBreak(String str, List<String> wordDict) {
    if (str == null || str.length() == 0) return true;
    int n = str.length();
    boolean[] partition = new boolean[n + 1];
    partition[0] = true;
    String part = null;
    for (int i = 1; i <= n; i++) {
      for (int j = 0; j < i; j++) {
        part = str.substring(j, i);
        if (wordDict.contains(part) && partition[j]) {
          partition[i] = true;
          break;
        }
      }
    }
    return partition[n];
  }

  /*  [Prob 140]
  * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to
  * construct a sentence where each word is a valid dictionary word. You may assume the dictionary does not contain
  * duplicate words.
  * Return all such possible sentences.
  * For example, given
  * s = "catsanddog",
  * dict = ["cat", "cats", "and", "sand", "dog"].
  * solution is ["cats and dog", "cat sand dog"].
  * A) we use a hashMap to store the list of words that can be formed via dictionary by using some portion of the original
  * string. For this purpose we break the string from behind & check if the backPortion cab be formed using dictinary,
  * if yes then we send the front remainder for same process if at the end the frontRem can be broken down in to valid word
  * formed via dictionary then those words are returned, this is the portion to pay attention we verify this by checking
  * the returned sub list length, while this subresult is returned & stored in map, we add these
  * results to the previously borken down backPortion and add this sub portion in map. hence while returning from recursion
  * we will fianlly have the results of valid borken words  against the same key from where we can return.
  * */
  public static List<String> wordBreakII(String str, List<String> wordDict) {
    Map<String, List<String>> subResMap = new HashMap<>();
    return wordBreakAll(str, wordDict, subResMap);
  }

  private static List<String> wordBreakAll(String str, List<String> dict, Map<String, List<String>> subResMap) {
    if (subResMap.containsKey(str)) return subResMap.get(str);
    List<String> subList = new ArrayList<>();
    if (dict.contains(str)) subList.add(str);
    String backPart;
    for (int i = 1; i < str.length(); i++) {
      backPart = str.substring(i);
      if (dict.contains(backPart)) {
        String frontRem = str.substring(0, i);
        List<String> backRes = wordBreakAll(frontRem, dict, subResMap);
        if (backRes.size() > 0)
          for (int j = 0; j < backRes.size(); j++)
            subList.add(backRes.get(j) + " " + backPart);
      }
    }
    subResMap.put(str, subList);
    return subResMap.get(str);
  }

  /* [Prob 516] Longest Palindromic Subsequence
	* Given a string s, find the longest palindromic subsequence's length in s.
	* You may assume that the maximum length of s is 1000.
	* Example 1:
	* Input:
	* "bbbab"
	* Output:
	* 4
	* One possible longest palindromic subsequence is "bbbb".
	* Example 2:
	* Input:
	* "cbbd"
	* Output:
	* 2
	* One possible longest palindromic subsequence is "bb".
	*  A) USE DP  In this solution we solve while moving towards top right, rather than usual bottom right. So Effectively
	*  lower half of matrix is useless, only top half of matrix is utilized
	*  Two Rules :
	*   if char at head == char at tail pick vlaue from lower diagonal & add 2
	*   if chats are diff then take max of one col behind & one row below
	*/
  public static int longestPalidromicSubsequence(String str) {
    int len = str.length();
    int[][] DP = new int[len][len];
    // red strings fromlast
    for (int i = len - 1; i >= 0; i--) {
      DP[i][i] = 1;
      for (int j = i + 1; j < len; j++) {
        if (str.charAt(i) == str.charAt(j)) {
          DP[i][j] = DP[i + 1][j - 1] + 2;
        } else {
          DP[i][j] = Math.max(DP[i + 1][j], DP[i][j - 1]);
        }
        System.out.println(str.substring(i, j));
      }
    }
    return DP[0][len - 1];
  }

  public static String shortestPath(char[][] matrix, char a, char b) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return "";
    int startR, startC;
    startR = startC = 0;
    boolean found = false;
    char toSearch = '\u0000';
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        if (matrix[i][j] == a || matrix[i][j] == b) {
          startR = i;
          startC = j;
          found = true;
          toSearch = matrix[i][j] == a ? b : a;
          break;
        }
      }
      if (found) break;
    }
    return found ? bfsShortestPath(matrix, startR, startC, toSearch) : "";
  }

  private static String bfsShortestPath(char[][] matrix, int startR, int startC, char toSearch) {
    boolean[][] visited = new boolean[matrix.length][matrix[0].length];
    int[] rowNeighbours = new int[]{-1, -1, -1, 0, 1, 1, 1, 0};
    int[] colNeighbours = new int[]{-1, 0, 1, 1, 1, 0, -1, -1};
    Queue<QueNode> queue = new LinkedList<>();
    queue.add(new QueNode(0, startR, startC, matrix[startR][startC] + ""));
    QueNode trav;
    while (!queue.isEmpty()) {
      trav = queue.remove();
      visited[trav.row][trav.col] = true;
      if (toSearch == matrix[trav.row][trav.col]) {
        return trav.path;
      }
      int eRow, eCol;
      for (int i = 0; i < rowNeighbours.length; i++) {
        eRow = trav.row + rowNeighbours[i];
        eCol = trav.col + colNeighbours[i];
        if (isSafe(matrix, eRow, eCol, visited))
          queue.add(new QueNode(trav.dist + 1, eRow, eCol, trav.path + " " + matrix[eRow][eCol]));
      }
    }
    return "";
  }

  private static boolean isSafe(char[][] matrix, int r, int c, boolean[][] visited) {
    return (r > -1 && r < matrix.length && c > -1 && c < matrix[0].length && !visited[r][c]);
  }

  private static class QueNode {
    int dist;
    int row;
    int col;
    String path;

    QueNode(int l, int r, int c, String p) {
      dist = l;
      row = r;
      col = c;
      path = p;
    }
  }
}