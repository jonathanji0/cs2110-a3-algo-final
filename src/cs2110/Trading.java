package cs2110;
import static cs2110.LoopInvariants.*;

/**
 * Contains methods for computing the optimal achievable profit of a stock
 * transaction based on its
 * price history in a given time window.
 */
public class Trading {
    public static void main(String[] args) {
        int[] stonks10 = { 5, 3, 8, 3, 6, 4, 2, 5, 7, 10 };
        System.out.println(optimalProfit1(stonks10));
        System.out.println(optimalProfit2(stonks10));
        for (int i = 0; i <= 10; i++) {
            final int MULTIPLIER = 100000;
            int[] xookCoin = new int[i * MULTIPLIER];
            for (int j = 0; j < i * MULTIPLIER; j++) {
                xookCoin[j] = (int) (Math.random() * 14341434);
            }
            long startTime = System.nanoTime();
            System.out.println(
                    "Optimal profit for xookCoin over " + i * MULTIPLIER
                     + " days is: " + optimalProfit1(xookCoin));
            long endTime = System.nanoTime();
            System.out.println("Time elapsed: " + (endTime - startTime));
        }
    }

    /**
     * Returns an *index* of the maximum value in `prices(i..]`. Requires that `0 <=
     * i <
     * prices.length-1`, and that every value of `prices[]` is nonnegative.
     */
    static int argmaxTail(int[] prices, int i) {
        if (i < 0 || i >= prices.length) {
            throw new IllegalArgumentException("`i` must be between `0` and `prices.length-1`");
        }
        int maxValue = -1;
        int maxIndex = -1;
        for (int j = i + 1; j < prices.length; j++) {
            if (prices[j] > maxValue) {
                maxValue = prices[j];
                maxIndex = j;
            }
        }
        return maxIndex;
    }

    /**
     * Returns the maximum profit that can be achieved through a transaction (a
     * purchase followed by
     * a sale in a later time period) for the given `prices` window, or returns 0 if
     * no profitable
     * transaction can be made. Requires `prices.length > 1`, and each entry of
     * `prices` is >= 0.
     */
    static int optimalProfit1(int[] prices) {
        /*
         * Loop variant: `i` is the time block we're currently looking at
         */
        int i = 0;

        /*
         * Loop invariant: `optProfit` is the maximum profit that can be achieved when
         * the share is
         * purchased at a time in `[..i)`.
         */
        int optProfit = 0;
        while (i < prices.length - 1) {
            assert optimalProfit1Invariant(prices, optProfit, i);
            // The above `assert` statement should appear as the first line in your loop
            // body.
            // You may ignore it if you'd like. It is here so the autograder can verify that
            // you have maintained the invariant. Do not factor this `assert` statement into
            // your runtime complexity analysis.
            int buyPrice = prices[i];
            int sellPrice = prices[argmaxTail(prices, i)];
            optProfit = Math.max(sellPrice - buyPrice, optProfit);
            i++;
        }

        return optProfit;
    }

    /**
     * Returns the maximum profit that can be achieved through a transaction (a
     * purchase followed by
     * a sale in a later time period) for the given `prices` window, or returns 0 of
     * no profitable
     * transaction can be made. Requires `prices.length > 1`, and each entry of
     * `prices` is >= 0.
     */
    static int optimalProfit2(int[] prices) {
        /*
         * Loop invariant: `optProfit` is the maximum profit that can be achieved when
         * the share
         * is purchased at a time in `(j..]`.
         */

        int highestSellPrice = 0;
        int optProfit = 0;
        int j = prices.length - 1;
        while (j >= 0) {
            assert optimalProfit2Invariant(prices, optProfit, j);

            optProfit = Math.max(highestSellPrice - prices[j], optProfit);
            highestSellPrice = Math.max(prices[j], highestSellPrice);
            j--;
        }
        return optProfit;
    }

}
