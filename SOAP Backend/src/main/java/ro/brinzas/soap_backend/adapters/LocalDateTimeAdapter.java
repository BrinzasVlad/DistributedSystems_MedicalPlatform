package ro.brinzas.soap_backend.adapters;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {
	// TODO: switch to using ZonedDateTime for timezone congruency?

	@Override
	public LocalDateTime unmarshal(String v) throws Exception {
		return LocalDateTime.parse(v);
	}

	@Override
	public String marshal(LocalDateTime v) throws Exception {
		return v.toString();
	}
}
