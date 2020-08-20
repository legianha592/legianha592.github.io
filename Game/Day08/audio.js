var keyNotes = [
    [
        {
            name: "C3",
            src: "http://carolinegabriel.com/demo/js-keyboard/sounds/040.wav",
        },
        {
            name: "D3",
            src: "http://carolinegabriel.com/demo/js-keyboard/sounds/042.wav",
        },
        {
            name: "E3",
            src: "http://carolinegabriel.com/demo/js-keyboard/sounds/044.wav",
        },
        {
            name: "F3",
            src: "http://carolinegabriel.com/demo/js-keyboard/sounds/045.wav",
        },
        {
            name: "G3",
            src: "http://carolinegabriel.com/demo/js-keyboard/sounds/047.wav",
        },
        {
            name: "A4",
            src: "http://carolinegabriel.com/demo/js-keyboard/sounds/049.wav",
        },
        {
            name: "B4",
            src: "http://carolinegabriel.com/demo/js-keyboard/sounds/051.wav",
        },
        {
            name: "C4",
            src: "http://carolinegabriel.com/demo/js-keyboard/sounds/052.wav",
        },
        {
            name: "D4",
            src: "http://carolinegabriel.com/demo/js-keyboard/sounds/054.wav",
        },
        {
            name: "E4",
            src: "http://carolinegabriel.com/demo/js-keyboard/sounds/056.wav",
        },
    ],
    [
        {
            name: "Cs3",
            src: "http://carolinegabriel.com/demo/js-keyboard/sounds/041.wav",
        },
        {
            name: "Ds3",
            src: "http://carolinegabriel.com/demo/js-keyboard/sounds/043.wav",
        },
        {
            name: "Es3",
            src: "",
        },
        {
            name: "Fs3",
            src: "http://carolinegabriel.com/demo/js-keyboard/sounds/046.wav",
        },
        {
            name: "Gs3",
            src: "http://carolinegabriel.com/demo/js-keyboard/sounds/048.wav",
        },
        {
            name: "As4",
            src: "http://carolinegabriel.com/demo/js-keyboard/sounds/050.wav",
        },
        {
            name: "Bs4",
            src: "",
        },
        {
            name: "Cs4",
            src: "http://carolinegabriel.com/demo/js-keyboard/sounds/053.wav",
        },
        {
            name: "Ds4",
            src: "http://carolinegabriel.com/demo/js-keyboard/sounds/055.wav",
        },
    ]
]

function playAudio(arr){
    if (arr[0] == 0){
        let audio = document.getElementById("white"+arr[1])
        audio.currentTime = 0;
        audio.play()
    }
    else{
        let audio = document.getElementById("black"+arr[1])
        audio.currentTime = 0;
        audio.play()
    }
}

function addAudio(){
    let white_audio = [], black_audio = [];
    for (let i=0; i<keyNotes[0].length; i++){
        let audio = document.createElement("audio")
        audio.id = "white"+i;

        let source = document.createElement("source")
        source.src = keyNotes[0][i].src;
        source.type = "audio/mpeg"
        source.id = "sound"

        document.body.appendChild(audio)
        audio.appendChild(source)

        white_audio.push(audio)
    }

    for (let i=0; i<keyNotes[1].length; i++){
        let audio = document.createElement("audio")
        audio.id = "black"+i;

        let source = document.createElement("source")
        source.src = keyNotes[1][i].src;
        source.type = "audio/mpeg"
        source.id = "sound"

        document.body.appendChild(audio)
        audio.appendChild(source)

        black_audio.push(audio)
    }
}

addAudio()
console.log(document.body)