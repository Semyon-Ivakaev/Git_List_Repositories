# Требуется реализовать приложение работающее с api Github’а, состоящее из двух экранов.

### Технические требования:

 - Экраны должны написаны с помощью Fragment-ов. Для переходов
   запрещено использовать Navigation Component
   (https://developer.android.com/guide/navigation), а также сторонние
   библиотеки - использовать только Android SDK для этих целей.
   
 - Каждый из двух запросов должен обрабатывать случай отсутствия
   интернета. Поведение при ошибках от сервера пусть совпадает с
   поведением отсутствия интернета - так как у нас только получение данных -
   не нужно тратить на это дополнительное время.
   
 - Минимальная поддерживаемая версия Android 6 (minSdkVersion 23),
   targetSdkVersion 29.
   
 - Остальных ограничений по использованию сторонних библиотек нет, выбор
   архитектуры на усмотрение кандидата.
   
### 1. Экран “Список репозиториев”

   На первом экране нужно отобразить список публичных репозиториев с
   пагинацией, используя параметр since запроса.

   Запрос: https://developer.github.com/v3/repos/#list-public-repositories

   В ячейке должна отображаться следующая информация:

 - Аватар владельца (поле owner.avatar_url)
   
 - Название репозитория (поле full_name)

 - Логин владельца (поле owner.login)

### 2. Экран “Детальная информация репозитория”

   По клику на ячейку с первого экрана должен открываться экран со следующей
   информацией:

 - Аватар владельца (поле owner.avatar_url с предыдущего экрана)
   
 - Логин владельца (поле owner.login с предыдущего экрана)

 - Название репозитория (поле full_name с предыдущего экрана)

 - Последний коммит из репозитория - готовый url для запроса можно
   получить из поля commits_url с предыдущего экрана. Значение будет
   заканчиваться на {/sha} - это необходимо подрезать, поскольку необходим
   общий список. И выводить только первый элемент списка.
   

   Информация о коммите состоит из:
   
   ● Сообщение коммита (поле commit.message)
   
   ● Имя автора (поле commit.author.name)
   
   ● Дата (поле commit.author.date) в формате dd.MM.YYYY (пример -
   01.06.2020)
   
   ● Все sha родителей (родители доступны по полю parents, а из каждого
   элемента уже необходимо поле sha, таким образом получатся список
   sha)