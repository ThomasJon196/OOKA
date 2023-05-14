# Wir-Schiffen-Das


## Exercises:

### a) Betrachte die Statements kritisch und beantworte Fragen in 2-3 Saetzen.


Statement 1: "Uebernahme der strengen Komponenten und Schichtenbildung der Entwicklungsteams fuer Microservices."

- Die Aufteilung in horizontale Schichten funktionierte bei Entwicklung eines Monolithen, allerdings nicht bei Entwicklung von Microservices, da diese in sich abgeschlossene Komponenten sind. Jeder Microservice benoetigt somit ein Entwicklungsteam, welches Kompetenz fuer jede Schicht enthaelt.


Statement 2: "Nur geringfuegige Aenderung. - Benachrichtigung der Geschaeftsfuehrung nicht noetig."

- Die Architekturaenderung sollte nicht unterschaetzt werden. Diese Architekturentscheidung zieht grundsaetzliche Konsquenzen mit sich da von einem zentalisierten zu einem verteilten System uebergegangen wird.

- Es koennten neue Mitarbeiter fuer die neuen vertikalen Teams benoetigt werden.


Statement 3: "Anwendung bereits auf Cloud skalierbar. Laut Bereichsleitung:'Es besteht keine Notwendigkeit fur Skalierbarkeit'"

- Einen Monolith in der Cloud als ganzes zu skalieren ist kostentechnisch nicht realisierbar.

- Bei skalierung der Datenbank muss Datenkonsistenz garantiert werden koennen.

- Sobald die Architektur als Microservice implementiert ist, wird auch eine skalierbarkeit einzelner/bestimmter Komponenten noetig.


Statement 4: "Steigende komplezitaet bei GP-API als Fassade/Adapter"

- Zunehmende komplexitaet durch abhaengigkeiten und Aenderungen fuer Clientside/UI und Serverside Aenderungen.

- Stattdessen waere eine Implementierung von Server-Side APIs abhaengig von den Frontend Devices sinnvoller und schlanker.

Statement 5: "Eine Komponente macht Probleme" (siehe Dokument)

- Skalierbarkeit koennte notwendig werden.


Statement 6: "globales/universelles Datenmodell"

- gemeinsames Datenmodell weniger praktisch
- Services sollten in sich konsistenze Datenmodelle halten


Statement 7: "Monitoring zu teuer. "

- Notwendig um skalierung zu steuern
- Monitoring fuer Sicherheit noetig




### b) Modellierung des WirSchaffenDas Monolithen als Microservice-Architektur.

Entscheidung fuer die Mischform als Architektur Schablone.
Aufspaltung in UI, Feature und ein Datenbank Team.


#### Context Sicht

![](context-view.svg)

####  Bausteinsicht 


![](development-view.svg)