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

// /// Überprüfen der Parameter user_name und user-passwort im select teil

$user_name = $_POST["user_name"];
$user_passwort = md5($_POST["user_passwort"]);

// // Eigentliche Benutzerauthentifizierung
$sql = "SELECT * from user WHERE user_name='$user_name' AND user_passwort='$user_passwort'";
$result = $conn->query($sql);
// WENN Gefunden
if ($result->num_rows > 0) {
    // REDIRECT zu homelogin
    header("Location: http://localhost:8080/lernprogrammjsp/homelogin.jsp?nutzername=" . $user_name . "&eingeloggt=wahr");
}// WENN NICHT GEFUNDEN
else {
    // REDIRECT startseite mit fehschlag-get methode
    header('Location: http://localhost:8080/lernprogrammjsp/startseite.jsp?eingeloggt=falsch');
}

// Verbindung trennen
$conn->close();
?>