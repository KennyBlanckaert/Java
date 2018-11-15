<!DOCTYPE HTML>
<?php
DEFINE ('DB_USER', 'Kenny');
DEFINE ('DB_PASSWORD', 'Azerty123');
DEFINE ('DB_HOST', 'localhost');
DEFINE ('DB_NAME', 'School');

$conn = new mysqli(DB_HOST, DB_USER, DB_PASSWORD, DB_NAME);

$query = "SELECT * FROM teachers";
$result = $conn->query($query);
?>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Teacher from the database">
        <meta name="author" content="Kenny Blanckaert">
        <title>Online Courses</title>

        <!-- css -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
        <link href='css/dosis-font.css' rel='stylesheet' type='text/css'>
    </head>

    <body id="page-top" data-spy="scroll" data-target=".side-menu">
        <div>
            <!-- Teachers -->
            <div class="row me-row content-ct speaker" id="speakers">
              <h2 class="row-title">Meet the Teachers</h2>
                <?php
              
                if ($result->num_rows > 0) {
                    while($row = $result->fetch_assoc()) {
                        echo '<div class="col-md-4 col-sm-6 feature">';
                        echo '<img src="img/' . $row["image"] . '" class="speaker-img">';
                        echo '<h3>' . $row["firstname"] . " " . $row["lastname"] . '</h3>';
                        echo '<p>' . $row["description"] . '</p>';
                        echo '</div>';
                    }
                }
         
                ?>
            </div>
        </div>
    </body>  
</html>