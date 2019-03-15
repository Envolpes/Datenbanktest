<?php
// Arrayerstellung
$array = array();

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

// Initalisieurng und vergabe Parameter über POST
$anzahl = $_POST["beantwortet"];
$richtig = $_POST["richtig"];
$user = $_POST["user"];

// Daten bei TESTERGEBNIS einfügen mit den Values
$sql = "INSERT INTO testergebnisse (user, anzahl, richtige)
    VALUES ('$user','$anzahl','$richtig')";

if ($conn->query($sql) === TRUE) {
    // Weiterleitung zu Testubersicht
    header("Location: testubersicht.php");
}

// Verbindung trennen
$conn->close();

?>