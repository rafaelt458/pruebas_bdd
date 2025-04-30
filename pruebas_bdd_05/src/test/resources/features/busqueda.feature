Feature: busqueda en Internet con DuckDuckGo
  Como usuario, quiero buscar información en Internet con DuckDuckGo

  Scenario: busqueda en Internet con DuckDuckGo
    Given el usuario esta en la pagina principal de DuckDuckGo
    When escribe la espresion de busqueda "El Laboratorio de Rafa"
    And pulsa el boton Buscar
    Then el sistema muestra una lista con los resultados encontrados
