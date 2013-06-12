package nl.ica.breas.burgernet.backend.model;

import java.util.Calendar;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import nl.ica.breas.burgernet.backend.exceptions.OnvolledigeMeldingException;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Length.List;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author Eric,bramjo, Samuel
 * @version 0.4
 * @since 26-11-2012
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Melding implements IMelding {

    /**
     * Hierin wordt de beschrijving opgeslagen.
     */
    @JsonProperty
    @NotEmpty
    @List({
        @Length(min = 5, message = "The field must be at least 5 characters"),
        @Length(max = 300, message = "The field must be less than 300 characters")
    })
    private String beschrijving;
    /**
     * Hierin wordt de categorie opgeslagen.
     */
    @JsonProperty
    @NotNull
    @Valid
    private AbstractCategorie categorie;
    /**
     * Hierin wordt de locatie opgeslagen.
     */
    @JsonProperty
    @NotNull
    private Locatie locatie;

    /**
     *De bijlage van de melding. 
     */
    @JsonProperty
    private Bijlage bijlage;
    /**
     *Na deze datum word het bericht verwijderd uit de database. 
     */
    @JsonProperty
    private Calendar verloopDatumTijd;
    /**
     * Deze boolean geeft aan of de straal aangepast is.
     */
    @JsonProperty
    private boolean aangepasteStraal = false;
    /**
     * Het id van de burger die de Melding heeft gemaakt.
     */
    @JsonProperty
    @NotEmpty
    @NotNull
    @Length(max = 25, message = "The field must be less than 25 characters")
    private String burgerId;

    /**
     * Dit is de constructor voor een nieuwe melding zonder parameter.
     */
    public Melding() {
        // deze constructor word gebruikt voor de jackson object omzetter.
    }
    /**Dit is de constructor voor een nieuwe melding.
     * 
     * @param beschrijving van de melding.
     * @param categorie	de doorgegeven categorie wordt lokaal opgeslagen in categorie.
     * @param locatie De locatie vanaf waar het bericht is gestuurt.
     * @param bijlage De meegestuurde bijlage.
     * @param verloopDatumTijd De datum waarop de melding verzonden is.
     */
    public Melding(final String beschrijving, AbstractCategorie categorie, Locatie locatie, Bijlage bijlage, Calendar verloopDatumTijd) {
        this.beschrijving = beschrijving;
        this.categorie = categorie;
        this.locatie = locatie;
        this.bijlage = bijlage;
        this.verloopDatumTijd = verloopDatumTijd;
    }
    /**Met deze methode kan de beschrijving van een bericht worden opgehaald.
     * 
     * @return de beschrijving van de melding.
     */
    public final String getBeschrijving() {
        return beschrijving;
    }
    /**Met deze methode kan de beschrijving van een bericht worden veranderd.
     * 
     * @param beschrijving De nieuwe beschrijving voor de melding.
     */
    public final void setBeschrijving(final String beschrijving) {
        this.beschrijving = beschrijving;	
    }
    /**
     * met deze methode kan de categorie worden opgehaald.
     * 
     * @return de Categorie.
     */
    public final AbstractCategorie getCategorie() {
        return categorie;
    }
    /**
     * Met deze methode kan de categorie worden veranderd.
     * @param categorie de categorie waarnaar het bericht moet veranderen.
     */
    public final void setCategorie(AbstractCategorie categorie) {
        this.categorie = categorie;
    }
    /**
     * Met deze methode kan de locatie worden opgehaald.
     * @return de locatie
     */
    public final Locatie getLocatie() {
        return locatie;
    }
    /**
     * Met deze methode kan de locatie worden veranderd.
     * @param locatie de locatie die het bericht moet krijgen.
     */
    public final void setLocatie(Locatie locatie) {
        this.locatie = locatie;
    }
    /**
     * Hiermee wordt de bijlage opgehaald.
     * @return de bijlage
     */
    public final Bijlage getBijlage() {
        return bijlage;
    }
    /**
     * Met deze methode kan de bijlage worden ingesteld.
     * @param bijlage De bijlage die moet worden ingesteld.
     */
    public final void setBijlage(Bijlage bijlage) {
        this.bijlage = bijlage;
    }
    /**
     * Hiermee wordt de datum opgehaald.
     * @return de datum
     */
    public final Calendar getVerloopDatumTijd() {
        return verloopDatumTijd;
    }
    /**
     * Met de methode kan de datum worden ingesteld.
     * @param setVerloopDatumTijd De datum die moet worden ingesteld.
     */
    public final void setVerloopDatumTijd(Calendar setVerloopDatumTijd) {
        this.verloopDatumTijd = setVerloopDatumTijd;
    }
    /**
     * @return the aangepasteStraal
     */
    public final boolean isAangepasteStraal() {
        return aangepasteStraal;
    }
    /**
     * @param aangepasteStraal the aangepasteStraal to set
     */
    public final void setAangepasteStraal(boolean aangepasteStraal) {
        this.aangepasteStraal = aangepasteStraal;
    }
    /**
     * Hiermee kan de Verloop datum van dit bericht worden gezet.
     * @throws OnvolledigeMeldingException dit wordt gegooid wanneer er een bewerking 
     * wordt gedaan op een melding die nog niet helemaal af is.
     */
    @Override
    public final void zetVerloopDatumVast() throws OnvolledigeMeldingException {
        if (categorie instanceof DummyCategorie) {
            throw new OnvolledigeMeldingException();
        } else {
            Calendar cal = Calendar.getInstance();  
            cal.add(Calendar.SECOND, categorie.getTimeToLive());
            cal.add(Calendar.HOUR, 1);
            this.setVerloopDatumTijd(cal);
        }

    }
    /**
     * @return the burgerId
     */
    public final String getBurgerId() {
        return burgerId;
    }
    /**
     * @param burgerId the burgerId to set
     */
    public final void setBurgerId(String burgerId) {
        this.burgerId = burgerId;
    }
    /**
     * Met deze methode worden de aangepaste waardes uitgelzen 
     * op melding en doorgegeven aan de categorie.
     * @param dummy de dummy categorie met aangepaste waardes.
     */
    public final void laadAangepasteParamters(DummyCategorie dummy) {

        if (dummy.getStraal() != 0) {
            setAangepasteStraal(true); 
        }
        ((AbstractCopyableCategorie) categorie).laadAangepasteParamters(dummy);  
    }
}
