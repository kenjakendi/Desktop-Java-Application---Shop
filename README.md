# PAP22L-Z16
## Grupa 16 - System zarządzania sklepem

### Run

```
mvn compile
mvn exec:java -Dexec.mainClass=pap.AppPanel
```

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
    - może sprawdzić dostępność produktów i ich cenę
    - dokonać zakupów
    - każdy klient ma swój koszyk który może sfinalizować
 - manager: 
    - ma dostęp do stanu towarów widocznych dla klienta
    - widzi stan produktów w magazynie
    - zleca wystawienie produktów
    - składa zamówienia na dostawę brakujących produktów
    - ma dostęp do monitora finansowego oraz statystyk
    - może edytować oferty

## Wstępny zarys planowanych klas

|Klasa|Informacje|
|-----|-----|
|**produkt**| będzie zawierała informacje o produktach: ilość, nazwa, id, cena|
|**klient**| klasa odpowiedzialna za obsługę interfejsu klienta, metody przeglądu produktów i dodawania produktów do pól koszyka|
|**koszyk**| przeglądanie, dodawanie i usuwanie produktów, finalizacja zamówień|
|**manager**| klasa odpowiedzialna za obsługę interfejsu managera, sprawdzanie stanu sklepu, statystyk, dodawania produktów, zlecanie zamówień|
|**sklep**| klasa inicjalizująca interfejsy i magazyn|
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
