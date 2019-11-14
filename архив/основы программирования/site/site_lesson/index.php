<?php
	$root=$_SERVER["DOCUMENT_ROOT"];
	include $root."/header.php";
?>

<div class="content">

	<h1>Личный сайт студента GeekBrains</h1>
	
	<?php 
		include $root."/my_decription.php";
	?>
	
</div>

<?php
	include $root."/footer.php";
?>