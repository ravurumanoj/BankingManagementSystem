function calculateEfficiency(K, L, M, N, Total) {
    let count = 0;

    for (let fish = 1; fish <= Total; fish++) {
        if (fish % K === 0) {
            continue;
        } else if (fish % L === 0) {
            continue;
        } else if (fish % M === 0) {
            continue;
        } else if (fish % N === 0) {
            continue;
        }
        count++;
    }

    return Total - count;
}

console.log(calculateEfficiency(1, 2, 3, 4, 12)); 
console.log(calculateEfficiency(2, 3, 4, 5, 24)); 
