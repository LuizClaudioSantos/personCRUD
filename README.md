# Simple Person CRUD

This is a simple Person CRUD.

## Build and run

To build clone the repository and run

```
gradlew clean build
```

## Operarions


### Add
Use the command
```
java -jar personCRUD-0.0.1-SNAPSHOT.jar --add {\"firstName\": \"The first Name\", \"lastName\": \"The last Name\"}
```

### Edit
Use the command
```
java -jar personCRUD-0.0.1-SNAPSHOT.jar --edit {\"id\": 1, \"firstName\": \"The first Name edited\", \"lastName\": \"The last Name edited\"}
```

### list 
Use the command
```
java -jar personCRUD-0.0.1-SNAPSHOT.jar --list 
```

### Edit
Use the command
```
java -jar personCRUD-0.0.1-SNAPSHOT.jar --delete {\"id\": 1}
```


### Count
Use the command
```
java -jar personCRUD-0.0.1-SNAPSHOT.jar --count
```

### Add Person from the xml file
Use the command, the person.xml example is provide in this repository
```
java -jar personCRUD-0.0.1-SNAPSHOT.jar --addFile person.xml
```

## Databases 
There are two databases used in this project, the first one is the Apache Derby DB, it is playing the role  prod database, also there is a H2, and it is used in memory as Integration test database.
