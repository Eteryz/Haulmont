# Haulmont
### Development of the "Insurance Company" system using the [Jmix](https://www.jmix.ru/ "Платформа быстрой разработки веб приложений Jmix") framework.
### Содержание
 + [База данных](#Task)
 + [Стек](#Stack)
 + [Функционал](#Requirements)
 + [Примечание](#Note)
 
##### <a name="Task"></a> База данных
![Схема базы данных](src/main/resources/db.png "Логическая схема базы данных")

![Схема базы данных](src/main/resources/dbEn.png "Физическая схема базы данных")

##### <a name="Stack"></a> Стек
+ Java 11
+ Jmix 1.2.4
+ Jmix Studio
+ PostgreSQL

##### <a name="Requirements"></a> Функционал
+ Приложение имеет локализацию на русском и английском языках.
+ Сущности Jmix имеют UUID, поддерживают оптимистическую блокировку, аудит создания и изменения, а также мягкое удаление.
+ БД наполняется содержимым, расположенным в логах изменений liquibase.

##### <a name="Note"></a> Примечание
Документация:
  >[EN](https://docs.jmix.io/jmix/intro.html "Jmix Documentation")
  
  >[RU](https://docs.jmix.ru/jmix/intro.html "Документация Jmix")
