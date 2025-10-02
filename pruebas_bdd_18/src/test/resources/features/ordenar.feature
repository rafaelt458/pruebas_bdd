Feature: ordenar los elementos de la pagina principal
  Como usuario quiero poder ordenar los elementos de la pagina principal de distintas maneras

  @Ordenamiento
  Scenario Outline: ordenar los elementos segun <orden>
    Given el usuario "<usuario>" esta en la pagina principal usando el password "<password>"
    When selecciona el orden "<orden>"
    Then el primer elemento de la pagina es el producto "<producto>"

    Examples:
    | usuario       | password     | orden               | producto                          |
    | standard_user | secret_sauce | Name (Z to A)       | Test.allTheThings() T-Shirt (Red) |
    | standard_user | secret_sauce | Price (low to high) | Sauce Labs Onesie                 |
    | standard_user | secret_sauce | Price (high to low) | Sauce Labs Fleece Jacket          |