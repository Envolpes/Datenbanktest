<?php
//Testen ob PHP überhaupt funktioniert
echo "<h2>PHP is Fun!</h2>";



//Parameter für die Verbindung
$servername = "localhost";
$username = "root";
$password = "";
$dbname ="myDB";

// Verbindung herstellen
$conn = new mysqli($servername, $username, $password, $dbname);

// Schauen, ob Verbindung klappt
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
echo "Connection to database successfully.<br><br>";

/*   DATENBANK MY DEM NAMEN MY DB ANLEGEN
 * 
// Create database
$sql = "CREATE DATABASE myDB";
if ($conn->query($sql) === TRUE) {
    echo "Database created successfully. ";
} else {
    echo "Error creating database: " . $conn->error;
}*/

/*  TABELLE MIT DEM NAMEN MyGuests MIT FOLGENDEN SPALTEN ANLEGEN
 * 
$sql = "CREATE TABLE MyGuests (
id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
firstname VARCHAR(30) NOT NULL,
lastname VARCHAR(30) NOT NULL,
email VARCHAR(50),
reg_date TIMESTAMP
)";

if ($conn->query($sql) === TRUE) {
    echo "Table MyGuests created successfully";
} else {
    echo "Error creating table: " . $conn->error;
}*//// ENDE




/* Daten bei MyGuests (XXX,...) einfügen mit den Values (XXX,....)


$sql = "INSERT INTO MyGuests (firstname, lastname, email)
VALUES ('John', 'Doe', 'john@example.com')";

if ($conn->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}
*/

/* SQL Löschen wo ID=3
$sql = "DELETE FROM MyGuests WHERE id=3";

if ($conn->query($sql) === TRUE) {
    echo "Record deleted successfully";
} else {
    echo "Error deleting record: " . $conn->error;
}

*/

/////       Überprüfen der Parameter user_name und user-passwort im select teil

$user_name = $_POST["user_name"];
$user_passwort= $_POST["user_passwort"];


$sql = "SELECT * from user WHERE user_name='$user_name' AND user_passwort='$user_passwort'";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        echo "Eingeloggt als: <br> Benutzername: " . $row["user_name"]. "<br> ID:   " . $row["id"]. "<br>";
        }
    }
 else {
    echo "Falscher Username oder Passwort eingegeben";
}

/*

// if-Abfrage
$t = 9;
$y = "Hallo Weld";

echo $y;
    
if ($t < "20") {
    echo "Have a good day!";
}
*/

//Verbindung trennen
$conn->close();
?>