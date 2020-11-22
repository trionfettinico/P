import java.util.*;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import Error.*;

public class Magazzino 
{
	String _reparto;
	String _tipoVendita;
	double _quantita;
	double _prezzo;
	String _codiceUnivoco;
	String _nome;
	String _descrizione;
	String _taglia;
	String _colore;
	Date _data;
	String _marca;
	String _dimensione;
	String _autore;
	String _casaEditrice;
	String _tipoLegno;
	Date _dataPubblicazione;
	ArrayList<Oggetto> _oggettoMagazzino = new ArrayList<Oggetto>();
	ArrayList<OggettoLegno> _oggettoLegno = new ArrayList<OggettoLegno>();
	ArrayList<OggettoAbbigliamento> _oggettoAbbigliamento = new ArrayList<OggettoAbbigliamento>();
	ArrayList<OggettoAlimento> _oggettoAlimento = new ArrayList<OggettoAlimento>();
	ArrayList<OggettoBevanda> _oggettoBevanda = new ArrayList<OggettoBevanda>();
	ArrayList<OggettoLibro> _oggettoLibro = new ArrayList<OggettoLibro>();
	String _appoggio;
	Scanner _input = new Scanner(System.in);
	String _scelta;
    DateFormat _df = new SimpleDateFormat("dd/MM/yyyy");
	File _file = new File();
	
	public Magazzino()
	{
		Date _date;
		BufferedReader _br = new BufferedReader(_file._letturaFile());
		try
		{	
			int _indice = 1;
			while(true) 
			{
				_appoggio = _br.readLine();
				if(_appoggio==null)
			    break;
				String[] _split; 
				_split = _appoggio.split(";");
					if(ControlloInserimentoDaFile(_split,_indice))
					{
					switch(Enum.valueOf(Reparto.class,_split[0])) 
					{
					 case AB: //Abbigliamento	 
							 OggettoAbbigliamento Ab = new OggettoAbbigliamento(Enum.valueOf(Unita.class, _split[1]), Double.parseDouble(_split[2]),Double.parseDouble(_split[3]) , _split[4],_split[5] , _split[6], _split[7], Enum.valueOf(Taglia.class, _split[8]), _split[9]);
							 _oggettoMagazzino.add(Ab);
							 _oggettoAbbigliamento.add(Ab);
						 	 break;
					 case AL: //alimenti
						 _date = _df.parse(_split[7]);
						 OggettoAlimento A = new OggettoAlimento(Enum.valueOf(Unita.class,_split[1]),Double.parseDouble(_split[2]), Double.parseDouble(_split[3]), _split[4], _split[5],_split[6], _date);
						 _oggettoMagazzino.add(A);
						 _oggettoAlimento.add(A); 
						 break;
					 case B:  //bevande
						 _date = _df.parse(_split[7]);
						 OggettoBevanda b = new OggettoBevanda(Enum.valueOf(Unita.class,_split[1]),Double.parseDouble(_split[2]), Double.parseDouble(_split[3]), _split[4], _split[5],_split[6], _date);
						 _oggettoMagazzino.add(b);
						 _oggettoBevanda.add(b);
						 break;
					 case LE: //Legno
						 OggettoLegno Le = new OggettoLegno(Enum.valueOf(Unita.class,_split[1]),Double.parseDouble(_split[2]), Double.parseDouble(_split[3]), _split[4], _split[5],_split[6], _split[7], _split[8]);
						 _oggettoMagazzino.add(Le);
						 _oggettoLegno.add(Le);
						 break;
					 case LI: //Libreria
						 _date = _df.parse(_split[9]);
						 OggettoLibro Li = new OggettoLibro(Enum.valueOf(Unita.class, _split[1]),Double.parseDouble(_split[2]), Double.parseDouble(_split[3]) , _split[4], _split[5], _split[6], _split[7], _split[8], _date);
						 _oggettoMagazzino.add(Li);
						 _oggettoLibro.add(Li);
						 break;
					}
					_indice++;
					}
		    }
		}
		catch (Exception e)
		{
			System.out.println("Errore nella lettura del file");
		}
	}

	public double CalcoloValore()
	{
		double valore=0;
		for(int i=0;i<_oggettoMagazzino.size();i++)
		{
			valore += _oggettoMagazzino.get(i).getPrezzo()*_oggettoMagazzino.get(i).getQuantita();
		}

		return valore;
	}
	
	public int VenditaProdotto(String _codiceUnivoco)
	{
		boolean _venduto = true;
		for(int i=0;i<_oggettoMagazzino.size();i++)
		{
			if(_oggettoMagazzino.get(i).getCodiceUnivoco().equals(_codiceUnivoco))
			{
				do
				{
					double _quantitaDaVendere=0;
					_venduto = true;
					if(_oggettoMagazzino.get(i).getTipoVendita().equals(Enum.valueOf(Unita.class, "U")))
					{
						String _app;
						do
						{
							Double _appQuantita= _oggettoMagazzino.get(i).getQuantita();
							System.out.println("il prodotto con codice univoco "+_codiceUnivoco+" e'presente nel magazzino con disponibilita "+_appQuantita.intValue()+"\n quantita da vendere : ");
							_app = _input.nextLine();
						}while(!ControlloInt(_app));
						_quantitaDaVendere = Integer.parseInt(_app);
					}
					else
					{
						System.out.println("il prodotto con codice univoco "+_codiceUnivoco+" e'presente nel magazzino con disponibilita "+_oggettoMagazzino.get(i).getQuantita()+"\n quantita da vendere : ");
						_quantitaDaVendere = Double.parseDouble((_input.nextLine().replace(",", ".")));
					}
					if(_quantitaDaVendere>0)
					{
						if( _oggettoMagazzino.get(i).getQuantita()>=_quantitaDaVendere)
						{
							try
							{
								if(_oggettoMagazzino.get(i).getTipoVendita().equals(Enum.valueOf(Unita.class, "U")))
								{
									Double _app = _oggettoMagazzino.get(i).getQuantita() - _quantitaDaVendere;
									_oggettoMagazzino.get(i).setQuantita(_app.intValue());
								}
								else 
								{
									_oggettoMagazzino.get(i).setQuantita(_oggettoMagazzino.get(i).getQuantita() - _quantitaDaVendere);
								}
							}
							catch(Exception e)
							{
								System.out.println(e);
							}
							
							if(Double.compare(_oggettoMagazzino.get(i).getQuantita(), 0)==0)
							{
								return i;
							}
							return -2;
						}
						else
						{
							do
							{
								System.out.println("il prodotto non e'disponibile nella quantita'richiesta");
								System.out.println("\nScegliere una nuova azione da svolgere \n");
								System.out.println("1) inserire un'altra quantita \n2) torna al menu principale \n");
								_scelta = _input.nextLine();
								switch (_scelta) 
								{
									case "1":
										_venduto = false;
										break;
									case "2":
										return -1;
								default:
										System.out.println("\n valore non valido \n");
										break;
								}
							}while(_scelta == "2");
						}
					}
					else
					{
						System.out.println("la quantita da vendere non puo'essere minore uguale a 0");
					}
				}while(!_venduto);
				return -1;
			}
		}
		return -3;
	}
	
	public void InserimentoProdotto()
	{
		String _appoggio;
		
		do
		{
			System.out.println("\nInserisci il codice univoco del prodotto da inserire : \n(il codice univico deve essere di 8 numeri e non puo contenere lettere e caratteri speciali)");
			_appoggio = _input.nextLine();
		}while(!ControlloCodiceUnivoco(_appoggio));
		_codiceUnivoco = _appoggio;
		
		do
		{
			System.out.println("inserisci il codice relativo al reparto :   \ninserire : AB = Abbigliamento  AL = Alimento  B = Bevanda  LE = Legno  LI = Libreria ");
			_appoggio = _input.nextLine();
		}while(!ControlloReparto(_appoggio));
		_reparto = _appoggio.toUpperCase();		
		
		do
		{
			System.out.println("inserisci il nome :");
			_appoggio = _input.nextLine();
		}while(_appoggio.trim().isEmpty());
		_nome=_appoggio;
		
		do 
		{
			System.out.println("inserisci descrizione prodotto :");
			_appoggio = _input.nextLine();
		}while(_appoggio.trim().isEmpty());
		_descrizione=_appoggio;
		
		switch(_reparto)
		{
			case "AB":
				_tipoVendita = "U";
				do
				{
					System.out.println("inserisci marca del prodotto : ");
					_appoggio = _input.nextLine();
				}while(_appoggio.trim().isEmpty());
				_marca = _appoggio;
				do
				{
					System.out.println("inserisci la quantita :");
					_appoggio = _input.nextLine();

				}while(!ControlloInt(_appoggio));
				_quantita = Integer.parseInt(_appoggio);
				
				do
				{
					System.out.println("inserisci il prezzo :");
					_appoggio = _input.nextLine();
				}while(!ControlloDouble(_appoggio));
				_prezzo = Double.parseDouble(_appoggio.replace(',','.'));
				do
				{
					System.out.println("inserisci taglia del prodotto : \n(XXS,XS,S,M,L,XL,XXL)");
					_appoggio = _input.nextLine();
				}while(!ControlloTaglia(_appoggio));
				_taglia = _appoggio.toUpperCase();
				
				do
				{
					System.out.println("inserisci colore del prodotto : ");
					_appoggio = _input.nextLine();
				}while(_appoggio.trim().isEmpty());
				_colore = _appoggio;
				OggettoAbbigliamento Ab = new OggettoAbbigliamento( Enum.valueOf(Unita.class, _tipoVendita), _quantita,_prezzo, _codiceUnivoco, _nome, _marca,_descrizione, Enum.valueOf(Taglia.class, _taglia),_colore);
				_oggettoAbbigliamento.add(Ab);
				_oggettoMagazzino.add(Ab);
				break;
			case "AL":
				do
				{
					System.out.println("inserisci il codice relativo all'unita'di vendita : \ninserire : U = Unita  K = Kili  L = Litri ");
					_appoggio = _input.nextLine();
				}while(!ControlloTipoVendita(_appoggio));
				_tipoVendita=_appoggio.toUpperCase();
				if(_tipoVendita.equals("U"))
				{
					do
					{
						System.out.println("inserisci la quantita :");
						_appoggio = _input.nextLine();

					}while(!ControlloInt(_appoggio));
					_quantita = Integer.parseInt(_appoggio);
					
				}
				else
				{
					if(_tipoVendita.equals("K")||_tipoVendita.equals("L"))
					{
						do
						{
							System.out.println("inserisci la quantita :");
							_appoggio = _input.nextLine();

						}while(!ControlloDouble(_appoggio));
						_quantita = Double.parseDouble(_appoggio.replace(",", "."));
					}
				}
				do
				{
					System.out.println("inserisci il prezzo :");
					_appoggio = _input.nextLine();
				}while(!ControlloDouble(_appoggio));
				_prezzo = Double.parseDouble(_appoggio.replace(',','.'));
				do
				{
					System.out.println("inserisci la data di scadenza del prodotto (ricorda che un prodotto non puo'essere scaduto) : \nformato della data dd/mm//yyyy");
					_appoggio = _input.nextLine();
				}while(!ControlloDataScadenza(_appoggio));
				try {
					_data = _df.parse(_appoggio);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				OggettoAlimento Al = new OggettoAlimento(Enum.valueOf(Unita.class, _tipoVendita), _quantita,_prezzo, _codiceUnivoco, _nome, _descrizione,_data);
				_oggettoAlimento.add(Al);
				_oggettoMagazzino.add(Al);
				break;
			case "B":
				do
				{
					System.out.println("inserisci il codice relativo all'unita'di vendita : \ninserire : U = Unita  K = Kili  L = Litri ");
					_appoggio = _input.nextLine();
				}while(!ControlloTipoVendita(_appoggio));
				_tipoVendita=_appoggio.toUpperCase();
				if(_tipoVendita.equals("U"))
				{
					do
					{
						System.out.println("inserisci la quantita :");
						_appoggio = _input.nextLine();

					}while(!ControlloInt(_appoggio));
					_quantita = Integer.parseInt(_appoggio);
					
				}
				else
				{
					if(_tipoVendita.equals("K")||_tipoVendita.equals("L"))
					{
						do
						{
							System.out.println("inserisci la quantita :");
							_appoggio = _input.nextLine();

						}while(!ControlloDouble(_appoggio));
						_quantita = Double.parseDouble(_appoggio.replace(",", "."));
					}
				}
				do
				{
					System.out.println("inserisci il prezzo :");
					_appoggio = _input.nextLine();
				}while(!ControlloDouble(_appoggio));
				_prezzo = Double.parseDouble(_appoggio.replace(',','.'));
				do
				{
					System.out.println("inserisci la data di scadenza del prodotto (ricorda che un prodotto non puo'essere scaduto) : \nformato della data dd/mm//yyyy");
					_appoggio = _input.nextLine();
				}while(!ControlloDataScadenza(_appoggio));
				try {
					_data = _df.parse(_appoggio);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				OggettoBevanda B = new OggettoBevanda(Enum.valueOf(Unita.class, _tipoVendita),_quantita, _prezzo, _codiceUnivoco, _nome, _descrizione,_data);
				_oggettoBevanda.add(B);
				_oggettoMagazzino.add(B);
				break;
			case "LE":
				do
				{
					System.out.println("inserisci il tipo di legno del prodotto : ");
					_appoggio = _input.nextLine();
				}while(_appoggio.trim().isEmpty());
				_tipoLegno=_appoggio;
				do
				{
					System.out.println("inserisci il codice relativo all'unita'di vendita : \ninserire : U = Unita  K = Kili ");
					_appoggio = _input.nextLine();
				}while(!ControlloTipoVenditaLegno(_appoggio));
				_tipoVendita=_appoggio.toUpperCase();
				if(_tipoVendita.equals("U"))
				{
					do
					{
						System.out.println("inserisci la quantita :");
						_appoggio = _input.nextLine();

					}while(!ControlloInt(_appoggio));
					_quantita = Integer.parseInt(_appoggio);
					
				}
				else
				{
					if(_tipoVendita.equals("K"))
					{
						do
						{
							System.out.println("inserisci la quantita :");
							_appoggio = _input.nextLine();

						}while(!ControlloDouble(_appoggio));
						_quantita = Double.parseDouble(_appoggio.replace(",", "."));
					}
				}
				do
				{
					System.out.println("inserisci il prezzo :");
					_appoggio = _input.nextLine();
				}while(!ControlloDouble(_appoggio));
				_prezzo = Double.parseDouble(_appoggio.replace(',','.'));
				do
				{
					System.out.println("inserisci la dimensione del prodotto : ");
					_appoggio = _input.nextLine();
				}while(_appoggio.trim().isEmpty());
				_dimensione=_appoggio;
				OggettoLegno Le = new OggettoLegno(Enum.valueOf(Unita.class, _tipoVendita), _quantita,_prezzo, _codiceUnivoco, _nome,_tipoLegno,_dimensione, _descrizione);
				_oggettoLegno.add(Le);
				_oggettoMagazzino.add(Le);
				break;
			case "LI":
				_tipoVendita = "U";
				do
				{
					System.out.println("inserisci la quantita :");
					_appoggio = _input.nextLine();

				}while(!ControlloInt(_appoggio));
				_quantita = Integer.parseInt(_appoggio);
				do {
					System.out.println("inserisci il prezzo :");
					_appoggio = _input.nextLine();
				}while(!ControlloDouble(_appoggio));
				_prezzo = Double.parseDouble(_appoggio.replace(',','.'));
				do
				{
					System.out.println("inserisci l'autore del prodotto : ");
					_appoggio = _input.nextLine();
				}while(_appoggio.trim().isEmpty());
				_autore=_appoggio;
				do
				{
					System.out.println("inserisci la casa editrice del prodotto : ");
					_appoggio = _input.nextLine();
				}while(_appoggio.trim().isEmpty());
				_casaEditrice=_appoggio;
				do
				{
					System.out.println("inserisci la data di pubblicazione del prodotto : formato della data dd/mm//yyyy\ne ricorda che un libro non puo'essere scritto nel futuro");
					_appoggio = _input.nextLine();
				}while(!ControlloData(_appoggio)|!ControlloDataPassato(_appoggio));
				try {
					_dataPubblicazione=_df.parse(_appoggio);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				OggettoLibro Li = new OggettoLibro(Enum.valueOf(Unita.class, _tipoVendita),_quantita, _prezzo, _codiceUnivoco, _nome, _descrizione,_autore,_casaEditrice,_dataPubblicazione);
				_oggettoLibro.add(Li);
				_oggettoMagazzino.add(Li);
				break;
		}
		
		_file.modificaFile(_oggettoMagazzino,_oggettoAbbigliamento,_oggettoAlimento,_oggettoBevanda,_oggettoLegno,_oggettoLibro);
	}
	
	public int RicercaProdotto()
	{
		System.out.println("Codice univoco del prodotto da ricercare : ");
		String _codiceUnivoco = _input.nextLine();
		for(int i = 0 ; _oggettoMagazzino.size()>i;i++)
		{
			if(_codiceUnivoco.equals(_oggettoMagazzino.get(i).getCodiceUnivoco()))
			{
				System.out.println("il prodotto e'stato trovato\n");
				System.out.println("Reparto : "+_oggettoMagazzino.get(i).getReparto());
				System.out.println("Tipo vendita : "+_oggettoMagazzino.get(i).getTipoVendita());
				switch(Enum.valueOf(Unita.class, _oggettoMagazzino.get(i).getTipoVendita().toString()))
				{
					case U:
						Double _app=_oggettoMagazzino.get(i).getQuantita();
						System.out.println("Quantita : "+_app.intValue());
						break;
					default:
						System.out.println("Quantita : "+_oggettoMagazzino.get(i).getQuantita());
						break;
				}				System.out.println("Prezzo : "+_oggettoMagazzino.get(i).getPrezzo());
				System.out.println("Codice univoco : "+_oggettoMagazzino.get(i).getCodiceUnivoco());
				System.out.println("Nome : "+_oggettoMagazzino.get(i).getNome());
				System.out.println("Descrizione : "+_oggettoMagazzino.get(i).getDescrizione());
				switch (_oggettoMagazzino.get(i).getReparto()) 
				{
					case AB:
						for(int j=0;j<_oggettoAbbigliamento.size();j++)
						{
							if(_oggettoMagazzino.get(i).getCodiceUnivoco().equals(_oggettoAbbigliamento.get(j).getCodiceUnivoco()))
							{
								System.out.println("Marca : "+_oggettoAbbigliamento.get(j).getMarca());
								System.out.println("Taglia : "+_oggettoAbbigliamento.get(j).getTaglia());
								System.out.println("Colore : "+_oggettoAbbigliamento.get(j).getColore());
							}
						}
						break;
					case AL:
						for(int j=0;j<_oggettoAlimento.size();j++)
						{
							if(_oggettoMagazzino.get(i).getCodiceUnivoco().equals(_oggettoAlimento.get(j).getCodiceUnivoco()))
							{
								System.out.println("Data Scadenza : "+_df.format(_oggettoAlimento.get(j).getData()));
							}
						}			
						break;
					case B:
						for(int j=0;j<_oggettoBevanda.size();j++)
						{
							if(_oggettoMagazzino.get(i).getCodiceUnivoco().equals(_oggettoBevanda.get(j).getCodiceUnivoco()))
							{
								System.out.println("Data Scadenza : "+_df.format(_oggettoBevanda.get(j).getData()));
							}
						}	
						break;
					case LE:
						for(int j=0;j<_oggettoLegno.size();j++)
						{
							if(_oggettoMagazzino.get(i).getCodiceUnivoco().equals(_oggettoLegno.get(j).getCodiceUnivoco()))
							{
								System.out.println("Tipo Legno : "+_oggettoLegno.get(j).getTipoLegno());
								System.out.println("Dimensione : "+_oggettoLegno.get(j).getDimensione());
							}
						}
						break;
					case LI:
						for(int j=0;j<_oggettoLibro.size();j++)
						{
							if(_oggettoMagazzino.get(i).getCodiceUnivoco().equals(_oggettoLibro.get(j).getCodiceUnivoco()))
							{
								System.out.println("Autore : "+_oggettoLibro.get(j).getAutore());
								System.out.println("Casa Editrice : "+_oggettoLibro.get(j).getCasaEditrice());
								System.out.println("Data Pubblicazione : "+_df.format(_oggettoLibro.get(j).getData()));
							}
						}
						break;
	
					default:
						break;
				}
				return i;
			}
		}
		System.out.println("il prodotto con codice univoco "+_codiceUnivoco+" non e'inserito nel file Emporio");
		return -1;
	}
	
	public boolean ControlloInt(String _valore)
	{
		try
		{
			Integer.parseInt(_valore);
			if((Integer.parseInt(_valore))>0)
				return true;
			else
				return false;
		}
		catch (Exception e) 
		{
			return false;
		}
	}

	public boolean ControlloReparto( String _Reparto )
	{
		switch(_Reparto.toUpperCase())
		{
			case "AB":
			case "AL":
			case "B":
			case "LE":
			case "LI":
				return true;
			default:
				return false;
		}
	}
	
	public boolean ControlloData(String _appoggio)
	{
		DateFormat _df = new SimpleDateFormat("dd/MM/yyyy");
		try 
		{
			_df.parse(_appoggio);
			return true;
		} catch (ParseException e) 
		{
			return false;
		}
	}
	
	public boolean ControlloDataPassato(String _appoggio)
	{
		DateFormat _df = new SimpleDateFormat("dd/MM/yyyy");
		Date _now = new Date();
		try 
		{
			_df.parse(_appoggio);
			if(_now.after(_df.parse(_appoggio)))
			{
				return true;
			}
			else
				return false;
		} catch (ParseException e) 
		{
			return false;
		}
	}
	
	public boolean ControlloDataScadenza(String _data)
	{
		DateFormat _df = new SimpleDateFormat("dd/MM/yyyy");
		Date _now = new Date();
		try 
		{
			_df.parse(_data);
			if(_now.before(_df.parse(_data)))
			{
				return true;
			}
			else
				return false;
		} catch (ParseException e) 
		{
			return false;
		}	
	}
	
	public boolean ControlloTaglia( String _Reparto )
	{
		switch(_Reparto.toUpperCase())
		{
			case "XXS":
			case "XS":
			case "S":
			case "M":
			case "L":
			case "XL":
			case "XXL":
				return true;
			default:
				return false;
		}
	}
	
	public boolean ControlloTipoVendita( String _Reparto )
	{
		switch(_Reparto.toUpperCase())
		{
			case "U":
			case "K":
			case "L":
				return true;
			default:
				return false;
		}
	}
	
	public boolean ControlloTipoVenditaLegno( String _Reparto )
	{
		switch(_Reparto.toUpperCase())
		{
			case "U":
			case "K":
				return true;
			default:
				return false;
		}
	}
	
	public boolean ControlloDouble(String _double)
	{
		try
		{
			double _d = Double.parseDouble(_double.replace(',', '.'));
			if(_d>0)
				return true;
			else
				return false;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public boolean ControlloCodiceUnivoco(String _codiceUnivoco)
	{
		if(_codiceUnivoco.length()==8)
		{
			try 
			{
				Integer.parseInt(_codiceUnivoco);
				for(int i=0;i<_oggettoMagazzino.size();i++)
				{
					if(_oggettoMagazzino.get(i).getCodiceUnivoco().equals(_codiceUnivoco))
					{
						return false;	
					}
				}
				return true;
			}
			catch(Exception e)
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	public void StampaOggetti()
	{
		for(int i = 0 ; _oggettoMagazzino.size()>i;i++)
		{
			System.out.println("\n"+(i+1)+") Prodotto\n\nReparto : "+_oggettoMagazzino.get(i).getReparto());
			System.out.println("Tipo vendita : "+_oggettoMagazzino.get(i).getTipoVendita());
			switch(Enum.valueOf(Unita.class, _oggettoMagazzino.get(i).getTipoVendita().toString()))
			{
				case U:
					Double _app=_oggettoMagazzino.get(i).getQuantita();
					System.out.println("Quantita : "+_app.intValue());
					break;
				default:
					System.out.println("Quantita : "+_oggettoMagazzino.get(i).getQuantita());
					break;
			}
			System.out.println("Prezzo : "+_oggettoMagazzino.get(i).getPrezzo());
			System.out.println("Codice univoco : "+_oggettoMagazzino.get(i).getCodiceUnivoco());
			System.out.println("Nome : "+_oggettoMagazzino.get(i).getNome());
			System.out.println("Descrizione : "+_oggettoMagazzino.get(i).getDescrizione());
			switch (_oggettoMagazzino.get(i).getReparto()) 
			{
				case AB:
					for(int j=0;j<_oggettoAbbigliamento.size();j++)
					{
						if(_oggettoMagazzino.get(i).getCodiceUnivoco().equals(_oggettoAbbigliamento.get(j).getCodiceUnivoco()))
						{
							System.out.println("Marca : "+_oggettoAbbigliamento.get(j).getMarca());
							System.out.println("Taglia : "+_oggettoAbbigliamento.get(j).getTaglia());
							System.out.println("Colore : "+_oggettoAbbigliamento.get(j).getColore());
						}
					}
					break;
				case AL:
					for(int j=0;j<_oggettoAlimento.size();j++)
					{
						if(_oggettoMagazzino.get(i).getCodiceUnivoco().equals(_oggettoAlimento.get(j).getCodiceUnivoco()))
						{
							System.out.println("Data Scadenza : "+_df.format(_oggettoAlimento.get(j).getData()));
						}
					}			
					break;
				case B:
					for(int j=0;j<_oggettoBevanda.size();j++)
					{
						if(_oggettoMagazzino.get(i).getCodiceUnivoco().equals(_oggettoBevanda.get(j).getCodiceUnivoco()))
						{
							System.out.println("Data Scadenza : "+_df.format(_oggettoBevanda.get(j).getData()));
						}
					}	
					break;
				case LE:
					for(int j=0;j<_oggettoLegno.size();j++)
					{
						if(_oggettoMagazzino.get(i).getCodiceUnivoco().equals(_oggettoLegno.get(j).getCodiceUnivoco()))
						{
							System.out.println("Tipo Legno : "+_oggettoLegno.get(j).getTipoLegno());
							System.out.println("Dimensione : "+_oggettoLegno.get(j).getDimensione());
						}
					}
					break;
				case LI:
					for(int j=0;j<_oggettoLibro.size();j++)
					{
						if(_oggettoMagazzino.get(i).getCodiceUnivoco().equals(_oggettoLibro.get(j).getCodiceUnivoco()))
						{
							System.out.println("Autore : "+_oggettoLibro.get(j).getAutore());
							System.out.println("Casa Editrice : "+_oggettoLibro.get(j).getCasaEditrice());
							System.out.println("Data Pubblicazione : "+_df.format(_oggettoLibro.get(j).getData()));
						}
					}
					break;

				default:
					break;
			}
		}
	}

	public boolean ControlloInserimentoDaFile(String[] _split,int _indice)
	{
		try
		{
			if(ControlloReparto(_split[0]))
			{
				if(ControlloTipoVendita(_split[1]))
				{
					if(ControlloDouble(_split[2]))
					{
						if(ControlloDouble(_split[3]))
						{
							if(ControlloCodiceUnivoco(_split[4]))
							{
								if(_split[5].trim().length()!=0)
								{
									if(_split[6].trim().length()!=0)
									{
										switch(Enum.valueOf(Reparto.class, _split[0]))
										{
											case AB:
													if(_split[7].trim().length()!=0)
													{
														if(ControlloTaglia(_split[8]))
														{
															if(_split[9].trim().length()!=0)
															{
																return true;
															}
															else
																 throw new ErroreColore();
														}
														else 
															throw new ErroreTaglia();
													}
													else 
														throw new ErroreMarca();											
											case AL:
												if(ControlloDataScadenza(_split[7]))
												{
													return true;
												}
												else 
													throw new ErroreDataScadenza();
												
											case B:
												if(ControlloDataScadenza(_split[7]))
												{
													return true;
												}
												else 
													throw new ErroreDataScadenza();
												
											case LE:
												if(_split[7].trim().length()!=0)
												{
													if(_split[8].trim().length()!=0)
													{
														return true;
													}
													else 
														throw new ErroreDimensione();
												}
												else 
													throw new ErroreTipoLegno();
												
											case LI:
												if(_split[7].trim().length()!=0)
												{
													if(_split[8].trim().length()!=0)
													{
														if(ControlloData(_split[9]))
														{
															return true;
														}
														else 
															throw new ErroreDataPubblicazione();
													}
													else 
														throw new ErroreCasaProduttrice();
												}
												else 
													throw new ErroreAutore();
											
										}
									}
									else 
										throw new ErroreDescrizione();
								}
								else 
									throw new ErroreNome();
							}
							else 
								throw new ErroreCodiceUnivoco();
						}
						else 
							throw new ErrorePrezzo();
					}
					else 
						throw new ErroreNome();
				}
				else 
				throw new ErroreTipoVendita();
			}
			else 
			throw new ErroreReparto();
		
		}
		catch(IndexOutOfBoundsException e)
		{
			System.out.println("errore : c'e'un campo vuoto nel prodotto in posizione "+_indice+" del file\n");
			_indice++;
			return false;
		}
		catch(Exception e)
		{
			System.out.println("\n"+e.getMessage()+_indice+" del file\n");
			_indice++;
			return false;
		}
		return true;
	}
}