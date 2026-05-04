# Finding differences (Java)

[![Actions Status](https://github.com/Vetrash/java-project-71/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/Vetrash/java-project-71/actions)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=Vetrash_java-project-71&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=Vetrash_java-project-71)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=Vetrash_java-project-71&metric=coverage)](https://sonarcloud.io/summary/new_code?id=Vetrash_java-project-71)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=Vetrash_java-project-71&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=Vetrash_java-project-71)

## О проекте

gendiff — консольная утилита для сравнения двух файлов конфигурации и отображения различий между ними.

Проект поддерживает входные файлы в форматах JSON и YAML, вывод результата в возможен в нескольких форматах
представления:

- stylish — древовидный человекочитаемый формат

- plain — плоское текстовое описание изменений

- json — структурированный вывод для дальнейшей обработки другими программами

В ходе разработки были реализованы:

- парсинг JSON и YAML файлов

- построение внутреннего представления различий

- форматирование результата в разных видах

- автоматические тесты

- проверка качества кода с помощью Checkstyle, GitHub Actions и SonarQube

## Требования

- Java 21+
- Gradle

---

## Как запустить проект

```bash
# Сборка
make build-dist

# Запуск
make run-dist

# Запуск c тестовой командой
make run-dist-fr
```

## Пример использования

`stylish` format (default):

```bash
./build/install/app/bin/app   src/test/resources/fixtures/file1.json src/test/resources/fixtures/file2.json
```

`plain` format:

```bash
./build/install/app/bin/app --format plain src/test/resources/fixtures/file1.json src/test/resources/fixtures/file2.json
```

`json` format:

```bash
./build/install/app/bin/app --format json src/test/resources/fixtures/file1.yaml src/test/resources/fixtures/file2.yaml
```

