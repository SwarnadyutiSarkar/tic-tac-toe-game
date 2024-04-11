let board = ['', '', '', '', '', '', '', '', ''];
let currentPlayer = 'X';
let gameOver = false;

function makeMove(index) {
    if (!gameOver && board[index] === '') {
        board[index] = currentPlayer;
        drawBoard();
        if (checkWin()) {
            document.getElementById('message').innerText = `Player ${currentPlayer} wins!`;
            gameOver = true;
        } else if (checkTie()) {
            document.getElementById('message').innerText = 'It\'s a tie!';
            gameOver = true;
        } else {
            currentPlayer = currentPlayer === 'X' ? 'O' : 'X';
        }
    }
}

function drawBoard() {
    for (let i = 0; i < 9; i++) {
        document.getElementsByClassName('cell')[i].innerText = board[i];
    }
}

function checkWin() {
    const winConditions = [
        [0, 1, 2], [3, 4, 5], [6, 7, 8], // Rows
        [0, 3, 6], [1, 4, 7], [2, 5, 8], // Columns
        [0, 4, 8], [2, 4, 6]             // Diagonals
    ];
    return winConditions.some(condition =>
        condition.every(index => board[index] === currentPlayer)
    );
}

function checkTie() {
    return board.every(cell => cell !== '');
}

function resetGame() {
    board = ['', '', '', '', '', '', '', '', ''];
    currentPlayer = 'X';
    gameOver = false;
    document.getElementById('message').innerText = '';
    drawBoard();
}

drawBoard();
