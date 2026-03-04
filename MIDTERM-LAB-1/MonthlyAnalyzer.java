import java.util.*;

public class MonthlyAnalyzer {

    public static Map<String, Double> analyze(List<DataRecord> records) {
        Map<String, Double> monthlyTotals = new TreeMap<>();

        for (DataRecord record : records) {
            monthlyTotals.put(
                record.getMonth(),
                monthlyTotals.getOrDefault(record.getMonth(), 0.0) + record.getSales()
            );
        }

        return monthlyTotals;
    }

    public static String getBestMonth(Map<String, Double> monthlyTotals) {
        return Collections.max(monthlyTotals.entrySet(),
                Map.Entry.comparingByValue()).getKey();
    }
}