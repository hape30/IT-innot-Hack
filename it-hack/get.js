function getData() {
    fetch('http://localhost:8080/api/data')  // URL вашего API
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();  // Преобразуем ответ в JSON
        })
        .then(data => {
            console.log('Response:', data);  // Вывод JSON-объекта в консоль
            document.getElementById('output').innerText = JSON.stringify(data, null, 2);  // Выводим данные в элемент
        })
        .catch(error => {
            console.error('Fetch error:', error);
        });
}
