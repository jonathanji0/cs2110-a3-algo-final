package cs2110;

import java.util.Random;
import static cs2110.LoopInvariants.*;

/**
 * Contains methods for computing the optimal achievable profit of a stock transaction based on its
 * price history in a given time window.
 */
public class Trading {

    /**
     * Returns an *index* of the maximum value in `prices(i..]`. Requires that `0 <= i <
     * prices.length-1`, and that every value of `prices[]` is nonnegative.
     */
    static int argmaxTail(int[] prices, int i) {
        if (i<0 || i>=prices.length) {
            throw new IllegalArgumentException("`i` must be between `0` and `prices.length-1`");
        }
        int maxValue = 0;
        for (int j=i+1; j<prices.length; j++) {
            maxValue = Math.max(maxValue,prices[j]);
        }
        return maxValue;
    }

    /**
     * Returns the maximum profit that can be achieved through a transaction (a purchase followed by
     * a sale in a later time period) for the given `prices` window, or returns 0 if no profitable
     * transaction can be made. Requires `prices.length > 1`, and each entry of `prices` is >= 0.
     */
    static int optimalProfit1(int[] prices) {
        // TODO 3: Implement this method according to its specifications. Uncomment and fill in the
        //  definition of this `while` loop so that it has the given loop invariant. The body of
        //  this loop should call `argmaxTail()` in each iteration.

        /*
         * Loop variant: `i` is the time block we're currently looking at
         */
        int i=0;
        
        /*
         * Loop invariant: `optProfit` is the maximum profit that can be achieved when the share is
         * purchased at a time in `[..i)`.
         */
        int optProfit=0;
        while (i < prices.length) {
            assert optimalProfit1Invariant(prices, optProfit, i);
            // The above `assert` statement should appear as the first line in your loop body.
            // You may ignore it if you'd like. It is here so the autograder can verify that
            // you have maintained the invariant. Do not factor this `assert` statement into
            // your runtime complexity analysis.
            int buyPrice = prices[i];
            int sellPrice = argmaxTail(prices, i);
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

        throw new UnsupportedOperationException();
    }

}
