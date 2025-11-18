[ReadMe (2).md](https://github.com/user-attachments/files/23601163/ReadMe.2.md)

# Progetto Java: FileChooserDemo, Cavallo e Simulazione Gara

## Descrizione

Questo progetto raccoglie tre componenti Java indipendenti ma didatticamente collegati tra loro:

-   **FileChooserDemo**: dimostrazione dell’uso di `JFileChooser` per apertura e salvataggio file tramite interfaccia grafica Swing.
    
-   **Cavallo**: classe che rappresenta un cavallo per simulazioni, utilizzata anche in contesti multithreading.
    
-   **SimulazioneGara**: programma che gestisce una gara simulata tra cavalli, sfruttando thread, sincronizzazione e salvataggio del risultato tramite `JFileChooser`.
    

----------

## Classi principali

###  FileChooserDemo

Mostra come utilizzare il componente grafico **JFileChooser** per:

-   aprire file
    
-   scegliere un percorso di salvataggio
    
-   visualizzare il log delle operazioni in una textarea
    

È completamente basata su Java Swing ed è ideale come esempio pratico per l’uso di finestre di dialogo standard.

----------

###  Cavallo (extends Thread)

Rappresenta un cavallo utilizzato in una simulazione.  
Ogni cavallo ha:

-   un **nome**
    
-   un **tempo di ritardo** che simula la sua velocità
    
-   un metodo `run()` che esegue la corsa passo per passo
    
-   la possibilità di essere **interrotto** (“azzoppato”) tramite `interrupt()`
    

La classe contiene inoltre metodi getter e setter e comunica con la classe gestore tramite metodi sincronizzati.

----------

###  SimulazioneGara

È il programma principale che gestisce l’intera gara:

-   permette l’inserimento dei cavalli
    
-   assegna la lentezza di ciascuno
    
-   avvia i thread della corsa
    
-   seleziona casualmente un cavallo da interrompere
    
-   attende la fine di tutti i thread (`join()`)
    
-   determina e mostra il vincitore
    
-   salva il risultato tramite `JFileChooser`
    

L’interfaccia è testuale (console), mentre la parte grafica è usata solo per il salvataggio del risultato.

----------

## Funzionamento

1.  L’utente inserisce il **nome** dei cavalli.
    
2.  Per ogni cavallo viene impostato un **tempo di ritardo** in millisecondi.
    
3.  La gara viene avviata facendo partire ogni cavallo come **thread separato**.
    
4.  Un cavallo casuale può essere “azzoppato” (interrotto) durante la simulazione.
    
5.  Il primo cavallo che completa la corsa aggiorna la variabile condivisa.
    
6.  Al termine di tutti i thread, il programma dichiara il **vincitore**.
    
7.  L’utente può salvare l’esito in un file tramite `JFileChooser`.
    

----------

## Concetti di programmazione utilizzati

-   Utilizzo dei **Thread** per eseguire processi in parallelo
    
-   Metodo `sleep()` per simulare velocità differenti
    
-   **Sincronizzazione** tra thread con `synchronized`, `join()` e variabili condivise
    
-   Gestione delle **interruzioni** (`interrupt()`)
    
-   Uso di **JFileChooser** per selezione e salvataggio file
    
-   Utilizzo di **ArrayList** per memorizzare dinamicamente i cavalli
    
-   Struttura **OOP** chiara e separazione in classi distinte
    

----------

## Autore

-   **Nome:** Squarta Jordan
    
-   **Classe:** 5DINF
    
-   **Scuola:** ITTS A. Volta – Perugia
    
-   **Anno:** 2025
