# Cucumber with Selenium Framework
## To init
- Clone the repo
- Execute "node i"

## Description
Framework para scrap de precios de farmacity usando Selenium + Cucumber + Excel


### Run headless Chrome

- ChromeOptions options = new ChromeOptions();
  options.addArguments("--headless");

### Maximize Window

- driver.manage().window().maximize(); # Selenium-CleanStructure

# Gradle commands

- gradle clean -x test -PenvFile=qa.properties tests
  Ejecuta los test con el @tag SmokeTest y settea
### Passing arguments from CLI
```description
Para usar estos valores pasados por cli usamos project.getProperty("args") y
System.getProperty("args").
Los argumentos -D se pueden llamar desde cualquier parte del sistema
Los argumentos -P se pueden llamar solo desde el build.gradle
```
- setting system properties with the -D flag
- setting project properties with the -P flag
#### Ejemplo de ejecucion en el CLI

``` groovy
apply plugin: "java"
description = "Gradle Command Line Arguments examples"

task propertyTypes(){
    doLast{
        if (project.hasProperty("args")) {
            println "Our input argument with project property ["+project.getProperty("args")+"]"
        }
        println "Our input argument with system property ["+System.getProperty("args")+"]"
    }
}
```
Ejecutamos con comando
```
./gradlew propertyTypes -Dargs=lorem -Pargs=ipsum

> Task :cmd-line-args:propertyTypes
Our input argument with project property [ipsum]
Our input argument with system property [lorem]
```


