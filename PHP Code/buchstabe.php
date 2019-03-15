<?php
// Cookie auslesen und in Variable speichern
$cookie = $_COOKIE["benutzername"];

?>


<html>
<head>
<!-- Import -->
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
<!-- Navbar -->
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">English For Pros</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a
					href="http://localhost:8080/lernprogrammjsp/homelogin.jsp?eingeloggt=ja">Home</a></li>
				<li class="active"><a href="buchstabe.php">Buchstaben lernen</a></li>
				<li><a href="vokabeln.php">Vokabeln lernen</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#"><span class="glyphicon glyphicon-user"></span> Eingeloggt als: <?php echo $cookie?>   </a></li>
				<li><a
					href="http://localhost:8080/lernprogrammjsp/startseite.jsp?eingeloggt=logout"><span
						class="glyphicon glyphicon-log-in"></span> Logout</a></li>
			</ul>
		</div>
	</nav>

<!-- Oberfläche für die Auswahl des Buchstabens -->
	<div class="container-fluid bg-1 text-center ">
		<table class="table table-hover ">
			<thead>
				<tr>
					<th>Button Zeichen</th>
					<th>Button Zeichen</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><a
						href="http://localhost:8080/lernprogrammjsp/buchstabezeichnen.jsp?bild=Agross.svg"
						class="btn btn-info button2" role="button"><span>"A" zeichnen</span></a></td>
					<td><a
						href="http://localhost:8080/lernprogrammjsp/buchstabezeichnen.jsp?bild=aklein.svg"
						class="btn btn-info button2" role="button"><span>"a" zeichnen</span></a></td>


				</tr>
				<tr>
					<td><a
						href="http://localhost:8080/lernprogrammjsp/buchstabezeichnen.jsp?bild=ygross.svg"
						class="btn btn-info button2" role="button"><span>"Y" zeichnen</span></a></td>
					<td><a
						href="http://localhost:8080/lernprogrammjsp/buchstabezeichnen.jsp?bild=yklein.svg"
						class="btn btn-info button2" role="button"><span>"y" zeichnen</span></a></td>
				</tr>
			</tbody>
		</table>
	</div>



	<div class="col-sm-12 container-fluid bg-2 text-center">
		<h1>Freies Zeichnen:</h1>
		<a
			href="http://localhost:8080/lernprogrammjsp/buchstabezeichnen.jsp?bild=leer.svg"
			class="btn btn-info button2" role="button"><span>Click Me!</span></a>
	</div>


</body>
</html>
