<?php
// Initaliserung Array
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

// Initalisieurng und vergabe Parameter
$user_name = $_POST["user_name"];
$user_passwort = md5($_POST["user_passwort"]);
$user_nachname = $_POST["user_nachname"];
$user_vorname = $_POST["user_vorname"];
$rolle = $_POST["rolle"];

// Gucken ob username bereits vergeben
$sql = "SELECT * from user WHERE user_name='$user_name'";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // Wenn User-Name bereits vergeben, dann direkt zurück zur Startseite
    header('Location: http://localhost:8080/lernprogrammjsp/startseite.jsp?eingeloggt=vergeben');
} else {
    
    // Daten bei user einfügen mit den Values
    $sql = "INSERT INTO user (user_name, user_passwort, user_nachname, user_vorname, rolle)
    VALUES ('$user_name','$user_passwort','$user_nachname', '$user_vorname', '$rolle')";
    
    if ($conn->query($sql) === TRUE) {
        // Weiterleitung zur "Homelogin-Seite mit GET_METHODE
        header("Location: http://localhost:8080/lernprogrammjsp/homelogin.jsp?nutzername=" . $user_name . "&eingeloggt=wahr");
    }
}
// Verbindung trennen
$conn->close();

?>