<?php
  if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $login = $_POST['login'];
    $password = $_POST['password'];

    // Faire le traitement souhaité avec le login et le mot de passe
    // par exemple, vérifier les informations dans une base de données

    // Exemple de réponse :
    if ($login === 'nafy' && $password === 'avoir') {

      $message = 'ConnexionReussie';

    } else {

      $message = 'invalide';
      
    }
   
    echo $message;
  }
 
?>

