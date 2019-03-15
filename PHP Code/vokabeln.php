<?php
// Cookie auslesen und in Variable speichern
$cookie = $_COOKIE["benutzername"];
?>

<html>
<head>
<!-- IMPORT -->
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

<!-- NAVBAR -->
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


	<!-- Aufteilung zur Auswahl -->
	<div class="row container-fluid bg-2 text-center">
		<div class="col-sm-4">
			<b> Vokabeln lernen:</b> <a href="vokabeltest.php?a=0&r=0"
				class="btn btn-info btn-block button2" role="button"><span>Click Me!</span></a>
			<br>
		</div>

		<div class="col-sm-4">
			<b> Vokabeltestübersicht: </b> <a href="testubersicht.php"
				class="btn btn-info btn-block button2" role="button"><span>Click Me!</span></a>
			<br>
		</div>

		<div class="col-sm-4">
			<b> Vokabeln hinzufügen: </b> <a href="vokabelhinzufugen.php"
				class="btn btn-info btn-block button2" role="button"><span>Click Me!</span></a>
			<br>
		</div>





	</div>
</body>

</html>
