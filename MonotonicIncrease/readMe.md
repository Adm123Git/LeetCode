# Монотонное увеличение

https://leetcode.com/problems/flip-string-to-monotone-increasing/

### Сложность

Средняя

### Условие задачи

Дана двоичная строка s. Вы можете перевернуть [i], изменив его с 0 на 1 или с 1 на 0.

Верните минимальное количество переворотов, чтобы сделать s монотонно увеличивающимся.

Бинарная строка монотонно увеличивается, если она состоит из некоторого числа нулей (возможно, ни одного), за которым
следует некоторое количество единиц (также возможно, ни одного).

### Примеры

---

Ввод:
```
s = "00110"
```
Вывод:
```
1
```
Объяснение:
```
Переворачиваем последнюю цифру и получаем 00111
```

---

Ввод:
```
s = "010110"
```
Вывод:
```
2
```
Объяснение:
```
Переворачиваем третью и последнюю цифры и получем 011111 или вторую и последнюю и тогда - 000111
```

---

Ввод:
```
s = "00011000"
```
Вывод:
```
2
```
Объяснение:
```
Переворачиваем единички и получаем 00000000
```