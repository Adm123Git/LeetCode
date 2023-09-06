# Мороженое

https://leetcode.com/problems/construct-string-from-binary-tree/

### Сложность

Лёгкая

### Условие задачи

Дается корень двоичного дерева. 

Постройте строку, состоящую из круглых скобок и целых чисел из двоичного дерева с помощью способа обхода 
предварительного порядка, и верните ее.

Опустите все пары пустых скобок, которые не влияют на взаимно однозначное сопоставление между строкой и 
исходным двоичным деревом.

### Примеры

---

![](readMeImages\imgTree1.jpg)

Ввод
```
root = [1,2,3,4]
```
Вывод
```
1(2(4))(3)
```
Объяснение
```
Изначально должно быть «1(2(4)())(3()())», но нужно опустить все ненужные пустые пары скобок.
Получается "1(2(4))(3)"
```

---

![](readMeImages\imgTree1.jpg)

Ввод
```
root = [1,2,3,null,4]
```
Вывод
```
1(2()(4))(3)
```
Объяснение
```
Почти то же самое, что и первый пример, за исключением того, что мы не можем опустить первую пару круглых скобок, чтобы 
не нарушить взаимно-однозначное соотношение между входными и выходными данными. 
```