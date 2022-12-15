@functional
Feature: Scrapper de precios
  Como usuario quiero tener en un excel con los precios de los distintos productos

  @farmacia
  Scenario: Sacar precios farmacity
    Given   Entro a la pagina de farmacia
    """
    https://www.farmacity.com/farmacia
    """
    When    Extraigo los precios
    Then    Los paso a un excel


  @nutricion&&deportes
  Scenario: Sacar precios farmacity nutricion y deportes
    Given   Entro a la pagina de nutricion
    """
    https://www.farmacity.com/nutricion-y-deportes
    """
    When    Extraigo los precios de nutricion
    Then    Paso los articulso de nutrcion a excel

  @electrosalud
  Scenario: Sacar precios farmacity electrosalud
    Given   Entro a la pagina de electrosalud
    """
    https://www.farmacity.com/electrosalud
    """
    When    Extraigo los precios de electrosalud
    Then    Paso los articulos de electrosalud a excel

  @cuidadoBucal
  Scenario: Sacar precios farmacity cuidado bucal
    Given   Entro a la pagina de cuidado bucal
    """
   https://www.farmacity.com/cuidado-personal/cuidado-oral
    """
    When    Extraigo los precios de cuidado bucal
    Then    Paso los articulos de cuidado bucal a excel

  @cuidadoRostro
  Scenario: Sacar precios farmacity cuidado del rostro
    Given   Entro a la pagina de cuidado del rostro
    """
   https://www.farmacity.com/dermocosmetica/rostro
    """
    When    Extraigo los precios de cuidado del rostro
    Then    Paso los articulos de cuidado del rostro a excel

  @cuidadoCuerpo
  Scenario: Sacar precios farmacity cuidado del cuerpo
    Given   Entro a la pagina de cuidado del cuerpo
    """
   https://www.farmacity.com/dermocosmetica/corporal
    """
    When    Extraigo los precios de cuidado del cuerpo
    Then    Paso los articulos de cuidado del cuerpo a excel

  @cuidadoSolar
  Scenario: Sacar precios farmacity cuidado solar
    Given   Entro a la pagina de cuidado solar
    """
   https://www.farmacity.com/dermocosmetica/solar
    """
    When    Extraigo los precios de cuidado solar
    Then    Paso los articulos de cuidado solar a excel


