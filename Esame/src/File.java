import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class File
{
    DateFormat _df = new SimpleDateFormat("dd/MM/yyyy");
	String _nomeFile = "EMPORIO.txt";
	Path _path = Paths.get("EMPORIO.txt");
	
	public BufferedReader _letturaFile()
	{
		BufferedReader _lettura=null;
		FileReader _file;
		try
		{
			if(!Files.exists(_path))
			{	
				try
				{
					Files.createFile(_path);
					System.out.println("IL FILE EMPORIO NON ERA PRESENTE QUINDI NE E'STATO CREATO UNO NUOVO");
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			_file = new FileReader(_nomeFile);
			_lettura = new BufferedReader(_file);
			return _lettura;
		}
		catch(FileNotFoundException e)
		{
			System.out.println("/n IMPOSSIBILE TROVARE IL FILE EMPORIO");
			return null;
		}
	}
	

	
	public void _eliminaRigaDaFile(ArrayList<Oggetto> _oggettoMagazzino,ArrayList<OggettoAbbigliamento> _oggettoAbbigliamento,ArrayList<OggettoAlimento> _oggettoAlimento,ArrayList<OggettoBevanda> _oggettoBevanda,ArrayList<OggettoLegno> _oggettoLegno,ArrayList<OggettoLibro> _oggettoLibro,int _indiceDaEliminare)
	{
		switch (_oggettoMagazzino.get(_indiceDaEliminare).getReparto())
		{
		case AB:
			for(int i = 0;i<_oggettoAbbigliamento.size();i++)
			{
				if(_oggettoAbbigliamento.get(i).getCodiceUnivoco().equals(_oggettoMagazzino.get(_indiceDaEliminare).getCodiceUnivoco()))
				{
					_oggettoAbbigliamento.remove(i);
					_oggettoMagazzino.remove(_indiceDaEliminare);
					break;
				}
			}
			break;
		case AL:
			for(int i = 0;i<_oggettoAlimento.size();i++)
			{
				if(_oggettoAlimento.get(i).getCodiceUnivoco().equals(_oggettoMagazzino.get(_indiceDaEliminare).getCodiceUnivoco()))
				{
					_oggettoAlimento.remove(i);
					_oggettoMagazzino.remove(_indiceDaEliminare);
					break;
				}
			}		
			break;
		case B:
			for(int i = 0;i<_oggettoBevanda.size();i++)
			{
				if(_oggettoBevanda.get(i).getCodiceUnivoco().equals(_oggettoMagazzino.get(_indiceDaEliminare).getCodiceUnivoco()))
				{
					_oggettoBevanda.remove(i);
					_oggettoMagazzino.remove(_indiceDaEliminare);
					break;
				}
			}
			break;
		case LI:
			for(int i = 0;i<_oggettoLibro.size();i++)
			{
				if(_oggettoLibro.get(i).getCodiceUnivoco().equals(_oggettoMagazzino.get(_indiceDaEliminare).getCodiceUnivoco()))
				{
					_oggettoLibro.remove(i);
					_oggettoMagazzino.remove(_indiceDaEliminare);
					break;
				}
			}
			break;
		case LE:
			for(int i = 0;i<_oggettoLegno.size();i++)
			{
				if(_oggettoLegno.get(i).getCodiceUnivoco().equals(_oggettoMagazzino.get(_indiceDaEliminare).getCodiceUnivoco()))
				{
					_oggettoLegno.remove(i);
					_oggettoMagazzino.remove(_indiceDaEliminare);
					break;
				}
			}
			break;
		}
		modificaFile(_oggettoMagazzino,_oggettoAbbigliamento,_oggettoAlimento,_oggettoBevanda,_oggettoLegno,_oggettoLibro);
	}
	
	public void modificaFile(ArrayList<Oggetto> _oggettoMagazzino,ArrayList<OggettoAbbigliamento> _oggettoAbbigliamento,ArrayList<OggettoAlimento> _oggettoAlimento,ArrayList<OggettoBevanda> _oggettoBevanda,ArrayList<OggettoLegno> _oggettoLegno,ArrayList<OggettoLibro> _oggettoLibro)
	{
		FileWriter _scrittura = null;
		try 
		{
			_scrittura = new FileWriter(_nomeFile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try 
		{
			int _indiceAb = 0;
			int _indiceAl = 0;
			int _indiceB = 0;
			int _indiceLe = 0;
			int _indiceLi = 0;
			for(int i = 0; i<_oggettoMagazzino.size();i++)
			{
				if(Enum.valueOf(Reparto.class,"AB")==_oggettoMagazzino.get(i).getReparto())
				{
				_scrittura.write(_oggettoAbbigliamento.get(_indiceAb).getReparto()+";"+_oggettoAbbigliamento.get(_indiceAb).getTipoVendita()+";"+_oggettoAbbigliamento.get(_indiceAb).getQuantita()+";"+_oggettoAbbigliamento.get(_indiceAb).getPrezzo()+";"+_oggettoAbbigliamento.get(_indiceAb).getCodiceUnivoco()+";"+_oggettoAbbigliamento.get(_indiceAb).getNome()+";"+_oggettoAbbigliamento.get(_indiceAb).getMarca()+";"+_oggettoAbbigliamento.get(_indiceAb).getDescrizione()+";"+_oggettoAbbigliamento.get(_indiceAb).getTaglia()+";"+_oggettoAbbigliamento.get(_indiceAb).getColore()+"\n");
				_indiceAb++;
				}
				else 
				{	
					if(Enum.valueOf(Reparto.class,"AL")==_oggettoMagazzino.get(i).getReparto())
					{
						_scrittura.write(_oggettoAlimento.get(_indiceAl).getReparto()+";"+_oggettoAlimento.get(_indiceAl).getTipoVendita()+";"+_oggettoAlimento.get(_indiceAl).getQuantita()+";"+_oggettoAlimento.get(_indiceAl).getPrezzo()+";"+_oggettoAlimento.get(_indiceAl).getCodiceUnivoco()+";"+_oggettoAlimento.get(_indiceAl).getNome()+";"+_oggettoAlimento.get(_indiceAl).getDescrizione()+";"+ _df.format(_oggettoAlimento.get(_indiceAl).getData()).toString()+"\n");
						_indiceAl++;
					}
					else
					{
						if(Enum.valueOf(Reparto.class,"B")==_oggettoMagazzino.get(i).getReparto())
						{
							_scrittura.write(_oggettoBevanda.get(_indiceB).getReparto()+";"+_oggettoBevanda.get(_indiceB).getTipoVendita()+";"+_oggettoBevanda.get(_indiceB).getQuantita()+";"+_oggettoBevanda.get(_indiceB).getPrezzo()+";"+_oggettoBevanda.get(_indiceB).getCodiceUnivoco()+";"+_oggettoBevanda.get(_indiceB).getNome()+";"+_oggettoBevanda.get(_indiceB).getDescrizione()+";"+_df.format(_oggettoBevanda.get(_indiceB).getData())+"\n");
							_indiceB++;
						}
						else
						{
							if(Enum.valueOf(Reparto.class,"LE")==_oggettoMagazzino.get(i).getReparto())
							{
								_scrittura.write(_oggettoLegno.get(_indiceLe).getReparto()+";"+_oggettoLegno.get(_indiceLe).getTipoVendita()+";"+_oggettoLegno.get(_indiceLe).getQuantita()+";"+_oggettoLegno.get(_indiceLe).getPrezzo()+";"+_oggettoLegno.get(_indiceLe).getCodiceUnivoco()+";"+_oggettoLegno.get(_indiceLe).getNome()+";"+_oggettoLegno.get(_indiceLe).getTipoLegno()+";"+_oggettoLegno.get(_indiceLe).getDescrizione()+";"+_oggettoLegno.get(_indiceLe).getDimensione()+"\n");
								_indiceLe++;
							}
							else
							{
								if(Enum.valueOf(Reparto.class,"LI")==_oggettoMagazzino.get(i).getReparto())
								{
									_scrittura.write(_oggettoLibro.get(_indiceLi).getReparto()+";"+_oggettoLibro.get(_indiceLi).getTipoVendita()+";"+_oggettoLibro.get(_indiceLi).getQuantita()+";"+_oggettoLibro.get(_indiceLi).getPrezzo()+";"+_oggettoLibro.get(_indiceLi).getCodiceUnivoco()+";"+_oggettoLibro.get(_indiceLi).getNome()+";"+_oggettoLibro.get(_indiceLi).getDescrizione()+";"+_oggettoLibro.get(_indiceLi).getAutore()+";"+_oggettoLibro.get(_indiceLi).getCasaEditrice()+";"+_df.format(_oggettoLibro.get(_indiceLi).getData())+"\n");
									_indiceLi++;
								}
							}
						}
					}
				}
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		try {
			_scrittura.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
