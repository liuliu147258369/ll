Const board=Array.from({length:4}，()=>Array(4).fill(0))；
设得分=0；

document.addEventListener('DOMContentLoaded'，()=>{
文件。getElementById('newGameButton')。addEventListener('click'，newgame)；
document.addEventListener('touchstart'，handleTouchStart)；
document.addEventListener('touchend'，handleTouchEnd)；
newgame()；
});

函数newgame(){
for(设i=0；i<4；i++){
for(设j=0；j<4；j++){
board[i][j]=0；
}
}
得分=0；
updateScore()；
generateNewNumber()；
generateNewNumber()；
updateBoard()；
文件。getElementById(“gameover”)。风格。显示='无'；
}

函数generateNewNumber(){
设emptyCells=[]；
for(设i=0；i<4；i++){
for(设j=0；j<4；j++){
if(board[i][j]===0){
emptyCells.push({x:i，y:j})；
}
}
}
if(emptyCells.length===0)返回；
Const{x，y}=emptyCells[Math.floor(Math.random()*emptyCells.length)]；
board[x][y]=Math.random()<0.9？2:4；
}

函数updateBoard(){
for(设i=0；i<4；i++){
for(设j=0；j<4；j++){
常数单元=文件。getElementById('grid-cell-${i}-${j}')；
cell.textContent=board[i][j]===0？"：board[i][j]；
cell.style.backgroundColor=getBackgroundColor(board[i][j])；
}
}
}

函数getBackgroundColor(value){
开关(值){
案例2：返回'#eee4da'；
案例4:return'#ede0c8'；
案例8:return'#f2b179'；
案例16:return'#f59563'；
案例32:return'#f67c5f'；
案例64:return'#f65e3b'；
案例128:return'#edcf72'；
case256:return'#edcc61'；
案例512:return'#edc850'；
case1024:return'#edc53f'；
判例2048：传回'#edc22e'；
默认值：return'#cdc1b4'；
}
}

让startx，starty；

函数句柄TouchStart(事件){
startx=event.touches[0].clientX；
starty=event.touches[0].clientY；
}

函数handleTouchEnd(事件){
Const endX=event.changedTouches[0].clientX；
Const endY=event.changedTouches[0].clientY；
    const deltaX = endX - startX;
    const deltaY = endY - startY;

    if (Math.abs(deltaX) > Math.abs(deltaY)) {
        if (deltaX > 0) {
            moveRight();
        } else {
            moveLeft();
        }
    } else {
        if (deltaY > 0) {
            moveDown();
        } else {
            moveUp();
        }
    }

    generateNewNumber();
    updateBoard();
    if (isGameOver()) {
        document.getElementById('gameover').style.display = 'block';
    }
}

function moveUp() {
    for (let j = 0; j < 4; j++) {
        let compressed = compress(board.map(row => row[j]));
        for (let i = 0; i < 4; i++) {
            board[i][j] = compressed[i];
        }
    }
}

function moveDown() {
    for (let j = 0; j < 4; j++) {
        let compressed = compress(board.map(row => row[j]).reverse()).reverse();
        for (let i = 0; i < 4; i++) {
            board[i][j] = compressed[i];
        }
    }
}

function moveLeft() {
    for (let i = 0; i < 4; i++) {
        board[i] = compress(board[i]);
    }
}

function moveRight() {
    for (let i = 0; i < 4; i++) {
        board[i] = compress(board[i].reverse()).reverse();
    }
}

function compress(row) {
    let newRow = row.filter(val => val !== 0);
    for (let i = 0; i < newRow.length - 1; i++) {
        if (newRow[i] === newRow[i + 1]) {
            newRow[i] *= 2;
            score += newRow[i];
            newRow[i + 1] = 0;
        }
    }
    newRow = newRow.filter(val => val !== 0);
    while (newRow.length < 4) {
        newRow.push(0);
    }
    updateScore();
    return newRow;
}

function updateScore() {
    document.getElementById('score').textContent = score;
}

function isGameOver() {
    for (let i = 0; i < 4; i++) {
        for (let j = 0; j < 4; j++) {
            if (board[i][j] === 0) return false;
            if (i < 3 && board[i][j] === board[i + 1][j]) return false;
            if (j < 3 && board[i][j] === board[i][j + 1]) return false;
        }
    }
    return true;
}
