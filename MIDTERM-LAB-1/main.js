const fs = require('fs');
const readline = require('readline');
const { analyze, getBestMonth } = require('./analyzer');

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

function askFilePath() {
    rl.question("Enter dataset file path: ", function(path) {

        if (!fs.existsSync(path)) {
            console.log("File does not exist.");
            return askFilePath();
        }

        if (!path.toLowerCase().endsWith(".csv")) {
            console.log("File is not a CSV.");
            return askFilePath();
        }

        try {
            const data = fs.readFileSync(path, 'utf8');
            const lines = data.split('\n').slice(1);

            const records = [];

            lines.forEach(line => {
                const parts = line.split(',');

                if (parts.length < 13) return;

                const releaseDate = parts[12];
                const totalSales = parts[7];

                if (!releaseDate || !totalSales) return;

                const month = releaseDate.substring(0, 7);
                records.push({
                    month: month,
                    sales: parseFloat(totalSales)
                });
            });

            const monthlyTotals = analyze(records);

            console.log("\n===== MONTHLY SALES SUMMARY =====");
            Object.keys(monthlyTotals)
                .sort()
                .forEach(month => {
                    console.log(`${month} : ${monthlyTotals[month].toFixed(2)} million`);
                });

            const bestMonth = getBestMonth(monthlyTotals);
            console.log(`\nBest Performing Month: ${bestMonth}`);

            rl.close();

        } catch (error) {
            console.log("Error reading file:", error.message);
            askFilePath();
        }
    });
}

askFilePath();