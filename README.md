# ZPO-Java-Zadanie-8
Giełda papierów wartościowych
Do zaimplementowania są następujące elementy aplikacji:
Giełda: giełda tworzy się automatycznie w losowy sposób:
- 5 do 10 losowanych aktywów (akcji) z puli 15 możliwych
- losowa cena początkowa akcji z przedziału 1-1000 (np PLN)
Giełda działa w sposób ciągły aż do zatrzymania jej przez polecenie Administratora Giełdy (możliwość wydania polecenia w konsoli). Giełda co 5 sekund zmienia losowo kurs każdej akcji o losową wartość z przedziału 1-3. Cena akcji nie może przekroczyć 10000 PLN i nie może spaść poniżej 1 PLN.
Giełda (wersja prostsza) ma tylko jednego użytkownika (który też jest administratorem). 
Zlecenie: zawiera typ operacji (kupno/sprzedaż), liczbę kupowanych akcji. Każdy użytkownik może składać dowolną liczbę zleceń.
W momencie gdy kurs akcji osiągnie założony poziom w zleceniu, dane zlecenie jest realizowane automatycznie a użytkownik jest o tym informowany.
 Każda transakcja na giełdzie wyzwala zmianę kursu danej akcji (kupno -> wzrost, sprzedaż -> spadek). Zmiana kursu powinna być proporcjonalna do stosunku liczba kupowanych bądź sprzedawanych akcji / liczba akcji na giełdzie. Dokładny algorytm proporcjonalności do zaproponowania.
Należy obsłużyć w formie własnych wyjątków wszystkie możliwe błędy, np: 
- złożenie zlecenia większego niż liczba dostępnych akcji
- wykroczenie kursu akcji poza przedział 1-1000 USD
- podanie typu akcji która nie istnieje
