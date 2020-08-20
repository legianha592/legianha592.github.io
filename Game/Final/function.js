// Basic function

// 1. Cac ham ve map

checkInsideMap = (x, y) => {
    if (x >= 0 && x < 8) {
        if (y >= 0 && y < 8) {
            return true
        }
    }
    return false
}

resetMapOfChess = () => {
    for (let i = 0; i < 8; i++) {
        if (i == 0) {
            map[i] = [-2, -3, -4, -5, -6, -4, -3, -2];
        }
        else if (i == 7) {
            map[i] = [2, 3, 4, 5, 6, 4, 3, 2]
        }
        else {
            map[i] = []
            for (let j = 0; j < 8; j++) {
                if (i == 1) {
                    map[i].push(-1)
                }
                else if (i == 6) {
                    map[i].push(1)
                }
                else {
                    map[i].push(0)
                }
            }
        }
    }
    console.log("map = ", map);
}


// 2. Cac ham ve canvas

calculateCanvas = () => {
    if (canvas.width / canvas.height >= ratio) {
        height = canvas.height;
        width = height * ratio;
    }
    else {
        width = canvas.width;
        height = width / ratio;
    }
    item_square = inner_square_ratio * height / 8
}


// 3. Cac ham khac

checkIsChecked = () => {
    let king_x, king_y;
    for (let chess of chesses) {
        if (chess.type * player > 0 && Math.abs(chess.type) == 6) {
            king_x = chess.x;
            king_y = chess.y;
            break;
        }
    }
    for (let chess of chesses) {
        if (chess.type * player < 0) {
            for (let i = 0; i < chess.step.length; i++) {
                if (chess.step[i][0] == king_x && chess.step[i][1] == king_y) {
                    console.log("is checked")
                    isChecked = true;
                    return;
                }
            }
        }
    }
    console.log("is not checked")
    isChecked = false;
}


renderSituation = () => {
    // xac dinh nguoi dang danh
    if (player == 1) {
        circle1.isPlaying = true;
        circle3.isPlaying = false;
    }
    else {
        circle1.isPlaying = false;
        circle3.isPlaying = true;
    }

    // xac dinh nguoi bi chieu
    if (!isChecked) {
        circle2.isChecked = false;
        circle4.isChecked = false;
    }
    else {
        if (player == 1) {
            circle2.isChecked = true;
            circle4.isChecked = false;
        }
        else {
            circle2.isChecked = false;
            circle4.isChecked = true;
        }
    }
    for (let circle of circles) {
        circle.render()
    }
}

renderBoardAndChesses = () => {
    //render bang truoc, quan co sau
    board.render()
    for (let chess of chesses) {
        chess.render()
    }
    console.log("chesses = ", chesses)
    console.log("map = ", map)
}

renderPictureAndButton = () => {
    picture.render()
    button.render()
}

calculateMove = () => {
    //hoan thanh tinh toan moi nuoc di
    for (let chess of chesses) {
        chess.stepCanGo()
    }
    checkCastling()
    findWrongMove()
    removeWrongMove()
}

calculateGreySquare = (x, y) => {
    let listPreviousGreySquare = listGreySquare;
    listGreySquare = [];

    if (checkInsideMap(x, y)) {
        listGreySquare.push([x, y]);
    }


    //TH1: neu o truoc do undefined
    if (currentChess == undefined) {
        //TH1.1: neu o duoc tro vao co quan co => gan currentChess va tinh toan o grey
        console.log("TH1")
        if (map[y][x] * player > 0) {
            let stepCanGo;
            for (let chess of chesses) {
                if (y == chess.x && x == chess.y) {
                    currentChess = chess
                    stepCanGo = chess.step
                    break;
                }
            }


            for (let step of stepCanGo) {
                listGreySquare.push([step[1], step[0]])
            }
        }
        //TH1.2: neu o duoc tro vao khong co quan co => khong thuc hien them gi ca
    }
    //TH2: neu o truoc do da tro vao 1 quan co khac
    else {
        let previousStep = undefined;
        for (let step of listPreviousGreySquare) {
            if (x == step[0] && y == step[1]) {
                previousStep = step;
                break;
            }
        }
        //TH2.1: neu tro vao o di duoc va khac o dang dung => xoa bo phan tu dang dung o vi tri do khoi mang
        // => thuc hien nuoc di va gan currentChess = undefined
        if (previousStep != undefined) {
            console.log("TH2.1 can move to = ", x, y);
            for (let i = 0; i < chesses.length; i++) {
                if (chesses[i].x == y && chesses[i].y == x) {
                    // console.log(chesses[i])
                    chesses.splice(i, 1)
                    break;
                }
            }
            for (let chess of chesses) {
                if (currentChess.x == chess.x && currentChess.y == chess.y) {
                    //Case nho: xet truong hop bat tot qua duong
                    //Case nho 1: xet truong hop chu dong an tot qua duong
                    if (chess.moveToAnotherEnPassant != undefined) {
                        if (chess.moveToAnotherEnPassant[0] == y && chess.moveToAnotherEnPassant[1] == x) {
                            console.log("do en passant")
                            doEnPassant(chess.type, y, x);
                        }
                    }
                    // Buoc reset nuoc di truoc do
                    for (let chess of chesses) {
                        chess.isEnPassant = 0;
                    }
                    //Case nho 2: xet truong hop chu dong roi vao the bat tot qua duong
                    if (Math.abs(chess.type) == 1) {
                        if (!chess.moved && chess.EnPassantLocation[0] == y && chess.EnPassantLocation[1] == x) {
                            console.log("maybe en passant")
                            checkEnPassant(chess.type, y, x)
                        }
                    }

                    //case nho: xet truong hop nhap thanh
                    if (Math.abs(chess.type) == 6 && Math.abs(chess.y - x) == 2) {
                        doCastling(chess.type, y, x)
                    }

                    chess.move(y, x)

                    //Case nho: phong tot
                    checkPromotion()

                    player = -player;
                    break;
                }
            }
            currentChess = undefined;
        }
        //TH2.2: neu tro vao o khong di duoc
        //TH2.2.1: neu la o trong => gan currentChess = undefined
        //TH2.2.2: neu la o co quan co khac cua ben dang choi => gan lai currentChess va tinh toan o grey
        else {
            if (map[y][x] == 0) {
                currentChess = undefined;
                console.log("TH2.2.1")
            }
            else {
                console.log("TH2.2.2")
                if (map[y][x] * player > 0) {
                    let stepCanGo;
                    for (let chess of chesses) {
                        if (y == chess.x && x == chess.y) {
                            currentChess = chess
                            stepCanGo = chess.step
                            break;
                        }
                    }
                    for (let step of stepCanGo) {
                        listGreySquare.push([step[1], step[0]])
                    }
                }
            }
        }
    }
    console.log("player = ", player);
}

replay = () => {
    map = []
    resetMapOfChess()

    var link = "https://raw.githubusercontent.com/legianha592/legianha592.github.io/master/Game/Final/Picture/chess1.jpg"
    picture.changeLink(link)
    chesses = []
    currentChess = undefined, currentX = undefined, currentY = undefined;
    listGreySquare = [];
    for (let i = 0; i < 8; i++) {
        for (let j = 0; j < 8; j++) {
            if (map[i][j] != 0) {
                let chess = new Chess(map[i][j], i, j);
                chesses.push(chess)
            }
        }
    }
    player = 1;
    isChecked = false;

    circles = [];
    circle1 = new Circle(1, 1, true, false);
    circles.push(circle1)
    circle2 = new Circle(1, 2, false, false);
    circles.push(circle2)
    circle3 = new Circle(-1, 1, false, false);
    circles.push(circle3)
    circle4 = new Circle(-1, 2, false, false);
    circles.push(circle4)

    calculateMove()
    renderBoardAndChesses()
    renderSituation()
    renderPictureAndButton()
}


// 4. Cac ham hung su kien

addEventListener("resize", function () {
    canvas.width = innerWidth;
    canvas.height = innerHeight;
    calculateCanvas();
    calculateMove()
    renderBoardAndChesses()
    renderSituation()
    renderPictureAndButton()
    // console.log("canvas = ", canvas.width, canvas.height)

})

addEventListener("click", function (e) {
    let posX = e.clientX, posY = e.clientY;
    let min = inner_line_ratio * height, max = (inner_line_ratio + inner_square_ratio) * height;

    if (posX > max || posX < min || posY > max || posY < min) {
        let replay_x = {min: left_corner_2.x*height, max: right_corner_2.x*height};
        let replay_y = {min: left_corner_2.y*height, max: right_corner_2.y*height};
        if (posX < replay_x.max && posX > replay_x.min){
            if (posY <replay_y.max && posY > replay_y.min){
                replay();
            }
        }
        return;
    }

    let x = Math.floor((posX - inner_line_ratio * height) / item_square);
    let y = Math.floor((posY - inner_line_ratio * height) / item_square);

    if (currentX != x || currentY != y) {
        currentX = x, currentY = y;
        calculateGreySquare(x, y);
        calculateMove()
        renderBoardAndChesses()
        checkIsChecked()
        renderSituation()
        renderPictureAndButton()

        if (checkmate){
            var link
            if (player == 1){
                link = "https://raw.githubusercontent.com/legianha592/legianha592.github.io/master/Game/Final/Picture/chess3.jpg"
            }
            else{
                link = "https://raw.githubusercontent.com/legianha592/legianha592.github.io/master/Game/Final/Picture/chess2.jpg"
            }
            picture.changeLink(link);
            picture.render()
            return
        }
    }
})

// Advanced function

// 5. Cac ham thuc hien nuoc di dac biet

// 5.1. Bat tot qua duong 
checkEnPassant = (type, x, y) => {
    console.log(type, x, y)
    for (let chess of chesses) {
        if (chess.type == -type && chess.x == x && chess.y == y - 1) {
            console.log("en passant to right")
            chess.isEnPassant = 1;
            break;
        }
    }
    for (let chess of chesses) {
        if (chess.type == -type && chess.x == x && chess.y == y + 1) {
            console.log("en passant to left")
            chess.isEnPassant = -1;
            break;
        }
    }
}

doEnPassant = (type, x, y) => {
    if (type == 1) {
        for (let i = 0; i < chesses.length; i++) {
            if (chesses[i].type == -1 && chesses[i].x == x + 1 && chesses[i].y == y) {
                chesses.splice(i, 1)
                map[x + 1][y] = 0;
                console.log("removed")
                break;
            }
        }
    }
    if (type == -1) {
        for (let i = 0; i < chesses.length; i++) {
            if (chesses[i].type == 1 && chesses[i].x == x - 1 && chesses[i].y == y) {
                chesses.splice(i, 1)
                map[x - 1][y] = 0;
                console.log("removed")
                break;
            }
        }
    }
}

// 5.2. Phong cap: xet va thuc hien 

checkPromotion = () => {
    for (let i = 0; i < chesses.length; i++) {
        if (chesses[i].type == 1 && chesses[i].x == 0) {
            let x = chesses[i].x, y = chesses[i].y
            chesses.splice(i, 1);
            let chess = new Chess(5, x, y)
            chesses.push(chess)
            map[x][y] = 5;
            break;
        }
        if (chesses[i].type == -1 && chesses[i].x == 7) {
            let x = chesses[i].x, y = chesses[i].y
            chesses.splice(i, 1);
            let chess = new Chess(-5, x, y)
            chesses.push(chess)
            map[x][y] = -5;
            break;
        }
    }
}


// 5.3. Nhap thanh

checkCastling = () => {
    //Dieu kien
    //1. Vua va xe nhap thanh chua di chuyen
    //2. Giua vua va xe nhap thanh ko co vat can
    //3. Cac vi tri vua se di chuyen nam tren duong chieu cua bat ki quan doi phuong nao

    //B1: Xac dinh vua va cac xe da di chuyen hay chua
    let whiteKing = false, leftWhiteRook = false, rightWhiteRook = false;
    let blackKing = false, leftBlackRook = false, rightBlackRook = false;
    for (let chess of chesses) {
        if (!chess.moved) {
            switch (chess.type) {
                case 6:
                    whiteKing = true;
                    break;
                case -6:
                    blackKing = true;
                    break;
                case 2:
                    if (chess.y == 0) {
                        leftWhiteRook = true;
                    }
                    else {
                        rightWhiteRook = true;
                    }
                    break;
                case -2:
                    if (chess.y == 0) {
                        leftBlackRook = true;
                    }
                    else {
                        rightBlackRook = true;
                    }
                    break;
                default:
                    break;
            }
        }
    }
    console.log("white = ", whiteKing, leftWhiteRook, rightWhiteRook)
    console.log("black = ", blackKing, leftBlackRook, rightBlackRook)

    //B2: xac dinh cac o tren duong di co nam trong vung chieu hay khong
    let leftWhiteFree = true, rightWhiteFree = true, leftBlackFree = true, rightBlackFree = true;
    for (let chess of chesses) {
        if (chess.type < 0) {
            for (let i = 0; i < chess.step.length; i++) {
                if (chess.step[i][0] == 7) {
                    if (chess.step[i][1] == 4) {
                        leftWhiteFree = false;
                        rightWhiteFree = false;
                        break;
                    }
                    else if (chess.step[i][1] == 2 || chess.step[i][1] == 3) {
                        leftWhiteFree = false;
                        break;
                    }
                    else if (chess.step[i][1] == 5 || chess.step[i][1] == 6) {
                        rightWhiteFree = false;
                        break;
                    }
                }
            }
        }
        else {
            for (let i = 0; i < chess.step.length; i++) {
                if (chess.step[i][0] == 0) {
                    if (chess.step[i][1] == 4) {
                        leftBlackFree = false;
                        rightBlackFree = false;
                        break;
                    }
                    else if (chess.step[i][1] == 2 || chess.step[i][1] == 3) {
                        leftBlackFree = false;
                        break;
                    }
                    else if (chess.step[i][1] == 5 || chess.step[i][1] == 6) {
                        rightBlackFree = false;
                        break;
                    }
                }
            }
        }
    }
    console.log("can castling step 2= ", leftWhiteFree, rightWhiteFree, leftBlackFree, rightBlackFree)

    //B3: xac dinh cac o o giua co trong hay khong => ket hop dieu kien
    if (map[7][1] != 0 || map[7][2] != 0 || map[7][3] != 0 || !whiteKing || !leftWhiteRook) {
        leftWhiteFree = false;
    }
    if (map[7][5] != 0 || map[7][6] != 0 || !whiteKing || !rightWhiteRook) {
        rightWhiteFree = false;
    }
    if (map[0][1] != 0 || map[0][2] != 0 || map[0][3] != 0 || !blackKing || !leftBlackRook) {
        leftBlackFree = false;
    }
    if (map[0][5] != 0 || map[0][6] != 0 || !blackKing || !rightBlackRook) {
        rightBlackFree = false;
    }
    console.log("can castling step 3= ", leftWhiteFree, rightWhiteFree, leftBlackFree, rightBlackFree)


    //B4: gan co cho vua 
    for (let chess of chesses) {
        if (chess.type == 6) {
            chess.isLeftCastling = leftWhiteFree
            chess.isRightCastling = rightWhiteFree
        }
        if (chess.type == -6) {
            chess.isLeftCastling = leftBlackFree
            chess.isRightCastling = rightBlackFree
        }
    }

}

doCastling = (type, x, y) => {
    if (type == 6) {
        if (y == 2) {
            for (let chess of chesses) {
                if (chess.type == 2 && chess.y == 0) {
                    chess.y = 3;
                    chess.stepCanGo()
                    map[7][3] = 2;
                    map[7][0] = 0;
                    break;
                }
            }
        }
        else {
            for (let chess of chesses) {
                if (chess.type == 2 && chess.y == 7) {
                    chess.y = 5;
                    chess.stepCanGo()
                    map[7][5] = 2;
                    map[7][7] = 0;
                    break;
                }
            }
        }
    }
    else {
        if (y == 2) {
            for (let chess of chesses) {
                if (chess.type == -2 && chess.y == 0) {
                    chess.y = 3;
                    chess.stepCanGo()
                    map[0][3] = -2;
                    map[0][0] = 0;
                    break;
                }
            }
        }
        else {
            for (let chess of chesses) {
                if (chess.type == -2 && chess.y == 7) {
                    chess.y = 5;
                    chess.stepCanGo()
                    map[0][5] = -2;
                    map[0][7] = 0;
                    break;
                }
            }
        }
    }
}

// 6. Hinh thanh buoc di gia dinh => loai bo cac nuoc di khong hop le



findWrongMove = () => {
    makeACopyChessesAndMap()
    tryNewMove()
    returnACopyChessesAndMap()
}

makeACopyChessesAndMap = () => {
    copy_map = []
    for (let i = 0; i < 8; i++) {
        copy_map.push([])
        for (let j = 0; j < 8; j++) {
            copy_map[i].push(map[i][j])
        }
    }

    copy_chesses = [];
    for (let chess of chesses) {
        let copy_chess = new Chess(chess.type, chess.x, chess.y)

        copy_chess.moved = chess.moved
        copy_chess.isEnPassant = chess.isEnPassant
        copy_chess.EnPassantLocation = chess.EnPassantLocation
        copy_chess.moveToAnotherEnPassant = chess.moveToAnotherEnPassant
        copy_chess.isRightCastling = chess.isRightCastling
        copy_chess.isLeftCastling = chess.isLeftCastling

        copy_chess.stepCanGo()
        copy_chesses.push(copy_chess)
    }
    // console.log("copy chess and map = ", copy_chesses, copy_map)
}

returnACopyChessesAndMap = () => {
    map = []
    for (let i = 0; i < 8; i++) {
        map.push([])
        for (let j = 0; j < 8; j++) {
            map[i].push(copy_map[i][j])
        }
    }

    chesses = []
    for (let copy_chess of copy_chesses) {
        let chess = new Chess(copy_chess.type, copy_chess.x, copy_chess.y)

        chess.moved = copy_chess.moved
        chess.isEnPassant = copy_chess.isEnPassant
        chess.EnPassantLocation = copy_chess.EnPassantLocation
        chess.moveToAnotherEnPassant = copy_chess.moveToAnotherEnPassant
        chess.isRightCastling = copy_chess.isRightCastling
        chess.isLeftCastling = copy_chess.isLeftCastling

        chess.stepCanGo()
        chesses.push(chess)
    }
}


renderChessFromMap = () => {
    for (let i = 0; i<chesses.length; i++){
        if (map[chesses[i].x][chesses[i].y] != chesses[i].type){
            chesses.splice(i, 1);
            break;
        }
    }
}

tryNewMove = () => {
    checkmate = true
    wrong_move = []

    for (let i = 0; i < copy_chesses.length; i++) {
        //neu la quan co cung loai voi nguoi dang choi
        if (copy_chesses[i].type * player > 0) {
            for (let j = 0; j < copy_chesses[i].step.length; j++) {
                // console.log("chess can move = ", copy_chesses[i].type, copy_chesses[i].step[j])
                returnACopyChessesAndMap()
                // console.log("chesses = ", chesses)

                let old_x = copy_chesses[i].x
                let old_y = copy_chesses[i].y
                let new_x = copy_chesses[i].step[j][0]
                let new_y = copy_chesses[i].step[j][1]
                chesses[i].changePosition(new_x, new_y)
                map[old_x][old_y] = 0;
                map[new_x][new_y] = copy_chesses[i].type;

                
                console.log("new map", map)
                checkCheckmate(chesses[i].type, old_x, old_y, new_x, new_y)
            }
        }
    }
    console.log("list wrong move = ", wrong_move)
    console.log("checkmate = ", checkmate)
}

checkCheckmate = (type, old_x, old_y, new_x, new_y) => {
    let king_x, king_y;
    console.log("anonymous move = ", type, [old_x, old_y], [new_x, new_y])

    //loai bo quan co neu no bien mat khoi ban dau
    renderChessFromMap()

    //tim vi tri cua quan vua cung ben tren map
    for (let chess of chesses) {
        if (chess.type * player > 0) {
            if (Math.abs(chess.type) == 6) {
                king_x = chess.x
                king_y = chess.y
            }
        }
        else{
            chess.stepCanGo()
        }
    }

    // console.log("chesses after re render = ", chesses)



    console.log("position king = ", king_x, king_y)
    // console.log("new chesses = ", chesses)

    // neu nhu vi tri quan vua nam trong vung di duoc cua doi phuong => cho vao wrong_move
    for (let chess of chesses) {
        if (chess.type * player < 0) {
            // console.log("enemy steps = ", chess.type, chess.step)
            for (let step of chess.step) {
                if (step[0] == king_x && step[1] == king_y) {
                    wrong_move.push({ type: type, old_x: old_x, old_y: old_y, new_x: new_x, new_y: new_y })
                    return;
                }
            }
        }
    }

    // neu nhu nuoc di tao the an toan => doi checkmate
    checkmate = false;
}

removeWrongMove = () => {
    // phai luu ca type + toa do cu + toa do moi de de phong truong hop co 2 quan cung type va cung di den cho giong nhau
    for (let move of wrong_move){
        for (let chess of chesses){
            if (move.type == chess.type && move.old_x == chess.x && move.old_y == chess.y){
                let check = false;
                for (let i=0; i<chess.step.length; i++){
                    if (move.new_x == chess.step[i][0] && move.new_y == chess.step[i][1]){
                        chess.step.splice(i, 1)
                        check = true;
                        break;
                    }
                }
                if (check){
                    break;
                }
            }
        }
    }
}