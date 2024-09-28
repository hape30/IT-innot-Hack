## Запуск приложения
Основное требование — наличие установленного Docker на вашем локальном компьютере. Если он не установлен, вы можете установить его здесь: https://docs.docker.com/get-docker/

Далее клонируйте репозиторий на свой локальный компьютер:

```
git clone https://github.com/Shimady563/task-tracker.git
```

Перейдите в папку с клонированным проектом и запустите бэкенд с помощью docker compose

```
docker compose -f ./prod-compose.yaml up -d
```

Запустите приложение, открыв файл /it-hack/Unikit_v1.0/dist/index.html в браузере

Документация swagger доступна по адресу: http://localhost:8080/swagger-ui/index.html

Чтобы остановить приложение, выполните команду:

```
docker compose -f ./prod-compose.yaml down
```