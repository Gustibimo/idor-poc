# Introduction

It's the code repository of the OWASP cheatsheet [Insecure Direct Object Reference Prevention Cheat Sheet](https://www.owasp.org/index.php/Insecure_Direct_Object_Reference_Prevention_Cheat_Sheet).

# Run

Use either:
* The `Run Application` running configuration from Intellij project.
* The command line `gradlew.bat bootRun`

The application is then exposed on http://localhost:8080

```
$ curl http://localhost:8080/movies

{
  "F498AB2AFB450684FB0378AD6D87F10C3B1826AA":"Venom",
  "24D8FB4F39241C8E63F3CF9E82F2D644594CBB4B":"Avengers",
  "F1244AD6A71A9C6C9E08BA6D819D119FBD7944D0":"Toy Story 3"
}


$ curl http://localhost:8080/movies/F1244AD6A71A9C6C9E08BA6D819D119FBD7944D0

{
  "name":"Toy Story 3",
  "creationYear":2015,
  "creator":"Pixar"
}
```

# Build

Use the command line `gradlew.bat build`

The application runnable jar file will be generated into the folder **build/libs** (use the **.jar** file).
