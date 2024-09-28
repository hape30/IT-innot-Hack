function postData() {
    const data = {
        message: "Hello from Frontend!",
        value: 42
    };  // Данные для отправки в формате JSON

    fetch('http://localhost:8080/api/send', {  // URL вашего API
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)  // Преобразуем объект в JSON
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();  // Преобразуем ответ в JSON
        })
        .then(data => {
            console.log('Response:', data);  // Вывод JSON-ответа в консоль
            document.getElementById('output').innerText = JSON.stringify(data, null, 2);  // Выводим ответ в элемент
        })
        .catch(error => {
            console.error('Fetch error:', error);
        });
}
