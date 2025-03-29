package modelli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GiornoVisita {
    private String orarioDiInizioVisita;
    private double pausa;
    private double tempoVisita;

    @JsonCreator
    public GiornoVisita(
            @JsonProperty("orarioDiInizioVisita") String orarioDiInizioVisita,
            @JsonProperty("pausa") double pausa,
            @JsonProperty("tempoVisita") double tempoVisita) {
        this.orarioDiInizioVisita = orarioDiInizioVisita;
        this.pausa = pausa;
        this.tempoVisita = tempoVisita;
    }

    // Getters
    public String getOrarioDiInizioVisita() { return orarioDiInizioVisita; }
    public double getPausa() { return pausa; }
    public double getTempoVisita() { return tempoVisita; }
}
