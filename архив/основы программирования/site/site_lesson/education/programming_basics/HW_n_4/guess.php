<?php
	if (! session_id())
		session_start();
	$root=$_SERVER["DOCUMENT_ROOT"];
	include $root."/header.php";
?>

<div class="content">
	<div class="game">
		<h1>Игра угадай число от 1 до 100 для двух игроков</h1>
		<?php
		function getRandomInt(){
			$_SESSION["count"]=0;
			$_SESSION["randomInt"]=round(rand(0,100));
		}
		if(!isset($_SESSION["count"]) && !isset($_SESSION["randomInt"])){
			getRandomInt();
			$_SESSION["count"]=1;
		}
			$exitText="q";
			$msg = Array(" Игрок, Поздравляю вы угадали загаданное число, по этому ",
			" Игрок, Ваше число было больше загаданного",
			" Игрок, Ваше число было меньше загаданного",
			" Игрок, Ваш ход",
			" Икрок сдался, было загадано число:",
			" загадываю новое");
			if(isset($_GET["uAnswer"])){
				$answer = $_GET["uAnswer"];			
				if($_SESSION["count"]>=1 && $answer!="" ){
					if($answer == $_SESSION["randomInt"]){
						echo "<p>".$_SESSION["count"].$msg[0].$msg[5]."</p>";
						getRandomInt();
					}else if($answer == $exitText){
						echo "<p>".$_SESSION["count"].$msg[4].$_SESSION["randomInt"]."<br>".$msg[5]."</p>";
						getRandomInt();
					}else if($answer > $_SESSION["randomInt"]){
						echo "<p>".$_SESSION["count"].$msg[1]."</p>";
					}else{
						echo "<p>".$_SESSION["count"].$msg[2]."</p>";
					}
					if ($_SESSION["count"]>=2){
						$_SESSION["count"]--;
					}else{
						$_SESSION["count"]++;
					}
				}
			}
			echo "<p>".($_SESSION["count"]).$msg[3]."</p>";
		?>
		
		<form method="GET">
			<input type="text" name="uAnswer">
			<br>
			<input type="submit" value="Проверить">
		</form>
		</p>для того что бы сдаться нажмите "q"</p>
	</div>
</div>
<?php
	include $root."/footer.php";
?>