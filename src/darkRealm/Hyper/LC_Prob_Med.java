package darkRealm.Hyper;

import ADT.LinkedList;
import ADT.LLNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Created by Jayam on 1/27/2017.
 */
public class LC_Prob_Med {
  /*  [Prob 15] 3Sum
   *  Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets
   *  in the array which gives the sum of zero.
   *  Note: The solution set must not contain duplicate triplets.
   *  For example, given array S = [-1, 0, 1, 2, -1, -4],
   *  A solution set is:
   *  [
   *  [-1, 0, 1],
   *  [-1, -1, 2]
   *  ]
   *  */
  public static List<List<Integer>> threeSum(int[] arr) {
    List<List<Integer>> results = new ArrayList<>();
    Arrays.sort(arr); // we go till -2 beacuse those triplets will be counted in inside loop

    // we also dont want to run loop for duplicate elements as duplicates are not allowed in result
    // as we have to atleast begin from array we have to pass for index =0 thats why first part of condition
    for (int i = 0; i < arr.length - 2; i++) {
      if (i == 0 || (i > 0 && arr[i] != arr[i - 1])) {
        int low = i + 1;
        int high = arr.length - 1;
        while (low < high) {
          int a = arr[low];
          int b = arr[high];
          int sum = 0 - (arr[i]);
          if (a + b < sum) {
            low++;
          }
          if (a + b > sum) {
            high--;
          }
          if (a + b == sum) {
            //  a + b + c == 0
            List<Integer> list = new ArrayList<>();
            list.add(a);
            list.add(b);
            list.add(arr[i]);
            results.add(list);
            // skipping all the equal numbers in order to get rid of duplicate results
            while (low < high && arr[low] == arr[low + 1]) low++;
            while (low < high && arr[high - 1] == arr[high]) high--;
            low++;
            high--;
          }
        }
      }
    }
    return results;
  }

  /*  [Prob 457] Circular Array Loop
  * */
  public static boolean isCircularArrayLoopZZ(int[] arr) {
    int i = 0;
    boolean[] visited = new boolean[arr.length];
    int size = arr.length;
    List<Integer> nos = new ArrayList<>();
    for (int j = 0; j < arr.length; j++) {
      nos.add(j);
    }
    while (nos.size() > 0) {
      if (visited[i] == true) {
        return true;
      }
      visited[i] = true;
      nos.remove(new Integer(i));
      if (arr[i] > 0) {
        i = (i + arr[i]) % size;
      } else {
        i = i + arr[i];
        if (i < 0) {
          i = arr.length + i;
        }
      }
    }
    return false;
  }

  public static boolean isCircularArrayLoop(int[] arr) {
    if (arr.length > 1) {
      int val = 0;
      int i = 0;
      int size = arr.length;
      while (true) {
        if (i >= size) {
          break;
        }
        if (arr[i] == 0) {
          return true;
        }
        val = arr[i];
        arr[i] = 0;
        if (arr[i] > 0) {
          i = (i + val) % size;
        } else {
          i = i + val;
          if (i < 0) {
            i = arr.length + i;
          }
        }
      }
    }
    for (int j = 0; j < arr.length; j++) {
      if (arr[j] != 0) {
        return true;
      }
    }
    return false;
  }

  /* [442] Find All Duplicates in an Array
  * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
  * Find all the elements that appear twice in this array.
  * Could you do it without extra space and in O(n) runtime?
  * Reuse the same array to mark that a number has appeared once by mulitplying it with -1. And when we encounter a -ve
  * no we know that its index is duplicate.
  * */
  public static List<Integer> findDuplicates(int[] arr) {
    List<Integer> dups = new ArrayList<>();
    for (int i = 0; i < arr.length; i++) {
      if (arr[Math.abs(arr[i]) - 1] < 0) {
        dups.add(Math.abs(arr[i]));
        continue;
      }
      arr[Math.abs(arr[i]) - 1] = -1 * arr[Math.abs(arr[i]) - 1];
    }
    return dups;
  }



  /*
  * */
  public static int singleNumber(int[] arr) {
    int xorResult = 0;
    if (arr.length > 0) {
      xorResult = arr[0];
      for (int i = 1; i < arr.length; i++) {
        xorResult ^= arr[i];
      }
    }
    return xorResult;
  }

  public static int threeSumClosest(int[] arr, int target) {
    Arrays.sort(arr);
    int minDiff = Integer.MAX_VALUE;
    int minSum = 0;
    for (int i = 0; i < arr.length - 2; i++) {
      int low = i + 1;
      int high = arr.length - 1;
      int a = arr[i];

      while (low < high) {
        int b = arr[low];
        int c = arr[high];
        int sum = a + b + c;
        int diff = Math.abs(target - (sum));
        if (diff < minDiff) {
          minDiff = diff;
          minSum = sum;
          System.out.println("~DL~ a: " + a + " b: " + b + " c: " + c + " mindiff : " + minDiff);
          if (minDiff == 0) {
            return minSum;
          }
        }
        if (sum < target) {
          low++;
        }
        if (sum > target) {
          high--;
        }
      }
    }
    return minSum;
  }


  /*  [Prob 121] Best Time to Buy and Sell Stock
    * Say you have an array for which the ith element is the price of a given stock on day i.
    *  You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times).
    *  However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
    *  -2, 2, 4, 1, 2, 3, 5, 6  MaxP = 11 -2 to 4 + 1 to 6
    *  */
  public static int maxProfitII(int[] prices) {
    if (prices == null || prices.length < 2) return 0;
    int maxP = 0;
    // this is the explanation
    // a<= b <= c <= d;
    // d - a = (b - a) + (c - b) + (d - c)
    // d - a = b -a + c -b + d -c
    // d - a = d - a
    for (int i = 1; i < prices.length; i++)
      if (prices[i] > prices[i - 1])
        maxP += prices[i] - prices[i - 1];
    return maxP;
  }



  public static int maxProfitIII(int[] prices) {
    if (prices == null || prices.length == 0) return 0;
    int buy1, sell1, buy2, sell2;
    buy1 = buy2 = Integer.MIN_VALUE;
    sell1 = sell2 = 0;
    for (int i = 0; i < prices.length; i++) {
      sell2 = Math.max(sell2, buy2 + prices[i]);
      buy2 = Math.max(buy2, sell1 - prices[i]);
      sell1 = Math.max(sell1, buy1 + prices[i]);
      buy1 = Math.max(buy1, -prices[i]);
    }
    return sell2;
  }

  public static int maxProfitFinal(int[] prices, int k) {
    if (prices == null || prices.length == 0) return 0;
    int[][] DP = new int[k + 1][prices.length];
    if (k >= prices.length / 2) return quickProfit(prices);
    int maxDiff = 0;
    /*  DP Rule is
    *   T[i,j] = Max of{ T[i, j-1] --> not transacting
    *                   OR
    *                   Price[j] + maxDiff
    *                 maxDiff = max(maxDiff, T[i-1][j] - price[j])
    * */
    for (int i = 1; i < DP.length; i++) {
      maxDiff = -prices[0];
      for (int j = 1; j < DP[0].length; j++) {
        DP[i][j] = Math.max(DP[i][j - 1], prices[j] + maxDiff);
        maxDiff = Math.max(maxDiff, DP[i - 1][j] - prices[j]);
      }
    }
    return DP[k][prices.length - 1];
  }

  private static int quickProfit(int[] prices) {
    int max = 0;
    for (int i = 1; i < prices.length; i++)
      if (prices[i] > prices[i - 1])
        max += prices[i] - prices[i - 1];
    return max;
  }
}