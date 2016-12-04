ReaderValidator

Do czego s³u¿y
	
	Program jest przeznaczony do u³atwienia serwisowania ró¿nych czytników mikrop³ytek poprzez implementacjê sterowników ró¿nych urz¹dzeñ  
	oraz funkcje u³atwiaj¹ce i automatyzuj¹ce pracê serwisanta. Docelowo ma umo¿liwiæ komunikacjê z czytnikiem, sterowanie, wykonywanie automatycznych pomiarów p³ytkami wzorcowymi, umo¿liwiaæ dodawanie nowych p³ytek wzorcowych, pomoc przy diagnozowaniu usterek

Cele

	G³ównym celem napisania tego programu jest praktyczna nauka Javy SE obejmuj¹ca zagadnienia wielow¹tkowe, typy generyczne, kolekcje, 
	klasy abstrakcyjne i interfejsy, pracê z zewnêtrznymi bibliotekami, dynamiczne dodawanie plików, automatyzacjê procesu komplilacji, testy jednostkowe JUnit, pracê z kontrol¹ wersji GIT + Github, zaprzê¿enie wzorców projektowych, kierowanie siê zasadami czystego kodu i inne niewymienione.
	
	Drugim celem jest napisanie dzia³aj¹cego programu, który spe³nia poni¿sze za³o¿enia.

Za³o¿enia

	- Konfiguracja testu poprzez wybór czytnika, portu COM, wybór serwisanta, wybór daty
	- Konfiguracja progów poprawnych odczytów, parametrów iloœciowych danego testu
	- Wydruk raportu serwisowego
	- Odczyty surowe poszczególnych kana³ów
	- Kalibracjê filtrów, odczyt firmware, roku produkcji itp
	- kontrola pracy lampy
	- Testy wytrzyma³oœciowe czytnika
	- uruchomienie w œrodowisku Windows i Linux niezale¿nie od architektury procesora
	
Œrodowisko testowe

	- osobna aplikacja ReaderSimulator w repozytorium https://github.com/kuzyyn/ReaderSimulator
	- program Free Virtual Serial Ports https://freevirtualserialports.com/
	- Windows na architekturze x64 (stan obecny)
	- Java 1.8

Uruchomienie

	- zainstalowaæ program https://freevirtualserialports.com/ i utworzyæ wirtualny mostek pomiêdzy dwoma portami com (na cele symulacji)
	lub pod³¹czyæ siê pod fizyczne urz¹dzenie 
	- œci¹gn¹æ i uruchomiæ ReaderSimulator.jar z https://github.com/kuzyyn/ReaderSimulator
	- uruchomiæ ReaderValidator.jar, pod³¹czyæ siê pod wybrany COM i czytnik (Expert96)
	- wiêkszoœæ funkcjonalnoœci w przysz³oœci, obecnie wylane s¹ fundamenty.

	
	