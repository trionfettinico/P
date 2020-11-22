import java.util.Date;

public class OggettoLibro extends Oggetto implements IOggettoLibro
{
	private String _autore;
	private String _casaEditrice;
	private Date _dataPubblicazione;
	OggettoLibro( Unita _tipoVendita,  double _quantita,double _prezzo, String _codiceUnivoco,String _nome, String _descrizione,String _autore,String _casaEditrice,Date _dataPubblicazione) 
	{
		super(Enum.valueOf(Reparto.class, "LI"), _tipoVendita,_quantita, _prezzo, _codiceUnivoco, _nome, _descrizione);
		this._autore = _autore;
		this._casaEditrice = _casaEditrice;
		this._dataPubblicazione = _dataPubblicazione;
	}
	public String getAutore() {
		return _autore;
	}
	public String getCasaEditrice() {
		return _casaEditrice;
	}
	public Date getData() {
		return _dataPubblicazione;
	}
	public void setAutore(String _autore) {
		this._autore =_autore;
	}
	public void setCasaEditrice(String _casaEditrice) {
		this._casaEditrice=_casaEditrice;
	}
	public void setData(Date _data) {
		this._dataPubblicazione = _data;
	}

}
