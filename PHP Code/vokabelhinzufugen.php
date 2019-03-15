<?php
// Cookie auslesen und in Variable speichern
$cookie = $_COOKIE["benutzername"];

// Verbindung DB
$pdo = new PDO('mysql:host=localhost;dbname=mydb', 'root', '');

// Post Methode für Alert Meldung

if (empty($_GET["vokabel"])) {
    $vokabelhinzufugen = "leer";
} else {
    $vokabelhinzufugen = $_GET["vokabel"];
}

// Wenn nicht leer, dann Fehlermedlungen ausgeben
if ($vokabelhinzufugen != "leer") {
    if ($vokabelhinzufugen == "Fehler") {
        echo "<script type='text/javascript'>alert('Du bist leider kein Lehrer, weshalb die Vokabel nicht hinzugefügt wurde.');</script>";
    } else {
        echo "<script type='text/javascript'>alert('Vokabel erfolgreich hinzugefügt.');</script>";
    }
}

// Initalisierung Zähler + Array
$zahler1 = 0;
$zahler2 = 0;

$array;
$array[0][0] = "leere Datenbank";

// DE und ENG Vokabeln in Array einfügen

$sql = "SELECT de, eng FROM vokabeln";
foreach ($pdo->query($sql) as $row) {
    $array[$zahler1][$zahler2] = $row['de'];
    $zahler2 ++;
    $array[$zahler1][$zahler2] = $row['eng'];
    $zahler1 ++;
    $zahler2 = 0;
}

?>

<html>
<!-- Import  -->
<head>
<title>Login Startseite</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="http://localhost:8080/lernprogrammjsp/style.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


</head>

<!-- Navleiste -->
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">English For Pros</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a
					href="http://localhost:8080/lernprogrammjsp/homelogin.jsp?eingeloggt=ja">Home</a></li>
				<li><a href="buchstabe.php">Buchstaben lernen</a></li>
				<li class="active"><a href="vokabeln.php">Vokabeln lernen</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#"><span class="glyphicon glyphicon-user"></span> Eingeloggt als: <?php echo $cookie?>   </a></li>
				<li><a
					href="http://localhost:8080/lernprogrammjsp/startseite.jsp?eingeloggt=logout"><span
						class="glyphicon glyphicon-log-in"></span> Logout</a></li>
			</ul>
		</div>
	</nav>


	<!-- Neue Vokabeln einfügen -->
	<div id="id02">
		<div class="row container-fluid bg-2 text-center">
			<form action="vokhinz.php" method="POST">
				<div class="col-sm-12 bg-2">
					<b> Vokabel Hinzufugen:</b><br> <br> <b> Deutsche Bedeutung</b> <input
						style="color: #000000;" type="text"
						placeholder="Deutsche Bedeutung" name="de"> <b>Englische Bedeutung</b>
					<input style="color: #000000;" type="text"
						placeholder="Englische Bedeutung" name="eng"> <b> Vokabeln
						hinzufügen: </b> <input class="button2" type="submit"> <br>

					<div class="col-sm-0">
						<input id="nutzernamerolle" type="hidden" name="nutzer"
							value=<?php echo $cookie?>>
					</div>


				</div>
			</form>
		</div>
	</div>

	<!-- Header für Tabelle -->

	<div class="container-fluid bg-1 text-center">
		<b>Vokabelliste</b>
		<table class="table table-hover">


			<thead>
				<tr>
					<th>ID</th>
					<th>Deutsche Übersetzung</th>
					<th>English Translation</th>
				</tr>
			</thead>

<?php

// Ausgeben des Arrays in Tabelle
if ($array[0][0] > 0) {
    foreach ($array as $field => $row) {
        echo '<tr>';
        echo "<th>{$field}</th>";
        foreach ($row as $value) {
            echo "<td>{$value}</td>";
        }
        echo '</tr>';
    }
} 
else {
    echo "Leider eine leere Datenbank, bitte Vokabeln hinzufügen.";
}
echo '</table> </div>';
?>
	</table>
	</div>


</body>
</html>
