## Group anagrams

###### How to run the project:

- you need maven to run the project
- this project is build on **open jdk 14** so you need that version or higher

use **mvn package** to compile / create jar
go to target folder and run with **java -jar anagramJava-1.0-SNAPSHOT.jar**

###### Configuration
You can configure the project to run the anagram tool in **simple** mode or **parallel** mode. The parallel mode is a bit faster with large data.

###### know potential optimizations

- empty element
- groups anagrams but does not consider duplicate entries