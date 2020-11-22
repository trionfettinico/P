
public class OggettoAbbigliamento extends Oggetto implements IOggettoAbbigliamento
{
	private Taglia _taglia;
	private String _colore;
	private String _marca;
	
	public OggettoAbbigliamento (Unita _unita, double _quantita,double _prezzo, String _codiceUnivoco,String _nome, String _marca,String _descrizione,Taglia _taglia,String _colore) 
	{
		super(((Enum.valueOf(Reparto.class, "AB"))), _unita, _quantita, _prezzo, _codiceUnivoco,_nome, _descrizione);
		this._taglia = _taglia;
		this._marca = _marca;
		this._colore = _colore;
	}

	public Taglia getTaglia() {
		return _taglia;
	}

	public String getColore() {
		return _colore;
	}

	public String getMarca() {
		return _marca;
	}

	public void setTaglia(Taglia _taglia) {
		this._taglia = _taglia;
	}

	public void setColore(String _colore) {
		this._colore = _colore;
	}

	public void setMarca(String _marca) {
		this._marca=_marca;
	}

}
