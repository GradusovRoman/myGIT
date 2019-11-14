<?php
	$root=$_SERVER["DOCUMENT_ROOT"];
	include $root."/header.php";
?>

<div class="content">
	<div class="game">
		<h1>генератор паролей</h1>
		<p>введите длинну требуемого пароля для генерации</p>
		
			<input type="text" id="passLength">
			<br>
			<a href="#" onClick="onClick();">Сгенерировать</a>
			<br>
			<div id="password">
			</div>
			<script type="text/javascript">
			
				var passArrChar = "01234567890qqwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
				function onClick(){
					var passLen = parseInt(document.getElementById("passLength").value);
					if (passLen > 0){
							document.getElementById("password").innerHTML = "<p>Ваш пароль: " + genPassword(passArrChar,passLen) + "</p>";
							
					}
				}
				
				function genPassword(passArr,passLen){
					var seed = Math.round(Math.random()*(passArr.length-1));
					var passChar = passArr[seed];
					if(passLen > 1){
						return passChar + genPassword(passArr,passLen-1);
					}else{
						return passChar;
					}
				}
				
			</script>
	</div>
</div>

<?php
	include $root."/footer.php";
?>