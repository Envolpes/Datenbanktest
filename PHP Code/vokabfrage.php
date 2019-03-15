<?php
// Parameter für die Verbindung
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "myDB";

// Verbindung herstellen
$conn = new mysqli($servername, $username, $password, $dbname);

// Schauen, ob Verbindung klappt
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
echo "Connection to database successfully.<br><br>";

// Vokabeln und Anzahl Vokabeln / richtige Vokabeln bekommen

$vokeng = $_POST["vokeng"];
$vokde = $_POST["vokde"];
$anzahl = $_POST["beantwortet"];
$richtig = $_POST["richtig"];

// Abfrage, ob die Englische und Deutsche Vokabel identisch in der Datenbank existieren
$sql = "SELECT * from vokabeln WHERE eng='$vokeng' AND de='$vokde'";
$result = $conn->query($sql);
// WENN Gefunden, richtig +1
if ($result->num_rows > 0) {
    
    $richtig = $richtig + 1;
}

// Anzahl auf jeden Fall erhöhen
$anzahl = $anzahl + 1;
header("Location: http://localhost/vokabeltest.php?a=" . $anzahl . "&r=" . $richtig);
// Verbindung trennen
$conn->close();
?>