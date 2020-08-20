alert("Xin Chào");

function render(row, colume) {
    var resume = " <table cellpading = '0' cellspacing = '0' > ";
    for (var i = 0; i < row; i++) {
        resume += "<tr>";
        for (var j = 0; j < colume; j++) {
            resume += "<td>";
            resume += "<button  onclick ='fillData(" + i + "," + j + ")'><input class='sssss' id='" +
                i + "_" +
                j + "'></button> ";
            resume += "<td>";
        }
        resume += "</tr>";
    }
    resume += "</table>";
    return resume;
}

function playgame() {
    var rows = document.getElementById("row").value;
    var columes = document.getElementById("colume").value;
    document.getElementById("main").innerHTML = render(rows, columes);
}


var chess = "X";

var fillData = function(i, j) {
    console.log(i + '_' + j);

    document.getElementById(i + '_' + j).value = chess;
    if (chess == 'X') {
        chess = 'O';
    } else if (chess == 'O') {
        chess = 'X';
    }
    document.getElementById(i + '_' + j).style.display = "block";


    var value = document.getElementById(i + '_' + j).value
    if ( //trường hợp 2 2
        (document.getElementById((i - 1) + '_' + (j - 1)).value == value &&
            document.getElementById((i + 1) + '_' + (j + 1)).value == value &&
            document.getElementById((i - 2) + '_' + (j - 2)).value == value &&
            document.getElementById((i + 2) + '_' + (j + 2)).value == value) ||
        (document.getElementById((i - 1) + '_' + (j)).value == value &&
            document.getElementById((i + 1) + '_' + (j)).value == value &&
            document.getElementById((i - 2) + '_' + (j)).value == value &&
            document.getElementById((i + 2) + '_' + (j)).value == value) ||
        (document.getElementById((i) + '_' + (j - 1)).value == value &&
            document.getElementById((i) + '_' + (j + 1)).value == value &&
            document.getElementById((i) + '_' + (j - 2)).value == value &&
            document.getElementById((i) + '_' + (j + 2)).value == value) ||
        (document.getElementById((i - 1) + '_' + (j + 1)).value == value &&
            document.getElementById((i + 1) + '_' + (j - 1)).value == value &&
            document.getElementById((i - 2) + '_' + (j + 2)).value == value &&
            document.getElementById((i + 2) + '_' + (j - 2)).value == value) ||
        //trường hợp 1 3
        (document.getElementById((i - 1) + '_' + (j - 1)).value == value &&
            document.getElementById((i + 1) + '_' + (j + 1)).value == value &&
            document.getElementById((i - 2) + '_' + (j - 2)).value == value &&
            document.getElementById((i - 3) + '_' + (j - 3)).value == value) ||
        (document.getElementById((i + 1) + '_' + (j)).value == value &&
            document.getElementById((i - 1) + '_' + (j)).value == value &&
            document.getElementById((i - 2) + '_' + (j)).value == value &&
            document.getElementById((i - 3) + '_' + (j)).value == value) ||
        (document.getElementById((i - 1) + '_' + (j + 1)).value == value &&
            document.getElementById((i + 1) + '_' + (j - 1)).value == value &&
            document.getElementById((i + 2) + '_' + (j - 2)).value == value &&
            document.getElementById((i + 3) + '_' + (j - 3)).value == value) ||
        (document.getElementById((i) + '_' + (j - 1)).value == value &&
            document.getElementById((i) + '_' + (j + 1)).value == value &&
            document.getElementById((i) + '_' + (j + 2)).value == value &&
            document.getElementById((i) + '_' + (j + 3)).value == value) ||
        (document.getElementById((i - 1) + '_' + (j - 1)).value == value &&
            document.getElementById((i + 1) + '_' + (j + 1)).value == value &&
            document.getElementById((i + 2) + '_' + (j + 2)).value == value &&
            document.getElementById((i + 3) + '_' + (j + 3)).value == value) ||
        (document.getElementById((i - 1) + '_' + (j)).value == value &&
            document.getElementById((i + 1) + '_' + (j)).value == value &&
            document.getElementById((i + 2) + '_' + (j)).value == value &&
            document.getElementById((i + 3) + '_' + (j)).value == value) ||
        (document.getElementById((i - 1) + '_' + (j + 1)).value == value &&
            document.getElementById((i + 1) + '_' + (j - 1)).value == value &&
            document.getElementById((i + 2) + '_' + (j - 2)).value == value &&
            document.getElementById((i + 3) + '_' + (j - 3)).value == value) ||
        (document.getElementById((i) + '_' + (j + 1)).value == value &&
            document.getElementById((i) + '_' + (j - 1)).value == value &&
            document.getElementById((i) + '_' + (j - 2)).value == value &&
            document.getElementById((i) + '_' + (j - 3)).value == value) ||
        // trường hợp 0 4
        (document.getElementById((i - 1) + '_' + (j - 1)).value == value &&
            document.getElementById((i - 2) + '_' + (j - 2)).value == value &&
            document.getElementById((i - 3) + '_' + (j - 3)).value == value &&
            document.getElementById((i - 4) + '_' + (j - 4)).value == value) ||
        (document.getElementById((i - 1) + '_' + (j)).value == value &&
            document.getElementById((i - 2) + '_' + (j)).value == value &&
            document.getElementById((i - 3) + '_' + (j)).value == value &&
            document.getElementById((i - 4) + '_' + (j)).value == value) ||
        (document.getElementById((i - 1) + '_' + (j + 1)).value == value &&
            document.getElementById((i - 2) + '_' + (j + 2)).value == value &&
            document.getElementById((i - 3) + '_' + (j + 3)).value == value &&
            document.getElementById((i - 4) + '_' + (j + 4)).value == value) ||
        (document.getElementById((i) + '_' + (j + 1)).value == value &&
            document.getElementById((i) + '_' + (j + 2)).value == value &&
            document.getElementById((i) + '_' + (j - 3)).value == value &&
            document.getElementById((i) + '_' + (j + 4)).value == value) ||
        (document.getElementById((i + 1) + '_' + (j + 1)).value == value &&
            document.getElementById((i + 2) + '_' + (j + 2)).value == value &&
            document.getElementById((i + 3) + '_' + (j + 3)).value == value &&
            document.getElementById((i + 4) + '_' + (j + 4)).value == value) ||
        (document.getElementById((i + 1) + '_' + (j)).value == value &&
            document.getElementById((i + 2) + '_' + (j)).value == value &&
            document.getElementById((i + 3) + '_' + (j)).value == value &&
            document.getElementById((i + 4) + '_' + (j)).value == value) ||
        (document.getElementById((i - 1) + '_' + (j + 1)).value == value &&
            document.getElementById((i - 2) + '_' + (j + 2)).value == value &&
            document.getElementById((i - 3) + '_' + (j + 3)).value == value &&
            document.getElementById((i - 4) + '_' + (j + 4)).value == value) ||
        (document.getElementById((i) + '_' + (j - 1)).value == value &&
            document.getElementById((i) + '_' + (j - 2)).value == value &&
            document.getElementById((i) + '_' + (j - 3)).value == value &&
            document.getElementById((i) + '_' + (j - 4)).value == value)


    ) {
        alert(value + 'win');
    }
}