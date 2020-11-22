package Error;

public class ErroreFileVuoto extends Exception {
	
	public ErroreFileVuoto()
	{
		super("\nimpossibile eseguire questa operazione visto che il file Emporio e' vuoto");
	}

}
