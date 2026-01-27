function computeGrades() {

    // INPUTS
    let attendance = parseFloat(prompt("Enter Attendance Grade:"));
    let lab1 = parseFloat(prompt("Enter Lab Work 1 Grade:"));
    let lab2 = parseFloat(prompt("Enter Lab Work 2 Grade:"));
    let lab3 = parseFloat(prompt("Enter Lab Work 3 Grade:"));

    // COMPUTATIONS
    let labWorkAverage = (lab1 + lab2 + lab3) / 3;
    let classStanding = (attendance * 0.40) + (labWorkAverage * 0.60);

    let requiredPrelimPass =
        (75 - (classStanding * 0.70)) / 0.30;

    let requiredPrelimExcellent =
        (100 - (classStanding * 0.70)) / 0.30;

    // OUTPUT
    let output =
        "===== RESULTS =====\n" +
        "Attendance Grade: " + attendance.toFixed(2) + "\n" +
        "Lab Work Average: " + labWorkAverage.toFixed(2) + "\n" +
        "Class Standing: " + classStanding.toFixed(2) + "\n\n" +
        "Required Prelim Exam to PASS (75): " +
        requiredPrelimPass.toFixed(2) + "\n" +
        "Required Prelim Exam for EXCELLENT (100): " +
        requiredPrelimExcellent.toFixed(2) + "\n\n";

    // REMARKS
    if (requiredPrelimPass > 100) {
        output += "Remarks: Passing the prelim is no longer achievable.";
    } else if (requiredPrelimPass <= 0) {
        output += "Remarks: You already passed the prelim!";
    } else {
        output += "Remarks: You must aim for the required prelim score.";
    }

    alert(output);
}
