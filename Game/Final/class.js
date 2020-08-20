class Circle {
    constructor(player, type, isPlaying, isChecked) {
        this.player = player;
        this.type = type;
        this.isPlaying = isPlaying;
        this.isChecked = isChecked;
    }

    render() {
        if (this.player == 1) {
            if (this.type == 1) {
                this.x = first_center * height;
            }
            else {
                this.x = second_center * height;
            }
            this.y = 0.9 * height
        }
        else {
            if (this.type == 1) {
                this.x = first_center * height;
            }
            else {
                this.x = second_center * height;
            }
            this.y = 0.1 * height
        }

        this.color = "white";
        if (this.isPlaying) {
            this.color = "green"
        }
        if (this.isChecked) {
            this.color = "red"
        }

        context.beginPath()
        context.arc(this.x, this.y, item_square / 10, 0, 2 * Math.PI, false);
        context.fillStyle = this.color;
        context.fill();
        context.lineWidth = 3;
        context.strokeStyle = "black";
        context.stroke()
    }
}



class Board {
    constructor() {

    }

    render() {
        context.beginPath();
        context.rect(0, 0, width, height);
        context.fillStyle = "grey";
        context.fill();
        context.lineWidth = 3;
        context.strokeStyle = "black";
        context.stroke();


        context.beginPath();
        context.rect(outer_line_ratio * height, outer_line_ratio * height, outer_square_ratio * height, outer_square_ratio * height);
        context.fillStyle = "coral";
        context.fill();
        context.lineWidth = 3;
        context.strokeStyle = "black";
        context.stroke();


        context.beginPath();
        context.rect(inner_line_ratio * height, inner_line_ratio * height, inner_square_ratio * height, inner_square_ratio * height);
        context.lineWidth = 3;
        context.strokeStyle = "black";
        context.stroke();


        for (let i = 0; i < 8; i++) {
            for (let j = 0; j < 8; j++) {
                context.beginPath();
                context.rect(i * item_square + inner_line_ratio * height, j * item_square + inner_line_ratio * height, item_square, item_square);
                if ((i + j) % 2 == 0) {
                    context.fillStyle = "white";
                    context.lineWidth = 3;
                    context.strokeStyle = "black";
                    context.stroke();
                }
                else {
                    context.fillStyle = "coral";
                    context.lineWidth = 3;
                    context.strokeStyle = "black";
                    context.stroke();
                }
                context.fill();
            }
        }

        for (let greySquare of listGreySquare) {
            context.beginPath();
            context.rect(greySquare[0] * item_square + inner_line_ratio * height, greySquare[1] * item_square + inner_line_ratio * height, item_square, item_square);
            context.fillStyle = "grey";
            context.fill();
            context.lineWidth = 3;
            context.strokeStyle = "black";
            context.stroke();
        }


        context.beginPath()
        context.rect(left_corner.x * height, left_corner.y * height, (right_corner.x - left_corner.x) * height, (right_corner.y - left_corner.y) * height)
        context.fillStyle = "white"
        context.fill();
        context.lineWidth = 3;
        context.strokeStyle = "black"
        context.stroke()
    }
}

class Picture {
    constructor() {
        this.link = "https://raw.githubusercontent.com/legianha592/legianha592.github.io/master/Game/Final/Picture/chess1.jpg"
    }

    changeLink(link) {
        this.link = link
    }

    render() {
        var imageObj = new Image();
        imageObj.onload = () => { 
            context.drawImage(imageObj, left_corner.x * height, left_corner.y * height, (right_corner.x - left_corner.x) * height, (right_corner.y - left_corner.y) * height);
            console.log("picture = ", imageObj)
        };
        imageObj.src = this.link

    }
}

class Chess {
    constructor(type, x, y) {
        this.step = [];
        this.moved = false;
        this.type = type;
        this.x = x;
        this.y = y;

        this.isEnPassant = 0;
        this.EnPassantLocation = undefined;
        this.moveToAnotherEnPassant = undefined;
        if (type == 1) {
            this.EnPassantLocation = [x - 2, y];
        }
        if (type == -1) {
            this.EnPassantLocation = [x + 2, y]
        }

        this.isLeftCastling = false;
        this.isRightCastling = false;
    }

    changePosition(x, y) {
        this.x = x;
        this.y = y
    }

    move(x, y) {
        map[this.x][this.y] = 0;
        this.x = x;
        this.y = y;
        this.moved = true;
        map[this.x][this.y] = this.type
    }

    stepCanGo() {
        this.step = []
        switch (this.type) {

            //Kiem tra tot
            case 1:
                whitePawnMove.call(this)
                break;
            case -1:
                blackPawnMove.call(this)
                break;

            // Kiem tra xe
            case 2:
            case -2:
                rookMove.call(this)
                break;
            // Kiem tra ma
            case 3:
            case -3:
                knightMove.call(this)
                break;

            // Kiem tra tuong
            case 4:
            case -4:
                bishopMove.call(this)
                break;

            // Kiem tra hau
            case 5:
            case -5:
                //nuoc di giong xe
                rookMove.call(this)
                bishopMove.call(this)
                break;


            // Kiem tra vua
            case 6:
            case -6:
                kingMove.call(this)
                break;

            default:
                break;
        }

        return this.step
    }

    render() {
        if (this.x !== undefined && this.y !== undefined) {
            var link = ''
            switch (this.type) {
                case 1:
                    link = 'https://upload.wikimedia.org/wikipedia/commons/thumb/4/45/Chess_plt45.svg/800px-Chess_plt45.svg.png'
                    break;
                case 2:
                    link = 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/72/Chess_rlt45.svg/800px-Chess_rlt45.svg.png'
                    break;
                case 3:
                    link = 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/70/Chess_nlt45.svg/800px-Chess_nlt45.svg.png'
                    break;
                case 4:
                    link = 'https://upload.wikimedia.org/wikipedia/commons/thumb/b/b1/Chess_blt45.svg/800px-Chess_blt45.svg.png'
                    break;
                case 5:
                    link = 'https://upload.wikimedia.org/wikipedia/commons/thumb/1/15/Chess_qlt45.svg/800px-Chess_qlt45.svg.png'
                    break;
                case 6:
                    link = 'https://upload.wikimedia.org/wikipedia/commons/thumb/4/42/Chess_klt45.svg/800px-Chess_klt45.svg.png'
                    break;
                case -1:
                    link = 'https://upload.wikimedia.org/wikipedia/commons/thumb/c/c7/Chess_pdt45.svg/800px-Chess_pdt45.svg.png'
                    break;
                case -2:
                    link = 'https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Chess_rdt45.svg/800px-Chess_rdt45.svg.png'
                    break;
                case -3:
                    link = 'https://upload.wikimedia.org/wikipedia/commons/thumb/e/ef/Chess_ndt45.svg/800px-Chess_ndt45.svg.png'
                    break;
                case -4:
                    link = 'https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/Chess_bdt45.svg/800px-Chess_bdt45.svg.png'
                    break;
                case -5:
                    link = 'https://upload.wikimedia.org/wikipedia/commons/thumb/4/47/Chess_qdt45.svg/800px-Chess_qdt45.svg.png'
                    break;
                case -6:
                    link = 'https://upload.wikimedia.org/wikipedia/commons/thumb/f/f0/Chess_kdt45.svg/800px-Chess_kdt45.svg.png'
                    break;
                default:
                    break;
            }
            var imageObj = new Image();
            imageObj.onload = () => {
                context.drawImage(imageObj, inner_line_ratio * height + this.y * item_square, inner_line_ratio * height + this.x * item_square, item_square, item_square);
            };
            if (link != '') {
                imageObj.src = link;
            }
        }
    }
}