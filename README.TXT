
- HexBinConverter.java har f�lgende testklasser:
		HexBinConverterTest.java
		BinToDecParameterizedTest.java
		DecToBinParameterizedTest.java
		HexToDecParameterizedTest.java
		DecToHexParameterizedTest.java
		
		Der de parametriserte testene er enkle og kun for � vise bruk av parametriserte tester
		
- DataHandler.java har f�lgende testklasser:
		DataHandlerTest.java
		
- Testing av lesing fra fil er gjort via Mockito og interface injection for � unng� diskaccess, 
	men produksjonskoden for skriving og lesing er fungerende og kan sees i bruk i ActualReadWriteDemo.java.

- Noen metoder er protected der de burde v�re private for � tillate direkte testing.

- I HexBinConverter.java er det noen sjekker jeg antagelig ville lagt i validate-metodene,
	men som ligger i konverterings-metodene pga kravene i oppgaven (spesifisert returtype o.l.).
	
- Infinitest er brukt for code coverage.

- Hvis du syns det er eksessive mengder kommentarer s� er det kun for � demonstrere hva som skjer,
	ikke slik jeg vanligvis skriver kode, s� vennligst overse det :)
	
Repository: https://github.com/henningfredriksen/prog2_prosjekt_990432
Clone Url:  https://github.com/henningfredriksen/prog2_prosjekt_990432.git