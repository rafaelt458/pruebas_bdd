Feature: Busquedas en Internet
  Como usuario, quiero poder hacer busquedas en Internet con una aplicacion de busquedas

  @SmokeTest
  Scenario: buscar un texto con la aplicacion de busqueda
    Given el usuario esta en la pagina de busqueda
    When el usuario introduce el texto "texto a buscar"
    And pulsa el boton Buscar
    Then el sistema muestra los resultados