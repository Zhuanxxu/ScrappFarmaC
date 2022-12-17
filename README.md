# Cucumber with Selenium Framework
## To init
- Clone the repo
- Exc "node i"

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

