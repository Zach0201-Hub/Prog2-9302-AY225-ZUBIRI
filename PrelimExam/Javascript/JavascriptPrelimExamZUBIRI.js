/*
 Programmer Identifier:
 Zach Aeon R. Zubiri - 23-0932-265
*/

let students = []; // Array to store CSV data

// Fetch CSV file
fetch('MOCK_DATA.csv')
    .then(response => {
        if (!response.ok) throw new Error("Could not load CSV file.");
        return response.text();
    })
    .then(csvText => {
        const lines = csvText.trim().split('\n');
        // Assuming header: ID,FirstName,LastName,Lab1,Lab2,Lab3,Prelim,Attendance
        students = lines.slice(1).map(line => {
            const values = line.split(',');
            return {
                id: values[0],
                firstName: values[1],
                lastName: values[2],
                lab1: Number(values[3]),
                lab2: Number(values[4]),
                lab3: Number(values[5]),
                prelim: Number(values[6]),
                attendance: Number(values[7]),
                grade: computeGrade(values[3], values[4], values[5], values[6], values[7])
            };
        });

        render(); // Populate table
    })
    .catch(error => alert(error));

// Compute Grade function (example: weighted average)
function computeGrade(lab1, lab2, lab3, prelim, attendance) {
    return Math.round(
        (Number(lab1) + Number(lab2) + Number(lab3)) * 0.2 +
        Number(prelim) * 0.5 +
        Number(attendance) * 0.1
    );
}

// Render table
function render() {
    const tbody = document.getElementById('tableBody');
    tbody.innerHTML = '';

    students.forEach((student, index) => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${student.id}</td>
            <td>${student.firstName}</td>
            <td>${student.lastName}</td>
            <td>${student.lab1}</td>
            <td>${student.lab2}</td>
            <td>${student.lab3}</td>
            <td>${student.prelim}</td>
            <td>${student.attendance}</td>
            <td>${student.grade}</td>
            <td><button onclick="deleteRecord(${index})">Delete</button></td>
        `;
        tbody.appendChild(row);
    });
}

// Add a new record
function addRecord() {
    const id = document.getElementById('idInput').value.trim();
    const firstName = document.getElementById('firstNameInput').value.trim();
    const lastName = document.getElementById('lastNameInput').value.trim();
    const lab1 = document.getElementById('lab1Input').value.trim();
    const lab2 = document.getElementById('lab2Input').value.trim();
    const lab3 = document.getElementById('lab3Input').value.trim();
    const prelim = document.getElementById('prelimInput').value.trim();
    const attendance = document.getElementById('attendanceInput').value.trim();

    if (!id || !firstName || !lastName || !lab1 || !lab2 || !lab3 || !prelim || !attendance) {
        alert('Please fill in all fields.');
        return;
    }

    const grade = computeGrade(lab1, lab2, lab3, prelim, attendance);

    students.push({ id, firstName, lastName, lab1, lab2, lab3, prelim, attendance, grade });

    // Clear inputs
    document.getElementById('idInput').value = '';
    document.getElementById('firstNameInput').value = '';
    document.getElementById('lastNameInput').value = '';
    document.getElementById('lab1Input').value = '';
    document.getElementById('lab2Input').value = '';
    document.getElementById('lab3Input').value = '';
    document.getElementById('prelimInput').value = '';
    document.getElementById('attendanceInput').value = '';

    render();
}

// Delete a record
function deleteRecord(index) {
    students.splice(index, 1);
    render();
}
