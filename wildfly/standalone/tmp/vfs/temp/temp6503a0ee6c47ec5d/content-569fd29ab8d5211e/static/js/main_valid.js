const tableContainer = document.querySelector('.tableContainer');
const submitButton = document.querySelector('.submitButton');
const RequestStatus = document.querySelector(".status");

// нажатие кнопки отправить
submitButton.addEventListener('click', () => {
    tableContainer.style.display = 'block';
    validate();
});

// нажатие на график
canvas.addEventListener('click', function (event) {
    let selectedValue = document.querySelector('input[type="radio"]:checked').value;
    if (!selectedValue || !checkR(parseFloat(selectedValue))) {
        printError("Выберите радиус!");
        return;
    }

    const rValue = parseFloat(selectedValue);
    const rect = canvas.getBoundingClientRect();
    const xClick = event.clientX - rect.left;
    const yClick = event.clientY - rect.top;

    const canvasCenterX = canvas.width / 2;
    const canvasCenterY = canvas.height / 2;

    const scale = canvasCenterX / 5;

    const xValue = (xClick - canvasCenterX) / scale;
    const yValue = -(yClick - canvasCenterY) / scale;


    let initX = document.querySelector('.x').value;
    let initY = document.querySelector('.yVar').value;

    document.querySelector('.x').value = xValue.toFixed(2);
    document.querySelector('.yVar').value = yValue.toFixed(2);

    document.querySelector('.submitButton').click();
    document.querySelector('.x').value = initX;
    document.querySelector('.yVar').value = initY;
});

// функция валидации значений
function validate(){
    let xValue =  parseFloat(document.querySelector('.x').value);
    let yValue = parseFloat(document.querySelector('.yVar').value);
    let rValue = parseFloat(document.querySelector('input[type="radio"]:checked').value);

    if ( checkX(xValue) && checkY(yValue)) {

        hideError();

        setTimeout(() => {
            const tableRows = document.querySelectorAll("#table tbody tr");
            const lastRow = tableRows[tableRows.length - 1];
            const cell = lastRow.querySelectorAll("td")[3];
            const status = cell.textContent.trim() === "Попадание";
            drawPoint(xValue, yValue, rValue, status);
        }, 200);
    } else {
        printError("Невалидные данные!");
    }
}

// допустимые значения
function checkX(value) {
    return (-5 <= value && value <= 5);
}

function checkY(value) {
    return (-5 <= value && value <= 5);
}

function checkR(value) {
    return [1, 2, 3, 4, 5].includes(parseFloat(value));
}

// скрыть ошибку
function hideError(){
    RequestStatus.hidden = true;
}

// отобразить ошибку
function printError(message) {
    RequestStatus.innerHTML = '';
    RequestStatus.hidden = false;
    let h = RequestStatus.querySelector("h2");

    if (!h) {
        let statusText = document.createElement("h2");
        statusText.textContent = message;
        RequestStatus.style.color = "red";
        RequestStatus.appendChild(statusText);
    }else{
        h.textContent = message;
    }
}

// установить значение X
function setX(){
    let xValue =  parseFloat(document.querySelector(".xVar").value);
    document.querySelector('.x').value = xValue.toFixed(2);
}