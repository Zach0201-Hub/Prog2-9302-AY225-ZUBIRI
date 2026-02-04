/*
 Programmer Identifier:
 Zach Aeon R. Zubiri - 23-0932-265
*/

let students = []; // Array to store CSV data

// Fetch the CSV file from the same folder
fetch('MOCK_DATA.csv')
    .then(response => {
        if (!response.ok) throw new Error("Could not load CSV file.");
        return response.text();
    })
    .then(csvText => {
        const lines = csvText.trim().split('\n');

        // Assuming header: ID,FirstName,LastName,Grade
        students = lines.slice(1).map(line => {
            const values = line.split(',');
            return {
                id: values[0],
                firstName: values[1],
                lastName: values[2],
                grade: values[3]
            };
        });

        render(); // Populate table
    })
    .catch(error => alert(error));

// Render function
function render() {
    const tbody = document.getElementById('tableBody');
    tbody.innerHTML = ''; // clear previous rows

    students.forEach((student, index) => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${student.id}</td>
            <td>${student.firstName}</td>
            <td>${student.lastName}</td>
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
    const grade = document.getElementById('gradeInput').value.trim();

    if (!id || !firstName || !lastName || !grade) {
        alert('Please fill in all fields.');
        return;
    }

    students.push({ id, firstName, lastName, grade });

    document.getElementById('idInput').value = '';
    document.getElementById('firstNameInput').value = '';
    document.getElementById('lastNameInput').value = '';
    document.getElementById('gradeInput').value = '';

    render();
}

// Delete a record by index
function deleteRecord(index) {
    students.splice(index, 1);
    render();
}
