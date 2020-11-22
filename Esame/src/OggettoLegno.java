
public class OggettoLegno extends Oggetto implements IOggettoLegno {

	private String _tipoLegno;
	private String _dimensione;
	OggettoLegno( Unita _tipoVendita,  double _quantita,double _prezzo, String _codiceUnivoco, String _nome,String _descrizione,String _tipoLegno,String _dimensione) 
	{
		super(Enum.valueOf(Reparto.class, "LE"), _tipoVendita,_quantita, _prezzo, _codiceUnivoco,_nome, _descrizione);
		this._dimensione = _dimensione;
		this._tipoLegno = _tipoLegno;
	}
	public String getDimensione() {
		return _dimensione;
	}
	
	public String getTipoLegno() {
		return _tipoLegno;
	}
	public void setDimensione(String _dimensione) {
		this._dimensione = _dimensione;
	}
	public void setTipoLegno(String _tipoLegno) {
		this._tipoLegno = _tipoLegno;
	}

}
