import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import Error.*;

public class Main
{
	public static void main(String[] args) throws ErroreFileVuoto
	{
		System.out.println("\nPROGETTO PER LA PROVA ORALE DI PROGRAMMAZIONE DI TRIONFETTI NICO\n");
		Magazzino magazzino = new Magazzino();
		if(magazzino._oggettoMagazzino.size()>0)
		{
			System.out.println("FILE EMPORIO CARICATO CON SUCCESSO");
		}
		else
		{
			System.out.println("\nIl File Emporio e'vuoto");
		}
        Scanner input = new Scanner(System.in);
        int Scelta = 0;
        do 
        {
            boolean errore = false;
            System.out.println("\n Scegli l'azione da svoglere :\n");
            System.out.println("1 calcolo del valore del magazzino");
            System.out.println("2 vendita di un prodotto");
            System.out.println("3 inserimento di un prodotto");
            System.out.println("4 ricerca di un prodotto mediante codice univoco");
            System.out.println("5 Stampa i prodotti che sono inseriti nel file");
            System.out.println("0 Termina il programma");
            try 
            {
                Scelta = Integer.parseInt(input.nextLine());
            } catch (Exception e) 
            {
                System.out.println("\n comando non valido! \n");
                errore = true;
            }
            if (!errore) 
            {
            	try
            	{
	                switch(Scelta)
	                {
		                case 1: 
		                	System.out.println("\nil valore totale del magazzino e` : " + magazzino.CalcoloValore() + " euro ");
	                        System.out.println("\nPremere invio per procedere");
	                        input.nextLine();
	                        break;
		                case 2:  
		                	if(magazzino._oggettoMagazzino.size()>0)
		                	{
			                	magazzino.StampaOggetti();
		                        String _codiceUnivoco;
		                        System.out.print("\nInserisci il codice univoco del prodotto da vendere :\n");
		                        _codiceUnivoco = input.nextLine().toString();
		                        int _vendita = magazzino.VenditaProdotto(_codiceUnivoco);
		                        if(_vendita==-3)
		                        {
		                        	System.out.println("il prodotto non e' inserito nel file EMPORIO.txt");
		                        }
		                        else
		                        {
			                        if (_vendita==-2) 
			                        {
			                            System.out.println("\nIl prodotto e'stato venduto correttamente ");
			                            File _file = new File();
			                           _file.modificaFile(magazzino._oggettoMagazzino,magazzino._oggettoAbbigliamento,magazzino._oggettoAlimento,magazzino._oggettoBevanda,magazzino._oggettoLegno,magazzino._oggettoLibro);
			                        } 
			                        else 
			                        {
			                        	if(_vendita==-1)
			                        	{
			                            System.out.println("\nIl prodotto non e'stato venduto");
			                        	}
			                        	else 
			                        	{
			                        		File _file = new File();
			                        		_file._eliminaRigaDaFile(magazzino._oggettoMagazzino,magazzino._oggettoAbbigliamento,magazzino._oggettoAlimento,magazzino._oggettoBevanda,magazzino._oggettoLegno,magazzino._oggettoLibro,_vendita);
				                            System.out.println("\nIl prodotto e'stato venduto correttamente ");
			                        	}
			                        }
		                        }
		                	}
		                	else
		                		throw new ErroreFileVuoto();
		                	System.out.println("\nPremere invio per procedere");
	                        input.nextLine();
	                        break;
		                case 3:
	                        magazzino.InserimentoProdotto();
	                        System.out.println("\nPremere invio per procedere");
	                        input.nextLine();
	                        break;
		                case 4:
		                	if(magazzino._oggettoMagazzino.size()!=0)
		                	{
		                		magazzino.RicercaProdotto();
		                	}
		                	else
                				throw new ErroreFileVuoto();
		                	System.out.println("\nPremere invio per procedere");
	                        input.nextLine();
		                	break;
		               
		                case 5:
		                	if(magazzino._oggettoMagazzino.size()!=0)
		                	{
			                	magazzino.StampaOggetti();
		                	}
		                	else
                				throw new ErroreFileVuoto();
		                	System.out.println("\nPremere invio per procedere");
	                        input.nextLine();
		                	break;
		                case 0:
		                	break;
	                    default: 
	                        System.out.println("\n comando non valido! \n");
	                        System.out.println("\nPremere invio per procedere");
	                        input.nextLine();
	                        break;   
	                }
            	}
            	catch(ErroreFileVuoto e)
                {
                	System.out.println(e.getMessage());
                	System.out.println("\nPremere invio per procedere");
                    input.nextLine();
                }
            }
        } while (Scelta != 0);
        System.out.println("\n\n\n\n\n\n\n   FINE \n\n\n\n");
	}
}



