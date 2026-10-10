# Гайд: Создание и кастомизация кнопок в Minecraft Forge 1.20.1

В Minecraft 1.20.1 кнопки создаются с помощью класса `net.minecraft.client.gui.components.Button` и встроенного паттерна **Builder**.

---

## 1. Базовое создание ванильной кнопки

Создание и регистрация кнопки происходит внутри метода `init()` вашего экрана (`Screen` или `AbstractContainerScreen`).

```java
@Override
protected void init() {
    super.init();

    // Создаем стандартную кнопку через Builder
    Button myButton = Button.builder(
            Component.literal("Нажми меня"), // Текст на кнопке
            button -> {
                // Действие при клике
                System.out.println("Кнопка была нажата!");
            }
    )
    .bounds(this.width / 2 - 50, this.height / 2 - 10, 100, 20) // X, Y, Ширина, Высота
    .tooltip(Tooltip.create(Component.literal("Всплывающая подсказка"))) // Подсказка при наведении (опционально)
    .build();

    // Обязательно регистрируем кнопку на экране!
    this.addRenderableWidget(myButton);
}
```

---

## 2. Графическая кнопка с текстурой (ImageButton)

Ванильный класс net.minecraft.client.gui.components.ImageButton позволяет создавать кнопки со своими текстурами (иконками, стрелками, переключателями) без необходимости писать собственный рендер через extends Button.

```java
ImageButton customButton = new ImageButton(
        0,                            // 1.  x            — X-координата левого верхнего угла на экране
        0,                            // 2.  y            — Y-координата левого верхнего угла на экране
        10,                           // 3.  width        — Ширина кнопки на экране (в пикселях)
        10,                           // 4.  height       — Высота кнопки на экране (в пикселях)
        0,                            // 5.  xTexStart    — U-координата (X) начала спрайта на PNG
        0,                            // 6.  yTexStart    — V-координата (Y) начала спрайта на PNG
        10,                           // 7.  yDiffTex     — Смещение по Y (V) при наведении мыши (Hover offset)
        TEXTURE_LOCATION,             // 8.  resourceLocation — Путь к текстуре (ResourceLocation)
        256,                          // 9.  textureWidth — Полная ширина PNG-файла (обычно 256)
        256,                          // 10. textureHeight— Полная высота PNG-файла (обычно 256)
        button -> {                   // 11. onPress       — Действие при нажатии
            System.out.println("Нажата графическая кнопка!");
        }
);
```

---