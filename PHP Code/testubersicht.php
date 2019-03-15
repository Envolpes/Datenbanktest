<?php
        // Cookie auslesen und in Variable speichern
$cookie = $_COOKIE["benutzername"];
$user = $cookie;
        // DATENBANKZUGRIFF
$pdo = new PDO('mysql:host=localhost;dbname=mydb', 'root', '');

        // DATENBANK in ARRAY einlesen
$zahler1 = 0;
$zahler2 = 0;

$array;
$array[0][0]="leere Datenbank";

$sql = "SELECT * FROM `testergebnisse` where user='" . $user . "'";
foreach ($pdo->query($sql) as $row) {
    $array[$zahler1][$zahler2] = $row['anzahl'];
    $zahler2 ++;
    $array[$zahler1][$zahler2] = $row['richtige'];
    $zahler1 ++;
    $zahler2 = 0;
}

?>
		<!-- HEAD mit IMPORTS -->
<html>
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
		<!-- NAVLEISTE -->
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
	
	
		<!-- HEADER TABLE -->
	<div class="container-fluid bg-1 text-center">
		<b>Testübersicht</b>
		<br>
		<table class="table table-hover">


			<thead>
				<tr>
					<th>Anzahl Tests</th>
					<th>Anzahl beantworteter Vokabeln</th>
					<th>Davon richtig beantwortet</th>
				</tr>
			</thead>

<?php

        // Ausgeben der Testergebnisse



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
else{
    echo "Leider eine leere Datenbank, bitte Tests machen.";
    
}
echo '</table> </div>';

?>
	</table>
	</div>