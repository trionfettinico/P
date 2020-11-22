
public abstract class Oggetto 
{	
	private Reparto _reparto;
	private Unita _tipoVendita;
	private double _prezzo;
	private double _quantita;
	private String _codiceUnivoco;
	private String _nome;
	private String _descrizione;
	
	Oggetto(Reparto _reparto,Unita _tipoVendita,double _quantita, double _prezzo , String _codiceUnivoco , String _nome , String _descrizione)
	{
		 this._reparto = _reparto;
		 this._tipoVendita=_tipoVendita;
		 this._prezzo = _prezzo;
		 this._quantita= _quantita;
		 this._codiceUnivoco=_codiceUnivoco;
		 this._nome= _nome;
		 this._descrizione = _descrizione;		 
	}
	

	public Reparto getReparto() 
	{
        return _reparto;
    }
	
	public void setReparto(Reparto _Reparto)
	{
		this._reparto=_Reparto;
	}

	public String getNome() {
        return _nome;
    }
	
	public void setNome(String _nome) 
	{
		this._nome = _nome;
				
	}
	
    public Unita getTipoVendita() {
        return _tipoVendita;
    }
    
    public void setTipoVendita(Unita _tipoVendita)
    {
    	this._tipoVendita = _tipoVendita;
    }

    public double getQuantita() {
        return _quantita;
    }

    public void setQuantita(double _quantita) throws Exception {
        if (_quantita >= 0)
            this._quantita = _quantita;
    }

    public double getPrezzo() {
        return _prezzo;
    }

    public void setPrezzo(double _prezzo) throws Exception {
        if (_prezzo > 0)
            this._prezzo = _prezzo;
            
    }

    public String getCodiceUnivoco() {
        return _codiceUnivoco;
    }
    
    public void setCodiceUnivoco(String _codiceUnivoco) 
    {
    	this._codiceUnivoco=_codiceUnivoco;
    }

    public String getDescrizione() {
        return _descrizione;
    }
    
    public void setDrescrizione(String _descrizione)
    {
    	this._descrizione=_descrizione;
    }
}