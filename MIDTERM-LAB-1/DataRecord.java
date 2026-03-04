public class DataRecord {
    @SuppressWarnings("FieldMayBeFinal")
    private String month;
    @SuppressWarnings("FieldMayBeFinal")
    private double sales;

    public DataRecord(String month, double sales) {
        this.month = month;
        this.sales = sales;
    }

    public String getMonth() {
        return month;
    }

    public double getSales() {
        return sales;
    }
}