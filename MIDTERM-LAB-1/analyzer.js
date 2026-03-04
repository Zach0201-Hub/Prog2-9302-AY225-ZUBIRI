function analyze(records) {
    const monthlyTotals = {};

    records.forEach(record => {
        if (!monthlyTotals[record.month]) {
            monthlyTotals[record.month] = 0;
        }
        monthlyTotals[record.month] += record.sales;
    });

    return monthlyTotals;
}

function getBestMonth(monthlyTotals) {
    return Object.keys(monthlyTotals).reduce((a, b) =>
        monthlyTotals[a] > monthlyTotals[b] ? a : b
    );
}

module.exports = { analyze, getBestMonth };