public interface IOggetto
{
		public String getNome();
		
		public void setNome(String _nome);
	
	    Reparto getReparto();
	    
		public void setReparto(Reparto _Reparto);

	    Unita getTipoVendita();
	    
	    public void setTipoVendita(Unita _tipoVendita);

	    double getQuantita();

	    void setQuantita(double _quantita) throws Exception;

	    String getCodiceUnivoco();
	    
	    public void setCodiceUnivoco(String _codiceUnivoco);

	    String getDescrizione();
	    
	    public void setDrescrizione(String _descrizione);

	    double getPrezzo();

	    void setPrezzo(double _prezzo) throws Exception;
}
