<?php
//declare(encoding='UTF-8');
//declare(strict_types=1);
namespace foo;
//phpinfo();
?>
<html>
<head>
    <title>PHP 测试</title>
</head>
<body>
<?php
/* HTML */
//$text = "<>";
//echo '<a href="'.htmlspecialchars('http://foo.com?a='.urlencode($text)).'">'.htmlspecialchars($text)."</a>";

/* References */
//class A {
//    public $a = 1;
//}
//$x=new A;
//$y=$x;
//$z=&$x;
//$x->a+=1;
//echo $y->a;
//echo $z->a;
//
//$x=new A;
//echo $y->a;
//echo $z->a;

/* Class */
//class A {
//    public $a = 0;
//};
//
//class B extends A {
//    public $a = 1;
//}
//
//echo (new A)->a;
//echo (new B)->a;

//class A {
//    static $a = 0;
//
//    static function foo() {
//        static $b = 0;
//        $b += 1;
//        print $b;
//    }
//}
//print A::$a;
//A::foo();
//A::foo();

/* Functions */
function sum() {
    $acc = 0;
    foreach (func_get_args() as $n) {
        $acc += $n;
    }
    return $acc;
}

echo sum(1, 1);

//$f = function($name){
//    return function() use($name){
//      print $name;
//    };
//};
//$f2 = $f("n1");
//$f2();

/* Expression */
//@$a = 1/0;
//echo $php_errormsg;
//echo `ls`

/* Variables */
//$a = 1;
//$b = 2;
//function Sum()
//{
//    global $a;
//    $GLOBALS['b'] = $a + $GLOBALS['b'];
//}
//Sum();
//echo $b;
//$a = 'hello';
//$$a = 'world';
//echo $hello;

//echo __LINE__;
//echo __FILE__;
//echo __DIR__;
//echo __CLASS__;
//echo __METHOD__;
//echo __FUNCTION__;
//echo __NAMESPACE__;
//echo __TRAIT__;

//echo $php_errormsg;

/* Array */
//$arr1 = array(2, 3);
//$arr2 = $arr1;
//$arr2[] = 4; // $arr2 is changed,$arr1 is still array(2, 3)
//$arr3 = &$arr1;
//$arr3[] = 5; // now $arr1 and $arr3 are the same
//var_dump($arr1);
//var_dump($arr2);
//var_dump($arr3);
//$cars = array
//(
//    "abc"=>array("Volvo",1,22,18),
//    array("BMW",15,13),
//    array("Saab",5,2),
//    array("Land Rover",17,15)
//);
//echo "{$cars['abc'][0]}";
//$v=$cars['abc'][0];
//echo $v;
//print_r($cars);
//$a = array("a" => "apple", "b" => "banana");
//$b = array("a" => "pear", "b" => "strawberry", "c" => "cherry");
//$c = $a + $b; // Union of $a and $b
//var_dump($c);
//$c = $a - $b; // Error
//var_dump($c);

/* String */
//$text = '测试';
//echo strlen($text);
//for ($i = 0; $i < strlen($text); $i++) {
//    echo $text[$i];
//}

/* General */
//foreach ($_SERVER as $k=>$v) {
//  echo $k."=".$v."<br>";
//}
//echo $_SERVER['PHP_SELF'];
?>
<? //=var_dump($_SERVER)?>
<? //='测\n试'?>
<? //="测\n试"?>
</body>
</html>