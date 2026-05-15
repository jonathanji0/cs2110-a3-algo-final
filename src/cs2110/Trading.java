package cs2110;

import java.util.Random;
import static cs2110.LoopInvariants.*;

/**
 * Contains methods for computing the optimal achievable profit of a stock transaction based on its
 * price history in a given time window.
 */
public class Trading {
    public static void main(String[] args) {
        int[] stonks10 = {5,3,8,3,6,4,2,5,7,10};
        System.out.println(optimalProfit1(stonks10));
        System.out.println(optimalProfit2(stonks10));
        for (int i=0; i<=10; i++) {
            int[] xookCoin = new int[i*10000000];
            for (int j=0; j<i*10000000; j++) {
                xookCoin[j] = (int)(Math.random()*14341434);
            }
            long startTime = System.nanoTime();
            System.out.println("Optimal profit for xookCoin over "+i*10000000+" days is: "+optimalProfit2(xookCoin));
            long endTime = System.nanoTime();
            System.out.println("Time elapsed: "+(endTime-startTime));
        }
    }

    /**
     * Returns an *index* of the maximum value in `prices(i..]`. Requires that `0 <= i <
     * prices.length-1`, and that every value of `prices[]` is nonnegative.
     */
    static int argmaxTail(int[] prices, int i) {
        if (i<0 || i>=prices.length) {
            throw new IllegalArgumentException("`i` must be between `0` and `prices.length-1`");
        }
        int maxValue = -1;
        int maxIndex = -1;
        for (int j=i+1; j<prices.length; j++) {
            if (prices[j]>maxValue) {
                maxValue = prices[j];
                maxIndex = j;
            }
        }
        return maxIndex;
    }

    /**
     * Returns the maximum profit that can be achieved through a transaction (a purchase followed by
     * a sale in a later time period) for the given `prices` window, or returns 0 if no profitable
     * transaction can be made. Requires `prices.length > 1`, and each entry of `prices` is >= 0.
     */
    static int optimalProfit1(int[] prices) {
        /*
         * Loop variant: `i` is the time block we're currently looking at
         */
        int i=0;
        
        /*
         * Loop invariant: `optProfit` is the maximum profit that can be achieved when the share is
         * purchased at a time in `[..i)`.
         */
        int optProfit=0;
        while (i < prices.length-1) {
            assert optimalProfit1Invariant(prices, optProfit, i);
            // The above `assert` statement should appear as the first line in your loop body.
            // You may ignore it if you'd like. It is here so the autograder can verify that
            // you have maintained the invariant. Do not factor this `assert` statement into
            // your runtime complexity analysis.
            int buyPrice = prices[i];
            int sellPrice = prices[argmaxTail(prices, i)];
            optProfit = Math.max(sellPrice-buyPrice,optProfit);
            i++;
        }

        return optProfit;
    }

    /**
     * Returns the maximum profit that can be achieved through a transaction (a purchase followed by
     * a sale in a later time period) for the given `prices` window, or returns 0 of no profitable
     * transaction can be made. Requires `prices.length > 1`, and each entry of `prices` is >= 0.
     */
    static int optimalProfit2(int[] prices) {
        // TODO 5: Implement this method according to its specifications. Uncomment and fill in the
        //  definition of this `while` loop so that it has the given loop invariant. Augment this
        //  invariant to account for any additional local variables you modify within the body of
        //  the loop. Your implementation should have worst-case runtime complexity O(N), where
        //  N=prices.length, and worst-case space complexity O(1).

        /*
         * Loop invariant: `optProfit` is the maximum profit that can be achieved when the share
         * is purchased at a time in `(j..]`.
         */
        // while () {
        //   assert optimalProfit2Invariant(prices, optProfit, j);
        //   // The above `assert` statement should appear as the first line in your loop body.
        //   // You may ignore it if you'd like. It is here so the autograder can verify that
        //   // you have maintained the invariant. Do not factor this `assert` statement into
        //   // your runtime complexity analysis.
        // }
        int highestSellPrice = 0;
        int maxProfit = 0;
        for (int i=prices.length-1; i>=0; i--) {
            maxProfit = Math.max(highestSellPrice-prices[i],maxProfit);
            highestSellPrice = Math.max(prices[i],highestSellPrice);
        }
        return maxProfit;
        // throw new UnsupportedOperationException();
    }

}
