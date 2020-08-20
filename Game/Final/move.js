//cac nuoc di cua quan co

function whitePawnMove(){
    if (map[this.x - 1][this.y - 1] < 0) {
        this.step.push([this.x - 1, this.y - 1])
    }
    if (map[this.x - 1][this.y + 1] < 0) {
        this.step.push([this.x - 1, this.y + 1])
    }
    if (map[this.x - 1][this.y] == 0) {
        this.step.push([this.x - 1, this.y]);
        if (this.moved == false && map[this.x - 2][this.y] == 0) {
            this.step.push([this.x - 2, this.y])
        }
    }

    // case nho: bat tot qua duong
    if (this.isEnPassant != 0) {
        this.step.push([this.x - 1, this.y + this.isEnPassant])
        this.moveToAnotherEnPassant = [this.x - 1, this.y + this.isEnPassant]
    }
}

function blackPawnMove() {
    if (map[this.x + 1][this.y - 1] > 0) {
        this.step.push([this.x + 1, this.y - 1])
    }
    if (map[this.x + 1][this.y + 1] > 0) {
        this.step.push([this.x + 1, this.y + 1])
    }
    if (map[this.x + 1][this.y] == 0) {
        this.step.push([this.x + 1, this.y]);
        if (!this.moved && map[this.x + 2][this.y] == 0) {
            this.step.push([this.x + 2, this.y])
        }
    }
    if (this.isEnPassant != 0) {
        this.step.push([this.x + 1, this.y + this.isEnPassant])
        this.moveToAnotherEnPassant = [this.x + 1, this.y + this.isEnPassant]
    }
}

function rookMove(){
    var up = this.x, down = this.x, left = this.y, right = this.y;
    var count = 0;
    while (true) {
        count++;
        if (!checkInsideMap(this.x - count, this.y)) {
            up = 0;
            break;
        }
        if (map[this.x - count][this.y] != 0) {
            if (map[this.x - count][this.y] * this.type > 0) {
                up = this.x - count + 1;
            }
            else {
                up = this.x - count;
            }
            break;
        }
    }
    var count = 0;
    while (true) {
        count++;
        if (!checkInsideMap(this.x + count, this.y)) {
            down = 7;
            break;
        }
        if (map[this.x + count][this.y] != 0) {
            if (map[this.x + count][this.y] * this.type > 0) {
                down = this.x + count - 1;
            }
            else {
                down = this.x + count;
            }
            break;
        }
    }
    var count = 0;
    while (true) {
        count++;
        if (!checkInsideMap(this.x, this.y - count)) {
            left = 0;
            break;
        }
        if (map[this.x][this.y - count] != 0) {
            if (map[this.x][this.y - count] * this.type > 0) {
                left = this.y - count + 1;
            }
            else {
                left = this.y - count;
            }
            break;
        }
    }
    var count = 0;
    while (true) {
        count++;
        if (!checkInsideMap(this.x, this.y + count)) {
            right = 7;
            break;
        }
        if (map[this.x][this.y + count] != 0) {
            if (map[this.x][this.y + count] * this.type > 0) {
                right = this.y + count - 1;
            }
            else {
                right = this.y + count;
            }
            break;
        }
    }
    for (let i = up; i <= down; i++) {
        if (i == this.x) {
            continue;
        }
        this.step.push([i, this.y])
    }
    for (let i = left; i <= right; i++) {
        if (i == this.y) {
            continue;
        }
        this.step.push([this.x, i]);
    }
}

function knightMove(){
    for (let i = -2; i <= 2; i++) {
        for (let j = -2; j <= 2; j++) {
            if (Math.abs(i) + Math.abs(j) == 3) {
                if (checkInsideMap(this.x + i, this.y + j)) {
                    if (map[this.x + i][this.y + j] * this.type <= 0) {
                        this.step.push([this.x + i, this.y + j])
                    }
                }
            }
        }
    }
}

function bishopMove(){
    var up_left = 0, down_left = 0, up_right = 0, down_right = 0;
    var count = 0;
    while (true) {
        count++;
        if (!checkInsideMap(this.x - count, this.y - count)) {
            up_left = count - 1;
            break;
        }
        if (map[this.x - count][this.y - count] != 0) {
            if (map[this.x - count][this.y - count] * this.type > 0) {
                up_left = count - 1
            }
            else {
                up_left = count;
            }
            break;
        }
    }
    var count = 0;
    while (true) {
        count++;
        if (!checkInsideMap(this.x + count, this.y + count)) {
            down_right = count - 1;
            break;
        }
        if (map[this.x + count][this.y + count] != 0) {
            if (map[this.x + count][this.y + count] * this.type > 0) {
                down_right = count - 1
            }
            else {
                down_right = count;
            }
            break;
        }
    }
    var count = 0;
    while (true) {
        count++;
        if (!checkInsideMap(this.x - count, this.y + count)) {
            up_right = count - 1;
            break;
        }
        if (map[this.x - count][this.y + count] != 0) {
            if (map[this.x - count][this.y + count] * this.type > 0) {
                up_right = count - 1
            }
            else {
                up_right = count;
            }
            break;
        }
    }
    var count = 0;
    while (true) {
        count++;
        if (!checkInsideMap(this.x + count, this.y - count)) {
            down_left = count - 1;
            break;
        }
        if (map[this.x + count][this.y - count] != 0) {
            if (map[this.x + count][this.y - count] * this.type > 0) {
                down_left = count - 1
            }
            else {
                down_left = count;
            }
            break;
        }
    }
    for (let i = -up_left; i <= down_right; i++) {
        if (i == 0) {
            continue;
        }
        this.step.push([this.x + i, this.y + i])
    }
    for (let i = -down_left; i <= up_right; i++) {
        if (i == 0) {
            continue;
        }
        this.step.push([this.x - i, this.y + i]);
    }
}

function kingMove(){
    for (let i = -1; i <= 1; i++) {
        for (let j = -1; j <= 1; j++) {
            if (checkInsideMap(this.x + i, this.y + j)) {
                if (i != 0 || j != 0) {
                    if (map[this.x + i][this.y + j] * this.type <= 0) {
                        this.step.push([this.x + i, this.y + j])
                    }
                }
            }
        }
    }
    if (this.type == 6){
        if (this.isLeftCastling){
            this.step.push([7, 2])
        }
        if (this.isRightCastling){
            this.step.push([7, 6])
        }
    }
    else{
        if (this.isLeftCastling){
            this.step.push([0, 2])
        }
        if (this.isRightCastling){
            this.step.push([0, 6])
        }
    }
}
