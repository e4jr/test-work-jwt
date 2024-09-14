# Тестовое задание JWT
Я немного отошла от требований тестового задания и написала код на Java, а не на Golang.

Какие технологии использовались:
-Java
-Spring
-JWT
-Postgres

# Запуск 
Чтобы подключиться к базе используется Docker.(данные указаны в application.properties).
Перед проверкой запускаем AthTestApplication, далее переходим в AuthController и выбираем нужную нам функцию.
Пробуем зарегистрировать пользователя:
###
POST http://localhost:8080/auth
Content-Type: application/json
    {
      "guid": "guid",
      "userSecret": "key"
    }

получаем:

User registered successfully

Пробуем получить токен(с refresh токеном то же самое):

###
POST http://localhost:8080/auth/token
Content-Type: application/json

{
  "guid": "guid",
  "userSecret": "key"
}

получаем:

{
  "token": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIiLCJpYXQiOjE3MjYyOTgzNTMsImV4cCI6MTcyNjI5OTI1M30.KOL3sRoj_c0z7Xr8uJmwFmqxZ07rs7gvAySw9CIsgzJsTo17KA8Wf9PK3D8-HHdYiUUvQz37nyphUOekqde0vg"
}

В случае если мы попробуем получить токен для несуществующего пользователя:

POST http://localhost:8080/auth/token
Content-Type: application/json

{
  "guid": "userid",
  "userSecret": "secretKey"
}

получаем:

{
  "message": "Client with id: userid not found"
}
