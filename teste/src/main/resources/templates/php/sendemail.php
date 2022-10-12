<?php
if (isset($_POST['email'])) {

    function died($error) {
        // your error code can go here
        echo "We are very sorry, but there were error(s) found with the form you submitted. ";
        echo "These errors appear below.<br /><br />";
        echo $error . "<br /><br />";
        echo "Please go back and fix these errors.<br /><br />";
        die();
    }

//                !isset($_POST['last_name']) ||
    // validation expected data exists
    if (!isset($_POST['nome']) ||
            !isset($_POST['email']) ||
            !isset($_POST['telefone']) ||
            !isset($_POST['mensagem'])) {
        died('We are sorry, but there appears to be a problem with the form you submitted.');
    }

    $nome = $_POST['nome']; // required
//    $last_name = $_POST['last_name']; // required
    $email_from = $_POST['email']; // required
    $telefone = $_POST['telefone']; // not required
    $mensagem = $_POST['mensagem']; // required
    // EDIT THE 2 LINES BELOW AS REQUIRED
    $email_to = "contato@valkcastellani.com.br";
    $email_subject = $nome . " -> Contato no site valkcastellani.com.br";

    $error_message = "";
    $email_exp = '/^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}$/';

    if (!preg_match($email_exp, $email_from)) {
        $error_message .= 'The Email Address you entered does not appear to be valid.<br />';
    }

    $string_exp = "/^[A-Za-z .'-]+$/";

    if (!preg_match($string_exp, $nome)) {
        $error_message .= 'The First Name you entered does not appear to be valid.<br />';
    }

    //if (!preg_match($string_exp, $last_name)) {
    //    $error_message .= 'The Last Name you entered does not appear to be valid.<br />';
    //}

    if (strlen($mensagem) < 2) {
        $error_message .= 'The Comments you entered do not appear to be valid.<br />';
    }

    if (strlen($error_message) > 0) {
        died($error_message);
    }

    $email_message = "Form details below.\n\n";

    function clean_string($string) {
        $bad = array("content-type", "bcc:", "to:", "cc:", "href");
        return str_replace($bad, "", $string);
    }

    $email_message .= "First Name: " . clean_string($nome) . "\n";
    //$email_message .= "Last Name: " . clean_string($last_name) . "\n";
    $email_message .= "Email: " . clean_string($email_from) . "\n";
    $email_message .= "Telephone: " . clean_string($telefone) . "\n";
    $email_message .= "Comments: " . clean_string($mensagem) . "\n";

// create email headers
    $headers = 'From: ' . $email_from . "\r\n" .
            'Reply-To: ' . $email_from . "\r\n" .
            'X-Mailer: PHP/' . phpversion();
    @mail($email_to, $email_subject, $email_message, $headers);
}
?>



//<?php
//function pegaValor($valor) {
//    return isset($_POST[$valor]) ? $_POST[$valor] : '';
//}
//
////function validaEmail($email) {
////    return filter_var($email, FILTER_VALIDATE_EMAIL);
////}
////
////function enviaEmail($de, $assunto, $mensagem, $para, $email_servidor) {
////    $headers = "From: $email_servidor\r\n" .
////            "Reply-To: $de\r\n" .
////            "X-Mailer: PHP/" . phpversion() . "\r\n";
////    $headers .= "MIME-Version: 1.0\r\n";
////    $headers .= "Content-Type: text/html; charset=ISO-8859-1\r\n";
////
////    mail($para, $assunto, nl2br($mensagem), $headers);
////}
//
//$email_servidor = "contato@valkcastellani.com.br";
//$para = "contato@valkcastellani.com.br";
//$de = $_REQUEST['email'];
//$nome = pegaValor("nome");
//$mensagem = pegaValor("msg");
//$assunto = pegaValor("assunto");
////Monta o Corpo da Mensagem
////====================================================
//$mensagem = "Nome = " + pegaValor("nome") + " \n";
//$mensagem .= "Email = " + pegaValor("email") + " \n";
//$mensagem .= "Assunto = " + pegaValor("assunto") + " \n";
//$mensagem .= "Mensagem = " + pegaValor("msg") + " \n";
////====================================================
//
////if ($nome && validaEmail($de) && $mensagem) {
//    echo "<h1>1->$de</h1>";
//    echo "<h1>2->$assunto</h1>";
//    echo "<h1>3->$nome</h1>";
//    echo "<h1>4->$mensagem</h1>";
////    enviaEmail($de, $assunto, $mensagem, $para, $email_servidor);
////    $pagina = "mail_ok.php";
////} else {
////    $pagina = "mail_error.php";
////}
//$pagina = "../index.php";
//
////header("location:$pagina");
//?>
