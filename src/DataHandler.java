
public class DataHandler {
	
	/**
	 * 	Krav, datainnsamlingsklassen:
	 * 
		-En fil skal kunne åpnes
		-Vi skal kunne lese en linje med måledata
		-Vi skal kunne finne ut om det er mer data å lese
		
		Når ei linje med måledata leses, skal følgende skje:
		- Måledata id skal brukes som nøkkel for lagring og gjenfinning av bearbeidede måledata i en
				passende Java collection klasse
		- Feil antall argumenter skal kaste en exception
		- Operasjon (bitwise OR eller AND) skal utføres på måledata (bearbeidingen)
		- Originale måledata samt resultat av operasjon (bearbeidede måledata) skal lagres, både som
				bitstrenger og int verdier, og skal kunne gjenfinnes ved hjelp av måledata id
		- Vi skal logge data med duplikate måledata-id, samt måledata med feil bitoperasjon (alt annet
				enn 1 eller 2 er å anse som en feil), dette skjer på enkleste måte ved at hele tekstlinja 
				som er lest lagres unna i en liste (en passende Java Collection klasse)
	 */
	
	// constructor
	public DataHandler ()
	{
		
	}
	
	
}
