Robotik-AG SS 2015
===
Lego Mindstorm EV3 (Lejos)
---

#### Das Team:
	Michel Vielmetter 5920493
	Vera Vielmetter 5563461
	Lukas Stachow 5296145

#### Projekt: Rubiks Cube
Die Aufgabe besteht darin einen Roboter zu bauen/programmieren, der fähig ist einen Würfel so zu manipulieren, dass alle Seiten aus Feldern der gleichen Farbe bestehen. Dieser Würfel, welcher in Höhe, Breite und Tiefe in drei Ebenen, welche sich um ihre jeweilige Raumachse drehen lassen, unterteilt ist, besitzt somit auf jeder Seite 3x3 Felder mit meist unterschiedlichen Farben.

#### Der Roboter:
Der Lego Mindstorm Roboter - EV3 mit dem Namen MindCub3r verfügt über drei Motoren und einen Sensor (Farbsensor). Die drei Motoren steuern „Arm“, „ColorArm“ und „Table“ an. Der Würfel wird in eine Schale gelegt, welche sich von 	Table um 90° clockwise und counterclockwise rotieren lässt. Arm kann den Würfel greifen und festhalten, oder nach 	vorne kippen. ColorArm bewegt den Farbsensor, mit welchem jedes Feld einzeln eingelesen werden kann. Durch Kombination der Motoren Arm und Table kann der 	Würfel beliebig gedreht, und in jede Lage bewegt werden. Dabei verhindert die 	„InvalidActivityException“, dass sich Arm und ColorArm gegenseitig behindern. Mit dieser Mechanik lässt sich ein Rubiks Cube lösen.

#### Das Programm:
Liegt ein Würfel in der vorgesehen Schale, so werden beim Starten des Programms mithilfe der drei Motoren und des Farbsensors zunächst alle Farben der 54 Felder auf den sechs Seiten eindeutig eingelesen. Dafür wird mit Arm nacheinander jede Seite nach oben gelegt. Liegt eine Seite oben bewegt ColorArm den Farbsensor zunächst über das mittlere Feld. Anschließend wird der Würfel von Tabel stückweise gedreht bis der Farbsensor alle übrigen Farben eingelesen hat. Für jede Seite wird ein Objekt des Typs RubikSide erstellt und die zugehörigen Farben werden in ihm gespeichert. Der Würfel wird durch das Array sides bestehend aus den sechs Objekten des Typs RubikSide repräsentiert.
Wurden alle Farben eingelesen und in Sides gespeichert, kann der Algorithmus zum Lösen des Rubiks Cube gestartet werden. Hierbei werden zunächst die eingelesenen Farben auf Fehler überprüft. So erkennt das Programm zum Beispiel wenn eine Farbe häufitger als 9 mal vertreten ist, erkennt somit einen Fehler und beginnt das Einlesen der Farben erneut. Wurde kein Fehler gefunden, berechnet der Algorithmus zunächst bestimmte Standardanordnungen der Farben des Würfels, die das Lösen erleichtern. Diese Berechnungen müssen nur beim Starten des Programms (falls erwünscht auch vor dem ersten Einlesen) vorgenommen, und somit nicht bei jedem Durchlauf wiederholt werden.
Im nächsten Schritt liefer der Algorithmus für die gegebene Anordnung der Felder eine eindeutige Abfolge von Bewegungen (Drehungen der Ebenen) um die Aufgabe zu lösen. Im Durchschnitt benötigt er dazu 21 Bewegungen. Wie schon bei der Farbeinlesung wird der Würfel in eine gewünschte Lage gebracht. Anschließend begibt Arm sich in die "Hold-Position" (POS_HOLD) und fixiert die oberen beiden Ebenen des Würfels. Durch die Rotation mit Table kann nun die undere Ebene relativ zu dem Würfel verdreht werden. Somit kann Jede Seite (Ebene) beliebig gedreht und der Würfel in seine zu erreichende Endform gebracht werden.
