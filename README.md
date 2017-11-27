# ProgettoAnalizzatoreSintattico

Per avviare il progetto bisogna scaricare la libreria https://github.com/scala/scala-parser-combinators ed aggiungerla al progetto. 

Analizzatore sintattico-semantico che verifica la correttezza del codice in linguaggio micro_C e produce come output l'albero sintattico
Quando si incontra un errore nel programma di ingresso (lessicali, sintattici o di semantica statica) il programma dovrebbe produrre in uscita un’indicazione della riga in cui si è rilevato l’errore.
Il programma sorgente può contenere dei commenti in stile C per i commenti multi linea (/ * ... * / e in stile C++ per i commenti su singola linea //dovrebbe essere considerato come punto di partenza per un commento singola riga.
