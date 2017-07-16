<?php

$list = array();
$con = mysqli_connect('localhost', 'root', '', 'test');
$Query = "Select * from question ORDER BY RAND() LIMIT 5";
$Result = mysqli_query($con, $Query);
if($Result->num_rows > 0){
    while ($row = $Result->fetch_assoc()) {
        $obj = array();
        $obj['Question'] = $row['Question'];
        $obj['Option1'] = $row['Option1'];
        $obj['Option2'] = $row['Option2'];
        $obj['Option3'] = $row['Option3'];
        $obj['Option4'] = $row['Option4'];
        $obj['Answer'] = $row['Answer'];
        array_push($list, $obj);
    }
}
echo json_encode($list);

?>
