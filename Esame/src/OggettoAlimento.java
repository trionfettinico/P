import java.util.*;

public class OggettoAlimento extends Oggetto implements IOggettoConScadenza
{
	private Date _dataScadenza;
	OggettoAlimento( Unita _tipoVendita, double _quantita, double _prezzo, String _codiceUnivoco,String _nome, String _descrizione , Date _dataScadenza ) 
	{
		super(Enum.valueOf(Reparto.class, "AL"), _tipoVendita, _quantita, _prezzo, _codiceUnivoco, _nome, _descrizione);
		this._dataScadenza=_dataScadenza;
	}
	
	

	public Date getData() {
		return _dataScadenza;
	}

	public void setData(Date _dataScadenza) {
		this._dataScadenza=_dataScadenza;
	}
	
}
