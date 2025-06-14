let R = 1;

// фигуры
const canvas = document.getElementById('canvas');
const ctx = canvas.getContext('2d');

// точки
const Pcanvas = document.getElementById('pointsCanvas');
const Pctx = Pcanvas.getContext('2d');

const Xcanvas = canvas.width;
const Ycanvas = canvas.height;


function clearCanvas() {
    ctx.clearRect(0, 0, Xcanvas, Ycanvas);
}

function clearPoints(){
    Pctx.clearRect(0, 0, Pcanvas.width, Pcanvas.height);
}

function drawGrid() {
    const gridSpacing = Xcanvas / 10;
    ctx.strokeStyle = '#e0e0e0';
    ctx.beginPath();
    for (let x = 0; x <= Xcanvas; x += gridSpacing) {
        ctx.moveTo(x, 0);
        ctx.lineTo(x, Ycanvas);
    }
    for (let y = 0; y <= Ycanvas; y += gridSpacing) {
        ctx.moveTo(0, y);
        ctx.lineTo(Xcanvas, y);
    }
    ctx.stroke();
}

function drawAxes() {
    ctx.strokeStyle = '#000000';
    ctx.beginPath();
    ctx.moveTo(Xcanvas / 2, 0);
    ctx.lineTo(Xcanvas / 2, Ycanvas);
    ctx.moveTo(0, Ycanvas / 2);
    ctx.lineTo(Xcanvas, Ycanvas / 2);
    ctx.stroke();
}

function drawShapes() {
    const scale = (Xcanvas / 2) * (R / 5);
    ctx.fillStyle = '#5f9ea0';

    // Четверть круга
    ctx.beginPath();
    ctx.moveTo(Xcanvas / 2, Ycanvas / 2);
    ctx.arc(Xcanvas / 2, Ycanvas / 2, scale / 2, 0.5 * Math.PI, Math.PI);
    ctx.fill();
    ctx.stroke();

    // Прямоугольник
    ctx.beginPath();
    ctx.rect(Xcanvas / 2, Ycanvas / 2, scale, scale/2);
    ctx.fill();
    ctx.stroke();

    // Треугольник
    ctx.beginPath();
    ctx.moveTo(Xcanvas / 2 - scale, Ycanvas / 2);
    ctx.lineTo(Xcanvas / 2, Ycanvas / 2);
    ctx.lineTo(Xcanvas / 2, Ycanvas / 2 - scale/2);
    ctx.closePath();
    ctx.fill();
    ctx.stroke();
}

// Функция для отрисовки значений R на осях
function drawLabels() {
    const scale = (Xcanvas / 2) * (R / 5);
    ctx.font = '16px Arial';
    ctx.fillStyle = '#000000';

    ctx.fillText('R', Xcanvas / 2 + scale - 15, Ycanvas / 2 - 2);

    ctx.fillText('R', Xcanvas / 2 + 2, Ycanvas / 2 - scale + 15);

}

function drawGraph() {
    if (R === null) return;

    clearCanvas();
    clearPoints();
    drawGrid();
    drawAxes();
    drawShapes();
    drawLabels();
}



drawGraph();


function processButtons(){
    R = document.querySelector('input[type="radio"]:checked').value;
    if(checkR(R)){
        drawGraph();
        hideError();
    }else{
        printError("Данные невалидны");
    }
};

// нарисовать точку
function drawPoint(xValue, yValue, rValue, status) {

    const plotX = xValue * 40;
    const plotY = -yValue * 40;

    Pctx.beginPath();

    Pctx.translate(Pcanvas.width / 2, Pcanvas.height / 2);
    Pctx.arc(plotX, plotY, 5, 0, 2 * Math.PI);
    Pctx.fillStyle = status ? 'green' : 'red';
    Pctx.fill();
    Pctx.resetTransform();
    Pctx.closePath();
}