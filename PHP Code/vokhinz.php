<?php
// Array erstellen
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
$de = $_POST["de"];
$eng = $_POST["eng"];
$nutzername = $_POST["nutzer"];

// Gucken ob Rolle=Lehrer

$sql = "SELECT * from user WHERE user_name ='$nutzername' AND rolle='lehrer'";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // Falls Lehrer, dann wird Vokabel hinzugefügt
    // Gucken ob Vokabel bereits vergeben
    $sql = "SELECT * from Vokabeln WHERE de='$de'";
    $result = $conn->query($sql);
    
    if ($result->num_rows > 0) {
        // Wenn Vokabel bereits vergeben, dann Seite neu laden
        header('Location: vokabelhinzufugen.php');
    } else {
        
        // Falls erfolgreich, Daten bei Vokabeln einfügen mit den Values
        $sql = "INSERT INTO Vokabeln (de, eng)
    VALUES ('$de','$eng')";
        
        if ($conn->query($sql) === TRUE) {
            // Neuladen der Webseite
            header("Location: vokabelhinzufugen.php?vokabel=Erfolgreich");
        }
    }
} else {
    //Falls Schüler, dann Fehler
    header("Location: vokabelhinzufugen.php?vokabel=Fehler");
}

// Verbindung trennen
$conn->close();

?>