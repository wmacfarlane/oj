<?hh
$file_name = 'POUR1.java';
$prob_code = 'POUR1';
$lang_id = '10';
// $time_pre = microtime(true);
// $ch = curl_init();
// curl_setopt_array($ch, array(
// 	CURLOPT_COOKIEJAR => '../tmp/spoj_cookie.txt',
// 	CURLOPT_URL => 'http://www.spoj.com/a/', // wrong url because it's the smallest amount of data
// 	CURLOPT_POST => 1,
// 	CURLOPT_POSTFIELDS => 'login_user=ufpt&password=willandemily'
// ));

// ob_start();		// prevent any output
// curl_exec($ch);	// execute the curl command
// ob_end_clean();	// stop preventing output

// curl_close($ch);
// unset($ch);

$upload_file = '../tmp/' . $file_name;
$ch = curl_init();
curl_setopt_array($ch, array(
	CURLOPT_RETURNTRANSFER => 1,
	CURLOPT_COOKIEFILE => '../tmp/spoj_cookie.txt',
	CURLOPT_URL => 'http://www.spoj.com/submit/complete/',
	CURLOPT_POST => 1,
	CURLOPT_POSTFIELDS => array(
		'subm_file'=>"@$upload_file",
		'problemcode'=>$prob_code,
		'lang'=>$lang_id
)));

curl_exec($ch);
$http_code = curl_getinfo($ch ,CURLINFO_HTTP_CODE);
curl_close($ch);
if ($http_code != 200) {
	die("failed problem submission");
}
for ($i = 1; $i <= 20; $i++) {
	sleep(3);
	unset($ch);
	$ch = curl_init();
	curl_setopt_array($ch, array(
		CURLOPT_RETURNTRANSFER => 1,
		CURLOPT_URL => 'http://www.spoj.com/status/',
		CURLOPT_COOKIEFILE => '../tmp/spoj_cookie.txt',
	));
	$time_post = microtime(true);
	$result = curl_exec($ch);
	$http_code = curl_getinfo($ch ,CURLINFO_HTTP_CODE);
	curl_close($ch);
	if ($http_code != 200) {
		die("failed status check");
	}
	// var_dump($result);
	$snipp_start_pos = strpos($result, '/users/ufpt');
	$snippet = substr($result, $snipp_start_pos, 500);
	$done_running = strpos($snippet, 'final="1"');
	if ($done_running) {
		$prefix = 'status="';
		$start = strpos($snippet, $prefix) + strlen($prefix);
		$end = strpos($snippet, '"', $start+1);
		$result = substr($snippet, $start, $end - $start);
		var_dump($i);
		var_dump($result);
		break;
	}
}
// echo '<PRE>' . htmlentities($buf2);
