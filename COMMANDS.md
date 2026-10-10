<div align="center">

# SK `Version`

</div>

# Príkazy projektu finesse-races-1-20-x

Všetky príkazy spúšťajte z koreňa módu (tam, kde sa nachádza gradlew).
V PowerShelli: `.\gradlew`, v termináli IDEA rovnako.

## Spustenie hry
| Príkaz | Čo robí |
|---|---|
| `.\gradlew runClient` | Spustí Minecraft klienta s módom (hlavný spôsob testovania) |
| `.\gradlew runServer` | Spustí dedikovaný server (No GUI) |
| `.\gradlew runGameTestServer` | Spustí GameTest server pre automatické testy |
| `.\gradlew runData` | Vygeneruje dáta (napriklad recepty) do `src/generated/resources` |

## Zostavenie
| Príkaz | Čo robí |
|---|---|
| `.\gradlew build` | Úplné zostavenie, jar sa objaví v `build/libs` |
| `.\gradlew clean` | Odstráni priečinok `build` |
| `.\gradlew clean build` | Čisté zostavenie od nuly |
| `.\gradlew :remedy-core-1-20-x:build` | Zostaví iba jadro |

## Diagnostika
| Príkaz | Čo robí |
|---|---|
| `.\gradlew --refresh-dependencies` | Znova skontroluje závislosti bez použitia cache |
| `.\gradlew :remedy-core-1-20-x:dependencies --configuration compileClasspath` | Ukáže, ako sa riešia závislosti jadra |
| `.\gradlew dependencies` | Strom závislostí hlavného módu |
| `.\gradlew tasks` | Zoznam všetkých dostupných úloh |
| `.\gradlew --stop` | Zastaví Gradle démony bežiace na pozadí (pomáha pri zasekávaní) |

## Príprava IDE
| Príkaz | Čo robí |
|---|---|
| `.\gradlew genIntellijRuns` | Znova vytvorí konfigurácie spustenia pre IDEA |
| `.\gradlew --stacktrace <úloha>` | Ľubovoľná úloha s podrobným výpisom chyby |

---

<div align="center">

# RU `Version`

</div>

# Команды проекта finesse-races-1-20-x

Все команды запускать из корня мода (там, где лежит gradlew).
В PowerShell: `.\gradlew`, в IDEA Terminal то же самое.

## Запуск игры
| Команда | Что делает |
|---|---|
| `.\gradlew runClient` | Запуск Minecraft-клиента с модом (основной способ тестирования) |
| `.\gradlew runServer` | Запуск выделенного сервера (No GUI) |
| `.\gradlew runGameTestServer` | Запуск GameTest-сервера для автотестов |
| `.\gradlew runData` | Генерация данных (например рецепты) в `src/generated/resources` |

## Сборка
| Команда | Что делает |
|---|---|
| `.\gradlew build` | Полная сборка, jar появится в `build/libs` |
| `.\gradlew clean` | Удалить папку `build` |
| `.\gradlew clean build` | Чистая пересборка с нуля |
| `.\gradlew :remedy-core-1-20-x:build` | Собрать только ядро |

## Диагностика
| Команда | Что делает |
|---|---|
| `.\gradlew --refresh-dependencies` | Перепроверить зависимости, игнорируя кэш |
| `.\gradlew :remedy-core-1-20-x:dependencies --configuration compileClasspath` | Показать, как разрешаются зависимости ядра |
| `.\gradlew dependencies` | Дерево зависимостей основного мода |
| `.\gradlew tasks` | Список всех доступных задач |
| `.\gradlew --stop` | Остановить фоновые Gradle-демоны (помогает при зависаниях) |

## Подготовка IDE
| Команда | Что делает |
|---|---|
| `.\gradlew genIntellijRuns` | Пересоздать конфигурации запуска для IDEA |
| `.\gradlew --stacktrace <задача>` | Любая задача с подробным текстом ошибки |