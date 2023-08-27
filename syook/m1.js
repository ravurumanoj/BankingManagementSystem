function result(matrix) {
    const res = [];

    const rows = matrix.length;
    const cols = matrix[0].length;

    for (let row = 0; row < rows; row++) {
        for (let col = 0; col < cols; col++) {
            const value = matrix[row][col];

            let isSpecial = true;

            for (let r = 0; r < rows; r++) {
                if (matrix[r][col] < value) {
                    isSpecial = false;
                    break;
                }
            }

            for (let c = 0; c < cols; c++) {
                if (matrix[row][c] > value) {
                    isSpecial = false;
                    break;
                }
            }

            if (isSpecial) {
                res.push(value);
            }
        }
    }

    return res;
}

const matrix = [
    [7, 8, 7],
    [5, 4, 2],
    [8, 6, 7]
];

console.log(result(matrix));  
