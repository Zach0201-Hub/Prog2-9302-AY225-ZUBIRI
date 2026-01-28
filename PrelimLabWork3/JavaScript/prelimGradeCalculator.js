function computeGrades() {

    let attendance = Number(document.getElementById("attendance").value);
    let lab1 = Number(document.getElementById("lab1").value);
    let lab2 = Number(document.getElementById("lab2").value);
    let lab3 = Number(document.getElementById("lab3").value);

    if (
        isNaN(attendance) || isNaN(lab1) || isNaN(lab2) || isNaN(lab3)
    ) {
        alert("Please enter valid numeric values.");
        return;
    }

    let labAverage = (lab1 + lab2 + lab3) / 3;
    let classStanding = (attendance * 0.40) + (labAverage * 0.60);

    let requiredPass =
        (75 - (classStanding * 0.70)) / 0.30;

    let requiredExcellent =
        (100 - (classStanding * 0.70)) / 0.30;

    let output = "";
    output += `Attendance Grade: ${attendance.toFixed(2)}\n`;
    output += `Lab Work Average: ${labAverage.toFixed(2)}\n`;
    output += `Class Standing: ${classStanding.toFixed(2)}\n\n`;

    output += `Required Prelim to PASS (75): ${requiredPass.toFixed(2)}\n`;
    output += `Required Prelim for EXCELLENT (100): ${requiredExcellent.toFixed(2)}\n\n`;

    // Academic Remarks
    if (requiredPass > 100) {
        output += "Remarks (Passing): Passing the prelim period is no longer attainable.\n";
    } else if (requiredPass <= 0) {
        output += "Remarks (Passing): The student has already secured a passing prelim grade.\n";
    } else {
        output += "Remarks (Passing): The student must obtain at least the computed prelim score.\n";
    }

    if (requiredExcellent > 100) {
        output += "Remarks (Excellent): Achieving an excellent prelim grade is not attainable.";
    } else if (requiredExcellent <= 0) {
        output += "Remarks (Excellent): The student has already achieved an excellent standing.";
    } else {
        output += "Remarks (Excellent): The student must obtain the computed prelim score.";
    }

    document.getElementById("output").textContent = output;
}
