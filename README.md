<div align="center">
  <h1>dd-demo-exam</h1>
  <p><em>Spring Boot REST API for managing users with Docker and Swagger integration</em></p>
</div>

---

## 🚀 Основные фичи

* 📦 REST API для управления пользователями
* 🧭 Swagger UI для визуальной документации (доступно на `/swagger-ui.html`)
* 🐳 Поддержка Docker (включая PostgreSQL)
* 🛡️ Разделение конфигурации для разных профилей (H2 в dev, PostgreSQL в Docker)
* 🧪 Интеграционные тесты с использованием Spring Boot Test и MockMvc
* 🧾 Логгирование на уровне сервисов и контроллеров

---

## 📦 Быстрый старт

### 💻 Локально (с H2)

```bash
./gradlew bootRun
```

### 🐳 Через Docker Compose (с Postgresql)

```bash
cp docker/.env.example docker/.env
docker-compose -f docker/docker-compose.yaml up --build
```

---

## 📚 Документация API

После запуска приложения Swagger доступен по адресу:

```
http://localhost:8080/swagger-ui.html
```

---

## 📁 Структура

* `controller/` - REST контроллеры
* `service/` - бизнес-логика
* `repository/` - Spring Data JPA
* `model/` - JPA сущности
* `dto/` - запросы и ответы API
* `config/` - конфигурация (Swagger, ModelMapper и пр.)

---

## 🧪 Тесты

```bash
./gradlew test
```

---

<div align="center">
  <sub>Made with ❤️ by <a href="https://github.com/glebpav">hlebnoe_pole</a></sub>
</div>
