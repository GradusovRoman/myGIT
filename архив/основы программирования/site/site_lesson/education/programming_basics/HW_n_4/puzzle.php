<?php
	$root=$_SERVER["DOCUMENT_ROOT"];
	include $root."/header.php";
?>

<div class="content">
	<div class="game">
		<h1>Игра в загадки</h1>
		<?php
			$msg = Array(
			"Сколько на берёзе яблок, если у нее восемь веток, на каждой из них по 5 яблок?",
			"Сидели на дереве шесть глухарей. Пришел охотник и убил одного глухаря. Сколько птиц осталось?",
			"Что нельзя съесть на завтрак?",
			"Что станет с красным шёлковым платком, если опустить его на 5 минут на дно моря?"
			)
		?>
		
		<p><?php echo $msg[0]?></p>
		<input type="text" id="uAnswer0">
		<p><?php echo $msg[1]?></p>
		<input type="text" id="uAnswer1">
		<p><?php echo $msg[2]?></p>
		<input type="text" id="uAnswer2">
		<p><?php echo $msg[3]?></p>
		<input type="text" id="uAnswer3">
		<br>
		
		<a href="#" onClick="onClick();">Проверить</а>
		
		<script type="text/javascript">
			var answer=[
			["0","ни сколько","на березе не растут яблоки","ни одного"],
			["0","ноль","все улетят","ни одного"],
			["ужин","обед","полдник","ланч"],
			["будет мокрым","намокнет"]
			];
			
			function onClick(){
				var score=0;
				for (var count = 0 ; count < answer.length ; count ++){
					var uAnswer = document.getElementById("uAnswer"+count).value;
					if (compare(uAnswer,answer[count])){
						score++;
					}
				}
				alert("вы угадали " + score + " загадок(у)");
			}
			
			function compare(uAnswer, trueAnswer){
				for (var count = 0 ; count < trueAnswer.length ; count++){
					if (trueAnswer[count] == uAnswer){
						return true;
					}
				}
				return false;
			}
		</script>
		
	</div>
</div>

<?php
	include $root."/footer.php";
?>