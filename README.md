# PAP22L-Z16
## Grupa 16 - System zarządzania sklepem

### Zespół: 
- Michał Laskowski  
 - Kateryna Naumenko
 - Wiktor Niemirski  
 - Julita Wasilewska
  ###### Prowadzący: Mateusz Modrzejewski

## Podstawowe informacje o projekcie:
Projekt jest systemem usprawniającym zarządzanie sklepem oraz realizacje zamówień. Będzie on miał charakter samoobsługowego sklepu stacjonarnego. System będzie składał się z interfejsu dla klienta, który służy jednocześnie za kasę samoobsługową oraz interfejsu managera zarządzającego sklepem.

## Funkcje aplikacji
 - oddzielne intefejsy dla managera sklepu oraz dla klienta 
 - klient:
    - może sprawdzić dostępność produktów i ich cenę, 
    - dokonać zakupów, 
    - każdy klient ma swój koszyk który może sfinalizować
 - manager: 
    - ma również dostęp do stanu towarów widocznych dla klienta,
    - widzi stan produktów w magazynie,
    - może zlecić wystawienie produktów, 
    - zamówić dostawę i mieć dostęp do monitora finansowego/statystyk, 
    - edytowanie oferty 

## Wstępny zarys planowanych klas

|Klasa|Informacje|
|-----|-----|
|**produkt**| będzie zawierała info o produktach: ilość, nazwa, id, cena|
|**klient**| klasa odpowiedzialna za obsługę intrfejsu klienta, metody przeglądu produktów i dodawania produktów do pól koszyk|
|**koszyk**| przeglądanie produktów, finalizacja, dodwanie/usuwanie|
|**manager**| klasa odpowiedzialana za obsługę intefejsu managera, sprawdzanie stanu sklepu, statystyk, dodowania produktów, zlecanie zamówień|
|**sklep**| klasa inizjalizująca interfejsy i magazyn|
|**magazyn**| klasa obsługująca bazę danych|

###### Zakres klas może ulec zmianie wraz z rozwojem projektu

## Typ aplikacji
- desktopowa ze wzorcem MVC

## Narzędzia

Środowiska:
- IntelliJ IDEA
- VS Code
- Gitlab

Planowane technologie:
- javafx
- sqlite
- maven

Planowane biblioteki:
- Lombok
- Jackson
###### Zakres pozostałych bibliotek będzie uzgodniony z prowadzącym podczas przebiegu projektu
