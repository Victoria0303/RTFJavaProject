# RTFJavaProject

1) Создаём проект и добавляем в него csv файл
![image](https://user-images.githubusercontent.com/105602849/211146452-87ba5b4e-1a37-44ae-8ef6-4814acdde441.png)
<br />
2) Проанализировав csv файл, мы можем разделить данные из него по 3 классам. country, happiness, population.
![image](https://user-images.githubusercontent.com/105602849/211146529-f378a653-a9a6-41af-80b5-96feeb3ca787.png)
![image](https://user-images.githubusercontent.com/105602849/211146537-170e049a-0c02-4a92-9043-9e58d1f10a86.png)
<br />
3) Для парсинга csv файла создадим класс CSVParser. В классе CSVParser опишим логику работы с библиотекой opencsv.
![image](https://user-images.githubusercontent.com/105602849/211146953-403732d3-ddf1-4cdd-9c78-34c17c58a1f1.png)
<br />
4) Для работы с sqlite создадим класс sql в котором будет логика работы с наше БД. В конструкторе подключимся к БД, создадим таблицы, если их не существует.
![image](https://user-images.githubusercontent.com/105602849/211146998-5ac19fc2-6d95-4d29-b47a-086c661e17ef.png)
5) Заполним БД данными из CSV файла
![image](https://user-images.githubusercontent.com/105602849/211147109-6aaed265-3171-4cff-bf7d-6deb0725bec3.png)
![image](https://user-images.githubusercontent.com/105602849/211147114-83c200a4-5e4b-41b7-97e0-a9255371b0d6.png)
![image](https://user-images.githubusercontent.com/105602849/211147161-b9f60e32-6733-4ccc-b42a-8f929b9d1625.png)
<br />
6) Выведим все данные из БД в консоль
![image](https://user-images.githubusercontent.com/105602849/211147220-0a00a55f-386e-4230-9a4e-f4298fb7283d.png)
![image](https://user-images.githubusercontent.com/105602849/211147192-9b1e3d39-00f1-49de-9bb4-83d00ee1e5d3.png)
<br />
7) Перейдем к решению заданий.
<br /><br />
1. Сформируйте график по показателю экономики объеденив их по странам
Напишем метод который вернет нам словарь с парами для графика из базы данных
![image](https://user-images.githubusercontent.com/105602849/211147316-10b44255-e210-44b5-84bc-70099f313cb0.png)
Заполним датасет данными и создадим график
![image](https://user-images.githubusercontent.com/105602849/211147328-03686bb5-6299-4a74-991f-373f0475bcf1.png)
![image](https://user-images.githubusercontent.com/105602849/211147346-520707cc-d237-493f-bce5-a117d54c0b78.png)
<br />
2. Выведите в консоль страну с самым высоким показателем экономики среди "Latin America and Caribbean" и "Eastern Asia"
Напишем запрос к БД где будем искать странны с регионами "Latin America and Caribbean" или "Eastern Asia" и получим страну с самой высокой экономикой
![image](https://user-images.githubusercontent.com/105602849/211147461-5abf1ab9-0440-45a1-8e41-822976c5d80a.png)
Выводим данные в консоль
![image](https://user-images.githubusercontent.com/105602849/211147558-8f46e1d6-d5c8-4240-abf8-c7e89354a437.png)
<br />
3. Найдите страну с "самыми средними показателями" среди "Western Europe" и "North America" **(Все ещё плохо понимаю, что конкретно нужно найти)
предположу, что нужно сложить все показатели и найти средний такой показатель между всеми странами в "Western Europe" или "North America"**
![image](https://user-images.githubusercontent.com/105602849/211147600-1f9e209f-6190-4542-9bb8-0a78c15a1603.png)
Выводим данные в консоль
![image](https://user-images.githubusercontent.com/105602849/211147603-9680207f-81bc-4806-8272-d698cb64915b.png)


**Результаты:**
![image](https://user-images.githubusercontent.com/105602849/211147811-501fc949-cac8-4b8f-bf23-af7b0856b6bd.png)

