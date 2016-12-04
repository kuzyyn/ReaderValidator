ReaderValidator

Do czego s�u�y
	
	Program jest przeznaczony do u�atwienia serwisowania r�nych czytnik�w mikrop�ytek poprzez implementacj� sterownik�w r�nych urz�dze�  
	oraz funkcje u�atwiaj�ce i automatyzuj�ce prac� serwisanta. Docelowo ma umo�liwi� komunikacj� z czytnikiem, sterowanie, wykonywanie automatycznych pomiar�w p�ytkami wzorcowymi, umo�liwia� dodawanie nowych p�ytek wzorcowych, pomoc przy diagnozowaniu usterek

Cele

	G��wnym celem napisania tego programu jest praktyczna nauka Javy SE obejmuj�ca zagadnienia wielow�tkowe, typy generyczne, kolekcje, 
	klasy abstrakcyjne i interfejsy, prac� z zewn�trznymi bibliotekami, dynamiczne dodawanie plik�w, automatyzacj� procesu komplilacji, testy jednostkowe JUnit, prac� z kontrol� wersji GIT + Github, zaprz�enie wzorc�w projektowych, kierowanie si� zasadami czystego kodu i inne niewymienione.
	
	Drugim celem jest napisanie dzia�aj�cego programu, kt�ry spe�nia poni�sze za�o�enia.

Za�o�enia

	- Konfiguracja testu poprzez wyb�r czytnika, portu COM, wyb�r serwisanta, wyb�r daty
	- Konfiguracja prog�w poprawnych odczyt�w, parametr�w ilo�ciowych danego testu
	- Wydruk raportu serwisowego
	- Odczyty surowe poszczeg�lnych kana��w
	- Kalibracj� filtr�w, odczyt firmware, roku produkcji itp
	- kontrola pracy lampy
	- Testy wytrzyma�o�ciowe czytnika
	- uruchomienie w �rodowisku Windows i Linux niezale�nie od architektury procesora
	
�rodowisko testowe

	- osobna aplikacja ReaderSimulator w repozytorium https://github.com/kuzyyn/ReaderSimulator
	- program Free Virtual Serial Ports https://freevirtualserialports.com/
	- Windows na architekturze x64 (stan obecny)
	- Java 1.8

Uruchomienie

	- zainstalowa� program https://freevirtualserialports.com/ i utworzy� wirtualny mostek pomi�dzy dwoma portami com (na cele symulacji)
	lub pod��czy� si� pod fizyczne urz�dzenie 
	- �ci�gn�� i uruchomi� ReaderSimulator.jar z https://github.com/kuzyyn/ReaderSimulator
	- uruchomi� ReaderValidator.jar, pod��czy� si� pod wybrany COM i czytnik (Expert96)
	- wi�kszo�� funkcjonalno�ci w przysz�o�ci, obecnie wylane s� fundamenty.

	
	