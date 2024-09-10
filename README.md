# Installation und Einrichtung

Das Projekt *likeZeroToZero* hat folgende Systemvoraussetzungen:

- Java JDK 17 oder höher
- Maven 3.6.x oder höher
- MariaDB 10.x oder höher
- JBoss Wildfly 33.0.1

### Klonen des Projekts

- Richte einen Projektordner und navigiere zu diesem:

```console
mkdir meinProjektOrdner
cd meinProjektOrdner
```

- Klone die Repository:

```console
git clone https://github.com/alessionocita/likeHeroToZero.git
```

oder nutze gleich Git-Import Funktionen deiner IDE.

### Einrichten der Datenbank

Falls du MariaDB noch nicht installiert hast, so tue dies mit:
(Linux)

```console
sudo apt-get install mariadb-server
```

(MacOS)
```console
brew install mariadb
```

Erschaffe einen neuen User und Password oder bleibe beim root-user: hauptsache, du merkst dir deine Zugangsdaten!

Nun importiere die im Projekt-Hauptordner vorhandene Datenbank.

```console
cd likeHeroToZero
mysql -u yourUserName -p < likeHeroToZero.sql
```

Falls du eine Meldung bekommst, "keine Datenbank unter diesen Namen" bedeutet das, dass dein MariaDB das direkte Erschaffen von Datenbank direkt aus dem Import nicht unterstützt.
In diesem Fall logge dich in deinem SQL-Client ein mit dem Terminal oder einem GUI-Client:

```console
mysql -u yourUserName -p
```

Erschaffe die Datenbank:
```sql
CREATE DATABASE likeHeroToZero;
```

Logge dich aus dem Client aus und dann gebe ein:
```console
mysql -u yourUserName -p likeHeroToZero < likeHeroToZero.sql
```

Die Datenbank ist nun installiert und eingerichtet.

### Installation und Einrichtung des Servers

- Lade WildFly 33.0.1. [hier](https://www.wildfly.org/downloads/) herunter. 
- Entpacke den heruntergeladenen Ordner und speichere den Serverordner in einem Ort deiner Wahl. 
- Lade die .jar-Datei des MariaDB JDBC Treibers [hier](https://mvnrepository.com/artifact/org.mariadb.jdbc/mariadb-java-client/3.4.1) herunter.  
Alternativ dazu findest du eine Kopie der .jar im Hauptordner des Projekts.  
(**WICHTIG: diese .jar NICHT zu den Libraries des Projekt selbst hinzufügen!**).

- Richte einen Management-User für den Server ein (keine Group notwendig):

```console
/pfad/zum/server/wildfly-33.0.1.Final/bin/add-user.sh
```

- Starte nun den Server in Standalone-Modus:
```console
/pfad/zum/server/wildfly-33.0.1.Final/bin/standalone.sh
```

- Logge dich in einem Browser deiner Wahl in der Server-Console ein: [http://localhost:9990/console](http://localhost:9990/console)

In der Console:  
- Gehe zu *Deployments*. Wähle *Add* (das kleine '+' Icon oben links). Wähle *Upload Deployment*.
- Lade die davor geladene .jar-Datei des JDBC Treiber hoch (maridb-java-client-3.4.1.jar).
- Wähle  *Homepage > Configurations*.  
- Wähle  *Subsystems > Datasources & Drivers > Datasources* und dann erneut *Add* (das + icon). Wähle *Add Datasource*.
- Unter *Choose Template* wähle *MariaDB*.
- Unter *Name* trage *LikeHeroToZeroDB* ein, unter *JNDI Name* trage *java:/jdbc/LikeHeroToZeroDB* ein.
- Unter *Driver Name* wähle die zuvor geladene *maridb-java-client-3.4.1.jar*, lasse *Driver Class Name* unverändert (org.mariadb.jdbc.Driver).
- Unter *Connection URL* trage am Ende der URL den Database-Namen ein. Die vollständige URL sollte sein: *jdbc:mariadb://localhost:3306/likeHeroToZero*.
- Unter *Username* und *Password* trage die Zugangsdaten deines Database-Users ein.
- Clicke auf "Test Connection". Bestätige anschließend alle Settings.

Dein Server ist nun eingerichtet!

### Einrichtung in IntelliJ IDEA

- Öffne IntelliJ IDEA
- Gehe auf *File > Open* und öffne den Projektordner. IntelliJ sollte automatisch das Projekt als Maven Project erkennen und die Dependencies importieren.
- Der Server sollte ebenfalls automatisch erkannt werden. Wenn nicht, dann gehe auf *File > Settings > Build, Execution, Deployment > Application Servers*, füge einen neuen Wildfly Server ein und gebe das WildFly-Verzeichnis an.
- Danach gehe zu *Run > Edit Configurations*. Dort das Verzeichnis des Servers checken (oder angeben, falls nicht zuvor geschehen), checken, dass der Server auf *Standalone* eingestellt ist, und unter *Before Launch* checken, ob der Artefakt *likeHeroToZero:war exploded* korrekt deployed wird.
Das korrekte Deployment des Artefakts ebenfalls unter dem Tab *Deployments* im selben Fenster checken.
- In *src/main/resources/META-INF/persistence.xml* ändere die Einträge *value="benutzer"* und *value="passwort"* in Zeile 11 und 12 mit den eigenen Benutzername und Password der MariaDB-Database.

Nun kannst du das Projekt mit dem "Run" Button starten! Das Projekt öffnet sich automatisch im Browser.

### Einrichtung in Eclipse

- Öffne Eclipse.
- Gehe zu *File > Import > Existing Maven Projects* und clicke auf *Next*.
- Gehe auf *Browse*, wähle das Projektordner und clicke auf *Finish*.
- Gehe zum Eclipse Terminal und führe `mvn clean install` aus.
- Gehe nun zu *Servers* und clicke auf dem Link, um einen neuen Server zu erstellen.
- Wähle *JBoss Community > WildFly 27+* und clicke *Next*.
- Akzeptiere alle Standardoptionen ("Local", "Filesystem and shell operation", "Server lifecycle is externally managed" auf unchecked, und "assign" a runtime to this server" auf checked) und clicke *Next*
- Füge die Resource *likeHeroToZero* zum Server hinzu, indem du sie wählst und auf *Add* clickst. Clicke anschließend auf *Finish*.
- In *src/main/resources/META-INF/persistence.xml* ändere die Einträge *value="benutzer"* und *value="passwort"* in Zeile 11 und 12 mit den eigenen Benutzername und Password der MariaDB-Database.
- Führe ein Recht-click auf dem Projektnamen, wähle *Run as > Run on server*, bestätige alle Optionen.

Das Projekt läuft nun und kann in jedem beliebigen Browser unter *localhost:8080/likeHeroToZero-1.0.SNAPSHOT/* aufgerufen werden.










