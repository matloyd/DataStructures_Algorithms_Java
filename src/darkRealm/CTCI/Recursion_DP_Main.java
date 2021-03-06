package darkRealm.CTCI;

import darkRealm.CTCI.Recursion_and_DynamicProg.Recursion_and_DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by Jayam on 12/20/2016.
 */
public class Recursion_DP_Main {

  public static void testNthFibonacciMemoized() {
    long n = Recursion_and_DP.NthFibonacciMemoized(0);
    System.out.println(" fibo " + n);
  }

  public static void testNthFiboIterative() {
    long n = Recursion_and_DP.NthFibonacciIterative(6);
    System.out.println("It fibo : " + n);
  }

  public static void testTripleSteps() {
    long startTime = System.currentTimeMillis();
    long nofways = Recursion_and_DP.TripleSteps(37, 3);
    long stopTime = System.currentTimeMillis();
    long elapsedTime = stopTime - startTime;
    System.out.println("no of ways " + nofways);
    System.out.println("time - " + elapsedTime + " ms");
  }

  public static void testRobotGrid() {
    int[][] matrix = new int[][]{
        {0, 0, 0, 1},
        {1, 0, 1, 0},
        {0, 0, 0, 0},
        {0, 1, 0, 0},
    };
    long startTime = System.currentTimeMillis();
    String result = Recursion_and_DP.RobotGridPath(matrix);
    long stopTime = System.currentTimeMillis();
    System.out.println("Path results   - " + result);
    long elapsedTime = stopTime - startTime;
    System.out.println("time - " + elapsedTime + " ms");
  }

  public static void testPowerSet() {
    char[] set = new char[]{'a', 'b', 'c'};
    String power = Recursion_and_DP.printPowerSet(set);
    System.out.println(" power set " + power);
  }

  public static void testAllPermutationsWithoutDups() {
    Recursion_and_DP.printtAllPermuationsWithOutDups("abca");
  }

  public static void testAllPermutationsWithDups() {
    Set<String> permutes = Recursion_and_DP.printtAllPermuationsWithDups("abcd");
    String[] arr = new String[permutes.size()];
    permutes.toArray(arr);
    for (String s :
        arr) {
      System.out.println(s + " perm");
    }
  }

  public static void testParensCombination() {
    long b4 = System.currentTimeMillis();
    ArrayList<String> perm = Recursion_and_DP.printAllParensCombo(4);
    long aftr = System.currentTimeMillis();
    for (String s :
        perm) {
      System.out.println("paran  " + s);
    }
    System.out.println("time ms " + (aftr - b4));
  }

  public static void testMagicIndex() {
    int[] arr = new int[]{-10, -5, 2, 2, 2, 3, 7, 9, 12, 13};
    int mi = Recursion_and_DP.magicIndex(arr, 0, arr.length);
    System.out.println("magic index -- " + mi);
  }

  public static void testMultiply() {
    long res = Recursion_and_DP.mulitply(12345, 60000);
//    long res = Recursion_and_DP.mulitply(123456789, 987654321);
//    long res = Recursion_and_DP.mulitply(333333, Integer.MAX_VALUE);
    System.out.println(" mulipty res : " + res);
  }

  public static void testTowerOfHanoi() {
    Recursion_and_DP.towerOfHanoi(9);
  }

  public static void testPaintFill() {
    Recursion_and_DP.COLOR[][] picture = new Recursion_and_DP.COLOR[][]{
        {Recursion_and_DP.COLOR.GREEN, Recursion_and_DP.COLOR.GREEN, Recursion_and_DP.COLOR.GREEN, Recursion_and_DP.COLOR.BLACK},
        {Recursion_and_DP.COLOR.BLACK, Recursion_and_DP.COLOR.GREEN, Recursion_and_DP.COLOR.GREEN, Recursion_and_DP.COLOR.BLACK},
        {Recursion_and_DP.COLOR.BLACK, Recursion_and_DP.COLOR.BLUE, Recursion_and_DP.COLOR.GREEN, Recursion_and_DP.COLOR.BLACK},
        {Recursion_and_DP.COLOR.BLACK, Recursion_and_DP.COLOR.GREEN, Recursion_and_DP.COLOR.GREEN, Recursion_and_DP.COLOR.BLACK}
    };
    Recursion_and_DP.painFill(picture, 2, 2, Recursion_and_DP.COLOR.RED);

    for (int i = 0; i < picture.length; i++) {
      for (int j = 0; j < picture[i].length; j++) {
        System.out.print(picture[i][j] + " ");
      }
      System.out.println();
    }
  }

  public static void testBalancedParanthesis() {
    System.out.println("balanced - " + Recursion_and_DP.balancedParanthesis("123456"));
  }

  public static void testWaysToReachN() {
    long b4 = System.currentTimeMillis();
    long res = Recursion_and_DP.waysToReachN(1.1);
    long after = System.currentTimeMillis();
    System.out.println("No of ways - " + res);
    System.out.println("time ms - " + ((after - b4) / 1000));
  }

  public static void testNQueenPlacingProblem() {
    long b4 = System.currentTimeMillis();
    Recursion_and_DP.placeNQueens(15);
    long after = System.currentTimeMillis();
    System.out.println("time seconds - " + ((after - b4)));
  }

  public static void testPermuteExpression() {
    String exp = "1^0|0|1";
//    Recursion_and_DP.booleanEvaluation(exp,false);
  }

  public static void testBooleanEvaluation() {
    String expression = "0&0&1^1|0";
    long b4 = System.currentTimeMillis();
//    expression = "0^1|0";
//    Recursion_and_DP.countEval("0|1|1",true);
//    int res = Recursion_and_DP.countEval(expression,false);
//    int res = Recursion_and_DP.combinedParathesieCombinaiton("0|1|0|1^0&1&1|0",false);
    int res = Recursion_and_DP.countEvalOptimized("0|1|0", false, new HashMap<String, Integer>());
    long after = System.currentTimeMillis();
    System.out.println("ways - " + res);
    System.out.println("time miliseconds - " + ((after - b4)));
  }

  public static void testLongestSubSequence() {
    String a = "GVCEKST";
    String b = "GDVEGTA";
    int res = Recursion_and_DP.LongestCommonSubsequence(a, b);
    System.out.println("Longest Subsequence : " + res);
  }

  public static void testRobotWays() {
    int n = 5;
    int res = Recursion_and_DP.robotNoOfWays(n);
    System.out.println("step : " + n + " ways : " + res);
  }

  public static void testLongestIncreasingSubSequence() {
    int[] arr = new int[]{3, 4, -1, 0, 6, 2, 3};
    int res = Recursion_and_DP.LongestIncreasingSubsequenceLength(arr);
    System.out.println("LCS : " + res + " Arr : " + Arrays.toString(arr));
  }
}