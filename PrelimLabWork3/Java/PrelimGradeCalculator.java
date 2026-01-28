import java.util.Scanner;

public class PrelimGradeCalculator {

    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // INPUTS
        System.out.print("Enter Attendance Grade: ");
        double attendance = sc.nextDouble();

        System.out.print("Enter Lab Work 1 Grade: ");
        double lab1 = sc.nextDouble();

        System.out.print("Enter Lab Work 2 Grade: ");
        double lab2 = sc.nextDouble();

        System.out.print("Enter Lab Work 3 Grade: ");
        double lab3 = sc.nextDouble();

        // COMPUTATIONS
        double labWorkAverage = (lab1 + lab2 + lab3) / 3;
        double classStanding = (attendance * 0.40) + (labWorkAverage * 0.60);

        double requiredPrelimPass =
                (75 - (classStanding * 0.70)) / 0.30;

        double requiredPrelimExcellent =
                (100 - (classStanding * 0.70)) / 0.30;

        // OUTPUT
        System.out.println("\n===== RESULTS =====");
        System.out.printf("Attendance Grade: %.2f%n", attendance);
        System.out.printf("Lab Work Average: %.2f%n", labWorkAverage);
        System.out.printf("Class Standing: %.2f%n", classStanding);

        System.out.printf("\nRequired Prelim Exam to PASS (75): %.2f%n",
                requiredPrelimPass);
        System.out.printf("Required Prelim Exam for EXCELLENT (100): %.2f%n",
                requiredPrelimExcellent);

        // REMARKS
        System.out.println("\nRemarks:");
        if (requiredPrelimPass > 100) {
            System.out.println("❌ Passing the prelim is no longer achievable.");
        } else if (requiredPrelimPass <= 0) {
            System.out.println("✅ You already passed the prelim!");
        } else {
            System.out.println("📌 You must work hard to reach the target prelim score.");
        }

        sc.close();
    }
}
