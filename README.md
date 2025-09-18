Denna applikation sparar och hämtar data från en databas. Syftet för användaren är att enklare hantera och sortera glosor för att kunna lära sig dessa. 
Datan i fråga är ordlistor och ord, där orden är kopplade till ordlistorna. Orden innehåller själva ordet och en översättning, och kan användas som en glosa.
Projektet använder sig av en lokal databas (postgreSQL) och en koppling till denna via application.properties.

Exempel på anrop:
http://localhost:PORT/wordList/random/1 - returnerar ett slumpat ord från ordlistan med ID 1
http://localhost:PORT/word/1 med en body 
{
	"word": "exempel",
    "answer": "example"
} - sätter in ett nytt ord i ordlistan med ID 1

Reflektion:
Vi har lärt oss hur man använder sig av en lokal databas och gör setup för denna från start till slut. Versionshantering har inte varit särskilt mycket nytt då vi var i en väldigt liten grupp och jobbade tillsammans
med allt, vilket ledde till att git inte fyller någon viktigare funktion. Samarbetet har flytit på och vi har gjort allt i projektet tillsammans med en som driver och en som navigatör.
