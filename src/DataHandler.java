
public class DataHandler {
	
	/**
	 * 	Krav, datainnsamlingsklassen:
	 * 
		-En fil skal kunne �pnes
		-Vi skal kunne lese en linje med m�ledata
		-Vi skal kunne finne ut om det er mer data � lese
		
		N�r ei linje med m�ledata leses, skal f�lgende skje:
		- M�ledata id skal brukes som n�kkel for lagring og gjenfinning av bearbeidede m�ledata i en
				passende Java collection klasse
		- Feil antall argumenter skal kaste en exception
		- Operasjon (bitwise OR eller AND) skal utf�res p� m�ledata (bearbeidingen)
		- Originale m�ledata samt resultat av operasjon (bearbeidede m�ledata) skal lagres, b�de som
				bitstrenger og int verdier, og skal kunne gjenfinnes ved hjelp av m�ledata id
		- Vi skal logge data med duplikate m�ledata-id, samt m�ledata med feil bitoperasjon (alt annet
				enn 1 eller 2 er � anse som en feil), dette skjer p� enkleste m�te ved at hele tekstlinja 
				som er lest lagres unna i en liste (en passende Java Collection klasse)
	 */
	
	// constructor
	public DataHandler ()
	{
		
	}
	
	
}
