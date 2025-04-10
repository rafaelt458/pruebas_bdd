@SmokeTest
Feature: operaciones de una calculadora
  Background:
    Given el usuario tiene abierta la aplicacion de calculadora

  Scenario: suma de 2 numeros
    When introduce el primer numero "5"
    And introduce el segundo numero "7"
    And pulsa el boton sumar
    Then se muestra en pantalla el resultado "12"

  Scenario: resta de 2 numeros
    When introduce el primer numero "9"
    And introduce el segundo numero "3"
    And pulsa el boton restar
    Then se muestra en pantalla el resultado "6"

  Scenario: multiplicacion de 2 numeros
    When introduce el primer numero "2"
    And introduce el segundo numero "4"
    And pulsa el boton multiplicar
    Then se muestra en pantalla el resultado "8"

  Scenario: division de 2 numeros
    Given las siguientes combinaciones de numeros estan disponibles
    | combinacion | numero1 | numero2 | resultado |
    | uno         | 10      | 2       | 5         |
    | dos         | 12      | 3       | 4         |
    | tres        | 21      | 7       | 3         |
    When escoge la combinacion "dos"
    And pulsa el boton dividir
    Then se muestra en pantalla el resultado correspondiente