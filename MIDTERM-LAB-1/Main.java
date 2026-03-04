import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        @SuppressWarnings("resource")
        Scanner input = new Scanner(System.in);
        File file;

        // ===============================
        // FILE VALIDATION LOOP
        // ===============================
        while (true) {
            System.out.print("Enter dataset file path: ");
            String path = input.nextLine().replace("\"", "");
            file = new File(path);

            if (!file.exists()) {
                System.out.println("Error: File does not exist.\n");
            } else if (!file.canRead()) {
                System.out.println("Error: File is not readable.\n");
            } else if (!path.toLowerCase().endsWith(".csv")) {
                System.out.println("Error: File is not a CSV.\n");
            } else {
                break;
            }
        }

        List<DataRecord> records = new ArrayList<>();

        // ===============================
        // FILE PROCESSING
        // ===============================
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            @SuppressWarnings("unused")
            String header = br.readLine(); // Skip header
            String line;

            while ((line = br.readLine()) != null) {

                // Safe CSV split (handles basic commas)
                String[] parts = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                if (parts.length < 13) continue;

                String totalSalesStr = parts[7].trim();
                String releaseDate = parts[12].trim();

                // Skip invalid rows
                if (totalSalesStr.isEmpty() || releaseDate.isEmpty())
                    continue;

                // Ensure full YYYY-MM-DD format exists
                if (releaseDate.length() < 7)
                    continue;

                try {
                    double sales = Double.parseDouble(totalSalesStr);
                    String month = releaseDate.substring(0, 7);

                    records.add(new DataRecord(month, sales));

                } catch (NumberFormatException e) {
                    // Skip rows with invalid numeric values
                }
            }

            // ===============================
            // ANALYTICS
            // ===============================
            Map<String, Double> monthlyTotals =
                    MonthlyAnalyzer.analyze(records);

            if (monthlyTotals.isEmpty()) {
                System.out.println("No valid data found.");
                return;
            }

            System.out.println("\n===== MONTHLY SALES SUMMARY =====");

            for (Map.Entry<String, Double> entry : monthlyTotals.entrySet()) {
                System.out.printf("%-10s : %.2f million\n",
                        entry.getKey(),
                        entry.getValue());
            }

            String bestMonth =
                    MonthlyAnalyzer.getBestMonth(monthlyTotals);

            System.out.println("\nBest Performing Month: " + bestMonth);

        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
        }
    }
}