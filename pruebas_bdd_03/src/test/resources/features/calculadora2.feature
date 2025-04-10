@SmokeTest
Feature: operaciones de una segunda calculadora

  Scenario Outline: efectuar una operacion matematica con 2 numeros
    Given el usuario tiene abierta la aplicacion de calculadora2
    When introduce el primer numero <num1>
    And introduce el segundo numero <num2>
    And pulsa el boton "<operacion>"
    Then se muestra en pantalla el resultado <resultado>

    Examples:
    | num1 | num2 | operacion      | resultado |
    | 5    | 7    | suma           | 12        |
    | 9    | 3    | resta          | 6         |
    | 2    | 4    | multiplicacion | 8         |