@functional
Feature: Scrapper de precios
  Como usuario quiero tener en un excel con los precios de los distintos productos

  @browser @slow
  Scenario: Sacar precios farmacity
    Given   Entro a la pagina principal
    When    Extraigo los precios
    Then    Los paso a un excel
  @browser @slow
  Scenario: Sacar precios farmacity nutricion y deportes
    Given   Entro a la pagina principal de nuetricion
    When    Extraigo los precios de nutricion
    Then    Paso los articulso de nutrcion a excel

