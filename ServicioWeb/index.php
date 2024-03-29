<?php

require_once 'tictactoe.class.php';

function autoinclude($className) {
	$className = str_replace('\\', '/', $className) . '.php';
	require_once $className;
}

spl_autoload_register('autoinclude');

if (isset($_GET['wsdl'])) {
	header('Content-Type: application/soap+xml; charset=utf-8');
	echo file_get_contents('tictactoe.wsdl');
}
else {
	session_start();

	if (!isset($_SESSION['service'])) {
		$_SESSION['service'] = new tictactoe();
	}
	$servidorSoap = new SoapServer('http://titanic.ecci.ucr.ac.cr:80/~eb51053/tictactoeServiceDocumentLiteral/?wsdl');

	//Para evitar la excepción por defecto de SOAP PHP cuando no existe HTTP_RAW_POST_DATA,
	//se regresa explícitamente el siguiente fallo cuando no hay solicitud (v.b. desde un navegador)
	if(!@$HTTP_RAW_POST_DATA){
		$servidorSoap->fault('SOAP-ENV:Client', 'Invalid Request');
		exit;
	}

	$servidorSoap->setObject(new Zend\Soap\Server\DocumentLiteralWrapper($_SESSION['service']));
	$servidorSoap->setPersistence(SOAP_PERSISTENCE_SESSION);
	$servidorSoap->handle();
}

?>