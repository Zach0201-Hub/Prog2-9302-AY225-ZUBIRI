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

        // Map CSV lines into student objects
        students = lines.slice(1).map(line => {
            const values = line.split(',');
            return {
                id: values[0],
                firstName: values[1],
                lastName: values[2],
                lab1: values[3],
                lab2: values[4],
                lab3: values[5],
                prelimExam: values[6]
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
            <td>${student.lab1}</td>
            <td>${student.lab2}</td>
            <td>${student.lab3}</td>
            <td>${student.prelimExam}</td>
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
    const prelimExam = document.getElementById('prelimInput').value.trim();

    if (!id || !firstName || !lastName || !lab1 || !lab2 || !lab3 || !prelimExam) {
        alert('Please fill in all fields.');
        return;
    }

    students.push({ id, firstName, lastName, lab1, lab2, lab3, prelimExam });

    // Clear input fields
    document.getElementById('idInput').value = '';
    document.getElementById('firstNameInput').value = '';
    document.getElementById('lastNameInput').value = '';
    document.getElementById('lab1Input').value = '';
    document.getElementById('lab2Input').value = '';
    document.getElementById('lab3Input').value = '';
    document.getElementById('prelimInput').value = '';

    render();
}

// Delete a record by index
function deleteRecord(index) {
    students.splice(index, 1);
    render();
}
