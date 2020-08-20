
var canvas = document.getElementById("myCanvas");
var context = canvas.getContext("2d");
canvas.width = innerWidth;
canvas.height = innerHeight;

// tinh toan ve phan ban co phia ben trai
var ratio = 1.3;
var inner_square_ratio = 0.9;
var inner_line_ratio = (1 - inner_square_ratio) / 2
var outer_line_ratio = (1 - inner_square_ratio) / 4
var outer_square_ratio = 1 - 2 * outer_line_ratio;

// tinh toan ve cac cham tron hien thi isPlay va isCheck phia ben phai
var first_center = 1 + 1 / 3 * (ratio - 1);
var second_center = 1 + 2 / 3 * (ratio - 1);
var left_corner = {x: 1.05, y: 0.4}
var right_corner = {x: 1.25, y: 0.6}
var left_corner_2 = {x: 1.05, y: 0.7}
var right_corner_2 = {x: 1.25, y: 0.8}
var width, height, item_square;

//thanh phan luu tru mang du lieu chuan => thanh lap nuoc di gia dinh
var copy_chesses, copy_map, checkmate, wrong_move = [];

// Khoi tao trang thai ban dau: map, canvas, board, chess, circle
var map = []
resetMapOfChess();

calculateCanvas();

var board = new Board();
var picture = new Picture();

var chesses = [];
var currentChess = undefined, currentX = undefined, currentY = undefined;
var listGreySquare = [];
for (let i = 0; i < 8; i++) {
    for (let j = 0; j < 8; j++) {
        if (map[i][j] != 0) {
            let chess = new Chess(map[i][j], i, j);
            chesses.push(chess)
        }
    }
}
var player = 1;
var isChecked = false;

var circles = [];
var circle1 = new Circle(1, 1, true, false);
circles.push(circle1)
var circle2 = new Circle(1, 2, false, false);
circles.push(circle2)
var circle3 = new Circle(-1, 1, false, false);
circles.push(circle3)
var circle4 = new Circle(-1, 2, false, false);
circles.push(circle4)

calculateMove()
renderBoardAndChesses()
renderSituation()
renderPicture()

