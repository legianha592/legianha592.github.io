
var canvas = document.getElementById("canvas");
var c = canvas.getContext('2d');

canvas_width = 1100;
canvas_height = 500;


class Key {
    constructor(x, y, width, height, type) {
        this.type = type;
        if (type == 1) {
            this.color = "white"
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
        else {
            this.color = "black"
            this.x = x + 3 / 4 * width;
            this.y = y;
            this.width = width / 2;
            this.height = height / 2
        }
        this.min_range = [this.x, this.y];
        this.max_range = [this.x + this.width, this.y + this.height]

        this.render()
    }

    render() {
        c.beginPath();
        c.rect(this.x, this.y, this.width, this.height);
        c.lineWidth = 2;
        c.fillStyle = this.color;
        c.fill();
        c.strokeStyle = "black";
        c.stroke();
        c.closePath();
    }
}


let key_number = 10;
let key_width = 80, key_height = 400;
let start_x = (canvas_width - key_number * key_width) / 2;
let start_y = (canvas_height - key_height) / 2;

let white_keys = [], black_keys = []
for (let i = 0; i < key_number; i++) {
    let key = new Key(start_x + i * key_width, start_y, key_width, key_height, 1)
    white_keys.push(key)
}
for (let i = 0; i < key_number - 1; i++) {
    if (i % 7 == 2 || i % 7 == 6) {
        black_keys.push(undefined)
        continue;
    }
    let key = new Key(start_x + i * key_width, start_y, key_width, key_height, 2)
    black_keys.push(key)
}

// console.log(white_keys, black_keys[0].min_range[0])



function getClickPosition(e) {
    console.log(black_keys[0].min_range[0])
    let pos_X = e.clientX, pos_Y = e.clientY;

    let check = false;
    for (let i = 0; i < black_keys.length; i++) {
        if (black_keys[i] != undefined) {
            if (pos_X >= black_keys[i].min_range[0] && pos_X <= black_keys[i].max_range[0]) {
                if (pos_Y >= black_keys[i].min_range[1] && pos_Y <= black_keys[i].max_range[1]) {
                    // console.log([1, i])
                    check = true;
                    playAudio([1, i])
                    break;
                }
            }
        }
    }

    if (!check){
        for (let i = 0; i < white_keys.length; i++) {
            if (pos_X >= white_keys[i].min_range[0] && pos_X <= white_keys[i].max_range[0]) {
                if (pos_Y >= white_keys[i].min_range[1] && pos_Y <= white_keys[i].max_range[1]) {
                    // console.log([0, i])
                    playAudio([0, i])
                    break;
                }
            }
        }
    }
}

canvas.addEventListener("click", getClickPosition);