function updateTime() {
    const timeElement = document.getElementById('time');
    const now = new Date();
    const options = {
        year: "numeric",
        month: "short",
        day: "numeric"
    };
    timeElement.innerHTML = now.toLocaleDateString('ru-RU', options) + '\n' + now.toLocaleTimeString('ru-RU');
}

updateTime();

setInterval(updateTime, 5000);