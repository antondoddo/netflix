package com.antondoddo.guardabili;

import java.util.regex.Pattern;

public class AnnoPubblicazione {

	private String dataPubblicazione;
	private static final Pattern pat = Pattern.compile("\\\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|[3][01])");

	public String getDataPubblicazione() {
		return dataPubblicazione;
	}

	public AnnoPubblicazione(String dataPubblicazione) throws DataException {

		if (!checkData(dataPubblicazione)) {

			throw new DataException();
		}
		this.dataPubblicazione = dataPubblicazione;

	}

	private boolean checkData(String dataPub) {

		return pat.matcher(dataPub).matches();
	}

}
