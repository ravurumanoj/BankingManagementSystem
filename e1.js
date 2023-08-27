function findFactors(n) {
    const factors = [];
    for (let i = 1; i <= n / 2; i++) {
        if (n % i === 0) {
            factors.push(i);
        }
    }
    return factors;
}

function result(n) {
    const factors = findFactors(n);
    const sum = factors.reduce((sum, factor) => sum + factor, 0);

    if (sum === n) {
        return "Perfect";
    } else if (sum > n) {
        return "Abundant";
    } else {
        return "Deficient";
    }
}

console.log(result(6));
console.log(result(12));
console.log(result(8));

