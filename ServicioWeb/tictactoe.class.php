<?php
/*Programa tic-tac-toe*/

class tictactoe {

public $matriz= array(array(" "," "," "),array(" "," "," "),array(" "," "," ")); //Creacion de una matriz 3x3 vacias

public function __construct() {
}

/*tictactoe::obtenery(int numero) Obtiene el indice del eje y de la matriz dado un numero como celular, 3->0 (Refleja fila)*/
public function obtenery($numero)
{
	return floor(($numero-1)/3); 
}

/*tictactoe::obtenerx(int numero) Obtiene el indice del eje x de la matriz dado un numero como celular, 3->2 (Refleja columna)*/
public function obtenerx($numero)
{
	$valorx;
	$modulo = $numero % 3; // 2%3 -> 2
	if ($modulo == 0 ) //Si es multiplo de 3, esta en la posicion 3
	{
		$valorx = 2;
	}
	else 			   //Si no es multiplo, se le debe restar 1
	{
		$valorx = $modulo - 1; 
	}
	return floor($valorx);
}
/*Verifica si el usuario gano o no*/
public function verificarGane($value)
{
	if ($this->ganePorColumna(0,$value) or $this->ganePorColumna(1,$value) or $this->ganePorColumna(2,$value) )
	{
		return true;
	} 
	if ($this->ganePorFila(0,$value) or $this->ganePorFila(1,$value) or $this->ganePorFila(2,$value)) 
	{
		return true;
	} 
	if ($this->ganePorDiagonal($value)) 
	{
		return true;
	} 
	return false; //Si no gano devuelve un false
}

public function ganePorColumna($numFila, $value) //Verifica la lineas horizontales
{
if ($this->matriz[$numFila][0]==$value and $this->matriz[$numFila][1]==$value and $this->matriz[$numFila][2]==$value)
	{
		return true;
	} 
else 
	{
		return false;
	}	
}

public function ganePorFila($numColumna, $value) //Verifica las lineas verticales
{
if ($this->matriz[0][$numColumna]==$value and $this->matriz[1][$numColumna]==$value and $this->matriz[2][$numColumna]==$value)
	{
		return true;
	} 
else 
	{
		return false;
	}	
}

public function ganePorDiagonal($value) //Verifica las diagonales
{
if (($this->matriz[0][0]==$value and $this->matriz[1][1]==$value and $this->matriz[2][2]==$value) or ($this->matriz[0][2]==$value and $this->matriz[1][1]==$value and $this->matriz[2][0]==$value)) //Verifica las dos diagonales
	{
		return true;
	} 
else 
	{
		return false;
	}	
}

//Si es par, devuelve un circulo, si es impar una x
public function getSimbolo($numeroJugada) 
{
	if ($numeroJugada % 2) //Si es impar
	{
		return "X";
	}
	else 
	{
		return "O";
	}
}

//Actualiza la matriz segun los parametros
public function setJugada($numFila, $numColumna,$simbolo) 
{
	if ($simbolo == '_') {
		$this->matriz[$numFila][$numColumna]=' ';
	} else {
		$this->matriz[$numFila][$numColumna]=$simbolo;
	}
}

//Devuelve lo que tiene la matriz en esa posicion
public function getIndiceMatriz($numFila, $numColumna)
{
	return $this->matriz[$numFila][$numColumna];
}


/*Se utiliza para probar el programa, devuelve el resultado*/
public function logicaTicTacToe()
{
	$numJugada=1;
    $simboloActual="X";
	$booleanSeguirJugando=true;
	$jugadas= array(1,2,3,4,5,6,7,8,9); //Creacion de una matriz 3x3 vacias

	
	while ( $numJugada <= 9 && $booleanSeguirJugando) {
		$simboloActual=$this->getSimbolo($numJugada);
		/*El usuario aqui debe modificar la matriz*/
		$valorNumerico=$jugadas[$numJugada-1];
	
		/*Se debe chequear que es valido e insertar*/
		$simPos=$this->getIndiceMatriz($this->obtenery($valorNumerico),$this->obtenerx($valorNumerico));
		if ((strcmp($simPos,"X")!=0) and (strcmp($simPos,"O")!=0))
		{
			$this->setJugada($this->obtenery($valorNumerico),$this->obtenerx($valorNumerico),$simboloActual);
			echo '<p>' . ($simboloActual.": ". strval($this->obtenery($valorNumerico)).",".strval($this->obtenerx($valorNumerico))). "</p>\n";

		}	
		else 
		{
			echo '<p>' ."Ya alguien jugo ahi "."</p>\n";
			//Pide de nuevo un valor numerico
		}

		if ($this->verificarGane($simboloActual)) {
			$booleanSeguirJugando=false;
			return "Gano: ".$simboloActual; //Ya gano un jugador
		}
		$numJugada++;//Aumenta el numero de jugada
	}

	if($booleanSeguirJugando) //Significa que acabaron las jugadas
	{
		return "Empate";
	}

 }
}

/*Prueba instanciandolo*/
//$gato=new tictactoe;
//echo $gato->logicaTicTacToe();

?>
