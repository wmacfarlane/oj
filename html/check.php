<?hh

$a = htmlentities('"/users/ufpt/" title="ufpt">William Macfarlane</a></td><td><a href="/problems/TEST/" title="TEST">Life, the Universe, and Everything</a></td><td class="statusres" id="statusres_13755299" status="14" final="1" manual="0"> <a href="/error/13755299" title="View details."> wrong answer </a> <br/> <a href="/su"');
$b = strpos($a, 'final="1"');
var_dump($a);
var_dump($b);
echo $b;
