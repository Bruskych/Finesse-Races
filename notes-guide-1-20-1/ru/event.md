# Forge Events — краткий конспект

## 1. Что такое Event?

**Event (событие)** — сообщение Forge о том, что что-то произошло или сейчас происходит.

Примеры:

* игрок вошёл в мир → `PlayerLoggedInEvent`
* игрок сломал блок → `BlockEvent.BreakEvent`
* сущность получила урон → соответствующее событие урона
* запускается мод → `FMLCommonSetupEvent`

Схема:

```text
Произошло событие
       ↓
Forge создаёт Event
       ↓
Event Bus
       ↓
вызываются методы, подписанные на это событие
```

---

## 2. Event Bus

**Event Bus** — система, которая принимает события и вызывает подписанные на них обработчики.

Есть два основных Bus:

### Forge Event Bus

```java
MinecraftForge.EVENT_BUS
```

Для **обычных игровых событий**:

```text
игрок
блоки
сущности
мир
урон
интеракции
рендер и т.д.
```

### Mod Event Bus

```java
FMLJavaModLoadingContext.get().getModEventBus()
```

Для **жизненного цикла и загрузки мода**:

```text
FMLCommonSetupEvent
FMLClientSetupEvent
регистрация объектов
и т.д.
```

Запомнить:

```text
Forge Event Bus → игра
Mod Event Bus   → загрузка мода
```

---

# 3. @SubscribeEvent

```java
@SubscribeEvent
public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
    // код
}
```

`@SubscribeEvent` говорит Forge:

> «Этот метод является обработчиком события».

Метод обычно имеет:

```java
@SubscribeEvent
public void method(НужныйEvent event) {
}
```

То есть:

```text
@SubscribeEvent
      ↓
метод подписан на Event
      ↓
происходит этот Event
      ↓
Forge вызывает метод
```

---

# 4. Регистрация обработчика

Если используется обычный instance handler:

```java
MinecraftForge.EVENT_BUS.register(this);
```

Например:

```java
public FinesseRaces() {
    MinecraftForge.EVENT_BUS.register(this);
}
```

Теперь `@SubscribeEvent` методы внутри `FinesseRaces` могут получать события.

---

# 5. @Mod.EventBusSubscriber

Позволяет **автоматически зарегистрировать класс** как обработчик событий.

```java
@Mod.EventBusSubscriber(
        modid = FinesseRaces.MOD_ID,
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class ModEvents {

    @SubscribeEvent
    public static void onPlayerJoin(
            PlayerEvent.PlayerLoggedInEvent event) {
    }
}
```

В этом случае вручную:

```java
MinecraftForge.EVENT_BUS.register(...)
```

писать не нужно.

Важно:

```text
@Mod.EventBusSubscriber
        ↓
автоматическая регистрация
        ↓
@SubscribeEvent методы
```

Обычно обработчики в таком классе делают `static`.

---

# 6. Bus у @Mod.EventBusSubscriber

Можно указать:

```java
bus = Mod.EventBusSubscriber.Bus.FORGE
```

→ обычные игровые события.

Или:

```java
bus = Mod.EventBusSubscriber.Bus.MOD
```

→ Mod Event Bus, события загрузки мода.

---

# 7. Клиент / сервер

Можно ограничить subscriber:

```java
@Mod.EventBusSubscriber(
        modid = FinesseRaces.MOD_ID,
        value = Dist.CLIENT
)
```

Это означает:

> Класс регистрируется только на клиенте.

Полезно для:

```text
GUI
рендер
Minecraft
клиентские настройки
и другой client-only код
```

---

# 8. Event Handler

**Event Handler** — метод, который реагирует на событие.

```java
@SubscribeEvent
public void onBreak(BlockEvent.BreakEvent event) {
    // обработка
}
```

`event` содержит информацию о произошедшем событии.

Например, в зависимости от Event можно получить:

```java
event.getPlayer()
event.getEntity()
event.getLevel()
event.getPos()
```

и т.д.

---

# 9. Отмена события

Некоторые события можно отменять.

```java
event.setCanceled(true);
```

Например:

```text
Игрок ломает блок
       ↓
BreakEvent
       ↓
event.setCanceled(true)
       ↓
блок не ломается
```

Проверить возможность отмены:

```java
event.isCancelable()
```

**Не каждое событие можно отменить.**

---

# 10. Event Result

Некоторые события имеют результат:

```java
Event.Result
```

Основные значения:

```java
ALLOW
DEFAULT
DENY
```

Упрощённо:

```text
ALLOW   → разрешить
DEFAULT → стандартное поведение
DENY    → запретить
```

Но значение Result зависит от конкретного события.

---

# 11. Event Priority

Если несколько обработчиков слушают одно событие, можно задать порядок:

```java
@SubscribeEvent(priority = EventPriority.HIGH)
```

Приоритеты:

```text
HIGHEST
HIGH
NORMAL ← стандартный
LOW
LOWEST
```

Обычно priority не нужен.

---

# 12. Sub Events

Некоторые события являются наследниками других.

Например:

```text
PlayerEvent
│
├── PlayerLoggedInEvent
├── PlayerLoggedOutEvent
├── PlayerRespawnEvent
└── ...
```

Можно слушать конкретное:

```java
PlayerLoggedInEvent
```

или более общее:

```java
PlayerEvent
```

Если нужен только один конкретный случай — обычно лучше использовать конкретное событие.

---

# 13. `addListener()`

Используется для регистрации обработчика напрямую, особенно для **Mod Event Bus**:

```java
bus.addListener(this::commonSetup);
```

Это означает:

```text
при нужном событии
       ↓
вызвать commonSetup()
```

Например:

```java
private void commonSetup(FMLCommonSetupEvent event) {
}
```

---

# 14. `addGenericListener()`

Используется для **Generic Events** — событий с параметром типа:

```java
SomeEvent<Entity>
```

Например:

```java
bus.addGenericListener(Entity.class, this::handler);
```

Для обычных событий обычно нужен просто:

```java
addListener()
```

---

# 15. `enqueueWork()`

Некоторые события загрузки выполняются параллельно.

Если внутри setup нужно выполнить работу на основном потоке:

```java
event.enqueueWork(() -> {
    // код
});
```

Запоминать глубоко пока не обязательно.

---

# Главное, что нужно помнить

```text
EVENT
  ↓
что-то произошло

EVENT BUS
  ↓
принимает события

@SubscribeEvent
  ↓
говорит: "мой метод слушает событие"

Event Handler
  ↓
метод, который выполняется при событии

MinecraftForge.EVENT_BUS
  ↓
обычные игровые события

Mod Event Bus
  ↓
загрузка / инициализация мода

@Mod.EventBusSubscriber
  ↓
автоматически регистрирует класс

setCanceled(true)
  ↓
отменяет событие (если оно Cancelable)

Event.Result
  ↓
ALLOW / DEFAULT / DENY

EventPriority
  ↓
порядок выполнения обработчиков
```

## Самый простой шаблон

Для обычного игрового события:

```java
@SubscribeEvent
public void onSomething(SomeEvent event) {
    // что делать
}
```

и регистрация:

```java
MinecraftForge.EVENT_BUS.register(this);
```

Или автоматически:

```java
@Mod.EventBusSubscriber(modid = FinesseRaces.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void onSomething(SomeEvent event) {
        // что делать
    }
}
```

**Главная идея:** тебе не нужно постоянно проверять состояние игры самому. Ты говоришь Forge **«когда произойдёт X — вызови этот метод»**, и Forge делает это сам.
