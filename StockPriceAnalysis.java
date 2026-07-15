import java.util.ArrayList;

public class StockPriceAnalysis {

    public static float calculateAveragePrice(float[] prices) {
        float sum = 0;

        for (float price: prices) {
            sum += price;
        }

        return sum/prices. length;
    }

    public static float findMaximumPrice(float[] prices) {
        float maximum = prices[0];

        for (float price: prices) {
            if (price > maximum) {
                maximum = price;
            }
        }

        return maximum;
    }

    public static int countOccurrences(float[] prices, float targetPrice) {
        int count = 0;

        for (float price: prices) {
            if (price == targetPrice) {
                count++;
            }
        }

        return count;
    }

    public static ArrayList<Float> computeCumulativeSum(
            ArrayList<Float> prices) {

        ArrayList<Float> cumulativeSum = new ArrayList<>();
        float sum = 0;

        for (float price: prices) {
            sum += price;
            cumulativeSum.add(sum);
        }

        return cumulativeSum;
    }

    public static void main(String[] args) {

        float[] stockPrices = {
            100.5f, 102.0f, 101.5f, 105.0f, 102.0f,
            108.5f, 110.0f, 107.5f, 102.0f, 112.0f
        };

        ArrayList<Float> stockPriceList = new ArrayList<>();

        for (float price: stockPrices) {
            stockPriceList.add(price);
        }

        System.out.println("Average Stock Price: "
                + calculateAveragePrice(stockPrices));

        System.out.println("Maximum Stock Price: "
                + findMaximumPrice(stockPrices));

        float targetPrice = 102.0f;

        System.out.println("Occurrences of " + targetPrice + ": "
                + countOccurrences(stockPrices, targetPrice));

        System.out.println("Cumulative Sum: "
                + computeCumulativeSum(stockPriceList));
    }
}
