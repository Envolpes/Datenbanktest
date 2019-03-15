<?php
// Cookie auslesen und in Variable speichern
$cookie = $_COOKIE["benutzername"];

// Datenbankzugriff
$pdo = new PDO('mysql:host=localhost;dbname=mydb', 'root', '');

// Initalisierung Array
$zahler1 = 0;
$zahler2 = 0;

$array;
// DATENBANK in ARRAY einlesen
$sql = "SELECT de,eng FROM vokabeln";
foreach ($pdo->query($sql) as $row) {
    $array[$zahler1][$zahler2] = $row['de'];
    $zahler2 ++;
    $array[$zahler1][$zahler2] = $row['eng'];
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

<?php
// Zufällig Vokabelauswahl und GET Methode für Anzahl Vokabeln und richtiger

$voknummer = rand(0, $zahler1 - 1);
$vokabeleng = $array[$voknummer][1];

$richtige = $_GET["r"];
$vokabeln = $_GET["a"];

?>

		
		<!-- Platzhalter oben leer -->

	<div class="container-fluid bg-2 text-center ">
		<div class="row">


			<div class="col-sm-12">
				<input id="vokabelUrsprung" type="hidden" name="platzhalter"
					value=<?php echo $vokabeln?>>

			</div>
		</div>
	</div>

	<!-- Vokabeltest -->

	<div class="container-fluid bg-2 text-center ">
		<form action="http://localhost/vokabfrage.php" method="POST">
			<div class="row">
				<div class="col-sm-2 Vokabelelement2">
					<b> Englische Bedeutung </b> <br>
					<p id="vokabelAbfrage" type="text" name="egal"><?php echo $vokabeleng?></p>
				</div>

				<div class="col-sm-8 Vokabelelement2">
					<b> Deine Übersetzung </b> <br> <input id="vokabelÜbersetzung"
						type="text" name="vokde" placeholder="Übersetzung eingeben"
						style="color: #000000;" required></input>
				</div>

				<div class="col-sm-2 Vokabelelement2">
					<b> Abgabe einzelner Vokabel: </b> <br>
					<button id="vokabelBestätigen">OK</button>
				</div>

				<div class="col-sm-6 Vokabelelement2">
					<b> Anzahl beantworteter Vokabeln </b> <br>
					<p id="vokabelAbfrage" type="text" name="beantwortet"><?php echo $vokabeln?></p>
				</div>

				<div class="col-sm-6">
					<b> Anzahl richtig beantworteter Vokabeln </b> <br>
					<p id="vokabelAbfrage" type="text" name="richtig"><?php echo $richtige?></p>
				</div>

				<div class="col-sm-0">
					<input id="vokabelUrsprung" type="hidden" name="vokeng"
						value=<?php echo $vokabeleng?>>
				</div>
				<div class="col-sm-0">
					<input id="vokabelUrsprung" type="hidden" name="beantwortet"
						value=<?php echo $vokabeln?>>
				</div>
				<div class="col-sm-0">
					<input id="vokabelUrsprung" type="hidden" name="richtig"
						value=<?php echo $richtige?>>
				</div>

			</div>
		</form>
	</div>

	<!-- versteckte Felder zur Übergabe per Post an Vokabgabe -->

	<div class="container-fluid bg-2 text-center ">
		<form action="http://localhost/vokabgabe.php" method="POST">
			<div class="row">


				<div class="col-sm-0">
					<input id="vokabelUrsprung" type="hidden" name="beantwortet"
						value=<?php echo $vokabeln?>>
				</div>

				<div class="col-sm-0">
					<input id="vokabelUrsprung" type="hidden" name="richtig"
						value=<?php echo $richtige?>>
				</div>

				<div class="col-sm-0">
					<input id="vokabelUrsprung" type="hidden" name="user"
						value=<?php echo $cookie?>>
				</div>


				<div class="col-sm-12">
					<b> Abgabe Test: </b> <br>
					<button id="vokabelBestätigen">OK</button>
				</div>


			</div>
		</form>
	</div>


</body>
</html>
